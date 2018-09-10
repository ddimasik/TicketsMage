package common.service;

import common.model.PassengerEntity;
import common.model.Station;
import common.model.TicketEntity;
import common.model.TrainEntity;
import common.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketsRepository ticketsRepository;

    public void addTicket(TrainEntity trainEntity, PassengerEntity passengerEntity, Station startSation, Station endStation){
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTrainId(trainEntity.getId());
        ticketEntity.setPassengerId(passengerEntity.getId());
        ticketEntity.setStartStationId(startSation.getId());
        ticketEntity.setEndStationId(endStation.getId());
        ticketsRepository.addTicket(ticketEntity);
    }

    public TicketEntity findById(int id){
        return ticketsRepository.findById(id);
    }}
