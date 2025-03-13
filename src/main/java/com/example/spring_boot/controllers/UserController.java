package com.example.spring_boot.controllers;

import com.example.spring_boot.dtos.UserRequest;
import com.example.spring_boot.services.UserManager;
import com.example.spring_boot.utils.IPAddressUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "index";
    }

    @PostMapping("/subscribe")
    public String subscribeEmail(
            @Valid @ModelAttribute UserRequest userRequest,
            BindingResult bindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        // Verify if email is duplicated
        if (userManager.emailExists(userRequest.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "This email already exists");

            return "index";
        }
        // Get clients ip
        userRequest.ipAddress = (IPAddressUtils.getClientIpAddress(request));
        redirectAttributes.addFlashAttribute("successMessage",
                "¡Thank you for subscribe!");

        userManager.saveUser(userRequest);
        return "redirect:/";
    }
}
