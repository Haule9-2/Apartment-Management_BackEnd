package com.dmp.controllers;

import com.dmp.services.FamilyMemberService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class ApiFamilyMemberController {

    @Autowired
    private FamilyMemberService familyMemberService;




}
