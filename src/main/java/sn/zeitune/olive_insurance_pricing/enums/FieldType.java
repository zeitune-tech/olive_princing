package sn.zeitune.olive_insurance_pricing.enums;

public enum FieldType {
    SELECT("SELECT"),
    NUMBER("NUMBER");

    private final String type;
    FieldType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public static FieldType fromString(String type) {
        for (FieldType fieldType : FieldType.values()) {
            if (fieldType.getType().equalsIgnoreCase(type)) {
                return fieldType;
            }
        }
        throw new IllegalArgumentException("Unknown field type: " + type);
    }
}
