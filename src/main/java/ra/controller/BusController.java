package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.Bus;
import ra.service.BusService;

import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    // Danh sách xe
    @GetMapping
    public String index(Model model) {
        List<Bus> list = busService.getAllBus();
        model.addAttribute("buses", list);
        return "bus-list";
    }


    // Hiển thị form thêm mới
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "bus-create";
    }

    // Xử lý thêm mới
    @PostMapping("/create")
    public String doCreate(@ModelAttribute Bus bus) {
        busService.addBus(bus);
        return "redirect:/bus";
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Bus bus = busService.findById(id);
        model.addAttribute("bus", bus);
        return "bus/edit";
    }

    // Xử lý cập nhật
    @PostMapping("/edit")
    public String doEdit(@ModelAttribute Bus bus) {
        busService.updateBus(bus);
        return "redirect:/bus";
    }

    // Xử lý xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        busService.deleteBus(id);
        return "redirect:/bus";
    }
}
