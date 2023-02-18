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

    public Client() {
    }

    public Client(String nickname) {
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
