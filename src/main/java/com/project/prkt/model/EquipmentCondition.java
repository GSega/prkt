package com.project.prkt.model;

public enum EquipmentCondition {
    GOOD("good"),
    USABLE("usable"),
    BROKEN("broken"),
    SERVICE("service");

    private final String displayValue;

    EquipmentCondition(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}