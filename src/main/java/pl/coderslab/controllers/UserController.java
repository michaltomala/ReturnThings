package pl.coderslab.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/")
public class UserController {

//   todo objąć filtrem żeby zalogowany uzytkownik nie móŋł przełączyć się na innego usera i zeby niezalogowany
//   todo nie mógł wejść na żadna z tych ściezek


    @GetMapping("settings/{id}")
    public String settings(@PathVariable Long id){
        return "user/settings";
    }

}
