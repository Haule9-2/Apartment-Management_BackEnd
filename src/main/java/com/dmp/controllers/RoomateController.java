package com.dmp.controllers;

import com.dmp.pojo.Roommate;
import com.dmp.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
@Controller
public class RoomateController {
    @Autowired
    private RoommateService roommateService;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @GetMapping("/roommates")
    public String listRoommates(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("roommates", roommateService.getRoommate(params));
        return "roommates";
    }

    @GetMapping("/roommates/create")
    public String createRoommateView(Model model) {
        model.addAttribute("roommate", new Roommate());
        return "roommate-form";
    }

    @PostMapping("/roommates")
    public String createRoommate(@ModelAttribute("roommate") @Valid Roommate roommate, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                roommateService.addRoommate(roommate);
                return "redirect:/roommates";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "roommate-form";
    }

    @GetMapping("/roommates/{id}")
    public String updateRoommateView(Model model, @PathVariable("id") int id) {
        Roommate roommate = roommateService.getRoommateById(id);
        if (roommate != null) {
            model.addAttribute("roommate", roommate);
            return "roommate-form";
        }
        return "redirect:/roommates";
    }

}
