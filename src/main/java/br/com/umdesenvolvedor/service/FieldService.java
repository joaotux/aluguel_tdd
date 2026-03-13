package br.com.umdesenvolvedor.service;

import java.util.List;
import java.util.Optional;

import br.com.umdesenvolvedor.exception.FieldWithDatasInvalidsException;
import br.com.umdesenvolvedor.model.Field;
import br.com.umdesenvolvedor.repository.FieldRepository;

public class FieldService {

    private final FieldRepository repository;

    public FieldService(FieldRepository repository) {
        this.repository = repository;
    }

    public Long create(Field entity) {
        if(!entity.isValid())
            throw new FieldWithDatasInvalidsException("Dados inválidos.");

        return this.repository.creat(entity);
    }

    public List<Field> list() {
        return this.repository.list();
    }

    public Optional<Field> findById(Long id) {
        return this.repository.findById(id);
    }
}
