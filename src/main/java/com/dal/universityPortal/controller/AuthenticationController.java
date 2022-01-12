package com.dal.universityPortal.controller;

import com.dal.universityPortal.exceptions.UnsupportedUser;
import com.dal.universityPortal.exceptions.ValidationException;
import com.dal.universityPortal.model.Credential;
import com.dal.universityPortal.model.ResetCredential;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.dal.universityPortal.constant.ErrorConstant.*;
import static com.dal.universityPortal.constant.UrlConstant.*;

@Controller
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(LOGIN)
    public String loginPage(Model model) {
        model.addAttribute("credential", new Credential());
        return "login";
    }

    @PostMapping(LOGIN)
    public String saveRegistration(@ModelAttribute Credential credential, Model model, HttpServletRequest request) {
        try {
            authenticationService.login(request.getSession(), credential);
        } catch (UnsupportedUser exception) {
            model.addAttribute("credential", credential);
            model.addAttribute("errors", USER_UNSUPPORTED_ERROR);
            return "login";
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        User currentUser = authenticationService.getCurrentUser(request.getSession());
        return "redirect:"+authenticationService.getRedirectLink(currentUser.getTypeEnum());
    }

    @GetMapping(LOGOUT)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.logout(request.getSession());
        response.sendRedirect("/login");
    }

    @GetMapping(RESET_PASSWORD_SEND_CODE)
    public String sendUniqueCodePage(Model model) {
        model.addAttribute("credential", new ResetCredential());
        return "get_reset_code";
    }

    @PostMapping(RESET_PASSWORD_SEND_CODE)
    public String sendUniqueCode(@ModelAttribute ResetCredential resetCredential, Model model) {
        try {
            authenticationService.sendPasswordCode(resetCredential.getUsername());
            return String.format("redirect:%s", RESET_PASSWORD);
        } catch (UnsupportedUser exception) {
            model.addAttribute("error", USER_NOT_FOUND_ERROR);
        } catch (SQLException exception) {
            model.addAttribute("error", UNEXPECTED_ERROR);
        }
        return "get_reset_code";
    }

    @GetMapping(RESET_PASSWORD)
    public String resetPasswordPage(Model model) {
        model.addAttribute("credentials", new ResetCredential());
        return "reset_password";
    }

    @PostMapping(RESET_PASSWORD)
    public String resetPassword(@ModelAttribute ResetCredential resetCredential, Model model) {
        model.addAttribute("credentials", resetCredential);
        try {
            authenticationService.resetPassword(resetCredential);
            return String.format("redirect:%s", LOGIN);
        } catch (ValidationException exception) {
            model.addAttribute("validationErrors", exception.getErrors());
        } catch (SQLException exception) {
            model.addAttribute("error",  UNEXPECTED_ERROR);
        }
        return "reset_password";
    }
}
