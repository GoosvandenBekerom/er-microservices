package com.s63d.invoice.clients;

import com.s63d.invoice.domain.Vehicle;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VehicleServiceClient {

    public List<Vehicle> loadVehicle(Long userId) {

        ArrayList<Vehicle> list = new ArrayList<>();
        list.add(new Vehicle());
        return list;

    }
}
