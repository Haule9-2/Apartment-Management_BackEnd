/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.cloudinary.Cloudinary;

import java.util.Map;

import com.dmp.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Admin
 */
@Controller
public class HomeController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private Cloudinary cloudinary;


    @RequestMapping("/")
    public String Index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("services", this.serviceService.getServices(params));
        return "index";
    }
}
