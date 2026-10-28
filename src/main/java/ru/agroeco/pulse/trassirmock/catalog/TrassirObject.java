package ru.agroeco.pulse.trassirmock.catalog;

public record TrassirObject(String name, String guid, String className, String parent) {
    public TrassirObject {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Object name is required");
        if (guid == null || guid.isBlank()) throw new IllegalArgumentException("Object guid is required");
        if (className == null || className.isBlank()) throw new IllegalArgumentException("Object class is required");
    }
}
