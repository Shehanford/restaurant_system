package com.mycompany;

import com.mycompany.model.Locations;
import com.mycompany.repository.LocationsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class LocationRepositoryTest {



    @Autowired
    LocationsRepository locationsRepository;

    @Test
    public void testAddLocation(){
        Locations locations = new Locations();
        locations.setName("Nuwara Eliya");

        Locations savelocations = locationsRepository.save(locations);
        Assertions.assertThat(savelocations.getId()).isGreaterThan(0);

    }

    @Test
    public void testListLocations(){
        Iterable <Locations> locations = locationsRepository.findAll();
        Assertions.assertThat(locations).hasSizeGreaterThan(0);

        for (Locations location: locations){
            System.out.println(location);
        }
    }

    @Test
    public void testUpdateLocations(){
        Integer locationid = 5;
        Optional <Locations> optionalLocations = locationsRepository.findById(locationid);

        Locations locations = optionalLocations.get();
        locations.setName("Galle");
        locationsRepository.save(locations);

        Locations updateLocations = locationsRepository.findById(locationid).get();
        Assertions.assertThat(updateLocations.getName()).isEqualTo("Galle");
    }

    @Test
    public void testDeleteLocations(){
        Integer locationid = 11;
        locationsRepository.deleteById(locationid);

        Optional <Locations> optionalLocations = locationsRepository.findById(locationid);
        Assertions.assertThat(optionalLocations).isNotPresent();
    }
}