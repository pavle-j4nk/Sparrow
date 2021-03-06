package com.sparrow.backend.controller;

import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.service.AddressService;
import com.sparrow.backend.service.HotelService;
import com.sparrow.backend.service.PriceListItemService;
import com.sparrow.backend.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "api/sa")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PriceListItemService priceListItemService;

    //TODO: Add Rent-a-car and flight services

    @GetMapping(value = "/hotels")
    public ResponseEntity<List<Hotel>> getHotels() {
        List<Hotel> hotels = hotelService.findAll();
        return ResponseEntity.ok(hotels);
    }

    @PostMapping(value = "/hotels")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) throws UnsupportedEncodingException {
        logger.info("Creating new hotel with following attributes: ");
        logger.info(String.format("Name: %s, \n Description: %s, \n Address: %s, \n", hotel.getName(), hotel.getDescription(), hotel.getAddress()));
        addressService.save(hotel.getAddress());
        return ResponseEntity.ok(hotelService.save(hotel));
    }

    @DeleteMapping(value = "/hotels/{id}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        if (hotel == null) {
            return (ResponseEntity<Hotel>) ResponseEntity.notFound();
        }

        logger.info(String.format("Deleting hotel %s", hotel.getName()));
        hotelService.delete(hotel);
        logger.info("Hotel deleted");

        return ResponseEntity.ok(hotel);
    }

}
