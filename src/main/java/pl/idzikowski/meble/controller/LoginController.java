package pl.idzikowski.meble.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {
	
	@RequestMapping("/mylogin")
	public String logIn() {
		return "mylogin";
	}
	
	@RequestMapping("/mylogin-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "mylogin";
	}
}
