package com.academia.bookstore.services;

import org.springframework.stereotype.Component;

@Component
public class ControlService {
    private boolean pauseProcessing = false;

    public void setPauseProcessing(boolean pauseProcessing) {
        this.pauseProcessing = pauseProcessing;
    }

    public boolean isPauseProcessing() {
        return pauseProcessing;
    }
}