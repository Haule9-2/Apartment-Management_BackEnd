package com.dmp.controllers;

import com.dmp.services.FloorService;
import com.dmp.pojo.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class FloorController {
    @Autowired
    private FloorService floorService;

    @RequestMapping("/floor")
    public String floorDetail(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("floors", floorService.getFloor(params));
        return "floor";
    }

    @GetMapping("/floor/rooms")
    public String showRoomsForFloor(@RequestParam("floorId") Integer floorId, Model model) {
        Floor floor = floorService.getFloorById(floorId);
        if (floor != null) {
            model.addAttribute("floor", floor);
            return "roomList";
        } else {
            return "errorPage";
        }
    }
}
