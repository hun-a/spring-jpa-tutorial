package io.huna.jt.domain.customer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Component
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerRepositoryTest {

    private final String FIRST_NAME = "Jack";

    private final String LAST_NAME = "Bauer";

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void init() {
        customerRepository.save(Customer.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Chloe")
                .lastName("O'Brian")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Kim")
                .lastName("Bauer")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("David")
                .lastName("Palmer")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Michelle")
                .lastName("Dessler")
                .build());
    }

    @PreDestroy
    public void cleanUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        // given
        Long id = 1L;

        // when
        Optional<Customer> found = customerRepository.findById(id);

        // then
        assertEquals(found.get().getId(), id);
    }

    @Test
    public void testFindByLastName() {
        // given
        // when
        List<Customer> customerList = customerRepository.findByLastName(LAST_NAME);

        // then
        customerList.forEach(found -> {
            assertEquals(found.getLastName(), LAST_NAME);
        });
    }

}