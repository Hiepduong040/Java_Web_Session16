package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import data.model.Bus;
import data.repository.BusRepository;

import java.util.List;

@Service

public class BusServiceImp implements BusService{


    @Autowired
    BusRepository busRepository;
    @Override
    public int addBus(Bus bus) {
        return busRepository.addBus(bus);
    }

    @Override
    public List<Bus> getAllBus() {
        return busRepository.getAllBus();
    }

    @Override
    public void updateBus(Bus bus) {
        busRepository.updateBus(bus);
    }

    @Override
    public void deleteBus(int id) {
        busRepository.deleteBus(id);
    }

    @Override
    public Bus findById(int id) {
        return findById(id);
    }
}
