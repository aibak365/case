package com.example.demo.ticket;

import com.google.common.base.Verify;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Mock
    private TicketRepoistery ticketRepoistery;
    private AutoCloseable autoCloseable;
    private MockMvc mockMvc;
    @InjectMocks
    private TicketService underTest;
    Ticket ticket;
    @BeforeEach
    void beforeEach()
    {
        ticket =new Ticket("test","test","low","done");
        underTest=new TicketService(ticketRepoistery);

    }
    @Test
    void getTicketById() {
        //when
        underTest.getTicketById(1L);
        //then
        verify(ticketRepoistery).findById(1L);

    }

    @Test
    void addNewTicket() {
        //given
        Ticket ticket=new Ticket("text","text","low","done");
        //when
        underTest.addNewTicket(ticket);
        //then
        ArgumentCaptor<Ticket> ticketArgumentCaptor=ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepoistery).save(ticketArgumentCaptor.capture());
        Ticket capturedTicket=ticketArgumentCaptor.getValue();
        assertThat(capturedTicket).isEqualTo(ticket);
    }




    @Test
    void getAllticket() {
        //when
        underTest.getAllticket();
        //then
        verify(ticketRepoistery).findAll();
    }

    @Test
    void getBySecurityLevel() {
        //when
        underTest.getBySecurityLevel("low");
        //then
        verify(ticketRepoistery).findTicketBySecurityLevel("low");
    }

    @Test
    void getTicketByStatue() {
        //then
        underTest.getTicketByStatue("new");
        //then
        verify(ticketRepoistery).findTicketByStatue("new");
    }
    @Test
    void getTicgeketByTitle() {
        //then
        underTest.getTicketByTitle("test");
        //then
        verify(ticketRepoistery).findTicketByTitle("test");
    }


}