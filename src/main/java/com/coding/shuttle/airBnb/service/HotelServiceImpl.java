package com.coding.shuttle.airBnb.service;

import com.coding.shuttle.airBnb.dto.HotelDto;
import com.coding.shuttle.airBnb.entity.Hotel;
import com.coding.shuttle.airBnb.exception.ResourceNotFoundException;
import com.coding.shuttle.airBnb.repository.HotelRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("creating a new hotel with name : {", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);       //convert hoteldto to hotel entity
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("created a new hotel with id : {}", hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting hotel with id : {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id : " + id));
        return modelMapper.map(hotel, HotelDto.class);
    }
}
