package br.com.arieltintel.cliente.service.impl;

import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
import br.com.arieltintel.cliente.exceptions.EnderecoBadRequestException;
import br.com.arieltintel.cliente.exceptions.EnderecoNotFoundException;
import br.com.arieltintel.cliente.model.Endereco;
import br.com.arieltintel.cliente.repository.EnderecoRepository;
import br.com.arieltintel.cliente.service.EnderecoService;
import br.com.arieltintel.cliente.utils.TextoUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable("clientes")
    public EnderecoRequestDTO findByCpfCliente(String cpf) {
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        if (TextoUtils.contemTexto(cpf)) {
            Endereco endereco = enderecoRepository.findByCpfCliente(cpf)
                    .orElseThrow(EnderecoNotFoundException::new);
            return modelMapper.map(endereco, EnderecoRequestDTO.class);
        } else {
            throw new EnderecoBadRequestException("CPF Inválido.");
        }
    }

    @Override
    @Cacheable("clientes")
    public EnderecoRequestDTO findByEmailCliente(String email) {

        Endereco endereco = enderecoRepository.findByEmailCliente(email)
                .orElseThrow(EnderecoNotFoundException::new);

        return EnderecoRequestDTO.builder()
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .uf(endereco.getUf())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .referencia(endereco.getReferencia())
                .build();
    }

    @Override
    @CacheEvict(value = "clientes", allEntries = true)
    public void updateEnderecoByCpfCliente(String cpf, EnderecoRequestDTO enderecoRequestDTO) {
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        Endereco endereco = enderecoRepository.findByCpfCliente(cpf)
                .orElseThrow(EnderecoNotFoundException::new);

        modelMapper.map(enderecoRequestDTO, endereco);
        enderecoRepository.save(endereco);
    }

    @Override
    public void updateEnderecoByEmailCliente(String email, EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = enderecoRepository.findByEmailCliente(email)
                .orElseThrow(EnderecoNotFoundException::new);

        modelMapper.map(enderecoRequestDTO, endereco);
        enderecoRepository.save(endereco);
    }
}
