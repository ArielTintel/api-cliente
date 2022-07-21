package br.com.arieltintel.cliente.client;

import br.com.arieltintel.cliente.dto.EnderecoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-endereco", url = "localhost:8080/v1/endereco")
public interface EnderecoClient {
    @GetMapping("/{cep}")
    EnderecoResponseDTO getEndereco(@PathVariable String cep);

}
