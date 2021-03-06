package com.sparrow.backend.service;

import com.sparrow.backend.dto.CarSearchDto;
import com.sparrow.backend.dto.RentACarDto;
import com.sparrow.backend.dto.RentACarSearchDto;
import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.Dealership;
import com.sparrow.backend.model.RentACar;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RentACarService {

    List<RentACar> findAll();

    /**
     * returns HotelDto list that contains hotel attributes extended with all hotel pricelists, extra services etc.
     */

    List<RentACarDto> findAllExtended();

    RentACar getOne(Long id);

    RentACar findOne(Long id);

    RentACar findByName(String name);

    RentACar findByAddress(String address);

    RentACar findById(Long id);

    RentACar save(RentACar rentACar);

    RentACar update(RentACar rentACar , Long rentacarId);

    void delete(RentACar rentACar);

    RentACar create(RentACar newRentacarDto);

    RentACar updateDealership(Long rentACarId, Long dealershipId, Dealership dealership);

    List<RentACar> search(RentACarSearchDto rentACarSearchDto);

    Set<Car> searchCars(CarSearchDto carSearchDto, Long hotelId);
}
