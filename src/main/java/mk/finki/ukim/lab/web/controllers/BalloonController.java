package mk.finki.ukim.lab.web.controllers;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.model.Order;
import mk.finki.ukim.lab.service.BalloonService;
import mk.finki.ukim.lab.service.ManufacturerService;
import mk.finki.ukim.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("balloons", balloonService.listAll());
        return "listBalloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.delete(id);
        return "redirect:/balloons";
    }

    @PostMapping("/save")
    public String saveBalloonForNextPage(@RequestParam Long id, HttpServletRequest req) {
        Balloon b = balloonService.findById(id).get();
        req.getSession().setAttribute("balloon", b);
        return "selectBalloonSize";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        if (balloonService.findById(id).isPresent()) {
            Balloon b = this.balloonService.findById(id).get();
            model.addAttribute("balloon", b);
            model.addAttribute("manufacturers", manufacturerService.findAll());
            return "add-balloon";
        } else {
            return "redirect:/balloons?error=BalloonWithThisIdNotFound";
        }
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name, @RequestParam String desc, @RequestParam Long manufacturer) {
        this.balloonService.save(name, desc, manufacturer);
        return "redirect:/balloons";
    }

    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model){
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "add-balloon";
    }
    @GetMapping("/orders")
    public String showOrders(HttpServletRequest request, Model model){
        String name=request.getParameter("clientName");
        String address=request.getParameter("clientAddress");
        List<Order> naracki=this.orderService.searchByClient(name, address);
        model.addAttribute("orders", naracki);
        return "userOrders";
    }
}
