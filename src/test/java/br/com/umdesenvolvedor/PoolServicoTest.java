package br.com.umdesenvolvedor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.umdesenvolvedor.exception.PoolNotValidException;
import br.com.umdesenvolvedor.model.Pool;
import br.com.umdesenvolvedor.repository.PoolRepository;
import br.com.umdesenvolvedor.service.PoolServico;

@ExtendWith(MockitoExtension.class)
public class PoolServicoTest {

    @Mock
    PoolRepository repository;

    @InjectMocks
    PoolServico servico;

    @BeforeEach
    void setup() {}
    
    @Nested
    @DisplayName("Cenários de regra de negócio")
    class TestBusinessLogic {
    
        @Test
        @DisplayName("Validar que o cadastro está ocorrendo com sucesso.")
        void validCreatePool() {
            var pool = new Pool("Piscina 01", false, 18.0, 1.5);
            
            when(repository.create(pool)).thenReturn(1L);

            Long id = servico.creat(pool);

            assertEquals(1L, id);
        }

        @Test
        @DisplayName("Valida que a listagem está retornando dados.")
        void validReturnListAll() {
            List<Pool> listReturn = List.of(
                new Pool("Piscina 01", false, 35.0, 2.5), 
                new Pool("Piscina 02", false, 35.0, 2.5),
                new Pool("Piscina 03", true, 19.0, 0.4),
                new Pool("Piscina 04", false, 35.0, 2.5),
                new Pool("Piscina 05", true, 20.0, 0.3)
            );

            when(repository.list()).thenReturn(listReturn);

            var list = servico.list();

            assertTrue(list.size() == 5);
        }
        
    }

    @Nested
    @DisplayName("Cenários de validações")
    class TestValidations {
        
        @Test
        @DisplayName("Valida exception para cadastro com campos inválidos.")
        void validExceptionFieldsInvalids() {
            var pool = new Pool(null, false, null, null);

            PoolNotValidException exception = assertThrowsExactly(PoolNotValidException.class, () -> {
                servico.creat(pool);
            });

            assertEquals("Campos inválidos.", exception.getMessage());
        }
        
    }
}
