package br.com.umdesenvolvedor.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.umdesenvolvedor.model.Field;

public class FieldRepository {

    List<Field> listFields = new ArrayList<>();

    public Long creat(Field entity) {
        Long newId = 1L;

        if(this.listFields.size() > 0) {
            newId = this.listFields.getLast().getId() + 1;
        }

        entity.setId(newId);
        this.listFields.add(entity);
        
        return newId;
    }

    public List<Field> list() {
        return this.listFields;
    }

    public Optional<Field> findById(Long id) {
        return this.listFields.stream().filter(f -> f.getId() == id).findFirst();
    }

}
