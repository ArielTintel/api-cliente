package br.com.arieltintel.cliente.service.impl;

import br.com.arieltintel.cliente.dto.TelefoneRequestDTO;
import br.com.arieltintel.cliente.exceptions.TelefoneBadRequestException;
import br.com.arieltintel.cliente.exceptions.TelefoneNotFoundEXception;
import br.com.arieltintel.cliente.model.Telefone;
import br.com.arieltintel.cliente.repository.TelefoneRepository;
import br.com.arieltintel.cliente.service.TelefoneService;
import br.com.arieltintel.cliente.utils.TextoUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;
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
                                           String telelefoneAntigo){
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        Telefone telefone = telefoneRepository.findByCpfDddNumeroCliente(cpf,dddAntigo, telelefoneAntigo)
                .orElseThrow(TelefoneNotFoundEXception::new);
        modelMapper.map(telefoneRequestDTO, telefone);
        telefoneRepository.save(telefone);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void updateTelefoneByEmailCliente(String email,
                                             TelefoneRequestDTO telefoneRequestDTO,
                                             String dddAntigo,
                                             String telelefoneAntigo){
        Telefone telefone = telefoneRepository.findByEmailDddNumeroCliente(email, dddAntigo, telelefoneAntigo)
                .orElseThrow(TelefoneNotFoundEXception::new);

        if (ObjectUtils.isEmpty(telefoneRequestDTO)) {
            throw new TelefoneNotFoundEXception("Telefone Inválido ou Nulo");
        }

        modelMapper.map(telefoneRequestDTO, telefone);
        telefoneRepository.save(telefone);
    }
}
