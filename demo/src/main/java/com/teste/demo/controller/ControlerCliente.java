package com.teste.demo.controller;

import com.teste.demo.model.Cliente;
import com.teste.demo.repositorioCliente.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControlerCliente {

    @Autowired
    private RepositorioCliente repositorioCliente;

    @GetMapping
    public Iterable<Cliente> lista() {
        return repositorioCliente.findAll();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Cliente cliente) {
        try {
            ValidarCliente validarCliente = new ValidarCliente();
            ResponseEntity<String> resultado = validarCliente.validarNomeIdadeTelefone(cliente);
            if (resultado.getStatusCode() == HttpStatus.OK) {
                repositorioCliente.save(cliente);
                return ResponseEntity.ok().body("{\"mensagem\":\"success\"}");
            }
            return ResponseEntity.badRequest().body(resultado.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"mensagem\":\"Erro ao salvar o cliente\"}");
        }
    }

    @PutMapping
    public ResponseEntity<?> alterar(@RequestBody Cliente cliente) {
        try {
            ValidarCliente validarCliente = new ValidarCliente();
            ResponseEntity<String> resultado = validarCliente.validarNomeIdadeTelefone(cliente);
            if (resultado.getStatusCode() == HttpStatus.OK) {
                repositorioCliente.save(cliente);
                return ResponseEntity.ok().body("{\"mensagem\":\"Cliente Alterado\"}");
            }
            return ResponseEntity.badRequest().body(resultado.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"mensagem\":\"Erro ao alterar o cliente\"}");
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Cliente clienteAltera) {
        try {
            ValidarCliente validarCliente = new ValidarCliente();
            ResponseEntity<String> resultado = validarCliente.validarNomeIdadeTelefone(clienteAltera);
            if (resultado.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.badRequest().body(resultado.getBody());
            }
            Optional<Cliente> cliente = repositorioCliente.findById(id);
            if (cliente.isPresent()) {
                clienteAltera.setId(id);
                repositorioCliente.save(clienteAltera);
                return ResponseEntity.ok().body("{\"mensagem\":\"Cliente alterado com sucesso\"}");
            }
            return ResponseEntity.badRequest().body("{\"mensagem\":\"Nao encotrado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"mensagem\":\"Erro ao alterar o cliente\"}");
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            Optional<Cliente> idCliente = repositorioCliente.findById(id);
            if (idCliente.isPresent()) {
                repositorioCliente.deleteById(id);
                return ResponseEntity.ok().body("{\"mensagem\":\"Excluido com sucesso\"}");
            }
            return ResponseEntity.badRequest().body("{\"mensagem\":\"Nao encotrado\"}");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
