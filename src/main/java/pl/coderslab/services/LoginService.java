package pl.coderslab.services;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service
public class LoginService {



    public void startLogin(Model model, HttpServletRequest request){
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath() + "/login");
    }


    public void loginUser(){

    }

    public void logout(HttpSession session){
        session.setAttribute("user", null);
    }

}
