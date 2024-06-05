package com.dmp.controllers;

import com.dmp.pojo.Services;
import com.dmp.services.ServiceService;
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
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private LocalValidatorFactoryBean validator;

    @GetMapping("/services")
    public String createView(Model model) {
        model.addAttribute("services", new Services());
        return "services";
    }
    @PostMapping("/services")
    public String createProduct(@ModelAttribute(value = "services") @Valid Services s, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                this.serviceService.addOrUpdate(s);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "services";
    }
    @GetMapping("/services/{serviceId}")
    public String updateService(Model model, @PathVariable("serviceId") int id) {
        model.addAttribute("services",this.serviceService.getServiceById(id));
        return "services";
    }
}
