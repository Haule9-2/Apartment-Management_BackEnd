package com.dmp.controllers;

import com.dmp.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@RestController
public class ApiServiceController {
    @Autowired
    private ServiceService serviceService;

    @DeleteMapping("/services/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(Model model, @PathVariable(value = "serviceId") int id) {
        this.serviceService.deleteService(id);
    }
}
