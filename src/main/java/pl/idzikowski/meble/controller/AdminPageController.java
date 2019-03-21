package pl.idzikowski.meble.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.idzikowski.meble.model.Kitchen;
import pl.idzikowski.meble.model.Room;
import pl.idzikowski.meble.model.Wardrobes;
import pl.idzikowski.meble.repository.KitchenRepository;
import pl.idzikowski.meble.repository.RoomRepository;
import pl.idzikowski.meble.repository.WadrobesRepository;

@Controller
@RequestMapping("/myPage")
public class AdminPageController {
	
	private String link = System.getProperty("user.dir") + "\\img";
	private UploadFilesController uploadFiles;
	private KitchenRepository kitchenRepository;
	private RoomRepository roomRepository;
	private WadrobesRepository wadrobesRepository;
	
	@Autowired		
	public AdminPageController(UploadFilesController uploadFiles, KitchenRepository kitchenRepository,
			RoomRepository roomRepository, WadrobesRepository wadrobesRepository) {
		
		this.uploadFiles = uploadFiles;
		this.kitchenRepository = kitchenRepository;
		this.roomRepository = roomRepository;
		this.wadrobesRepository = wadrobesRepository;
	}



	@GetMapping("")
	public String startPage(Model model) {
		model.addAttribute("namesFiles",uploadFiles.getNamesFiles());
		return "myPage/myPage";
	}
	
	@GetMapping("/kitchen")
	public String addPictureKitchen(Model model) {
		
		List<Kitchen> kitchens = kitchenRepository.findAll();
		model.addAttribute("kitchens", kitchens);
		model.addAttribute("namesFiles",uploadFiles.getNamesFiles());
		return "myPage/addPictureKitchen";
	}
	
	@GetMapping("/wardrobe")
	public String addPictureWardrobe(Model model) {
		
		List<Wardrobes> wardrobes = wadrobesRepository.findAll();
		model.addAttribute("wardrobes", wardrobes);
		model.addAttribute("namesFiles",uploadFiles.getNamesFiles());
		return "myPage/addPictureWardrobe";
	}
	
	@GetMapping("/room")
	public String addPictureRoom(Model model) {
		
		List<Room> rooms = roomRepository.findAll();
		model.addAttribute("rooms",rooms);
		model.addAttribute("namesFiles",uploadFiles.getNamesFiles());
		return "myPage/addPictureRoom";
	}
	
	@GetMapping("/carousel")
	public String updatePictureCarousel() {
		return "myPage/addCarousel";
	}
	
	
	@GetMapping("/kitchen/delete")
	public String deleteKitchen(Model model) {
		
		List<Kitchen> imageKitchen = kitchenRepository.findAll();
		model.addAttribute("kitchens", imageKitchen);
		return "myPage/deleteKitchen";
	}
	
	@GetMapping("/wardrobe/delete")
	public String deleteWardrobe(Model model) {

		List<Wardrobes> imageWardrobes = wadrobesRepository.findAll();
		model.addAttribute("wardrobes", imageWardrobes);
		return "myPage/deleteWardrobe";
	}
	
	@GetMapping("/room/delete")
	public String deleteRoom(Model model) {
		
		List<Room> imageRooms = roomRepository.findAll();
		model.addAttribute("rooms", imageRooms);
		return "myPage/deleteRoom";
	}
	
	@GetMapping("/kitchen/deleteImg")
	public String deleteKitchenImg(@RequestParam Long id) {
		
		Optional<Kitchen> findById = kitchenRepository.findById(id);
		
		if(findById.isPresent()) {
			Kitchen kitchen = findById.get();
			String fileDirectory = link + "\\kitchen\\";
			String fileName = kitchen.getName(); 
			fileDelete(fileDirectory, fileName);
			kitchenRepository.deleteById(id);
		}		 
		
		return "redirect:/myPage/kitchen/delete";
	}
	
	@GetMapping("/wardrobe/deleteImg")
	public String deleteWardrobeImg(@RequestParam Long id) {
		
		Optional<Wardrobes> findById = wadrobesRepository.findById(id);
		
		if(findById.isPresent()) {
			Wardrobes wardrobe = findById.get();
			String fileDirectory = link + "\\wardrobes\\";
			String fileName = wardrobe.getName(); 
			fileDelete(fileDirectory, fileName);
			
		}else {
			return " Wystapił bład";
		}
		 
		wadrobesRepository.deleteById(id);
		return "redirect:/myPage/wardrobe/delete";
	}
	
	@GetMapping("/room/deleteImg")
	public String deleteRoomImg(@RequestParam Long id) {
		
		Optional<Room> findById = roomRepository.findById(id);
		
		if(findById.isPresent()) {
			Room room = findById.get();
			String fileDirectory = link + "\\rooms\\";
			String fileName = room.getName(); 
			fileDelete(fileDirectory, fileName);
			
		}else {
			return " Wystapił bład";
		}
		 
		roomRepository.deleteById(id);
		return "redirect:/myPage/room/delete";
	}



	private void fileDelete(String fileDirectory, String fileName) {
		Path filePath = Paths.get(fileDirectory, fileName);
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

