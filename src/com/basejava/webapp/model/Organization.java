package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link link;
    private final List<Period> periods;

    public Organization(String name, String url, List<Period> periods) {
        this.link = new Link(name, url);
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(link, that.link) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, periods);
    }

    @Override
    public String toString() {
        return link + "\n" + periods;
    }
}
