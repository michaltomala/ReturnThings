package pl.coderslab.controllers;


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
import pl.coderslab.services.UserService;
import pl.coderslab.services.admin.AdminUserService;
import pl.coderslab.validator.ValidationRegisterUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user/")
public class UserController {

//   todo objąć filtrem żeby zalogowany uzytkownik nie móŋł przełączyć się na innego usera i zeby niezalogowany
//   todo nie mógł wejść na żadna z tych ściezek

    private final UserService userService;
    private final AdminUserService adminUserService;

    @Autowired
    public UserController(UserService userService,AdminUserService adminUserService) {

        this.userService = userService;
        this.adminUserService = adminUserService;
    }

// todo - usuniecie klas alert danger w formularzach


//   todo: wyrzucić id - bedziemy operować na modelu z sesji

    @GetMapping("settings")
    public String settings(HttpServletRequest request, Model model, HttpSession session){
        userService.edit(model, request,session);
        return "user/settings";
    }

//   todo - objaśnienie metody

    @PostMapping("settings")
    public String update(@Validated(ValidationRegisterUserGroup.class) User user, BindingResult errors,
                         HttpServletRequest req, Model model, HttpSession session){
//      todo - walidacja działa na cały obiekt,a trzeba ją zrobić na pojedyncze elementy
//       (żeby można edytować tylko email albo tylko hasło)
//      todo - prawdopobne rozwiazanie:
//       3.jedno i drugie - pełna walidacja


//      todo: sprawdzić czy wszystkie warunki działają !!
        if(user.getPassword().equals("")){
            if(adminUserService.checkEmail(user,model)){
                userService.edit(model,req,session);
                return "user/settings";
            }else{
                userService.onlySaveUser(user,session);
                return "redirect:/user/settings";
            }
        }

        if (errors.hasErrors()) {
            if(adminUserService.checkEmail(user,model)){
                userService.edit(model,req,session);
                return "user/settings";
            }

//          todo do sprawdzenia widok (czy errory musza byc typu div)
            return "user/settings";
        }


        if(userService.update(user,model,session)){
            return "user/settings";
        }

        return "redirect:"+req.getContextPath()+"/user/settings";
    }
}
