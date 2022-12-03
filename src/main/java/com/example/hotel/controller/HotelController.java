package com.example.hotel.controller;

import com.example.hotel.entity.Hotel;
import com.example.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {
        if (hotelRepository.existsByName(hotel.getName())) {
            return "Bunday mexmonxona avval qo'shilgan!!!";
        } else {
            hotelRepository.save(hotel);
            return "Mexmonxona qo'shildi!!!";
        }
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id) {
        if (hotelRepository.findById(id).isPresent()) {
            return hotelRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public String updateHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel hotel1 = byId.get();
            if (hotelRepository.existsByName(hotel.getName())) {
                return "Mehmonxonani o'zgartira olmisiz, chunki yangi berilayotgan nom avval foydalanilgan.";
            } else {
                hotel1.setName(hotel.getName());
                hotelRepository.save(hotel);
                return "Mexmonxona o'zgartirildi!";
            }
        } else {
            return "Bunday ID li mexmonxona yo'q";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        boolean b = hotelRepository.existsById(id);
        if (b) {
            hotelRepository.deleteById(id);
            return "Mexmonxona o'chirildi!";
        } else {
            return "Mexmonxona topilmadi";
        }
    }

}
