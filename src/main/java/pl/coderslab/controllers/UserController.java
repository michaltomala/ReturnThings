package pl.coderslab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.model.Err;
import pl.coderslab.services.UserService;
import pl.coderslab.validator.ValidationRegisterUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("settings")
    public String settings(HttpServletRequest request, Model model, HttpSession session){

        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("formAction", request.getContextPath() + "/user/settings");
        return "user/settings";
    }

    /**
     * Method update is for edit part of our User - only email / only password or email and password
     * I validated for all options
     * @param user
     * @param errors
     * @param req
     * @param model
     * @param session
     * @return
     */
// todo - zrobic dodatkowe pole na stare hasło
    @PostMapping("settings")
    public String update(@Validated(ValidationRegisterUserGroup.class) User user, BindingResult errors,
                         HttpServletRequest req, Model model, HttpSession session){

        Err modelErr = new Err();

        if(user.getPassword().equals("")) {
            return changeOnlyEmail(user, req, model, session, modelErr); }

        if (errors.hasErrors()) {
            userService.checkEmail(user, modelErr);
            if (!modelErr.isEmpty()) {
                model.addAttribute("emailErr", modelErr.getErrors().get(0));
                model.addAttribute("user", session.getAttribute("user"));
                model.addAttribute("formAction", req.getContextPath() + "/user/settings");
                return "user/settings";
            }
            return "user/settings";
        }

        if (checkEmailAndPwd(user, model, modelErr,req,session)) { return "user/settings"; }

        saveSettings(user, session);
        return "redirect:"+req.getContextPath()+"/user/settings";
    }



    @GetMapping("profile")
    public String profile(Model model , HttpServletRequest request , HttpSession session){

        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("formAction", request.getContextPath() + "/user/profile");
        return "user/profile";
    }

    @PostMapping("profile")
    public String saveProfile(User user,HttpSession session, HttpServletRequest request){

        User userFromSession = (User) session.getAttribute("user");
        userService.saveUserDetails(user,userFromSession);
        session.setAttribute("user",userFromSession);
        return "redirect:"+request.getContextPath()+"/user/profile";
    }



    private String changeOnlyEmail(@Validated(ValidationRegisterUserGroup.class) User user, HttpServletRequest req, Model model, HttpSession session, Err modelErr) {
        userService.checkEmail(user, modelErr);
        if (!modelErr.isEmpty()) {
            model.addAttribute("emailErr", modelErr.getErrors().get(0));
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("formAction", req.getContextPath() + "/user/settings");
            return "user/settings";
        } else {
            User userFromSession = (User) session.getAttribute("user");
            userFromSession.setEmail(user.getEmail());
            userService.saveUser(userFromSession);
            session.setAttribute("user", userFromSession);
            return "redirect:/user/settings";
        }
    }

    private void saveSettings(@Validated(ValidationRegisterUserGroup.class) User user, HttpSession session) {
        User userFromSession = (User) session.getAttribute("user");
        userService.saveChangedSettings(user,userFromSession);
        session.setAttribute("user",user);
    }


    /**
     * This method is for checking if email is unique and then if repeated password
     * is the same as password
     * @param user
     * @param model
     * @param modelErr
     * @return
     */

    private boolean checkEmailAndPwd(@Validated(ValidationRegisterUserGroup.class) User user,
                                     Model model, Err modelErr,HttpServletRequest req,HttpSession session) {
        userService.checkIfRepeatedPasswordIsTheSameAsPassword(user,modelErr);

        if (!modelErr.isEmpty()) {
            model.addAttribute("pwdErr", "Hasła muszą być takie same!");
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("formAction", req.getContextPath() + "/user/settings");
            return true;
        }

        userService.checkIfEmailIsUnique(user,modelErr);
        if (!modelErr.isEmpty()) {
            model.addAttribute("emailErr", "Taki użytkownik już istnieje !");
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("formAction", req.getContextPath() + "/user/settings");
            return true;
        }
        return false;
    }

}
