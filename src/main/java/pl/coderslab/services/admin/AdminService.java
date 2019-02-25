package pl.coderslab.services.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;


    public void addListOfAdmins(Model model){
        model.addAttribute("admins",userRepository.findAllByIsAdmin(true));
    }

    public void edit(Long id, Model model, HttpServletRequest request){
        User editingAdmin = userRepository.findOne(id);
        model.addAttribute("editingAdmin",editingAdmin);
        model.addAttribute("formAction", request.getContextPath() + "/admin/edit/{id}"+id);
    }



}
