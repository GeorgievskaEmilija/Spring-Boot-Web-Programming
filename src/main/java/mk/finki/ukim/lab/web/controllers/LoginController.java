package mk.finki.ukim.lab.web.controllers;

import mk.finki.ukim.lab.model.User;
import mk.finki.ukim.lab.model.exceptions.WrongCredentialsException;
import mk.finki.ukim.lab.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @GetMapping
    public String showLoginPage(){
        return "login";
    }
    @PostMapping
    public String tryLogin(@RequestParam String username,
                           @RequestParam String password,
                           Model model , HttpServletRequest request){
        User u=null;
        try{
            u=loginService.login(username,password);
            request.getSession().setAttribute("user",u);
            return "redirect:/balloons";
        }
        catch(WrongCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";

        }
    }

}
