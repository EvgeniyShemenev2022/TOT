package org.shemenev.DAO;


import org.shemenev.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;


/**
 * Содержит методы:
 * 1. добавить нового клиента
 * 2. получить клиента по ID
 * 3. переименовать клиента по ID
 * 4. получить всех клиентов
 * 5. удалить клиента по ID
 * 6.
 * 7.
 */
public class ClientDAO implements ClientDAOInterface {


    // выгоднее использовать, когда у сущности много параметров
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Client> getAllClients() {
        String sql = "Select * from client";

        return namedParameterJdbcTemplate.query(sql, new ClientRowMapper());
    }

    // using RowMapper
    @Override
    public Optional<Client> getClient(long id) {
        String sql = "select * from client where client.id = :id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                sql, sqlParameterSource, new ClientRowMapper()));
//        if(client == null) {
//            return Optional.empty();
//        }
//        return Optional.of(client);

//        return Optional.ofNullable(client);
    }

    @Override
    public Long addClient(Client newClient) {
        String sql = "insert into client (nickname) values (:nickname) returning id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("nickname", newClient.getNickname());

        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Long.class);
    }

    @Override
    public void renameClient(Client client) {
        String sql = "UPDATE client SET nickname=:nickname WHERE id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", client.getId())
                .addValue("nickname", client.getNickname());

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public void deleteClient(long id) {
        String sql = "DELETE from client where id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }


}
