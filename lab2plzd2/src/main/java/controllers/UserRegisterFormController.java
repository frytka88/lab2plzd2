package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import services.UserService;

import javax.validation.Valid;

@Controller
public class UserRegisterFormController {

//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/registerForm.html")
//    public String register(Model model) {
//        model.addAttribute("userCommand", new User());
//        return "registerForm";
//    }
//
//    @PostMapping("/registerForm.html")
//    public String registration(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "registerForm";
//        }
//        userService.save(userForm);
//        return "registerSuccess";
//    }
}
