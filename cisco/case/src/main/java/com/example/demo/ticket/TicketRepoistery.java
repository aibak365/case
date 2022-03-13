package com.example.demo.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepoistery extends JpaRepository<Ticket,Long> {
    Optional <List<Ticket>> findTicketBySecurityLevel(String level);
    Optional<List<Ticket>>findTicketByStatue(String statue);
    Optional<List<Ticket>> findTicketByTitle(String title);

}
