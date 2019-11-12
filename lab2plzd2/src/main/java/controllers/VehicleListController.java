package controllers;

import models.Vehicle;
import models.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repositories.VehicleRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class VehicleListController {

    @Autowired
    private VehicleRepository vehicleRepository;

    protected static final List<Vehicle> lista = new ArrayList<>();
    protected static final List<VehicleType> vehicleTypes = new ArrayList<>();

    static {

        vehicleTypes.add(new VehicleType(1, "SEDAN"));
        vehicleTypes.add(new VehicleType(2, "COUPE"));
        vehicleTypes.add(new VehicleType(3, "CABRIOLET"));
        vehicleTypes.add(new VehicleType(4, "SUV"));

        int id = 1;
        Vehicle v1 = new Vehicle();
        v1.setId(id++);
        v1.setName("Alfa Romeo");
        v1.setModel("Giulia");
        v1.setProductionDate(new Date(118, 6, 1));
        v1.setPrice((float)12900);
        v1.setVehicleType(vehicleTypes.get(0));
        lista.add(v1);

        Vehicle v2 = new Vehicle();
        v2.setId(id++);
        v2.setName("Jaguar");
        v2.setModel("X - type");
        v2.setProductionDate(new Date(106, 2, 21));
        v2.setPrice((float) 10500);
        v2.setVehicleType(vehicleTypes.get(3));
        lista.add(v2);

        Vehicle v3 = new Vehicle();
        v3.setId(id++);
        v3.setName("Alfa Romeo");
        v3.setModel("Pandion");
        v3.setProductionDate(new Date(100, 8, 16));
        v3.setPrice((float)400400);
        v3.setVehicleType(vehicleTypes.get(1));
        lista.add(v3);
    }

    @RequestMapping(value = "/vehicleList.html", method = RequestMethod.GET)
    public String showVehicleList(Model model) {
        model.addAttribute("lista", lista);
        return "vehicleList";
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    @RequestMapping(path = "/vehicleList.html", params = "id", method = RequestMethod.GET)
    public String vehicleDetails(Model model, int id) {
        Vehicle vehicle = lista.stream().filter(findId -> findId.getId() == id).findFirst().get();
        model.addAttribute("mojSamochod", vehicle);
        return "vehicleDetails";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/vehicleList.html", params = {"rId"})
    public String removeVehicle(long rId) {

        vehicleRepository.deleteById(rId);
//
//        for (int i = 0, n = VehicleListController.lista.size(); i < n; i++) {
//            if (VehicleListController.lista.get(i).getId() == rId) {
//                VehicleListController.lista.remove(i);
//                break;
//            }
//        }
        return "redirect:vehicleList.html";
    }

}
