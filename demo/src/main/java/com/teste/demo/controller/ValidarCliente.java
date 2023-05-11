package com.teste.demo.controller;

import com.teste.demo.model.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarCliente {

    public ResponseEntity<String> validarNomeIdadeTelefone(Cliente cliente) {
        String telefone = cliente.getTelefone().toString();
        Pattern pattern = Pattern.compile("\\d{9}");
        Matcher matcher = pattern.matcher(telefone);

        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"mensagem\":\"Coloquer um nome\"}");
        } else if (cliente.getIdade() == null || cliente.getIdade() < 0) {
            return ResponseEntity.badRequest().body("{\"mensagem\":\"Coloquer uma idade\"}");
        } else if (!matcher.matches() || cliente.getTelefone() == null) {
            return ResponseEntity.badRequest().body("{\"mensagem\":\"O telefone tem quer ter 9 digitos\"}");
        }
        return ResponseEntity.ok().body("{\"mensagem\":\"ok\"}");
    }
}
