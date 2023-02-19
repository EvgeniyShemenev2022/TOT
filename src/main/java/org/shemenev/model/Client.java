package org.shemenev.model;


/**
 * Top-level class.
 * 1. конструктор не содержит ID, т.к. в БД оно инкриминируется автоматически;
 * 2.
 * 3.
 */

public class Client {

    private long id;

    private String nickname;

    public Client(String nickname) {
        this.nickname = nickname;
    }

    public Client(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
