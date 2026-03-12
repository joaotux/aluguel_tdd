package br.com.umdesenvolvedor.model;

import br.com.umdesenvolvedor.enumerated.FieldTypeEnum;
import lombok.Builder;

@Builder
public class Field extends Source {

    private Double size;

    private FieldTypeEnum type;

    public static Field newField(String description) {
        var field = Field.builder()
                .size(50.0)
                .type(FieldTypeEnum.SOCCER)
                .build();

        field.setDescription(description);
        field.setActive(true);

        return field;
    }
    
}
