package common.service;

import common.dto.TicketDTO;
import common.model.TicketEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TicketEntityToDtoConverterImpl implements TicketEntityToDtoConverter, Converter<TicketEntity, TicketDTO> {

    @Override
    public TicketDTO convert(TicketEntity ticketEntity) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticketEntity.getId());
        ticketDTO.setTrainId(ticketEntity.getTrainId());
        ticketDTO.setPassengerId(ticketEntity.getPassengerId());
        ticketDTO.setStartStationId(ticketEntity.getStartStationId());
        ticketDTO.setEndStationId(ticketEntity.getEndStationId());
        return ticketDTO;
    }
}
