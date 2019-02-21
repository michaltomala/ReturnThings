package pl.coderslab.controllers.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminController {

// todo w widoku wywalić drugą linijkę z header - prawdopodobnie lepiej bedzie bez niej

    @GetMapping("dashboard")
    public String dashboard(){

        return "admin/dashboard";
    }

    @GetMapping("users")
    public String users(){

        return "admin/users";
    }

    @GetMapping("admins")
    public String admins(){

        return "admin/admins";
    }

    @GetMapping("institutions")
    public String institutions(){

        return "admin/institutions";

    }

    @GetMapping("bounties")
    public String bounties(){

        return "admin/bounties";
    }


}


