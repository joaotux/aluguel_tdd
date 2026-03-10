package br.com.umdesenvolvedor.service;

import java.util.List;

import br.com.umdesenvolvedor.exception.PoolNotValidException;
import br.com.umdesenvolvedor.model.Pool;
import br.com.umdesenvolvedor.repository.PoolRepository;

public class PoolServico {

    final private PoolRepository repository;

    public PoolServico(PoolRepository repository) {
        this.repository = repository;
    }

    public List<Pool> list() {
        return this.repository.list();
    }

    public Long creat(Pool entity) {
        if(!entity.isValid()) 
            throw new PoolNotValidException("Campos inválidos.");

        return this.repository.create(entity);
    }

}
