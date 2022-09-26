package com.diegoadp.helpdesk.services;

import com.diegoadp.helpdesk.domain.Pessoa;
import com.diegoadp.helpdesk.domain.Cliente;
import com.diegoadp.helpdesk.dtos.ClienteDTO;
import com.diegoadp.helpdesk.repositories.PessoaRepository;
import com.diegoadp.helpdesk.repositories.ClienteRepository;
import com.diegoadp.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.diegoadp.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validarPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return repository.save(newObj);
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);
        validarPorCpfEEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validarPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("CPF ja cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("E-mail ja cadastro no sistema!");
        }
    }
}
