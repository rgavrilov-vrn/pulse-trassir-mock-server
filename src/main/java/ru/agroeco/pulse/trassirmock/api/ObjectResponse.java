package ru.agroeco.pulse.trassirmock.api;

public record ObjectResponse(String name, String guid, String className, String parent) {
    public static ObjectResponse from(ru.agroeco.pulse.trassirmock.catalog.TrassirObject object) {
        return new ObjectResponse(object.name(), object.guid(), object.className(), object.parent());
    }
}
