package org.example.enums;

public enum ItemCategory {
    BURGER,
    BEVERAGES,
    DESSERTS,
    APPETIZERS;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
