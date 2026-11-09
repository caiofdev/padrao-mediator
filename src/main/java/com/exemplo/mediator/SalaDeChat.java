package com.exemplo.mediator;

import java.util.ArrayList;
import java.util.List;

public class SalaDeChat implements Mediador {

    private final List<Usuario> participantes = new ArrayList<>();

    public void registrar(Usuario usuario) {
        if (!participantes.contains(usuario)) {
            participantes.add(usuario);
        }
    }

    @Override
    public void enviar(String mensagem, Usuario remetente) {
        for (Usuario participante : participantes) {
            if (participante != remetente) {
                participante.receber(remetente.getNome() + ": " + mensagem);
            }
        }
    }
}
