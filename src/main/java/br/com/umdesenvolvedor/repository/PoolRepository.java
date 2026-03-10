package br.com.umdesenvolvedor.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.umdesenvolvedor.model.Pool;

public class PoolRepository {

    private ArrayList<Pool> listPool = new ArrayList<>();

    public List<Pool> list() {
        return this.listPool;
    }

    public Long create(Pool entity) {
        Long newId = 0L;
        var lastPool = this.listPool.getLast();

        if(Objects.nonNull(lastPool)) {
            newId = lastPool.getId();
        }

        entity.setId(newId);
        this.listPool.add(entity);
        
        return newId;
    }
}
