package com.sparrow.service;

import com.sparrow.dto.HotelSearchDto;
import com.sparrow.dto.NewHotelDto;
import com.sparrow.model.Hotel;
import com.sparrow.model.Room;

import java.util.List;

public interface HotelService {

    List<Hotel> findAll();

    Hotel getOne(Long id);

    Hotel findOne(Long id);

    Hotel findByName(String name);

    Hotel findByAddress(String address);

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    Hotel update(Hotel hotel);

    void delete(Hotel hotel);

    Hotel create(NewHotelDto newHotelDto);

    Hotel updateRoom(Long hotelId, Long roomId, Room room);

    List<Hotel> search(HotelSearchDto hotelSearchDto);

}
