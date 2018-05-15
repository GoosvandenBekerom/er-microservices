package com.s63d.vehicle.repository;

import com.s63d.generic.Repository;
import com.s63d.vehicle.domain.CarTracker;

public class CarTrackerRepository extends Repository<CarTracker, Long> {
    public CarTrackerRepository() { super(CarTracker.class); }
}
