package org.shemenev.exceptions;

// добавить логгер TODO

/**
 * Класс для перевода в json информации об ошибках
 */
public class IncorrectData {

    private String info;

    public IncorrectData() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
