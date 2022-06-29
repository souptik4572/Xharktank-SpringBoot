package com.example.xharktankspringboot.repository;

import com.example.xharktankspringboot.entity.Pitch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PitchRepositoryTest {

    @Autowired
    private PitchRepository pitchRepository;

    @Test
    public void savePitch() {
        Pitch childAppPitch =
                Pitch.builder().entrepreneurName("Asta Yuno").pitchTitle(
                        "Learning App for Kids").pitchIdea("Kids will be " +
                        "able" + " to learn through this app").askAmount(5000000.00).equityPercent(12.5f).build();
        pitchRepository.save(childAppPitch);
    }

    @Test
    public void getAllPitches() {
        List<Pitch> allPitches = pitchRepository.findAll();
        System.out.println(allPitches);
    }
}
