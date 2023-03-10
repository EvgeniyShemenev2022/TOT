package org.shemenev.DAO;

import org.shemenev.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong("id"));
        client.setNickname(rs.getString("nickname"));

        return client;
    }
}
