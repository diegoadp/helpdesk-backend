package com.diegoadp.helpdesk;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	@Override
	public void run(String... args) throws Exception {

		Tecnico tec1 = new Tecnico(null, "Vitoria Nina", "54762577480", "vitorianina@test.com.br", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Alexandre Caio", "94761423757", "alexandrecaio@test.com.br", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));

	}
}
