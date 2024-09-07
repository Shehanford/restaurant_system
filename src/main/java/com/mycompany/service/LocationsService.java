package com.mycompany.service;

import com.mycompany.model.Locations;
import com.mycompany.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class LocationsService {

    @Autowired
    private LocationsRepository locationsrepo;

    public void save(Locations locations){
        locationsrepo.save(locations);
    }

    public List<Locations> getAllLocations(){
        return locationsrepo.findAll();
    }

    public void deleteById(int id){
        locationsrepo.deleteById(id);
    }

    public Locations getLocationsById(int id){
        return locationsrepo.findById(id).get();
    }

}
