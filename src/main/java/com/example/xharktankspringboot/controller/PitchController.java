package com.example.xharktankspringboot.controller;

import com.example.xharktankspringboot.dto.ResponseDTO;
import com.example.xharktankspringboot.dto.ResponseDTOUtil;
import com.example.xharktankspringboot.entity.CounterOffer;
import com.example.xharktankspringboot.entity.Pitch;
import com.example.xharktankspringboot.service.PitchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/pitches")
@Slf4j
public class PitchController {

    @Autowired
    private PitchService pitchService;

    @GetMapping("/{pitchId}")
    private Optional<Pitch> getPitch(@PathVariable("pitchId") Long pitchId) {
        return pitchService.getPitch(pitchId);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllPitches() {
        try {
            List<Pitch> pitches = pitchService.getAllPitches();
            if (CollectionUtils.isEmpty(pitches)) {
                return ResponseEntity.ok(ResponseDTOUtil.createEmptySuccessResponse("No pitches found"));
            }
            return ResponseEntity.ok(ResponseDTOUtil.createSuccessResponse(pitches, "Pitches fetched successfully"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ResponseDTOUtil.createErrorResponse("Error fetching pitches", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
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
