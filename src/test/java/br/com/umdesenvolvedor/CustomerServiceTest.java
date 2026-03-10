package br.com.umdesenvolvedor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.umdesenvolvedor.exception.CpfInvalidException;
import br.com.umdesenvolvedor.exception.CustomerDataInvalidException;
import br.com.umdesenvolvedor.exception.CustomerNotFoundById;
import br.com.umdesenvolvedor.exception.EmailInvalidException;
import br.com.umdesenvolvedor.model.Address;
import br.com.umdesenvolvedor.model.Customer;
import br.com.umdesenvolvedor.repository.CustomerRepository;
import br.com.umdesenvolvedor.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository repository;

    @InjectMocks
    CustomerService service;

    Customer customer;

    Address address;

    @BeforeEach
    void setup() {
        address = new Address("Seringueiras", "RO", "Goveia Sales", "Ilha Nova", "77658934");
        customer = new Customer("João", "01550582231", "888888", "joao@gmail.com", "12123123", address);
    }

    @Nested
    @DisplayName("Cenários de regra de negócio")
    class testSuccess {
        @Test
        @DisplayName("Validar se customer foi cadastrado com sucesso e retornou o novo id.")
        void createNewCustomer() {
            when(repository.create(customer)).thenReturn(1L);

            Long newId = service.create(customer);

            assertEquals(1L, newId);
        }

        @Test
        @DisplayName("Validar que o método está retornando um customer.")
        void validCustomerFoundById() {
            when(repository.findById(1L)).thenReturn(Optional.of(customer));

            Optional<Customer> optional = service.findById(1L);

            assertTrue(optional.isPresent());
            assertEquals("João", optional.get().getName());
        }

        @Test
        @DisplayName("Deve validar a busca de consumers por nome na lista.")
        void validListCustomerByName() {
            var customer01 = new Customer("João", "01550582231", "888888", "joao@gmail.com", "12123123", address);
            var customer02 = new Customer("Maria", "01550582231", "888888", "joao@gmail.com", "12123123", address);
            var customer03 = new Customer("Renata", "01550582231", "888888", "joao@gmail.com", "12123123", address);
            var customer04 = new Customer("Marilene", "01550582231", "888888", "joao@gmail.com", "12123123", address);

            var listMock = List.of(customer01, customer02, customer03, customer04);

            var nameFilter = "Mar";

            when(repository.listByName(nameFilter)).thenReturn(listMock);

            var listReturn = service.listByName(nameFilter);

            assertTrue(listReturn.size() == 2);
        }
    }

    @Nested
    @DisplayName("Cenários de Validação")
    class groupTest {
        @Test
        @DisplayName("Validar se o método está lançando a exceção para e-mail inválido.")
        void validExceptionEmailInvalid() {
            Customer customer = new Customer("João", "01550582231", "888888", "joaogmail.com", "12123123", address);

            EmailInvalidException exception = assertThrows(EmailInvalidException.class, () -> {
                service.create(customer);
            });

            assertEquals("E-mail inválido.", exception.getMessage());
        }

        @Test
        @DisplayName("Validar exception para dados inválidos.")
        void validExceptionDatasInvalid() {
            var customer = new Customer();

            CustomerDataInvalidException exception = assertThrows(CustomerDataInvalidException.class, () -> {
                service.create(customer);
            });

            assertEquals("Dados do cliente inválidos.", exception.getMessage());
        }

        @Test
        @DisplayName("Validar se o método está lançando a exceção de CPF inválido.")
        void validExcptionCpfInvalid() {
            var customer = new Customer("João", "1232", "888888", "joao@gmail.com", "12123123", address);

            CpfInvalidException exception = assertThrows(CpfInvalidException.class, () -> {
                service.create(customer);
            });

            assertEquals("CPF inválido.", exception.getMessage());
        }

        @Test
        @DisplayName("Valida se o método está lançando a exceção para usuário não localizado pelo ID.")
        void validExceptionCustomerNotFoundById() {
            when(repository.findById(1L)).thenReturn(Optional.empty());

            CustomerNotFoundById exception = assertThrows(CustomerNotFoundById.class, () -> {
                service.findById(1L);
            });

            assertEquals("Cliente não localizado.", exception.getMessage());
        }

        @Test
        @DisplayName("Validar que os métodos de validação estão sendo chamados.")
        void validCallMethodsValidation() {
            Customer customerSpy = Mockito.spy(customer);

            when(repository.create(customerSpy)).thenReturn(1L);

            service.create(customerSpy);

            verify(customerSpy).validDatas();

            verify(customerSpy).cpfIsValid();

            verify(customerSpy).emailIsValid();

            verify(repository, times(1)).create(customerSpy);
        }
    }

}
