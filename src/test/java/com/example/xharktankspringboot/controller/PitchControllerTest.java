package com.example.xharktankspringboot.controller;

import com.example.xharktankspringboot.entity.Pitch;
import com.example.xharktankspringboot.exception.ResourceNotFoundException;
import com.example.xharktankspringboot.service.PitchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PitchControllerTest {
    private MockMvc mockMvc;
    private static final String ROOT_PATH = "/api/v1/pitches/";

    @InjectMocks
    private transient PitchController pitchController;

    @Mock
    private transient PitchService pitchService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pitchController).build();
    }
    @Test
    void testGetAllPitches_WithResults() throws Exception {

        when(pitchService.getAllPitches()).thenReturn(Collections.singletonList(new Pitch()));

        mockMvc.perform(get(ROOT_PATH))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllPitches_WithOutResults() throws Exception {

        when(pitchService.getAllPitches()).thenReturn(Collections.emptyList());

        mockMvc.perform(get(ROOT_PATH))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPitch_WithResult() throws Exception {
        when(pitchService.getPitch(any())).thenReturn(of(Pitch.builder().pitchId(1L).build()));

        mockMvc.perform(get(ROOT_PATH + "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"data\":{\"pitchId\":1},\"statusCode\":200}"));
    }

    @Test
    public void testGetPitch_WithOutResult() throws Exception {
        when(pitchService.getPitch(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> mockMvc.perform(get(ROOT_PATH + "1"))
                .andDo(print())
                .andExpect(status().isNotFound()))
                .hasCauseInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Could not find pitch with id 1");
    }
}