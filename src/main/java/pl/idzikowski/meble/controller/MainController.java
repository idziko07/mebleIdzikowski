package pl.idzikowski.meble.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.idzikowski.meble.model.Kitchen;
import pl.idzikowski.meble.model.Room;
import pl.idzikowski.meble.model.Wardrobes;
import pl.idzikowski.meble.repository.KitchenRepository;
import pl.idzikowski.meble.repository.RoomRepository;
import pl.idzikowski.meble.repository.WadrobesRepository;

@Controller
public class MainController {
	
	private String link = System.getProperty("user.dir") + "\\target\\classes\\img";
	private KitchenRepository kitchenRepository;
	private RoomRepository roomRepository;
	private WadrobesRepository wadrobesRepository;
	
	
	
	public MainController(KitchenRepository kitchenRepository, RoomRepository roomRepository,
			WadrobesRepository wadrobesRepository) {
		this.kitchenRepository = kitchenRepository;
		this.roomRepository = roomRepository;
		this.wadrobesRepository = wadrobesRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("link", link);
		return "mainPage/index";
	}
	
	@GetMapping("/kitchen")
	public String kitchen(Model model) {
		
		List<Kitchen> kitchens = kitchenRepository.findAll();
		model.addAttribute("kitchens", kitchens);
		model.addAttribute("link", link);
		return "mainPage/kitchen";
	}
	
	@GetMapping("/wardrobes")
	public String wardrobes(Model model) {
		
		List<Wardrobes> wardrobes = wadrobesRepository.findAll();
		model.addAttribute("wardrobes", wardrobes);
		model.addAttribute("link", link);
		return "mainPage/wardrobes";
	}
	
	@GetMapping("/rooms")
	public String rooms(Model model) {
		
		List<Room> rooms = roomRepository.findAll();
		model.addAttribute("rooms",rooms);
		model.addAttribute("link", link);
		return "mainPage/rooms";
	}
}
