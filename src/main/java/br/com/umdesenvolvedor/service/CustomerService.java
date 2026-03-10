package br.com.umdesenvolvedor.service;

import java.util.List;
import java.util.Optional;

import br.com.umdesenvolvedor.exception.CpfInvalidException;
import br.com.umdesenvolvedor.exception.CustomerDataInvalidException;
import br.com.umdesenvolvedor.exception.CustomerNotFoundById;
import br.com.umdesenvolvedor.exception.EmailInvalidException;
import br.com.umdesenvolvedor.model.Customer;
import br.com.umdesenvolvedor.repository.CustomerRepository;

public class CustomerService {

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Long create(Customer entity) {
        if (!entity.validDatas()) {
            throw new CustomerDataInvalidException("Dados do cliente inválidos.");
        }

        if (!entity.cpfIsValid())
            throw new CpfInvalidException("CPF inválido.");

        if (!entity.emailIsValid())
            throw new EmailInvalidException("E-mail inválido.");

        return this.repository.create(entity);
    }

    public List<Customer> listByName(String name) {
        return this.repository.listByName(name).stream().filter(c -> c.getName().contains(name)).toList();
    }

    public Optional<Customer> findById(Long id) {
        Optional<Customer> optional = this.repository.findById(id);

        if(optional.isEmpty())
            throw new CustomerNotFoundById("Cliente não localizado.");

        return optional;
    }

}
