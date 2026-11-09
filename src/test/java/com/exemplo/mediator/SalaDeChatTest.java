package com.exemplo.mediator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalaDeChatTest {

    private SalaDeChat sala;
    private Usuario ana;
    private Usuario bruno;

    @BeforeEach
    void setUp() {
        sala = new SalaDeChat();
        ana = new Usuario("Ana", sala);
        bruno = new Usuario("Bruno", sala);
    }

    @Test
    void registrarAdicionaUsuarioNovo() {
        sala.registrar(ana);
        sala.registrar(bruno);

        ana.enviar("Oi");

        assertEquals(List.of("Ana: Oi"), bruno.getMensagensRecebidas());
    }

    @Test
    void registrarIgnoraUsuarioJaRegistrado() {
        sala.registrar(ana);
        sala.registrar(bruno);
        sala.registrar(bruno);

        ana.enviar("Oi");

        assertEquals(List.of("Ana: Oi"), bruno.getMensagensRecebidas());
    }

    @Test
    void enviarEmSalaVaziaNaoEntregaMensagem() {
        sala.enviar("Oi", ana);

        assertTrue(ana.getMensagensRecebidas().isEmpty());
    }

    @Test
    void enviarNaoEntregaMensagemAoRemetente() {
        sala.registrar(ana);

        sala.enviar("Oi", ana);

        assertTrue(ana.getMensagensRecebidas().isEmpty());
    }

    @Test
    void enviarEntregaMensagemAosOutrosParticipantes() {
        sala.registrar(ana);
        sala.registrar(bruno);

        sala.enviar("Oi", ana);

        assertEquals(List.of("Ana: Oi"), bruno.getMensagensRecebidas());
        assertTrue(ana.getMensagensRecebidas().isEmpty());
    }
}
