package org.shemenev.DAO;


import org.shemenev.model.TimeLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * Содержит методы:
 * 1. добавить новую строку
 * 2. корректировать строку (поля activity & comment)
 * 3. получить все строки за СЕГОДНЯ по ID клиента
 * 4. получить все строки по ID клиента (за все время)
 * 5. получить все строки по ID клиента в УКАЗАННУЮ дату (dd/MM/yyyy)
 * 6. получить строку по ID строки
 * 7. удалить строку по ID строки
 * 8.
 * 9.
 */
@Repository
public class TimeLineDAO implements TimeLineDAOInterface {

    // выгоднее использовать, когда у сущности много параметров
    private final NamedParameterJdbcTemplate template;

    @Autowired
    public TimeLineDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }


    @Override
    public Long addTimeLine(TimeLine newTimeline) {
        String sql = "Insert INTO timeline (activity, comment, client_id) values (:activity, :comment, :client_id) returning id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("activity", newTimeline.getActivity())
                .addValue("comment", newTimeline.getComment())
                .addValue("client_id", newTimeline.getClient_id());

        return template.queryForObject(sql, sqlParameterSource, Long.class);
    }

    @Override
    public void correctTimeLine(TimeLine correctedTimeLine) {
        String sql = "UPDATE timeline SET activity=:activity, comment=:comment WHERE id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("activity", correctedTimeLine.getActivity())
                .addValue("comment", correctedTimeLine.getComment())
                .addValue("id", correctedTimeLine.getId());

        template.update(sql, sqlParameterSource);
    }

    @Override
    public List<TimeLine> getAllTimeLinesByClientIdToday(long id) {
        String sql = "SELECT * FROM timeline WHERE startat >= date_trunc('day', now()) and client_id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);

        return template.query(sql, sqlParameterSource, new TimeLineRowMapper());
    }

    @Override
    public List<TimeLine> getAllTimeLinesByClientID(long id) {
        String sql = "SELECT * FROM timeline WHERE client_id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);

        return template.query(sql, sqlParameterSource, new TimeLineRowMapper());
    }

    @Override
    public List<TimeLine> getAllTimeLinesOnSpecifiedDate(Date specifiedDate, long id) {
        String sql = "select * from timeline where  date(startat) = :specifiedDate and client_id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("specifiedDate", specifiedDate)
                .addValue("id", id);

        return template.query(sql, sqlParameterSource, new TimeLineRowMapper());
    }

    @Override
    public TimeLine getTimeLineById(long id) {
        String sql = "SELECT * FROM timeline WHERE timeline.id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);

        return template.queryForObject(sql, sqlParameterSource, new TimeLineRowMapper());
    }

    @Override
    public void deleteTimeLineByID(long id) {
        String sql = "DELETE FROM timeline WHERE timeline.id =:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);

        template.update(sql, sqlParameterSource);
    }


}
