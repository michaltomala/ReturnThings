package pl.coderslab.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.services.admin.UserService;
import pl.coderslab.validator.ValidationEditUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin/user/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("users")
    public String users(Model model){
        userService.start(model);
        return "admin/users";
    }


    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model , HttpServletRequest request){
        userService.edit(id,model,request);
        return "admin/user";
    }

    @PostMapping("edit/{id}")
    public String update(@Validated({ValidationEditUserGroup.class}) User user,
                         BindingResult errors, HttpServletRequest req, Model model, HttpSession session){
        if (errors.hasErrors()) {
            return "admin/user";
        }
//       todo dokończyć edycję (zrobić zakomentowany kod)

//        User checkUser = userRepository.findFirstByLogin(user.getLogin());
//        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
//            model.addAttribute("loginErr", "Taki użytkownik już istnieje !");
//            model.addAttribute("user",session.getAttribute("userFromSession"));
//            model.addAttribute("editingUser",user);
//            return "admin/user";
//        }
//        checkUser = userRepository.findFirstByEmail(user.getEmail());
//        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
//            model.addAttribute("emailErr", "Email musi być unikalny !");
//            model.addAttribute("user",session.getAttribute("userFromSession"));
//            model.addAttribute("editingUser",user);
//            return "admin/user";
//        }
//
//        userRepository.save(user);

        return "redirect:"+req.getContextPath()+"/admin/user/users";
    }


    /**
     * methods for deleting users
     * @param model
     * @param id
     * @return
     */

    @GetMapping("confirm/{id}")
    public String confirm(Model model, @PathVariable Long id){
        userService.start(model);
        userService.confirmDeleteUser(model,id);
        return "admin/users";
    }

    @GetMapping("delete/{id}")
    public String delete(User user,HttpServletRequest request){

        userService.deleteUser(user);
        return "redirect:"+request.getContextPath()+"/admin/user/users";
    }


}
