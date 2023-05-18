package com.pry20220262.augmentedanatomy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String url;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    @Lob
    private String detail;

    @NotNull
    private double x_scale;

    @NotNull
    private double y_scale;

    @NotNull
    private double z_scale;

    @NotNull
    private double x_position;

    @NotNull
    private double y_position;

    @NotNull
    private double z_position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "human_anatomy_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private HumanAnatomy humanAnatomy;
}
