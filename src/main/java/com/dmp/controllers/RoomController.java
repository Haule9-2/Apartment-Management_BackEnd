package com.dmp.controllers;

import com.dmp.pojo.Room;
import com.dmp.services.FloorService;
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

    @Autowired
    private FloorService floorService; // Inject FloorService

    @RequestMapping("/room")
    public String roomDetail(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("rooms", roomService.getRooms(params));
        return "room";
    }

    @GetMapping("/rooms")
    public String createRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("floors", floorService.getFloor(null)); // Add floors to model
        return "addroom";
    }

    @PostMapping("/rooms")
    public String createRoom(@ModelAttribute(value = "room") @Valid Room room, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                roomService.addOrUpdate(room);
                return "redirect:/room";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "addroom";
    }

    @GetMapping("/rooms/{roomId}")
    public String updateRoom(Model model, @PathVariable("roomId") int id) {
        model.addAttribute("room", roomService.getRoomById(id));
        model.addAttribute("floors", floorService.getFloor(null)); // Add floors to model
        return "addroom";
    }
}
