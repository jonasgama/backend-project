package com.ninjaone.backendinterviewproject.catalog.domain.enums;

public enum CatalogCompatibilityEnum {
    WINDOWS_SYSTEM("windows_system"),
    IOS_SYSTEM("ios_system"),
    GENERIC("generic");

    private String value;

    CatalogCompatibilityEnum(String value){
        this.value = value;
    }

    public static boolean isMember(java.lang.String aName) {
        CatalogCompatibilityEnum[] items = CatalogCompatibilityEnum.values();
        for (CatalogCompatibilityEnum item : items)
            if (item.value.equals(aName))
                return true;
        return false;
    }

    public String getValue() {
        return value;
    }
}
