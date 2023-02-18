package org.shemenev.DAO;


import org.shemenev.model.TimeLine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


// не совсем понятно как работать с clientID;  TODO
public class TimeLineRowMapper implements RowMapper<TimeLine> {
    @Override
    public TimeLine mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimeLine timeLine = new TimeLine();
        timeLine.setId(rs.getLong("id"));
        timeLine.setStartAt(rs.getTimestamp("startat").toLocalDateTime());
        timeLine.setActivity(rs.getString("activity"));
        timeLine.setComment(rs.getString("comment"));
        timeLine.setClient_id(rs.getLong("client_id"));

        return timeLine;
    }
}
