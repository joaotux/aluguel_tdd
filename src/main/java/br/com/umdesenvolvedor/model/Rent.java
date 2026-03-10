package br.com.umdesenvolvedor.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.umdesenvolvedor.enumerated.RentStatus;

public class Rent {

    private Long id;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private BigDecimal priceTotal;

    private Customer customer;

    private Club club;

    private RentStatus status;

}
