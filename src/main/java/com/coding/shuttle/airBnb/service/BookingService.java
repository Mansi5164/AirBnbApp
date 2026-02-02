package com.coding.shuttle.airBnb.service;

import com.coding.shuttle.airBnb.dto.BookingDto;
import com.coding.shuttle.airBnb.dto.BookingRequest;
import com.coding.shuttle.airBnb.dto.GuestDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingDto initializeBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
