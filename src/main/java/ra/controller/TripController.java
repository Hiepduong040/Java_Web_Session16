package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.Trip;
import ra.service.TripService;

import java.util.List;

@Controller
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping("/home")
    public String showTrips(Model model) {
        model.addAttribute("trips", tripService.findAll());
        return "home";
    }
    @GetMapping("/home/search")
    public String searchTrips(@RequestParam String departure,
                              @RequestParam String destination,
                              Model model) {
        List<Trip> trips = tripService.searchTrips(departure, destination);
        model.addAttribute("trips", trips);
        return "home";
    }

}
