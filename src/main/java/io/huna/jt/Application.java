package io.huna.jt;

import io.huna.jt.domain.customer.Customer;
import io.huna.jt.domain.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return args -> {
            repository.save(Customer.builder()
                    .firstName("Jack")
                    .lastName("Bauer")
                    .build());
            repository.save(Customer.builder()
                    .firstName("Chloe")
                    .lastName("O'Brian")
                    .build());
            repository.save(Customer.builder()
                    .firstName("Kim")
                    .lastName("Bauer")
                    .build());
            repository.save(Customer.builder()
                    .firstName("David")
                    .lastName("Palmer")
                    .build());
            repository.save(Customer.builder()
                    .firstName("Michelle")
                    .lastName("Dessler")
                    .build());

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> log.info(customer.toString()));
            log.info("");

            // Fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("---------------------------------");
            log.info(customer.toString());
            log.info("");

            // Fetch customers by last name
            log.info("Customer found with findByLastNAme('Bauer')");
            log.info("-------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
            log.info("");
        };
    }
}
