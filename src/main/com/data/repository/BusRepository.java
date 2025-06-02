package data.repository;

import data.model.Bus;

import java.util.List;

public interface BusRepository {
    int addBus(Bus bus);
    List<Bus> getAllBus();
    void updateBus(Bus bus);
    void deleteBus(int id);
    Bus findById(int id);
}
