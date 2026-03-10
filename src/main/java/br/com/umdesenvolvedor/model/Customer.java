package br.com.umdesenvolvedor.model;

import static java.util.Objects.isNull;

import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode()
public class Customer {

    @Setter
    @Getter
    private Long id;

    @Getter
    private String name;

    private String cpf;

    private String rg;

    private String email;

    private String fone;

    private Address address;

    public Customer() {
    }

    public Customer(String name, String cpf, String rg, String email, String fone, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.fone = fone;
        this.address = address;
    }

    public boolean validDatas() {
        if (isNull(name) || name.isBlank() || isNull(cpf) || cpf.isBlank() || isNull(rg) || rg.isBlank()
                || isNull(email) || email.isBlank() || isNull(fone) || fone.isBlank() || isNull(address))
            return false;

        return true;
    }

    public boolean cpfIsValid() {
        if (isNull(cpf))
            return false;

        Pattern pattern = Pattern.compile("^\\d{11}$");

        return pattern.matcher(cpf).matches();
    }

    public boolean emailIsValid() {
        if (isNull(this.email))
            return false;

        int index = this.email.indexOf("@");

        if (index < 0)
            return false;

        String sufix = this.email.substring(index);
        return sufix.length() > 0;
    }

}
