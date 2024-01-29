package org.djalexkidd.tartempion.domain;

public enum Priority {
    LOW(0, "faible"),
    HIGH(100, "haut");

    private final Integer level;
    private final String name;

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    Priority(Integer level, String name) {
        this.level = level;
        this.name = name;
    }
}
