package com.dmp.controllers;

import com.dmp.services.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class FloorController {
    @Autowired
    private FloorService floorService;

    @RequestMapping("/floor")
    public String FloorDetail(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("floors", this.floorService.getFloor(params));
        return "floor";
    }
}
