package org.shemenev.DAO;


import org.shemenev.model.Client;

import java.util.List;

public interface ClientDAOInterface {

    List<Client> getAllClients ();
    Client getClient(long id);
    Long addClient(Client newClient);
    void renameClient(Client client);
    void deleteClient(long id);


}
