package controllers;

import models.Vehicle;
import models.VehicleType;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.List;

@Controller
@SessionAttributes(names = {"vehicleTypes", "vehicle"}) //zad2
public class VehicleFormController {



    @RequestMapping(value = "/add.html", method = RequestMethod.GET) //Pobranie strony z formularzem
    public String showForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") int id) {
        Vehicle v;
        if (id > 0) {
            v = VehicleListController.lista.stream().filter(findCar -> findCar.getId() == id).findFirst().get();
            //obsłużyć not found exception
        } else {
            v = new Vehicle();
        }
        model.addAttribute("vehicle", v);
        return "vehicleForm";
    }

    @RequestMapping(value = "/add.html", method = RequestMethod.POST) //Wysylanie formularza
    public String processForm(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "vehicleForm";
        }

        if (vehicle.getVehicleType().getId() > 0) {
            VehicleType vehicleType = VehicleListController.vehicleTypes.stream().filter(xType -> xType.getId() == vehicle.getVehicleType().getId()).findFirst().get();
            vehicle.setVehicleType(vehicleType);
        }

        if (vehicle.getId() > 0) {
            for (int i = 0, n = VehicleListController.lista.size(); i < n; i++) {
                if (VehicleListController.lista.get(i).getId() == vehicle.getId()) {
                    VehicleListController.lista.set(i, vehicle);
                    break;
                }
            }
        } else {
            VehicleListController.lista.add(vehicle);
        }
        return "successVehicleForm";
// redirect jest do przekierowania / cofniecia |
// taka odpowiedź jest zwracana do przeglądarki a ona automatycznie generuje kolejne żądanie (przekierowanie) pod nowy URL.
    }

    @ModelAttribute("vehicleTypes")
    public List<VehicleType> loadType() {
        List<VehicleType> types = VehicleListController.vehicleTypes;
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
