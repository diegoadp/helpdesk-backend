package com.diegoadp.helpdesk.services;

import com.diegoadp.helpdesk.domain.Chamado;
import com.diegoadp.helpdesk.domain.Cliente;
import com.diegoadp.helpdesk.domain.Tecnico;
import com.diegoadp.helpdesk.domain.enums.Perfil;
import com.diegoadp.helpdesk.domain.enums.Prioridade;
import com.diegoadp.helpdesk.domain.enums.Status;
import com.diegoadp.helpdesk.repositories.ChamadoRepository;
import com.diegoadp.helpdesk.repositories.ClienteRepository;
import com.diegoadp.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Vitoria Nina", "547.625.774-80", "vitorianina@test.com", "123");
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Tiago Ricardo", "926.577.419-32", "tiagoricardo@test.com", "123");
        Tecnico tec3 = new Tecnico(null, "Luan Samuel", "781.950.072-79", "luansamuel@test.com", "123");

        Cliente cli1 = new Cliente(null, "Alexandre Caio", "947.614.237-57", "alexandrecaio@test.com", "123");
        Cliente cli2 = new Cliente(null, "Geraldo Pedro", "620.371.691-08", "geraldopedro@test.com", "123");
        Cliente cli3 = new Cliente(null, "Camila Isabelle", "668.707.733-35", "camilaisabelle@test.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
    }
}
