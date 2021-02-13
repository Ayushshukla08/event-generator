package com.example.fss.controller;

import com.example.fss.domain.Car;
import com.example.fss.service.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/")
public class ManualEvent {

    @Autowired
    private MessagePublisher messagePublisher;

    /**
     * Api to trigger an event with random city name and boolean value manually
     * @param car object
     * @return Message that event is triggered
     */
    @PostMapping("event")
    public String triggerEvent(@RequestBody Car car){
        messagePublisher.publishManualEvent(car);
        return "Manual event triggered.";
    }

}
