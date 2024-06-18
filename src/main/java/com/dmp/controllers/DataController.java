package com.dmp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class DataController {

    @GetMapping("/api/data")
    public ResponseEntity<String> getData() {
        // Example data response
        return ResponseEntity.ok("Data fetched successfully");
    }
}
