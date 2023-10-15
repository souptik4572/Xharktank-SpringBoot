package com.example.xharktankspringboot.controller;

import com.example.xharktankspringboot.dto.ResponseDTO;
import com.example.xharktankspringboot.entity.CounterOffer;
import com.example.xharktankspringboot.entity.Pitch;
import com.example.xharktankspringboot.exception.ResourceNotFoundException;
import com.example.xharktankspringboot.service.PitchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.xharktankspringboot.dto.ResponseDTOUtil.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/v1/pitches")
@Slf4j
public class PitchController {

    @Autowired
    private PitchService pitchService;

    @GetMapping("/{pitchId}")
    public ResponseDTO getPitch(@PathVariable("pitchId") Long pitchId) {
        return pitchService.getPitch(pitchId)
                .map(pitch -> createSuccessResponse(pitch, OK))
                .orElseThrow(() -> new ResourceNotFoundException("pitch", pitchId));
    }

    @GetMapping("/")
    public ResponseDTO getAllPitches() {
        List<Pitch> pitches = pitchService.getAllPitches();
        if (CollectionUtils.isEmpty(pitches)) {
            throw new ResourceNotFoundException("pitch");
        }

        return createSuccessResponse(pitches, OK);
    }

    @PutMapping("/")
    public Pitch createPitch(@Valid @RequestBody Pitch pitch) {
        return pitchService.createPitch(pitch);
    }

    @PatchMapping("/{pitchId}")
    public Pitch updatePitch(@PathVariable("pitchId") Long pitchId,
                             @RequestBody Pitch pitch) {
        return pitchService.updatePitch(pitchId, pitch);
    }

    @DeleteMapping("/{pitchId}")
    public String deletePitch(@PathVariable("pitchId") Long pitchId) {
        pitchService.deletePitch(pitchId);
        return "Pitch has been deleted successfully";
    }

    @PutMapping("/{pitchId}/make-offer")
    public String makeOfferOnPitch(@PathVariable("pitchId") Long pitchId,
            @Valid @RequestBody CounterOffer counterOffer) {
        return pitchService.addCounterOfferOnPitch(pitchId, counterOffer);
    }
}
