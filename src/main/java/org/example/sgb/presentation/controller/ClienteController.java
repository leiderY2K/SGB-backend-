package org.example.sgb.presentation.controller;

import org.example.sgb.persistence.entity.Cliente;
import org.example.sgb.presentation.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClientService clientService;

    @Autowired
    public ClienteController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Cliente> listarCliente() {
        return clientService.listarClientes();
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clientService.crearCliente(cliente);
    }
}