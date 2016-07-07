package com.anit.remider.model;

/**
 * Created by 79900 on 08.07.2016.
 */
public class ModelTask implements Item {

    private String title;
    private long date;

    public ModelTask() {
    }

    public ModelTask(String title, long date) {
        this.title = title;
        this.date = date;
    }

    @Override
    public Boolean isTask() {
        return true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
