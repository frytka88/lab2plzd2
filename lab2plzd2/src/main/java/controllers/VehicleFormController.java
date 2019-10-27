package controllers;

import models.Vehicle;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.DecimalFormat;

@Controller
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
    public String processForm(Model model, Vehicle vehicle) {

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
