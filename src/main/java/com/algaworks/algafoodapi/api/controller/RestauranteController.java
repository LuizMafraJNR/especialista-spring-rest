package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CadastroRestauranteService restauranteService;
    @GetMapping()
    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
        Restaurante restaurante = restauranteRepository.buscarId(id);
        if (restaurante == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping()
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = restauranteService.adicionar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,@RequestBody Restaurante restaurante){
        try {
            Restaurante restauranteAtual = restauranteRepository.buscarId(id);
            if (restauranteAtual == null){
                return ResponseEntity.notFound().build();
            }

            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            restauranteAtual = restauranteService.adicionar(restauranteAtual);
            return ResponseEntity.ok(restauranteAtual);

        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id,
                                              @RequestBody Map<String, Object> campos){
        Restaurante restauranteAtual = restauranteRepository.buscarId(id);
        if (Objects.isNull(restauranteAtual)){
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);
        
        return atualizar(id, restauranteAtual);
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteAtual) {
        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println(nomePropriedade +" = "+valorPropriedade);
        });
    }
}
