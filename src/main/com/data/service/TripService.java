package data.service;

import data.model.Trip;

import java.util.List;

public interface TripService {
    List<Trip> findAll();
    List<Trip> searchTrips(String departure, String destination);

}
