package pl.idzikowski.meble.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.idzikowski.meble.model.Kitchen;
import pl.idzikowski.meble.model.Room;
import pl.idzikowski.meble.model.Wardrobes;
import pl.idzikowski.meble.repository.KitchenRepository;
import pl.idzikowski.meble.repository.RoomRepository;
import pl.idzikowski.meble.repository.WadrobesRepository;

@Controller
@RequestMapping("/upload")
public class UploadFilesController {
 
	private List<String> namesFiles = new ArrayList<>();
	private String link = System.getProperty("user.dir") + "\\images";
	private KitchenRepository kitchenRepository;
	private WadrobesRepository wardrobesRepository;
	private RoomRepository roomRepository;
	
	@Autowired
	public UploadFilesController(KitchenRepository kitchenRepository, WadrobesRepository wardrobesRepository,
			RoomRepository roomRepository) {
		this.kitchenRepository = kitchenRepository;
		this.wardrobesRepository = wardrobesRepository;
		this.roomRepository = roomRepository;
	}


	@PostMapping("/uploadKitchen")
	public String uploadImageKitchen(Model model,@RequestParam("files") MultipartFile[] files) {
			namesFiles.clear();
			System.out.println(link);
			String folderName = "\\kitchen\\";
			String uploadLink = link + folderName;
			uploadFile(files,uploadLink,folderName);
			for(MultipartFile file: files) {
			  Kitchen fileImage = new Kitchen(file.getOriginalFilename());
			  kitchenRepository.save(fileImage);
			}
	        
		return "redirect:/myPage/kitchen";
		
	}
	

	@PostMapping("/uploadWardrobe")
	public String uploadImageWardrobe(Model model, @RequestParam("files") MultipartFile[] files) {
		namesFiles.clear();
		String folderName = "\\wardrobes\\";
		String uploadLink = link + folderName;
		uploadFile(files,uploadLink,folderName);
		for(MultipartFile file: files) {
			Wardrobes fileImage = new Wardrobes(file.getOriginalFilename());
			wardrobesRepository.save(fileImage);
		}
		return "redirect:/myPage/wardrobe";
		
	}
	
	@PostMapping("/uploadRoom")
	public String uploadImageRoom(Model model, @RequestParam("files") MultipartFile[] files) {
		namesFiles.clear();
		String folderName = "\\rooms\\";
		String uploadLink = link + folderName;
		uploadFile(files,uploadLink,folderName);
		for(MultipartFile file: files) {
			Room fileImage = new Room(file.getOriginalFilename());
			roomRepository.save(fileImage);
		}
		return "redirect:/myPage/room";
		
	}
	
	@PostMapping("/updateCarouselFist")
	public String updateImageCarouselFirst(Model model, @RequestParam("carousel1") MultipartFile file) {
		try {
			Files.deleteIfExists(Paths.get(link + "\\carousel\\carousel1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String folderName = "\\carousel\\";
		String uploadLink = link + folderName;
		String fileName = file.getName() + ".jpg";
		updateFile(file,uploadLink, fileName);
		return "redirect:/myPage/carousel";
		
	}
	
	@PostMapping("/updateCarouselSeconed")
	public String updateImageCarouselSecond(Model model, @RequestParam("carousel2") MultipartFile file) {
		try {
			Files.deleteIfExists(Paths.get(link + "\\carousel\\carousel2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String folderName = "\\carousel\\";
		String uploadLink = link + folderName;
		String fileName = file.getName() + ".jpg";
		updateFile(file,uploadLink, fileName);
		return "redirect:/myPage/carousel";
		
	}
	
	

	
	private void uploadFile(MultipartFile[] files, String uploadLink, String folderName) {
		StringBuilder fileName = new StringBuilder();
		for(MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadLink, file.getOriginalFilename());
			fileName.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath,file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			namesFiles.add(folderName + file.getOriginalFilename());
		}
	}
	
	private void updateFile(MultipartFile file, String uploadLink, String name) {
		StringBuilder fileName = new StringBuilder();
		Path fileNameAndPath = Paths.get(uploadLink, name);
		fileName.append(name);
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	public List<String> getNamesFiles() {
		return namesFiles;
	}	
	
}
