package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {
    private List<String> list = new ArrayList<>();

    public ListSection(List<String> list) {
        this.list = list;
    }
}
