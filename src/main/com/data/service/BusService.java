package data.service;

import data.model.Bus;

import java.util.List;

public interface BusService {
    int addBus(Bus bus);
    List<Bus> getAllBus();
    void updateBus(Bus bus);
    void deleteBus(int id);
    Bus findById(int id);
}
