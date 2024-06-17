package com.dmp.controllers;

import com.dmp.pojo.Resident;
import com.dmp.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ResidentController {
    @Autowired
    private ResidentService residentService;
    @Autowired
    private LocalValidatorFactoryBean validator;

    @GetMapping("/residents")
    public String createView(Model model) {
        model.addAttribute("resident", new Resident());
        return "residents";
    }

    @PostMapping("/residents")
    public String addResident(@ModelAttribute(value = "resident") @Valid Resident resident, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                this.residentService.addOrUpdate(resident);
                return "redirect:/residents";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "residents";
    }

    @GetMapping("/residents/{residentId}")
    public String updateResident(Model model, @PathVariable("residentId") int id) {
        model.addAttribute("resident", this.residentService.getResidentById(id));
        return "residents";
    }
}