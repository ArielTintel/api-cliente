package br.com.arieltintel.cliente.service.impl;

import br.com.arieltintel.cliente.dto.EnderecoRequestDTO;
import br.com.arieltintel.cliente.model.Endereco;
import br.com.arieltintel.cliente.repository.EnderecoRepository;
import br.com.arieltintel.cliente.service.EnderecoService;
import br.com.arieltintel.cliente.utils.TextoUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
            Endereco endereco = enderecoRepository.findByCpfCliente(cpf);
            return modelMapper.map(endereco, EnderecoRequestDTO.class);
        }
        return null;
    }

    @Override
    @Cacheable("clientes")
    public EnderecoRequestDTO findByEmailCliente(String email) {

        Endereco endereco = enderecoRepository.findByEmailCliente(email);

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
    public void updateEnderecoByCpfCliente(String cpf, EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        Endereco endereco = enderecoRepository.findByCpfCliente(cpf);
        if(endereco == null){
            throw new Exception("Endereço não encontrado.");
        }
        modelMapper.map(enderecoRequestDTO, endereco);
        enderecoRepository.save(endereco);
    }

    @Override
    public void updateEnderecoByEmailCliente(String email, EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        Endereco endereco = enderecoRepository.findByEmailCliente(email);
        if(ObjectUtils.isEmpty(endereco)){
            throw new Exception("Endereço não encontrado.");
        }
        modelMapper.map(enderecoRequestDTO, endereco);
        enderecoRepository.save(endereco);
    }
}
