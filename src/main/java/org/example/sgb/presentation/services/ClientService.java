package org.example.sgb.presentation.services;

import org.example.sgb.persistence.entity.Cliente;
import org.example.sgb.persistence.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClienteRepository clienteRepo;

    @Autowired
    public ClientService(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }
}
