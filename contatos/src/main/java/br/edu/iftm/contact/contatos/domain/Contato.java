package br.edu.iftm.contact.contatos.domain;

import lombok.Data;

@Data
public class Contato {

    private String nome;
    private String email;
    private String telefone;

    public Contato() {

    }

    public Contato(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

}