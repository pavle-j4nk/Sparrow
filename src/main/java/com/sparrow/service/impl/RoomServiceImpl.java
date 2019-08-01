package com.sparrow.service.impl;

import com.sparrow.model.Room;
import com.sparrow.repository.RoomRepository;
import com.sparrow.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }
}
