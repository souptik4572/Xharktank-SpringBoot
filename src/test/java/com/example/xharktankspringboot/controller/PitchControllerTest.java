package com.example.xharktankspringboot.controller;

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

import java.util.Optional;

import static com.example.xharktankspringboot.entity.Pitch.builder;
import static java.util.Collections.*;
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
        when(pitchService.getAllPitches()).thenReturn(singletonList(builder().pitchId(1L).build()));

        mockMvc.perform(get(ROOT_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"data\":[{\"pitchId\":1}],\"statusCode\":200}"));
    }

    @Test
    void testGetAllPitches_WithOutResults() {
        when(pitchService.getAllPitches()).thenReturn(emptyList());

        assertThatThrownBy(() -> mockMvc.perform(get(ROOT_PATH))
                .andDo(print())
                .andExpect(status().isNotFound()))
                .hasCauseInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Could not find any pitch with specified criteria");
    }

    @Test
    public void testGetPitch_WithResult() throws Exception {
        when(pitchService.getPitch(any())).thenReturn(of(builder().pitchId(1L).build()));

        mockMvc.perform(get(ROOT_PATH + "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"data\":{\"pitchId\":1},\"statusCode\":200}"));
    }

    @Test
    public void testGetPitch_WithOutResult() {
        when(pitchService.getPitch(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> mockMvc.perform(get(ROOT_PATH + "1"))
                .andDo(print())
                .andExpect(status().isNotFound()))
                .hasCauseInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Could not find pitch with id 1");
    }

    @Test
    void testInternalServerError() {
        when(pitchService.getPitch(any())).thenThrow(new RuntimeException());

        assertThatThrownBy(() -> mockMvc.perform(get(ROOT_PATH + "1"))
                .andDo(print())
                .andExpect(status().isInternalServerError()))
                .hasCauseInstanceOf(RuntimeException.class);
    }
}