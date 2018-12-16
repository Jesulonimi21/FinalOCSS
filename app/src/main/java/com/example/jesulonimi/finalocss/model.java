package com.example.jesulonimi.finalocss;

import android.support.annotation.NonNull;

import java.util.Date;

public class model implements Comparable<model> {
    String title;
    String link;
    Date date;

    public model(String title, String link, Date date) {
        this.title = title;
        this.link = link;
        this.date = date;
    }

    @Override
    public int compareTo(@NonNull model o) {
        return date.compareTo(o.date);
    }
}
