package br.com.umdesenvolvedor.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.umdesenvolvedor.model.Customer;

public class CustomerRepository {
    private List<Customer> listCustomer = new ArrayList<>();

    public Long create(Customer entity) {
        Long newId = getNewId();

        entity.setId(newId);
        this.listCustomer.add(entity);

        return newId;
    }

    public Long getNewId() {
        var lastEntity = this.listCustomer.getLast();
        Long newId = Objects.isNull(lastEntity) ? 1L : lastEntity.getId() + 1;
        return newId;
    }

    public Optional<Customer> findById(Long id) {
        return this.listCustomer.stream().filter(c -> c.getId() == id).findAny();
    }

    public List<Customer> listByName(String name) {
        return this.listCustomer;
    }
}
