package com.academia.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.bookstore.services.ControlService;

@RestController
@RequestMapping("/control")
public class ControlController {

    @Autowired
    private ControlService controlService;

    @PostMapping("/pause")
    public ResponseEntity<Void> pauseProcessing() {
        controlService.setPauseProcessing(true);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resume")
    public ResponseEntity<Void> resumeProcessing() {
        controlService.setPauseProcessing(false);
        return ResponseEntity.ok().build();
    }
}
