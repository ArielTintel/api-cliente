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
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
public class TelefoneServiceImpl implements TelefoneService {

    private final TelefoneRepository telefoneRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(value = "clientes", allEntries = true)
    public void updateTelefoneByCpfCliente(String cpf,
                                           TelefoneRequestDTO telefoneRequestDTO,
                                           String dddAntigo,
                                           String telelefoneAntigo) throws Exception {
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        Telefone telefone = telefoneRepository.findByCpfDddNumeroCliente(cpf, dddAntigo, telelefoneAntigo);

        if (ObjectUtils.isEmpty(telefone)) {
            throw new Exception("Telefone não encontrado para o CPF: " + cpf);
        }

        modelMapper.map(telefoneRequestDTO, telefone);
        telefoneRepository.save(telefone);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void updateTelefoneByEmailCliente(String email,
                                             TelefoneRequestDTO telefoneRequestDTO,
                                             String dddAntigo,
                                             String telelefoneAntigo) throws Exception {
        Telefone telefone = telefoneRepository.findByEmailDddNumeroCliente(email, dddAntigo, telelefoneAntigo);

        if (ObjectUtils.isEmpty(telefone)) {
            throw new Exception("Telefone não encontrado para o Email: " + email);
        }

        modelMapper.map(telefoneRequestDTO, telefone);
        telefoneRepository.save(telefone);
    }
}
