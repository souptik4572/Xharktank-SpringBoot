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
public class CounterOffer {
    @Id
    @SequenceGenerator(
            name = "counter_offer_sequence",
            sequenceName = "counter_offer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "counter_offer_sequence"
    )
    private Long counterOfferId;
    private String investorName;
    private Long amount;
    private Float equityPercent;
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "pitch_id",
            referencedColumnName = "pitchId"
    )
    private Pitch pitch;
}
