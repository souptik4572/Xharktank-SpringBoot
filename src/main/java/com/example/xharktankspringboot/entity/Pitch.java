package com.example.xharktankspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private long id;
    @Column(name = "entrepreneur_name")
    private String entrepreneurName;
    @Column(name = "pitch_title", unique = true)
    private String pitchTitle;
    @Column(name = "pitch_idea", unique = true)
    private String pitchIdea;
    @Column(name = "ask_amount")
    private Double askAmount;
    @Column(name = "equity_percent")
    private Float equityPercent;
}
