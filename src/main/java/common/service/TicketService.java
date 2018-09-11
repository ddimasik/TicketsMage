package common.service;

import common.dto.PassengerDTO;
import common.dto.SearchDTO;
import common.model.PassengerEntity;
import common.model.Station;
import common.model.TicketEntity;
import common.repository.PassengerRepository;
import common.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketsRepository ticketsRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    /**
     *  1. create new ticketEntity
        2. reduce free seats on each station on the route from searchDTO
        3. return ticketEntity
     * */
    public TicketEntity bookTicketOnTrain(int trainId, SearchDTO searchDTO){
        TicketEntity ticketEntity = createTicket(trainId, searchDTO.getStartStationId(), searchDTO.getEndStationId());
        return ticketEntity;
    }

    public TicketEntity addPassengerToTicket(PassengerDTO passengerDTO){
        PassengerEntity passengerEntity = passengerRepository.createPassengerFromDTO(passengerDTO);
        //TODO find passengerID and add to ticket
        int passengerId = passengerRepository.
        TicketEntity ticketEntity = ticketsRepository.findById(passengerDTO.getTicketId());
        ticketEntity.setPassengerId(passengerEntity.getId());
        return ticketEntity;
    }



    public TicketEntity createTicket(int trainId, int startSationId, int endStationId){
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTrainId(trainId);
        ticketEntity.setStartStationId(startSationId);
        ticketEntity.setEndStationId(endStationId);
        ticketsRepository.addTicket(ticketEntity);
        return ticketEntity;
    }

    public TicketEntity findById(int id){return ticketsRepository.findById(id);}
}
