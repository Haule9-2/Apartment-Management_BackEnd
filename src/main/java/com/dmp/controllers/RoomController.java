package com.dmp.controllers;

import com.dmp.pojo.Room;
import com.dmp.pojo.Roommate;
import com.dmp.pojo.Resident;
import com.dmp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoommateService roommateService;

    @Autowired
    private ResidentService residentService;
    @RequestMapping("/room")
    public String roomDetail(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("rooms", roomService.getRooms(params));
        return "room";
    }

    @GetMapping("/rooms")
    public String createRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("floors", floorService.getFloor(null)); // Add floors to model
        return "addRoom";
    }

    @PostMapping("/rooms")
    public String createRoom(@ModelAttribute(value = "room") Room room, BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                roomService.addOrUpdate(room);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "addRoom";
    }

    @GetMapping("/rooms/{roomId}")
    public String updateRoom(Model model, @PathVariable("roomId") int id) {
        model.addAttribute("room", roomService.getRoomById(id));
        model.addAttribute("floors", floorService.getFloor(null)); // Add floors to model
        return "addRoom";
    }
    @PostMapping("/rooms/updateStatus/{roomId}")
    public String updateRoomStatus(@PathVariable("roomId") int roomId, @RequestParam("status") String status) {
        try {
            Room room = roomService.getRoomById(roomId);
            roomService.updateStatus(room, status);
            return "redirect:/room";
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "redirect:/room";
        }
    }

    @PostMapping("/rooms/updateStatusConTrong_DaThue/{roomId}")
    public String updateRoomStatusConTrong_DaThue(@PathVariable("roomId") int roomId) {
        try {
            Room room = roomService.getRoomById(roomId);
            roomService.updateStatusConTrong_DaThue(room);
            return "redirect:/room";
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "redirect:/room";
        }
    }
    @GetMapping("/rooms/{roomId}/roommates")
    public String showRoommates(Model model, @PathVariable("roomId") int roomId) {
        List<Roommate> roommates = roommateService.getRoommatesByRoomId(roomId);

        // Fetch and set resident name for each roommate
//        for (Roommate roommate : roommates) {
//            // Assuming each roommate has a direct reference to Resident or Resident ID
//            Resident resident = residentService.getResidentByRoomId(roomId);
//            if (resident != null) {
//                roommate.setResidentName(resident.getName());
//            }
//        }

        model.addAttribute("roommates", roommates);
        return "roommates"; // Assuming this is your JSP file name
    }

}