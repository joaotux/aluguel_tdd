package br.com.umdesenvolvedor.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Source {

    private Long id;

    private String description;

    private Boolean active;

}
