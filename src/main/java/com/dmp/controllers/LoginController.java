package com.dmp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dmp.services.UserService;
import com.dmp.pojo.User;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
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
        model.addAttribute("error", true);
        return "login"; // Return to login page with error flag
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Handle logout logic here
        return ResponseEntity.ok("Logged out successfully!");
    }
}
