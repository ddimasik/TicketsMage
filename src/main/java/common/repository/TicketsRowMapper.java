package common.repository;

import common.model.Ticket;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TicketsRowMapper implements RowMapper<Ticket> {
    public Ticket mapRow(ResultSet resultSet, int RowNum) throws SQLException {

        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt(1));
        ticket.setTrain_id(resultSet.getInt(2));
        ticket.setPassenger_id(resultSet.getInt(3));
        ticket.setStartStation_id(resultSet.getInt(4));
        ticket.setEndStation_id(resultSet.getInt(5));
        return ticket;
    }
}
