package data.repository;

import data.model.Trip;

import java.util.List;

public interface TripRepository {
    List<Trip> findAll();
    List<Trip> searchTrips(String departure, String destination);
}
