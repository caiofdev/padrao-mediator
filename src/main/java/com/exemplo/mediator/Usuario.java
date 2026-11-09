package com.exemplo.mediator;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private final String nome;
    private final Mediador mediador;
    private final List<String> mensagensRecebidas = new ArrayList<>();

    public Usuario(String nome, Mediador mediador) {
        this.nome = nome;
        this.mediador = mediador;
    }

    public void enviar(String mensagem) {
        mediador.enviar(mensagem, this);
    }

    public void receber(String mensagem) {
        mensagensRecebidas.add(mensagem);
    }

    public String getNome() {
        return nome;
    }

    public List<String> getMensagensRecebidas() {
        return List.copyOf(mensagensRecebidas);
    }
}
