package org.martynas.blogapp.controller;

import org.martynas.blogapp.model.BlogUser;
import org.martynas.blogapp.service.BlogUserService;
import org.martynas.blogapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.management.relation.RoleNotFoundException;
import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@SessionAttributes("blogUser")
public class SignupController {

    private final BlogUserService blogUserService;
    private final EmailService emailService;

    @Autowired
    public SignupController(BlogUserService blogUserService, EmailService emailService) {
        this.blogUserService = blogUserService;
        this.emailService = emailService;
    }

    @GetMapping("/signup")
    public String getRegisterForm(Model model) {
        // create new BlogUser instance and pass it to registerForm view template
        BlogUser blogUser = new BlogUser();
        model.addAttribute("blogUser", blogUser);
        return "registerForm";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute BlogUser blogUser, BindingResult bindingResult, SessionStatus sessionStatus) throws RoleNotFoundException, MessagingException {
        System.err.println("newUser: " + blogUser);  // for testing debugging purposes
        
        // Check if username is available
        if (blogUserService.findByUsername(blogUser.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "error.username","Username is already registered, try another one.");
            System.err.println("Username already taken error message");
        }
        
        if (!blogUser.getPassword().equals(blogUser.getRetypePassword())){
            bindingResult.rejectValue("retypePassword", "error.retypePassword", "Passwords do not match.");
        }
        
        // Validate user's fields
        if (bindingResult.hasErrors()) {
            System.err.println("New user did not validate");
            return "registerForm";
        }
        
        // Persist new blog user
        this.blogUserService.saveNewBlogUser(blogUser);

        // Send welcome email
        emailService.sendWelcomeEmail(blogUser.getEmail(), blogUser.getUsername());
        
        // Complete the session
        sessionStatus.setComplete();
        
        // Redirect to login page after successful registration
        return "redirect:/login?signupSuccess";
    }
}
