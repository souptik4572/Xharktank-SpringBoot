package com.example.xharktankspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pitch {
    @Id
    @SequenceGenerator(
            name = "pitch_sequence",
            sequenceName = "pitch_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pitch_sequence"
    )
    private Long pitchId;
    private String entrepreneurName;
    @Column(unique = true)
    private String pitchTitle;
    @Column(unique = true)
    private String pitchIdea;
    private Double askAmount;
    private Float equityPercent;
}
