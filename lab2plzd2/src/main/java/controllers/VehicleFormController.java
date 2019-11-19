package controllers;

import models.Vehicle;
import models.VehicleType;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import repositories.VehicleRepository;
import repositories.VehicleTypeRepository;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names = {"vehicleTypes", "vehicle"})
public class VehicleFormController {

    private VehicleRepository vehicleRepository;
    private VehicleTypeRepository vehicleTypeRepository;

    public VehicleFormController(VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String showForm(Model model, @RequestParam(name = "id") Optional<Vehicle> vehicleOptional) {
        model.addAttribute("vehicle", vehicleOptional.isPresent()?vehicleOptional: new Vehicle());
        return "vehicleForm";
    }

    @RequestMapping(value = "/add.html", method = RequestMethod.POST) //Wysylanie formularza
    public String processForm(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult) {

//        if(bindingResult.hasErrors()){
//            return "vehicleForm";
//        }
        vehicleRepository.save(vehicle);
        return "successVehicleForm";
    }

    @ModelAttribute("vehicleTypes")
    public List<VehicleType> loadType() {
        List<VehicleType> types = vehicleTypeRepository.findAll();
        return types;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new org.springframework.format.datetime.DateFormatter("yyyy-MM-dd"));

        DecimalFormat decimalFormat = new DecimalFormat("0");
        decimalFormat.setMaximumFractionDigits(4);
        decimalFormat.setMinimumFractionDigits(1);
        decimalFormat.setGroupingUsed(false);
        binder.registerCustomEditor(Float.class, "price", new CustomNumberEditor(Float.class, decimalFormat, false));
    }
}
