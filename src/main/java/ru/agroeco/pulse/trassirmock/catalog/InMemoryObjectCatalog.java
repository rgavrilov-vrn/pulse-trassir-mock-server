package ru.agroeco.pulse.trassirmock.catalog;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryObjectCatalog implements ObjectCatalog {
    private static final List<TrassirObject> OBJECTS = List.of(
            new TrassirObject("video81", "tkcbkSr1", "Server", "tkcbkSr1"),
            new TrassirObject("Каналы", "tkcbkSr1C", "Folder", "tkcbkSr1"),
            new TrassirObject("IP-устройства", "tkcbkSr1I", "Folder", "tkcbkSr1"),
            new TrassirObject("TR-D2123ZCL6 15", "QhSW4hj2", "IP Device", "tkcbkSr1I"),
            new TrassirObject("СК Гаврильский тех.зона обработка рук", "A09M5rCx", "Channel", "tkcbkSr1C"),
            new TrassirObject("СК Гаврильский сер.зона бункер 9,10", "B4k6o1EE", "Channel", "tkcbkSr1C")
    );

    @Override
    public List<TrassirObject> findAll() {
        return OBJECTS;
    }
}
