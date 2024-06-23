package com.dmp.controllers;

import com.dmp.pojo.Resident;
import com.dmp.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ResidentController {

    private final ResidentService residentService;

    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/residents")
    public String getAllResidents(Model model) {
        model.addAttribute("residents", residentService.getResident());
        return "resident-list";
    }

    @GetMapping("/resident/{id}")
    public String showResidentDetails(@PathVariable Integer id, Model model) {
        Resident resident = residentService.getResidentById(id);
        if (resident == null) {
            return "error-page";
        }
        model.addAttribute("resident", resident);
        return "resident-details";
    }

}
