package com.example.xharktankspringboot.controller;

import com.example.xharktankspringboot.domain.RestResponse;
import com.example.xharktankspringboot.entity.Pitch;
import com.example.xharktankspringboot.service.PitchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class PitchControllerTest {
    @Mock
    private PitchService pitchService;

    @InjectMocks
    private PitchController pitchController;

    @Test
    public void shouldReturnSuccessResponseWithPitch() {
        //Given
        when(pitchService.getPitch(any())).thenReturn(Optional.of(new Pitch()));

        //When
        RestResponse response = pitchController.getPitch(1L);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getResponse()).isInstanceOf(Pitch.class);
    }

    @Test
    public void shouldReturnNotFoundResponseWithoutPitch() {
        //Given
        when(pitchService.getPitch(any())).thenReturn(Optional.empty());

        //When
        RestResponse response = pitchController.getPitch(1L);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
        assertThat(response.getResponse()).isInstanceOf(String.class);
        assertThat(response.getResponse()).isEqualTo("There is no pitch exists with provide id.");
    }

}