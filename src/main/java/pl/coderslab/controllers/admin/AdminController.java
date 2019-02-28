package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.services.admin.AdminUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin/")
public class AdminController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }


    @GetMapping("admins")
    public String admins(Model model){
        adminUserService.addListOfAdmins(model);
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
        adminUserService.addListOfAdmins(model);
        adminUserService.edit(id,model,request);
        return "admin/admins";
    }


    @PostMapping("edit/{id}")
    public String update(User user, HttpServletRequest req, Model model, HttpSession session){

        if(adminUserService.update(user,model,session)){
            return "admin/admins";
        }

        return "redirect:"+req.getContextPath()+"/admin/admins";
    }


    /**
     * methods for deleting admins
     * @param model
     * @param id
     * @return
     */

    @GetMapping("confirm/{id}")
    public String confirm(Model model, @PathVariable Long id){
        adminUserService.addListOfAdmins(model);
        adminUserService.confirmDeleteUser(model,id);
        return "admin/admins";
    }

    @GetMapping("delete/{id}")
    public String delete(User user,HttpServletRequest request){
        adminUserService.deleteUser(user);
        return "redirect:"+request.getContextPath()+"/admin/admins";
    }


}
