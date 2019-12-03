package controllers;

import controllers.commands.VehicleFilter;
import models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repositories.VehicleRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes("searchCommand")
public class VehicleListController {

    @Autowired
    private VehicleRepository vehicleRepository;

//    @RequestMapping(value = "/vehicleList.html", method = RequestMethod.GET)
//    public String showVehicleList(Model model) {
//        model.addAttribute("vehicleList", vehicleRepository.findAll());
//        return "vehicleList";
//    }

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

    @ModelAttribute("searchCommand")
    public VehicleFilter getSimpleSearch(){
        return new VehicleFilter();
    }

    @GetMapping(value="/vehicleList.html", params = {"all"})
    public String resetVehicleList(@ModelAttribute("searchCommand") VehicleFilter search){
        search.clear();
        return "redirect:vehicleList.html";
    }

    @RequestMapping(value = "/vehicleList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showVehicleList(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") VehicleFilter search, BindingResult bindingResult) {
        Page page;
        if (bindingResult.hasErrors()) {
            return "vehicleList";
        }
        if (search.isEmpty()) {
            page = vehicleRepository.findAll(pageable);
        } else {
            page = vehicleRepository.findAllVehiclesUsingFilter(search.getPhraseLIKE(), search.getMinPrice(), search.getMaxPrice(), pageable);
        }
        model.addAttribute("vehicleListPageable", page);
        return "vehicleList";
    }
}
