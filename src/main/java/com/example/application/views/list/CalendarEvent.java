package com.example.application.views.list;

import java.time.LocalDate;

public class CalendarEvent {
    private LocalDate date;
    private String checkInTime;
    private String checkOutTime;
    private String totalTime;
    private String description;
    private boolean highlighted;

    public CalendarEvent(LocalDate date, String checkInTime, String checkOutTime, String totalTime, String description, boolean highlighted) {
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.totalTime = totalTime;
        this.description = description;
        this.highlighted = highlighted;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHighlighted() {
        return highlighted;
    }
}
