package com.FooPedi.Proyecto.services;

import com.FooPedi.Proyecto.model.Client;
import com.FooPedi.Proyecto.util.CustomerException;
import com.FooPedi.Proyecto.util.CustomerNotFoundException;
import com.FooPedi.Proyecto.util.InvalidEmailException;
import java.util.ArrayList;
import java.util.List;

public class ClientsManager {
    private ArrayList<Client> clients;

    public ClientsManager() {
        this.clients = new ArrayList<>(); // Inicializar la lista de clientes
    }

    public void addClient(Client client) throws CustomerException, InvalidEmailException {
        // Validar que el cliente no sea nulo
        if (client == null) {
            throw new CustomerException("El cliente no puede ser nulo");
        }

        // Validar que el email contenga un '@'
        if (!client.getEmail().contains("@")) {
            throw new InvalidEmailException("El email del cliente debe contener un '@'.");
        }

        // Validar que no exista un cliente con el mismo email
        for (Client existingClient : clients) {
            if (existingClient.getEmail().equalsIgnoreCase(client.getEmail())) {
                throw new CustomerException("Ya existe un cliente con el mismo email");
            }
        }

        // Generar un ID único para el cliente
        client.setId(generateClientId());

        // Agregar el cliente a la lista
        clients.add(client);
    }

    //Busca el cliente y lo elimina
    public void removeClient(int id){
        try {
            Client searchId = searchClientById(id); //Valida que se encuentre el cliente
            if (searchId != null){
                clients.remove(searchId);
            }
        } catch (CustomerNotFoundException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    //Busca cliente por id
    public Client searchClientById(int id) throws CustomerNotFoundException {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        throw new CustomerNotFoundException("Cliente con ID " + id + " no encontrado.");
    }

    //Busca cliente por nombre
    public Client searchClientByName(String name) throws CustomerNotFoundException{
        for (Client client : clients){
            if(client.getName().equals(name)){
                return client;
            }
        }
        throw new CustomerNotFoundException("Cliente con nombre: " + name + " no encontrado.");
    }

    public List<Client> listClients(){
        List<Client> listReturn = new ArrayList<>();
        for (Client client : clients){
            listReturn.add(client);
        }
        return listReturn;
    }

    public void updateClient(int id,String name, String email, String numTelephone) throws CustomerNotFoundException , InvalidEmailException{
        
        //Validar que el cliente se encuentre cargado
        if (searchClientById(id) == null){
            throw new CustomerNotFoundException("Cliente con id: " + id + "no encontrado.");
        }

        //Validar email bien escrito
        if (!email.contains("@")){
            throw new InvalidEmailException("El email es invalido.");
        }

        Client client = searchClientById(id);
        client.setName(name);
        client.setEmail(email);
        client.setNumTelephone(numTelephone);
    }

    private int generateClientId() {
        return clients.isEmpty() ? 1 : clients.get(clients.size() - 1).getId() + 1;
    }
}
