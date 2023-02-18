package org.shemenev.model;

import java.time.LocalDateTime;

/**
 * Top-level class.
 * 1. конструктор не содержит ID, т.к. в БД оно инкриминируется автоматически;
 * 2.
 * 3.
 */
public class TimeLine {

    private long id;
    private LocalDateTime startAt;
    private String activity;
    private String comment;
    private long client_id;

    public TimeLine() {
    }

    public TimeLine(Long id, LocalDateTime startAt, String activity, String comment, long clientID) {
        this.id = id;
        this.startAt = startAt;
        this.activity = activity;
        this.comment = comment;
        this.client_id = clientID;
    }

    // without ID
    public TimeLine(LocalDateTime startAt, String activity, String comment, long clientID) {
        this.startAt = startAt;
        this.activity = activity;
        this.comment = comment;
        this.client_id = clientID;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getClient_id() {
        return client_id;
    }

    // уточнить нужен ли сеттер для этого поля? зачем его менять? TODO
    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "id=" + id +
                ", startAt='" + startAt + '\'' +
                ", activity='" + activity + '\'' +
                ", comment='" + comment + '\'' +
                ", clientID='" + client_id + '\'' +
                '}';
    }
}
