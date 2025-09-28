package com.swp.evmsystem.enums;

import java.time.LocalTime;

public enum TimeSlot {
    SLOT_08_00(LocalTime.of(8, 0)),
    SLOT_09_00(LocalTime.of(9, 0)),
    SLOT_10_00(LocalTime.of(10, 0)),
    SLOT_11_00(LocalTime.of(11, 0)),
    SLOT_13_00(LocalTime.of(13, 0)),
    SLOT_14_00(LocalTime.of(14, 0)),
    SLOT_15_00(LocalTime.of(15, 0)),
    SLOT_16_00(LocalTime.of(16, 0));

    private final LocalTime time;

    TimeSlot(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDisplay() {
        return time.toString();
    }
}
