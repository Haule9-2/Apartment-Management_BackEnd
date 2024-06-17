package com.dmp.controllers;

import com.dmp.pojo.Resident;
import com.dmp.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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