package com.example.demo.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepoistery ticketRepoistery;
    @Autowired
    public TicketService(TicketRepoistery ticketRepoistery) {
        this.ticketRepoistery = ticketRepoistery;
    }

    public Optional<Ticket> getTicketById(Long id)
    {
        return ticketRepoistery.findById(id);
    }

    public void addNewTicket(Ticket ticket) {
        ticketRepoistery.save(ticket);
    }
    public String deleteTicket(Long id) {
        boolean exisist=ticketRepoistery.existsById(id);
        if(!exisist)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"there no ticket in with this id");
        }
        ticketRepoistery.deleteById(id);
        return "deleted";

    }
    @Transactional
    public void updateTicket(long id, String statue, String securityLevel, String description, String title)  {
        Ticket ticket=ticketRepoistery.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"this id is not exsist"));
        if(statue!=null && statue.length()>0 && (statue.equals("new") || statue.equals("done") || statue.equals("in_progress") ))
        {
            ticket.setStatue(statue);
        }
        else if(statue!=null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"pls u should insert a one of those (new,done,in_progress)");
        }

        if(securityLevel!=null && securityLevel.length()>0 && (securityLevel.equals("low") || securityLevel.equals("medium") || securityLevel.equals("high")))
        {
            ticket.setSecurityLevel(securityLevel);
        }
        else if(securityLevel!=null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"pls insert a one of those (low,medium,high)");
        }
        if(description!=null && description.length()>0)
        {
            ticket.setDescription(description);
        }
        else if(description!=null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"pls the write something in description so we can help u ^_^");
        }
        if(title!=null)
        {
            ticket.setTitle(title);
        }
    }

    public List<Ticket> getAllticket() {
        return ticketRepoistery.findAll();
    }

    public Optional<List<Ticket>> getBySecurityLevel(String level) {
        return ticketRepoistery.findTicketBySecurityLevel(level);
    }



    public Optional<List<Ticket>> getTicketByStatue(String statue) {
        return ticketRepoistery.findTicketByStatue(statue);
    }

    public Optional<List<Ticket>> getTicketByTitle(String title) {
        return ticketRepoistery.findTicketByTitle(title);
    }

    public String help() {
        return "Hello on my ticket managment System ^_^,\n if u want the database write this in url http://localhost:8080/h2-ui/ \n" +
                " if u want the API docs pls write this http://localhost:8080/swagger-ui/index.html \n take care ^_^ |||||||| also pls note if u want to make" +
                "post request pls don`t put id and creationTime on your body request we take care of that also ;)" ;
    }
}
