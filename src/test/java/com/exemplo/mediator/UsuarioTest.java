package com.exemplo.mediator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class UsuarioTest {

    @Test
    void getNomeRetornaNomeInformadoNoConstrutor() {
        Usuario usuario = new Usuario("Ana", (mensagem, remetente) -> { });

        assertEquals("Ana", usuario.getNome());
    }

    @Test
    void enviarDelegaAoMediador() {
        List<String> mensagens = new ArrayList<>();
        List<Usuario> remetentes = new ArrayList<>();
        Usuario usuario = new Usuario("Ana", (mensagem, remetente) -> {
            mensagens.add(mensagem);
            remetentes.add(remetente);
        });

        usuario.enviar("Oi");

        assertEquals(List.of("Oi"), mensagens);
        assertSame(usuario, remetentes.get(0));
    }

    @Test
    void receberArmazenaMensagem() {
        Usuario usuario = new Usuario("Ana", (mensagem, remetente) -> { });

        usuario.receber("Bruno: Oi");

        assertEquals(List.of("Bruno: Oi"), usuario.getMensagensRecebidas());
    }

    @Test
    void getMensagensRecebidasIniciaVazia() {
        Usuario usuario = new Usuario("Ana", (mensagem, remetente) -> { });

        assertTrue(usuario.getMensagensRecebidas().isEmpty());
    }
}
