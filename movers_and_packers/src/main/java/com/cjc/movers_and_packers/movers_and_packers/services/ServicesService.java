package com.cjc.movers_and_packers.movers_and_packers.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjc.movers_and_packers.movers_and_packers.repositories.ServicesRepository;

@Service
public class ServicesService {

    @Autowired
    ServicesRepository servicesRepository;;

}
