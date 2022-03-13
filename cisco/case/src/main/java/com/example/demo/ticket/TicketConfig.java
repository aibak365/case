package com.example.demo.ticket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class TicketConfig {
    @Bean
    CommandLineRunner commandLineRunner(TicketRepoistery repoistery)
    {
        return args -> {
            Ticket hard=new Ticket("test","test", "test","test");
            repoistery.save(hard);
        };

    }
}
