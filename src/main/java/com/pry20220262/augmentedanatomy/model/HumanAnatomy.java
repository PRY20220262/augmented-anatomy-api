package com.pry20220262.augmentedanatomy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "human_anatomy")
public class HumanAnatomy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    private int organsNumber;

    @NotBlank
    @NotNull
    private String shortDetail;

    @Lob
    @NotBlank
    @NotNull
    private String detail;

    //TODO: REVISAR JSONIGNORE CUANDO SE USE DTO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private HumanAnatomy parent;
}
