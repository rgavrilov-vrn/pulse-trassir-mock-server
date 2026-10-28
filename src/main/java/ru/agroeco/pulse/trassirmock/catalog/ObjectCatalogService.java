package ru.agroeco.pulse.trassirmock.catalog;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectCatalogService {
    private final ObjectCatalog catalog;

    public ObjectCatalogService(ObjectCatalog catalog) {
        this.catalog = catalog;
    }

    public List<TrassirObject> allObjects() {
        return catalog.findAll();
    }
}
