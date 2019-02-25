package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.services.admin.AdminService;
import pl.coderslab.services.admin.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    @GetMapping("admins")
    public String admins(Model model){
        adminService.addListOfAdmins(model);
        return "admin/admins";
    }


    /**
     * methods for edit admins
     * @param id
     * @param model
     * @param request
     * @return
     */

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model , HttpServletRequest request){
        adminService.addListOfAdmins(model);
        adminService.edit(id,model,request);
        return "admin/admins";
    }


    @PostMapping("edit/{id}")
    public String update(User user, HttpServletRequest req, Model model, HttpSession session){

        if(userService.update(user,model,session)){
            return "admin/admins";
        }

        return "redirect:"+req.getContextPath()+"/admin/admins";
    }



}
