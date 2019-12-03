package controllers;

import lombok.extern.log4j.Log4j2;
import models.Accessory;
import models.Vehicle;
import models.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import repositories.VehicleRepository;
import repositories.VehicleTypeRepository;
import services.VehicleService;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names = {"vehicleTypes", "vehicle", "accessoryList"})
@Log4j2
public class VehicleFormController {

    private VehicleService vehicleService;

    public VehicleFormController(VehicleService vehicleService)
    {
        this.vehicleService = vehicleService;
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
        vehicleService.saveVehicle(vehicle);
        return "successVehicleForm";
    }

    @ModelAttribute("vehicleTypes")
    public List<VehicleType> loadType() {
        List<VehicleType> types = vehicleService.getAllTypes();
        return types;
    }

    @ModelAttribute("accessoryList")
    public List<Accessory> loadAccessories(){
        List<Accessory> accessories = vehicleService.getAllAccessories();
        log.info("Ładowanie listy "+ accessories.size()+" akcesoriów ");
        return accessories;
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
