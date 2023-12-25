package com.basejava.webapp.model;

import java.time.LocalDate;

public class Period {
    private Link link;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public String toString() {
        return link + "\n" + startDate + " - " + endDate + "  " + title + "\n" + description;
    }

    public Period(String name, String url, String title, String description, LocalDate startDate, LocalDate endDate) {
        this.link = new Link(name, url);
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
