package com.sparrow.backend.service;

import com.sparrow.backend.dto.HotelDto;
import com.sparrow.backend.dto.HotelSearchDto;
import com.sparrow.backend.dto.NewHotelDto;
import com.sparrow.backend.dto.RoomSearchDto;
import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.PriceListItem;
import com.sparrow.backend.model.Room;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface HotelService {

    List<Hotel> findAll();

    /**
     * returns HotelDto list that contains hotel attributes extended with all hotel pricelists, extra services etc.
     */
    List<HotelDto> findAllExtended();

    Hotel getOne(Long id);

    Hotel findByName(String name);

    Hotel findByAddress(String address);

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    Hotel update(Hotel hotel);

    void delete(Hotel hotel);

    Hotel create(NewHotelDto newHotelDto);

    Set<PriceListItem> searchRooms(RoomSearchDto roomSearchDto, Long hotelId);

    Set<PriceListItem> searchRoomsByCurrentDate(Long hotelId);

    Set<PriceListItem> findPriceListItemsByDates(Long hotelId, Date tripStart, Date tripEnd);

    Hotel updateRoom(Long hotelId, Long roomId, Room room);

    List<Hotel> search(HotelSearchDto hotelSearchDto);

}
