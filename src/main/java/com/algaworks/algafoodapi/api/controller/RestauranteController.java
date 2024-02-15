package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CadastroRestauranteService restauranteService;
    @GetMapping()
    public List<Restaurante> listar(){
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        /*// Testando o Lazy Loading
        System.out.println("o nome da cozinha é:");
        System.out.println(restaurantes.get(0).getCozinha().getNome());
*/
        return restaurantes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        if (restaurante.isPresent()){
            return ResponseEntity.ok(restaurante.get());
        }
        return ResponseEntity.notFound().build();
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
            Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
            if (restauranteAtual.isPresent()){
                BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id",
                        "formasPagamento", "endereco","dataCadastro", "produtos");
                Restaurante restauranteSalvo = restauranteService.adicionar(restauranteAtual.get());
                return ResponseEntity.ok(restauranteSalvo);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id,
                                              @RequestBody Map<String, Object> campos){
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
        if (restauranteAtual.isPresent()){
            merge(campos, restauranteAtual.get());

            return atualizar(id, restauranteAtual.get());
        }

        return ResponseEntity.notFound().build();
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteAtual) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field,restauranteAtual, novoValor);
        });
    }
}
