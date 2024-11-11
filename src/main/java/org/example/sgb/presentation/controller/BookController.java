package org.example.sgb.presentation.controller;

import org.example.sgb.persistence.entity.Cliente;
import org.example.sgb.persistence.entity.libro;
import org.example.sgb.presentation.services.LibroService;
import org.example.sgb.presentation.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final ClientService clientService;

    private final LibroService libroService;

    @Autowired
    public BookController(ClientService clientService, LibroService libroService) {
        this.clientService = clientService;
        this.libroService = libroService;
    }

    @GetMapping("/persona")
    @ResponseBody
    public List<Cliente> listarCliente() {
        return clientService.listarClientes();
    }

    @PostMapping("/aggCliente")
    @ResponseBody
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clientService.crearCliente(cliente);
    }

    @PostMapping("/aggLibro")
    @ResponseBody
    public libro crearLibro(@RequestBody libro libro){
        return libroService.crearLibro(libro);
    }



//    @GetMapping("/libros")
//    @ResponseBody
//    public List<BookResponseDTO> listarLibros(){
//        return bookService.listarLibros();
//    }


}
