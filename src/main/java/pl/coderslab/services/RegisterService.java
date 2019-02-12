package pl.coderslab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;


@Service
public class RegisterService {

//    @Autowired
//    private UserRepository userRepository;


    public void startRegister(Model model){
        model.addAttribute("user", new User());
    }





}
