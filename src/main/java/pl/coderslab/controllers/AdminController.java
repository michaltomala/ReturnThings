package pl.coderslab.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

// todo w widoku wywalić drugą linijkę z header - prawdopodobnie lepiej bedzie bez niej

    @GetMapping("/dashboard")
    public String dashboard(){

        return "admin/dashboard";
    }


}


