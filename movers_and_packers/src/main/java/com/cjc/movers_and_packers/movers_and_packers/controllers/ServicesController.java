package com.cjc.movers_and_packers.movers_and_packers.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cjc.movers_and_packers.movers_and_packers.DTO.ErrorResponseDTO;
import com.cjc.movers_and_packers.movers_and_packers.entities.Services;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.ServiceNotFoundException;
import com.cjc.movers_and_packers.movers_and_packers.services.ServicesService;

@RestController
@RequestMapping("/api/services")
public class ServicesController {
    
    @Autowired
    ServicesService servicesService;
    
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Services>> getAllServices() {
    List<Services> servicesList = servicesService.getAllServices();
    return ResponseEntity.ok(servicesList);
    }

       // Method to get service by ID
    @GetMapping(value = "/{serviceId}", produces = "application/json")
    public ResponseEntity<?> getServiceById(@PathVariable Long serviceId) {
        try {
            Services service = servicesService.getServiceById(serviceId);
            return ResponseEntity.ok(service);
        } catch (ServiceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("An error occurred while fetching the service."));
        }
    }

    


}
