package com.cjc.movers_and_packers.movers_and_packers.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.cjc.movers_and_packers.movers_and_packers.entities.Services;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.ServiceNotFoundException;
import com.cjc.movers_and_packers.movers_and_packers.repositories.ServicesRepository;

@Service
public class ServicesService {

    @Autowired
    ServicesRepository servicesRepository;

    // Method to retrieve all services
    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    // Method to retrieve service by ID
    public Services getServiceById(Long serviceId) {
        return servicesRepository.findById(serviceId)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found with ID: " + serviceId));
    }

}
