package com.pry20220262.augmentedanatomy.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "human_anatomy")
public class HumanAnatomy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    private int organs_number;

    @NotBlank
    @NotNull
    private String short_detail;

    @Lob
    @NotBlank
    @NotNull
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private HumanAnatomy parent;
}
