package org.shemenev.controller;

import org.shemenev.DAO.ClientDAO;
import org.shemenev.exceptions.MyAppException;
import org.shemenev.model.Client;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


/**
 * Содержит контроллеры приложения:
 * 1. добавить нового клиента
 * 2. получить клиента по ID
 * 3. переименовать клиента по ID
 * 4. получить всех клиентов
 * 5. удалить клиента по ID
 * 6.
 * 7.
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }


    @PostMapping
    public Long addNewClient(@RequestBody Client newClient) {
        return clientDAO.addClient(newClient);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable long id) {
        Optional<Client> clientOptional = clientDAO.getClient(id);
        if (clientOptional.isPresent()) {
            return clientOptional.get();
        }
        var exc = new ErrorResponseException(HttpStatusCode.valueOf(408));
        exc.setDetail("Пользователь не найден: id=" + id);
        throw exc;
    }


    // вывести сообщения о несуществующем id  TODO
    @PutMapping
    public void renameClientById(@RequestBody Client renamedClient) {
        clientDAO.renameClient(renamedClient);
    }


    @GetMapping("/all")
    public List<Client> getAllClients() {

        return clientDAO.getAllClients();
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable long id) {

        clientDAO.deleteClient(id);
    }

    // for quick test
    @GetMapping("/message")
    public String getMessage() {
        return "HOHOHO";
    }


}
