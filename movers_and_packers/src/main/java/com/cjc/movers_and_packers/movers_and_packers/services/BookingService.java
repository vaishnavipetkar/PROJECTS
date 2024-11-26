package com.cjc.movers_and_packers.movers_and_packers.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.movers_and_packers.movers_and_packers.entities.Booking;
import com.cjc.movers_and_packers.movers_and_packers.repositories.BookingRepository;
import com.cjc.movers_and_packers.movers_and_packers.repositories.UserRepository;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired 
    UserRepository userRepository;

    public Object bookService(Booking booking) {

        //User booking starts with service selection
        Booking book = new Booking();
        

        return null;
    }
    
}
