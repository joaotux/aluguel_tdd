package br.com.umdesenvolvedor.model;

import static java.util.Objects.isNull;

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

    public boolean isValid() {
        if(isNull(size) || isNull(type) || isNull(getDescription()) || getDescription().isBlank())
            return false;

        return true;
    }
    
}
