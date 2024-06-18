package com.dmp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("admin//dashboard")
    public String dashboard() {
//        model.addAttribute("residents", dashboardService.getAllResidents());
//        model.addAttribute("floors", dashboardService.getAllFloors());
//        model.addAttribute("rooms", dashboardService.getAllRooms());
        return "dashboard";
    }
}
