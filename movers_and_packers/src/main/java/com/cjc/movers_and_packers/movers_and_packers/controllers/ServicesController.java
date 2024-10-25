package com.cjc.movers_and_packers.movers_and_packers.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.movers_and_packers.movers_and_packers.services.ServicesService;

@RestController
public class ServicesController {
    
    @Autowired
    ServicesService servicesService;
    
}
