package com.example.application.views.components.calendar;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarEvent {
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

    private String description;
    private boolean highlighted;

    public CalendarEvent(LocalDate date, LocalTime checkInTime, LocalTime checkOutTime, String description, boolean highlighted) {
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.description = description;
        this.highlighted = highlighted;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public String getTotalTime() {
        if(this.checkOutTime == null || this.checkInTime == null) {
            return null;
        }

        // calculate time between timestamps.
        return String.valueOf(Duration.between(this.checkInTime, this.checkOutTime).toHours());
    }

    public String getDescription() {
        return description;
    }

    public boolean isHighlighted() {
        return highlighted;
    }
}
