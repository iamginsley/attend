package com.example.application.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalenderComponent extends VerticalLayout {

    public CalenderComponent() {
        // Title
        H1 title = new H1("Schedule");
        title.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.FontWeight.BOLD);

        // Container for the calendar
        VerticalLayout calendarContainer = new VerticalLayout();
        calendarContainer.setWidthFull();
        calendarContainer.addClassName("calendar-container");

        // Populate the calendar with sample events
        List<CalendarEvent> events = getSampleEvents();
        for (CalendarEvent event : events) {
            calendarContainer.add(createEventCard(event));
        }

        add(title, calendarContainer);
    }

    // Create a card for each event
    private Component createEventCard(CalendarEvent event) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("event-card");

        // Date Block
        HorizontalLayout dateBlock = new HorizontalLayout();
        dateBlock.addClassName("date-block");
        Paragraph dateText = new Paragraph(String.valueOf(event.getDate().getDayOfMonth()));
        Paragraph monthText = new Paragraph(event.getDate().getMonth().name().substring(0, 3));
        dateBlock.add(dateText, monthText);

        // Event Details
        HorizontalLayout detailsBlock = new HorizontalLayout();
        detailsBlock.addClassName("details-block");
        HorizontalLayout times = new HorizontalLayout(
                new Paragraph(event.getCheckInTime()),
                new Paragraph("Check In"),
                new Paragraph(event.getCheckOutTime()),
                new Paragraph("Check Out"),
                new Paragraph(event.getTotalTime()),
                new Paragraph("Total")
        );
        Paragraph description = new Paragraph(event.getDescription());
        detailsBlock.add(times, description);

        // Highlight current day
        if (event.isHighlighted()) {
            card.addClassName("highlighted");
        }

        card.add(dateBlock, detailsBlock);
        return card;
    }

    // Sample data
    private List<CalendarEvent> getSampleEvents() {
        List<CalendarEvent> events = new ArrayList<>();
        events.add(new CalendarEvent(LocalDate.of(2024, 5, 5), "08:30", "18:30", "10:00h", "SWE 2 | MCI V Raum 202", false));
        events.add(new CalendarEvent(LocalDate.of(2024, 5, 6), "08:30", "18:30", "10:00h", "SWE 2 | MCI V Raum 202", true));
        events.add(new CalendarEvent(LocalDate.of(2024, 5, 7), "08:30", "18:30", "10:00h", "ITS | MCI V Raum 301", false));
        return events;
    }
}

