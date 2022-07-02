package grsu.java.pets.dto;

import grsu.java.pets.model.Color;
import grsu.java.pets.model.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDto {

    private Long id;
    private String name;
    private Type petType;
    private Color petColor;
    private Integer hitPoints;
}
