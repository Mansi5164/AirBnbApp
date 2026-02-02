package com.coding.shuttle.airBnb.service;

import com.coding.shuttle.airBnb.dto.HotelDto;
import com.coding.shuttle.airBnb.dto.HotelSearchRequest;
import com.coding.shuttle.airBnb.entity.Hotel;
import com.coding.shuttle.airBnb.entity.Inventory;
import com.coding.shuttle.airBnb.entity.Room;
import com.coding.shuttle.airBnb.exception.ResourceNotFoundException;
import com.coding.shuttle.airBnb.repository.InventoryRepository;
import com.coding.shuttle.airBnb.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public void initializeRoomForAYear(Room room) {
        Room managedRoom = roomRepository
                .findById(room.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with id : "+room.getId()));

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for(;!today.isAfter(endDate);today=today.plusDays(1)){
            Inventory inventory = Inventory.builder()
                    .hotel(managedRoom.getHotel())
                    .room(managedRoom)
                    .bookedCount(0)
                    .reservedCount(0)
                    .city(managedRoom.getHotel().getCity())
                    .date(today)
                    .price(managedRoom.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(managedRoom.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        log.info("Deleting the inventories with room id : {}", room.getId());
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("searching hotels for {} city, from {} to {}", hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate());
        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(),
                                            hotelSearchRequest.getSize());
        Long dateCount = ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(),
                                                hotelSearchRequest.getEndDate()) + 1;
        Page<Hotel> hotelPage = inventoryRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(),
                hotelSearchRequest.getStartDate(),
                hotelSearchRequest.getEndDate(),
                hotelSearchRequest.getRoomCount(),
                dateCount,
                pageable);

        return hotelPage.map((element) -> modelMapper.map(element, HotelDto.class));
    }
}
