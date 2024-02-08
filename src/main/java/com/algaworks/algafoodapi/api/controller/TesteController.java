package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/buscaNomeTodas")
    public List<Cozinha> todasPorNome(@RequestParam String nome){
        return cozinhaRepository.findByNomeContaining(nome);
    }
    @GetMapping("/buscaNomeUnico")
    public Optional<Cozinha> nomeUnico(@RequestParam String nome){
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/restaurantes/taxaFrete")
    public List<Restaurante> findBetweenTaxa(@RequestParam BigDecimal taxaInicial,
                                       @RequestParam BigDecimal taxaFinal){
        return restauranteRepository.getRestauranteByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/nomeAndCozinha")
    public List<Restaurante> findByNomeAndCozinhaId(@RequestParam String nome,
                                             @RequestParam Long cozinhaId){
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/findFirstNomeContaining")
    public Optional<Restaurante> findByNomeFirst(@RequestParam String nome){
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/findByNomeTop2")
    public List<Restaurante> findByNomeTop2(@RequestParam String nome){
        return restauranteRepository.buscarTop2PorNome(nome, PageRequest.of(0,2));
    }

    @GetMapping("/exists")
    public boolean exists(@RequestParam String nome){
        return cozinhaRepository.existsByNome(nome);
    }

    @GetMapping("/restaurantes/countCozinhaId")
    public int countByCozinhaId(@RequestParam Long id){
        return restauranteRepository.countByCozinhaId(id);
    }

    @GetMapping("/restaurantes/getNomeBetweenTaxa")
    public List<Restaurante> getRestaurantesByNomeAndTaxaFrete(@RequestParam String nome,
                                                               @RequestParam BigDecimal taxaInicial,
                                                               @RequestParam BigDecimal taxaFinal){
        return restauranteRepository.consultar(nome,taxaInicial,taxaFinal);
    }
}
