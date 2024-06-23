package com.dmp.controllers;

import com.dmp.pojo.Services;
import com.dmp.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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
    @RequestMapping("/service")
    public String Index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("services", this.serviceService.getServices(params));
        return "service";
    }

    @PostMapping("/services")
    public String createService(@ModelAttribute(value = "services") @Valid Services s, BindingResult rs) {
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