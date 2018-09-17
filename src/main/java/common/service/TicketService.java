package common.service;

import common.dto.PassengerDTO;
import common.dto.SearchDTO;
import common.dto.TicketDTO;
import common.model.PassengerEntity;
import common.model.TicketEntity;
import common.repository.PassengerRepository;
import common.repository.TicketsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TicketService {

    private final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketsRepository ticketsRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TicketEntityToDtoConverter ticketEntityToDtoConverter;

    @Autowired
    private RouteService routeService;


    public boolean validate(PassengerDTO passengerDTO){

        List<PassengerEntity> passengerEntityList = passengerRepository.findPassengerByDTO(passengerDTO);
        if (passengerEntityList.isEmpty()){
            return true;

        } else if (passengerEntityList.size() > 1){
            //TODO catch error
            // Exception: result returns more than one elements.
            // org.hibernate.jpa.internal.QueryImpl.getSingleResult(QueryImpl.java:505)
            // common.repository.PassengerRepository.findPassengerByDTO(PassengerRepository.java:32)

            logger.debug("ERRRRROR: PassengerEntity {} not unique", passengerEntityList.get(0).getId() );

            return false;
        }
        return (!ticketsRepository.checkIfPassengerExistsOnTrain(passengerEntityList.get(0), passengerDTO.getTicketId()));
    }

    public TicketDTO buyTicket(PassengerDTO passengerDTO){

        List<PassengerEntity> passengerEntityList = passengerRepository.findPassengerByDTO(passengerDTO);
        PassengerEntity passengerEntity;
        if (passengerEntityList.isEmpty()) {
            passengerEntity = passengerRepository.createPassengerFromDTO(passengerDTO);
        } else {
            passengerEntity = passengerEntityList.get(0);
        }
        TicketEntity ticketEntity = ticketsRepository.findById(passengerDTO.getTicketId());
        addPassengerToTicket(passengerEntity, ticketEntity);
        return ticketEntityToDtoConverter.convert(ticketEntity);
    }

    /**
     *  1. create new ticketEntity
        2. reduce free seats on each station on the route from searchDTO
        3. return ticketEntity
     * */
    public int bookTicketOnTrain(int trainId, SearchDTO searchDTO){

        if (routeService.checkIfIsFreeSeatOnEachStation(trainId, searchDTO)) {
            return createTicket(trainId, searchDTO).getId();
        } else {
            return -1;
        }
    }

    public void addPassengerToTicket(PassengerEntity passengerEntity, TicketEntity ticketEntity ){
        ticketEntity.setPassengerId(passengerEntity.getId());
        ticketsRepository.updateTicket(ticketEntity);
    }

    public TicketEntity createTicket(int trainId, SearchDTO searchDTO){
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTrainId(trainId);
        ticketEntity.setStartStationId(searchDTO.getStartStationId());
        ticketEntity.setEndStationId(searchDTO.getEndStationId());
        ticketsRepository.addTicket(ticketEntity);
        routeService.decreaseFreeSeats(trainId, searchDTO);
        return ticketEntity;
    }

    public TicketEntity findById(int id){return ticketsRepository.findById(id);}
}
