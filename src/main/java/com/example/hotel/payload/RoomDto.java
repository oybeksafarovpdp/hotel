package com.example.hotel.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private int number;
    private int floor;
    private int size;
    private Integer hotel_id;

}
