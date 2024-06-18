package com.dmp.controllers;

import com.dmp.services.RoomService;
import com.dmp.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRoomController {
    @Autowired
    private RoomService roomService;

    @DeleteMapping("/rooms/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(Model model, @PathVariable(value = "roomId") int id) {
        this.roomService.deleteRoom(id);
    }

}