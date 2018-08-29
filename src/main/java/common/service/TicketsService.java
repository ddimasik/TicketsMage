package common.service;

import common.model.Ticket;
import common.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketsService {

    @Autowired
    private TicketsRepository ticketsRepository;

    public List<Ticket> findAll(){

        return ticketsRepository.findAll();
    }
}
