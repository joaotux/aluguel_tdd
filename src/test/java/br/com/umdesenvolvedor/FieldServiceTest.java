package br.com.umdesenvolvedor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.umdesenvolvedor.enumerated.FieldTypeEnum;
import br.com.umdesenvolvedor.model.Field;
import br.com.umdesenvolvedor.repository.FieldRepository;
import br.com.umdesenvolvedor.service.FieldService;

@ExtendWith(MockitoExtension.class)
public class FieldServiceTest {

    @Mock
    FieldRepository repository;

    @InjectMocks
    FieldService service;

    Field field;

    @BeforeEach
    void startup() {
        field = Field.builder()
                .size(50.0)
                .type(FieldTypeEnum.SOCCER)
                .build();

        field.setDescription("Campo Futebol sintético");
        field.setActive(true);
    }

    @Nested
    @DisplayName("Cenários de regra de negócio")
    class TestBusinessLogic {

        @Test
        @DisplayName("Deve salvar um novo cadastro com sucesso.")
        void validCreateNewField() {
            when(repository.creat(field)).thenReturn(1L);

            Long newId = service.create(field);

            assertThat(newId).as("Deve ser igual á").isEqualTo(1L);
        }

        @Test
        @DisplayName("Deve retornar todos os itens da lista.")
        void validReturnList() {
            var listMock = List.of(
                Field.newField("Campo futebol sintético"), 
                Field.newField("Campo volei de áreia"), 
                Field.newField("Campo volei de áreia 2"), 
                Field.newField("Campo futebol gramado")
            );

            when(repository.list()).thenReturn(listMock);

            var listReturn = service.list();

            assertThat(listReturn).extracting(Field::getDescription).contains("Campo volei de áreia");
            assertThat(listReturn).as("Deve ter quantidade 3").hasSize(4);
        }

    }

    @Nested
    @DisplayName("Cenários de validações")
    class TestsValidations {

    }

}
