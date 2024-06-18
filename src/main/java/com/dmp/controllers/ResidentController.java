package com.dmp.controllers;

import com.dmp.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    @RequestMapping("/details")
    public String residentDetail(Model model) {
        model.addAttribute("residents", residentService.getResident());
        return "resident";
    }


}