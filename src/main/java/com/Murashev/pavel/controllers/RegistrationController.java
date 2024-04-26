package com.Murashev.pavel.controllers;

import com.Murashev.pavel.domain.entity.User;
import com.Murashev.pavel.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model){
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty){
            model.addAttribute("password2Error", "Заполните поле подтверждения пароля");
        }
        if (user.getPassword() != null && !user.getPassword().equals( passwordConfirm )){
            model.addAttribute( "message", "Пароли не совпадают!" );
        }
        if (isConfirmEmpty || bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors( bindingResult );
            model.mergeAttributes( errors );
            return "registration";
        }
        if (!userService.addUser( user )){
            model.addAttribute( "message", "Пользователь уже существует!" );
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if (isActivated){
            model.addAttribute("messageType", "success");
            model.addAttribute( "message", "Пользователь активирован" );
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute( "message", "Код активации не найден" );
        }
        return "login";
    }
}
