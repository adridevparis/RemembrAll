package com.myadridev.rememberall.models;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize(as = ReminderModel.class)
public class ReminderModel implements Comparable<ReminderModel> {
    public int Id;
    public String Name;

    public String Description;

    public boolean Achieved;

    @JsonIgnore
    public Date NextReminderDate;

    @JsonProperty("rd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    public Date ReminderDate;

    @JsonProperty("ucrt")
    public boolean UseCustomReminderTime;

    @JsonProperty("crt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "CET")
    public Date CustomReminderTime;

    @JsonProperty("gId")
    public int GroupId;

    public ReminderModel() {
    }

    public ReminderModel(ReminderModel reminder) {
        Id = reminder.Id;
        Name = reminder.Name;
        Description = reminder.Description;
        GroupId = reminder.GroupId;
        ReminderDate = reminder.ReminderDate;
        UseCustomReminderTime = reminder.UseCustomReminderTime;
        CustomReminderTime = reminder.CustomReminderTime;
        NextReminderDate = reminder.NextReminderDate;
        Achieved = reminder.Achieved;
    }

    @Override
    public int compareTo(@NonNull ReminderModel otherReminder) {
        if (this instanceof GroupsItemAddReminder) {
            if (otherReminder instanceof GroupsItemAddReminder) {
                return 0;
            } else {
                return 1;
            }
        } else if (otherReminder instanceof GroupsItemAddReminder) {
            return -1;
        } else {
            int compare = NextReminderDate.compareTo(otherReminder.NextReminderDate);
            if (compare < 0) {
                return -1;
            } else if (compare > 0) {
                return 1;
            } else {
                return Name.compareTo(otherReminder.Name);
            }
        }
    }
}
