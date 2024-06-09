package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService bookingService;
    private static final String USER_ID_HEADER = "X-Sharer-User-Id";

    @PostMapping
    public Booking addBookingRequest(@RequestHeader(USER_ID_HEADER) long userId,
                                     @RequestBody BookingDto bookingDto) {
        log.info("Request for booking item {} from user {}", bookingDto.getItemId(), userId);
        return bookingService.createBooking(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public Booking updateItem(@RequestHeader(USER_ID_HEADER) long userId,
                           @PathVariable long bookingId,
                           @RequestParam boolean approved) {
        if (approved) {
            log.info("Request for approving booking request {} from user {}",
                    bookingId, userId);
        } else {
            log.info("Request for rejection booking request {} from user {}",
                    bookingId, userId);
        }
        return bookingService.updateBooking(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@RequestHeader(USER_ID_HEADER) long userId, @PathVariable long bookingId) {
        log.info("Request for get booking request {} from user {}", bookingId, userId);
        return bookingService.getBookingById(userId, bookingId);
    }

    @GetMapping
    public List<Booking> getBookingsByState(@RequestHeader(USER_ID_HEADER) long userId,
                                            @RequestParam(required = false) String state,
                                            @RequestParam(required = false) Integer from,
                                            @RequestParam(required = false) Integer size)  {
        log.info("Request for get {} bookings in state {} from user {} from {}", size, state, userId, from);
        return bookingService.getBookingByState(userId, state, from, size);
    }

    @GetMapping("/owner")
    public List<Booking> getBookingsByOwnerAndState(@RequestHeader(USER_ID_HEADER) long userId,
                                                    @RequestParam(required = false) String state,
                                                    @RequestParam(required = false) Integer from,
                                                    @RequestParam(required = false) Integer size)  {
        log.info("Request for get {} bookings of user {} in state {} from {}", size, userId, state, from);
        return bookingService.getBookingsByOwnerAndState(userId, state, from, size);
    }
}