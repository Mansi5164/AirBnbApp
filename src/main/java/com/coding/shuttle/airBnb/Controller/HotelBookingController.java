package com.coding.shuttle.airBnb.Controller;


import com.coding.shuttle.airBnb.dto.BookingDto;
import com.coding.shuttle.airBnb.dto.BookingRequest;
import com.coding.shuttle.airBnb.dto.GuestDto;
import com.coding.shuttle.airBnb.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDto> initializeBooking(@RequestBody BookingRequest bookingRequest){
        return new ResponseEntity<>(bookingService.initializeBooking(bookingRequest), HttpStatus.CREATED);
    }

    @PostMapping("/{bookingId}/addGuest")
    public ResponseEntity<BookingDto> addGuests(@PathVariable Long bookingId,
                                                @RequestBody List<GuestDto> guestDtoList){
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guestDtoList));
    }
}
