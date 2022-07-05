package com.example.xharktankspringboot.service;

import com.example.xharktankspringboot.entity.CounterOffer;
import com.example.xharktankspringboot.entity.Pitch;
import com.example.xharktankspringboot.repository.CounterOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterOfferService {
    @Autowired
    private CounterOfferRepository counterOfferRepository;

    public void createOffer(Pitch requiredPitch, CounterOffer counterOffer) {
        counterOffer.setPitch(requiredPitch);
        counterOfferRepository.save(counterOffer);
    }
}
