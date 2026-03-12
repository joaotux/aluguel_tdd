package br.com.umdesenvolvedor.service;

import java.util.List;

import br.com.umdesenvolvedor.model.Field;
import br.com.umdesenvolvedor.repository.FieldRepository;

public class FieldService {

    private final FieldRepository repository;

    public FieldService(FieldRepository repository) {
        this.repository = repository;
    }

    public Long create(Field entity) {
        return this.repository.creat(entity);
    }

    public List<Field> list() {
        return this.repository.list();
    }
}
