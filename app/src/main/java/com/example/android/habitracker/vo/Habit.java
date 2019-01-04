package com.example.android.habitracker.vo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Habit {

    @PrimaryKey
    @NonNull
    private String name;

    private String description;
    private HabitFrequency habitFrequency;
    private long reminderTime;

    public Habit(
            String name,
            String description,
            HabitFrequency habitFrequency,
            long reminderTime
    ) {
        this.name = name;
        this.description = description;
        this.habitFrequency = habitFrequency;
        this.reminderTime = reminderTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HabitFrequency getHabitFrequency() {
        return habitFrequency;
    }

    public void setHabitFrequency(HabitFrequency habitFrequency) {
        this.habitFrequency = habitFrequency;
    }

    public long getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(long reminderTime) {
        this.reminderTime = reminderTime;
    }
}
