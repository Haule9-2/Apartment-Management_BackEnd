package com.dmp.controllers;

import com.dmp.pojo.Room;
import com.dmp.pojo.Services;
import com.dmp.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    @RequestMapping("/rooms")
    public String RoomDetail(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("rooms", this.roomService.getRooms(params));
        return "room";
    }
    @GetMapping("/rooms")
    public String createRoom(Model model) {
        model.addAttribute("rooms", new Room());
        return "room";
    }
    @PostMapping("/rooms")
    public String addandupdateRoom(@ModelAttribute(value = "rooms") @Valid Room s, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                this.roomService.addOrUpdate(s);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "room";
    }
    @GetMapping("/rooms/{roomId}")
    public String updateService(Model model, @PathVariable("roomId") int id) {
        model.addAttribute("rooms",this.roomService.getRoomById(id));
        return "room";
    }
}
