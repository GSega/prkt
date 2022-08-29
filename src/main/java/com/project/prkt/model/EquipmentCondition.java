//package com.project.prkt.model;
//
//public enum EquipmentCondition {
//    GOOD("good"),
//    USABLE("usable"),
//    BROKEN("broken"),
//    SERVICE("service");
//
//    private final String displayValue;
//
//    EquipmentCondition(String displayValue) {
//        this.displayValue = displayValue;
//    }
//
//    public String getDisplayValue() {
//        return displayValue;
//    }
//}




//package com.project.prkt.model;
//
//import java.util.ResourceBundle;
//
//public enum EquipmentCondition {
//    GOOD,
//    USABLE,
//    BROKEN,
//    SERVICE;
//
//    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("src/main/messages");
//
//    public String toString() {
//        return resourceBundle.getString(name() + ".string");
//    }
//}




package com.project.prkt.model;

public enum EquipmentCondition {
    UNKNOWN("N/A", "Н/У"),
    GOOD("good", "хорошее"),
    USABLE("usable", "рабочее"),
    BROKEN("broken", "поломанное"),
    SERVICE("service", "в сервисе");

    private final String displayValueEn;
    private final String displayValueRu;

    EquipmentCondition(String displayValueEn, String displayValueRu) {
        this.displayValueEn = displayValueEn;
        this.displayValueRu = displayValueRu;
    }

    public String getDisplayValueEn() {
        return displayValueEn;
    }

    public String getDisplayValueRu() {
        return displayValueRu;
    }
}