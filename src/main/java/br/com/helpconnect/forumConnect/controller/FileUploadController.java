package br.com.helpconnect.forumConnect.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.helpconnect.forumConnect.model.ConfigProperties;
import br.com.helpconnect.forumConnect.storage.StorageFileNotFoundException;
import br.com.helpconnect.forumConnect.storage.StorageService;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@GetMapping("/print-all-headers")
    public void getAllheaders(@RequestHeader Map<String,String> headers){
        headers.forEach((key,value) ->{
            System.out.println("Header Name: "+key+" Header Value: "+value);
        });
    }

	@PostMapping("/{username}/nomeArquivo/{nomeArquivo}")
	public boolean handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, @PathVariable("username") String username, @PathVariable("nomeArquivo") String nomeArquivo) {
		try {
			
			ConfigProperties configProperties = new ConfigProperties();
			
			//String caminho = "/home/kevin/aplicacoes/arquivosUpload/"; // DEFINE O CAMINHO PADRAO DO SERVIDOR DE IMAGENS
			String caminho = configProperties.getCaminho(); // DEFINE O CAMINHO PADRAO DO SERVIDOR DE IMAGENS
			String caminhoParSerDeletado = new File("").getAbsolutePath() +"\\src\\main\\resources\\uploads";
			
			storageService.store(file);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
			
			String path = caminho; // CONSTROE O CAMINHO DO ARQUIVO COM A PASTA DO CLIENTE
			
			path = path + username;
			
			File criarPasta = new File(path);
			
			boolean bool = criarPasta.mkdir(); // CRIA A PASTA NO ENDERECO INFORMADO (RETORNOA: TRUE OU FALSE)
			
			if(bool){  
			   System.out.println("Pasta criada com sucesso!");
			   
			}else{  
			   System.out.println("Essa parta ja foi criada anteriormente.");
			   
			}
			
			String extensao = "";
			
			if(file.getContentType().equals("image/jpeg")) {
				extensao = ".jpg";
				
			}else if(file.getContentType().equals("image/png")) {
				extensao = ".png";
				
			}else if(file.getContentType().equals("image/svg+xml")) {
				extensao = ".svg";
				
			}else if(file.getContentType().equals("video/mp4")) {
				extensao = ".mp4";
				
			}else {
				return false;
			}
			
			file.transferTo(new File(caminho + username +"/"+ nomeArquivo + extensao)); // SALVA O ARQUIVO NO DESTINO ESPECIFICADO
			
			FileUtils.cleanDirectory(new File(caminhoParSerDeletado)); // A PASTA DEVE SER ZERADA PARA QUE ARQUIVOS COM O MESMO NOME, POSSAM SER CARREGADOS NOVAMENTE
			
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{username}/nomeArquivo/{nomeArquivo}")
	public boolean deletaImagemSubstituida(@PathVariable("username") String username, @PathVariable("nomeArquivo") String nomeArquivo) {
		
		try {
			ConfigProperties configProperties = new ConfigProperties();
			
			// String caminho = "/home/kevin/aplicacoes/arquivosUpload/";
			String caminho = configProperties.getCaminho(); // DEFINE O CAMINHO PADRAO DO SERVIDOR DE IMAGENS
			
			File f= new File(caminho + username +"/"+ nomeArquivo);
			
			if(f.delete()) {  
				System.out.println(f.getName() + " deleted");
				
				return true;
			}else{  
				System.out.println("failed");  
				
				return false;
			}  
		
		}catch(Exception e){  
			e.printStackTrace();  
			
			return false;
		}  
		
	} 

}