package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.ClientRequest;
import com.idsarl.backend.dto.response.ClientResponse;
import com.idsarl.backend.Entite.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientResponse toResponse(Client client) {
        if (client == null) return null;
        ClientResponse response = new ClientResponse();
        response.setId(client.getId());
        response.setNom(client.getNom());
        response.setContact(client.getContact());
        response.setEmail(client.getEmail());
        response.setTelephone(client.getTelephone());
        return response;
    }

    public Client toEntity(ClientRequest request) {
        if (request == null) return null;
        Client client = new Client();
        client.setNom(request.getNom());
        client.setContact(request.getContact());
        client.setEmail(request.getEmail());
        client.setTelephone(request.getTelephone());
        return client;
    }

    public void updateEntityFromRequest(ClientRequest request, Client client) {
        if (request == null || client == null) return;
        client.setNom(request.getNom());
        client.setContact(request.getContact());
        client.setEmail(request.getEmail());
        client.setTelephone(request.getTelephone());
    }
}