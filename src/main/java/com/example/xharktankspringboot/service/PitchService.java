package com.example.xharktankspringboot.service;

import com.example.xharktankspringboot.entity.CounterOffer;
import com.example.xharktankspringboot.entity.Pitch;
import com.example.xharktankspringboot.repository.PitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PitchService {

    @Autowired
    private PitchRepository pitchRepository;

    @Autowired
    private CounterOfferService counterOfferService;

    public List<Pitch> getAllPitches() {
        return pitchRepository.findAll();
    }

    public Pitch createPitch(Pitch pitch) {
        return pitchRepository.save(pitch);
    }

    public Optional<Pitch> getPitch(Long pitchId) {
        return pitchRepository.findById(pitchId);
    }

    public void deletePitch(Long pitchId) {
        pitchRepository.deleteById(pitchId);
    }

    public Pitch updatePitch(Long pitchId, Pitch pitch) {
        Pitch requiredPitch = pitchRepository.findById(pitchId).get();
        if(Objects.nonNull(pitch.getEntrepreneurName())) requiredPitch.setEntrepreneurName(pitch.getEntrepreneurName());
        if(Objects.nonNull(pitch.getPitchTitle())) requiredPitch.setPitchTitle(pitch.getPitchTitle());
        if(Objects.nonNull(pitch.getPitchIdea())) requiredPitch.setPitchIdea(pitch.getPitchIdea());
        if(Objects.nonNull(pitch.getAskAmount())) requiredPitch.setAskAmount(pitch.getAskAmount());
        if(Objects.nonNull(pitch.getEquityPercent())) requiredPitch.setEquityPercent(pitch.getEquityPercent());
        return pitchRepository.save(requiredPitch);
    }

    public String addCounterOfferOnPitch(Long pitchId, CounterOffer counterOffer) {
        try {
            Pitch requiredPitch = pitchRepository.findById(pitchId).get();
            counterOfferService.createOffer(requiredPitch, counterOffer);
            return "Counter offer has been added successfully";
        } catch (NoSuchElementException e) {
            return "Pitch with given id does not exist";
        }
    }
}
