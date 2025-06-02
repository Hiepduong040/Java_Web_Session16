package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.Trip;
import ra.repository.TripRepository;

import java.util.List;
@Service
public class TripServiceImp implements TripService{

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> searchTrips(String departure, String destination) {
        return tripRepository.searchTrips(departure, destination);
    }
}
