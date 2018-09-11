package common.service;

import common.dto.TicketDTO;
import common.model.TicketEntity;

public interface TicketEntityToDtoConverter {

    TicketDTO convert(TicketEntity ticketEntity);
}
