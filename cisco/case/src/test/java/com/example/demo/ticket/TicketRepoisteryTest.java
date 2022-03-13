package com.example.demo.ticket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TicketRepoisteryTest {
    @Autowired
    private TicketRepoistery underTest;
    @Test
    void findTicketBySecurityLevel() {
        //low
        Ticket ticket1=new Ticket("test","test","low","done");
        Ticket ticket2=new Ticket("test","test","low","done");
        underTest.save(ticket1);
        underTest.save(ticket2);
        //given
        Optional<List<Ticket>> actual=underTest.findTicketBySecurityLevel("low");
        Optional<List<Ticket>> except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertEquals(except,actual);

        //medium
        ticket1.setSecurityLevel("medium");
        ticket2.setSecurityLevel("medium");
        //given
         actual=underTest.findTicketBySecurityLevel("medium");
         except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertEquals(except,actual);

        //high
        ticket1.setSecurityLevel("high");
        ticket2.setSecurityLevel("high");
        //given
        actual=underTest.findTicketBySecurityLevel("high");
        except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertEquals(except,actual);

        //notEqual
        //given
        actual=underTest.findTicketBySecurityLevel("low");
        except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertNotEquals(except,actual);

    }

    @Test
    void findTicketByStatue() {
        //new
        Ticket ticket1=new Ticket("test","test","low","new");
        Ticket ticket2=new Ticket("test","test","low","new");
        underTest.save(ticket1);
        underTest.save(ticket2);
        //given
        Optional<List<Ticket>> actual=underTest.findTicketByStatue("new");
        Optional<List<Ticket>> except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertEquals(except,actual);

        //done
        ticket1.setStatue("done");
        ticket2.setStatue("done");
        //given
        actual=underTest.findTicketByStatue("done");
        except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertEquals(except,actual);

        //in_progress
        ticket1.setStatue("in_progress");
        ticket2.setStatue("in_progress");
        //given
        actual=underTest.findTicketByStatue("in_progress");
        except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertEquals(except,actual);

        //notEqual
        //given
        actual=underTest.findTicketBySecurityLevel("done");
        except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertNotEquals(except,actual);
    }

    @Test
    void findTicketByTitle() {
        //low
        Ticket ticket1=new Ticket("test1","test","low","done");
        Ticket ticket2=new Ticket("test1","test","low","done");
        underTest.save(ticket1);
        underTest.save(ticket2);
        //given
        Optional<List<Ticket>> actual=underTest.findTicketByTitle("test1");
        Optional<List<Ticket>> except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertEquals(except,actual);

        //notEqual
        //given
        actual=underTest.findTicketByTitle("null");
        except=Optional.of(List.of(ticket1,ticket2));
        //assert
        assertNotEquals(except,actual);
    }
}