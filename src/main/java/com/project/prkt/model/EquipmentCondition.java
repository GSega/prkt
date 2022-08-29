package com.project.prkt.model;

public enum EquipmentCondition {
    UNKNOWN("Н.У."),
    GOOD("хорошее"),
    USABLE("исправное"),
    BROKEN("сломано"),
    SERVICE("в ремонте");

    private final String displayValue;
    EquipmentCondition(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}