package controllers;

import models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import repositories.VehicleRepository;
import java.util.Optional;

@Controller
public class VehicleListController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @RequestMapping(value = "/vehicleList.html", method = RequestMethod.GET)
    public String showVehicleList(Model model) {
        model.addAttribute("vehicleList", vehicleRepository.findAll());
        return "vehicleList";
    }

    @Secured("IS_AUTHENTICATED_FULLY")
    @GetMapping(path = "/vehicleList.html", params = "id")
    public String vehicleDetails(Model model, @RequestParam(name = "id") Optional<Vehicle> vehicleOptional) {
        if(vehicleOptional.isPresent()){
            model.addAttribute("mojSamochod", vehicleOptional.get());
            return "vehicleDetails";
        }else {
            return null; // obsluzyc wyjatek
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/vehicleList.html", params = {"rId"})
    public String removeVehicle(long rId) {
        vehicleRepository.deleteById(rId);
        return "redirect:vehicleList.html";
    }
}
