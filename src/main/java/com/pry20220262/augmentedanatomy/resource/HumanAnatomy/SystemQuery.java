package com.pry20220262.augmentedanatomy.resource.HumanAnatomy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class SystemQuery {
    private Long id;

    private String name;
}
