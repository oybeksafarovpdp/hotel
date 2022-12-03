package com.example.hotel.controller;

import com.example.hotel.entity.Hotel;
import com.example.hotel.entity.Room;
import com.example.hotel.payload.RoomDto;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("/room")
public class RoomController {

    @GetMapping
    public Page<Room> getAllRoomByPage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {

        Optional<Hotel> byId = hotelRepository.findById(roomDto.getHotel_id());
        if (!byId.isPresent()) return "Bunday mehmonxona yo'q!!";
        Hotel hotel = byId.get();
        boolean b = roomRepository.existsByNumberAndHotel_Id(roomDto.getNumber(), hotel.getId());
        if (b) return "Bu mehmonxonada bunday xona bor!!!";
        Room room = new Room();
        room.setHotel(hotel);
        room.setSize(roomDto.getSize());
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        roomRepository.save(room);
        return "Xona qo'shildi!!!";
    }


}
