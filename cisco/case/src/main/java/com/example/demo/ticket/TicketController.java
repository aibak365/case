package com.example.demo.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/case")
public class TicketController {
    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("getById/{id}")
    public Ticket getTicketById(@PathVariable("id") Long id)
    {

                return ticketService.getTicketById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"there is no such this id"));

    }

    @GetMapping ("getAll")
    List<Ticket> getAllTicket()
    {
        if(ticketService.getAllticket().equals(List.of()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no ticket in the databse");
        }
        return ticketService.getAllticket();
    }

    @GetMapping("getBySecurityLevel/{level}")
    public Optional<List<Ticket>> getTicketBySecurityLevel(@PathVariable("level")  String level)
    {
        Optional<List<Ticket>> ticketByLevel = ticketService.getBySecurityLevel(level);
        if(ticketByLevel.equals(Optional.of(List.of())))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"there is no such level");
        }
        return ticketService.getBySecurityLevel(level);
    }

    @GetMapping("getByStatue/{statue}")
    public Optional<List<Ticket>> getTicketByStatue(@PathVariable  String statue)
    {
        Optional<List<Ticket>> ticketByStatue = ticketService.getTicketByStatue(statue);
        if(ticketByStatue.equals(Optional.of(List.of())))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"there is no such statue");
        }
        return ticketService.getTicketByStatue(statue);
    }

    @GetMapping("getByTitle/{title}")
    public Optional<List<Ticket>> getTicketByTicket(@PathVariable String title)
    {

        Optional<List<Ticket>> ticketByTitle = ticketService.getTicketByTitle(title);
        if(ticketByTitle.equals(Optional.of(List.of())))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"there is no such title");
        }
        return ticketByTitle;
    }

    @PutMapping("{id}")
    public void updateTicketById(
            @PathVariable("id")  long id,
            @RequestParam(required = false) String statue,
            @RequestParam(required = false) String securityLevel,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String title)
            {
                ticketService.updateTicket(id,statue,securityLevel,description,title);
            }

    @DeleteMapping(path = "{ticketId}")
    public void deleteTicket(@PathVariable("ticketId") Long id)
    {
        ticketService.deleteTicket(id);
    }

    @PostMapping
    public void addNewTicket(@RequestBody Ticket ticket)  {
        if(ticket.getDescription().length()==0)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"pls u should add some discrebtion so we can help u :D ");
        }
        else if(!ticket.getStatue().equals("done") && !ticket.getStatue().equals("in_progress") && !ticket.getStatue().equals("new"))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Pls u should choose one of those for the statue(done,in_progress,new)");
        }
        else if(!ticket.getSecurityLevel().equals("low") && !ticket.getSecurityLevel().equals("medium") && !ticket.getSecurityLevel().equals("high"))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"pls u should choose one of those for the securitylevel(low,medium,high)");
        }
        else
        {
            if(ticket.getTitle()==null || ticket.getTitle().length()==0)
            {
                ticket.setTitle("noTitleFromTheUser");
            }
            ticketService.addNewTicket(ticket);
        }

    }

    @GetMapping("/help")
    public String help()
    {
        return ticketService.help();
    }

}
