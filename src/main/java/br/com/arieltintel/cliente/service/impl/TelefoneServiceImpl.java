package br.com.arieltintel.cliente.service.impl;

import br.com.arieltintel.cliente.dto.TelefoneRequestDTO;
import br.com.arieltintel.cliente.model.Telefone;
import br.com.arieltintel.cliente.repository.TelefoneRepository;
import br.com.arieltintel.cliente.service.TelefoneService;
import br.com.arieltintel.cliente.utils.TextoUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class TelefoneServiceImpl implements TelefoneService {

    private final TelefoneRepository telefoneRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(value = "clientes", allEntries = true)
    public void updateTelefoneByCpfCliente(String cpf, TelefoneRequestDTO telefoneRequestDTO) throws Exception {
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        List<Telefone> telefones = telefoneRepository.findByCpfCliente(cpf);
        if(CollectionUtils.isEmpty(telefones)){
            throw new Exception("Telefone não encontrado para o CPF: " + cpf);
        }
        telefones.stream().forEach(telefone -> {
            modelMapper.map(telefoneRequestDTO, telefone);
        });
        telefoneRepository.saveAll(telefones);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void updateTelefoneByEmailCliente(String email, TelefoneRequestDTO telefoneRequestDTO) throws Exception {
        List<Telefone> telefones = telefoneRepository.findByEmailCliente(email);
        if(CollectionUtils.isEmpty(telefones)){
            throw new Exception("Telefone não encontrado para o Email: " + email);
        }
        telefones.stream().forEach(telefone -> {
            modelMapper.map(telefoneRequestDTO, telefone);
        });

        telefoneRepository.saveAll(telefones);
    }
}
