package common.service;

import common.dto.PassengerDTO;
import common.dto.SearchDTO;
import common.dto.TicketDTO;
import common.model.PassengerEntity;
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

    @Autowired
    private TicketEntityToDtoConverter ticketEntityToDtoConverter;


    public boolean validate(PassengerDTO passengerDTO){
        boolean validationResult = false;
        PassengerEntity passengerEntity = passengerRepository.exists(passengerDTO);
        if (passengerEntity == null){
            validationResult = true;

            // ТУТ надо поискать что в этом поезеде нет такого пассажира ААА"""
        } else if (passengerDTO.getTicketId() == passengerEntity )


        return validationResult;
    }


    public TicketDTO buyTicket(PassengerDTO passengerDTO){

        PassengerEntity passengerEntity = passengerRepository.createPassengerFromDTO(passengerDTO);
        TicketEntity ticketEntity = ticketsRepository.findById(passengerDTO.getTicketId());
        addPassengerToTicket(passengerEntity, ticketEntity);
        return ticketEntityToDtoConverter.convert(ticketEntity);
    }


    /**
     *  1. create new ticketEntity
        2. reduce free seats on each station on the route from searchDTO
        3. return ticketEntity
     * */
    public TicketEntity bookTicketOnTrain(int trainId, SearchDTO searchDTO){
        return createTicket(trainId, searchDTO.getStartStationId(), searchDTO.getEndStationId());
    }

    public void addPassengerToTicket(PassengerEntity passengerEntity, TicketEntity ticketEntity ){
        ticketEntity.setPassengerId(passengerEntity.getId());
        ticketsRepository.updateTicket(ticketEntity);
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
