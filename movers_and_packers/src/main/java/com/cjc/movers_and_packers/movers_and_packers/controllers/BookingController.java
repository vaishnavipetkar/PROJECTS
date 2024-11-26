package com.cjc.movers_and_packers.movers_and_packers.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.movers_and_packers.movers_and_packers.entities.Booking;
import com.cjc.movers_and_packers.movers_and_packers.services.BookingService;

@RestController
@RequestMapping("/api/Booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/book_Service")
    public ResponseEntity<?> bookService(@RequestBody Booking booking){
        return new ResponseEntity<>(bookingService.bookService(booking), HttpStatus.OK);
    }

    
}
