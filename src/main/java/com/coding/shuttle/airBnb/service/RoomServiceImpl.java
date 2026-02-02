package com.coding.shuttle.airBnb.service;

import com.coding.shuttle.airBnb.dto.RoomDto;
import com.coding.shuttle.airBnb.entity.Hotel;
import com.coding.shuttle.airBnb.entity.Room;
import com.coding.shuttle.airBnb.exception.ResourceNotFoundException;
import com.coding.shuttle.airBnb.repository.HotelRepository;
import com.coding.shuttle.airBnb.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room in hotel with Id : {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id : "+hotelId));

        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room , RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Get all rooms in hotel with id : {}",hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id : "+hotelId));
        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class)).toList();

    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("getting a room with roomID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with room id :"+ roomId));

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting a room with roomID: {}", roomId);

        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with room id :"+ roomId));
        inventoryService.deleteAllInventories(room);
        roomRepository.deleteById(roomId);
    }
}
