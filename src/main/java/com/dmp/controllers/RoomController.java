package com.dmp.controllers;

import com.dmp.pojo.Room;
import com.dmp.pojo.Floor;
import com.dmp.services.RoomService;
import com.dmp.services.FloorService;
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

    @Autowired  // Add this annotation to enable dependency injection
    private FloorService floorService;

    @RequestMapping("/room")
    public String floorDetail(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("rooms", this.roomService.getRooms(params));
        model.addAttribute("floors", this.floorService.getFloor(params));
        return "room";
    }

    @GetMapping("/rooms")
    public String createRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "room";
    }

    @PostMapping("/rooms")
    public String createOrUpdateRoom(@ModelAttribute("room") @Valid Room room, BindingResult result) {
        if (result.hasErrors()) {
            return "room";
        }
        try {
            this.roomService.addOrUpdate(room);
            return "redirect:/";
        } catch (Exception ex) {
            return "room";
        }
    }

    @GetMapping("/rooms/{roomId}")
    public String updateRoomForm(Model model, @PathVariable("roomId") int roomId) {
        model.addAttribute("room", this.roomService.getRoomById(roomId));
        return "room";
    }
}
