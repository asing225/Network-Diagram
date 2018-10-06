package com.company.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Activity {
    private final SimpleStringProperty activity;
    private final SimpleIntegerProperty duration;
    private final SimpleStringProperty dependency;

    public Activity(String activity, int duration, String dependency){
        this.activity = new SimpleStringProperty(activity);
        this.duration = new SimpleIntegerProperty(duration);
        this.dependency = new SimpleStringProperty(dependency);
    }

    public String getActivity() {
        return activity.get();
    }

    public void setActivity(String activity) {
        this.activity.set(activity);
    }

    public int getDuration() {
        return duration.get();
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public String getDependency() {
        return dependency.get();
    }

    public void setDependency(String dependency) {
        this.dependency.set(dependency);
    }
}
