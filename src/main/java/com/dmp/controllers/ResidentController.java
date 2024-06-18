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
@RequestMapping("/residents")
public class ResidentController {
    @Autowired
    private ResidentService residentService;
    @Autowired
    private LocalValidatorFactoryBean validator;

    @GetMapping
    public String listResidents(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("residents", this.residentService.getResident(params));
        return "residents";
    }

    @GetMapping("/create")
    public String createView(Model model) {
        model.addAttribute("resident", new Resident());
        return "residentForm";
    }

    @PostMapping("/create")
    public String createResident(@ModelAttribute("resident") @Valid Resident resident, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                this.residentService.addOrUpdate(resident);
                return "redirect:/residents";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "residentForm";
    }

    @GetMapping("/{residentId}/edit")
    public String updateResidentView(Model model, @PathVariable("residentId") int id) {
        model.addAttribute("resident", this.residentService.getResidentById(id));
        return "residentForm";
    }

    @PostMapping("/{residentId}/edit")
    public String updateResident(@PathVariable("residentId") int id, @ModelAttribute("resident") @Valid Resident resident, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                resident.setId(id);
                this.residentService.addOrUpdate(resident);
                return "redirect:/residents";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "residentForm";
    }
}
