package pl.coderslab.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/dashboard")
    public String dashboard(){
//      todo: widok dashboard do zrobienia - tak samo jak w loginie :
//       - nie ma obrazka i to klamra na środku z napisem panel administracyjny i 4 obrazki do zarządzania

        return "admin/dashboard";
    }
}


