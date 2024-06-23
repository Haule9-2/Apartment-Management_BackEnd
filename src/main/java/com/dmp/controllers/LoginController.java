package com.dmp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dmp.services.UserService;
import com.dmp.pojo.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully!");
        }
        return "login"; // Returns the login page (login.jsp or login.html)
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.authenticateUser(username, password);
        if (user != null) {
            if ("Admin".equals(user.getRole())) {
                return "redirect:/admin"; // Redirect to admin dashboard or page
            } else if ("Resident".equals(user.getRole())) {
                return "redirect:http://localhost:3000/home"; // Redirect to React.js home page
            }
        }
        // If authentication fails or role is not recognized, redirect back to login page with error
        model.addAttribute("error", "Invalid username or password!");
        return "login"; // Return to login page with error message
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Logout endpoint reached.");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("Invalidating session and clearing security context.");
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true"; // Redirect to login page with logout parameter
    }
}
