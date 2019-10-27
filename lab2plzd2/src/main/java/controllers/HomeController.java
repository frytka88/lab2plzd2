package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    private static final Logger logger = LogManager.getLogManager().getLogger(HomeController.class);
    @GetMapping(path = "/")
    public String home(Model model) {
       // logger.info("Wywołanie metody Home");
        model.addAttribute("ja", " hello!!!");
        return "home";
    }
}



