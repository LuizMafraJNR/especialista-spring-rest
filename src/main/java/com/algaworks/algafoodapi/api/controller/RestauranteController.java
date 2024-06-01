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
       return restauranteRepository.findAll();
        /*// Testando o Lazy Loading
        System.out.println("o nome da cozinha é:");
        System.out.println(restaurantes.get(0).getCozinha().getNome());
*/
    }

    @GetMapping("/{id}")
    public Restaurante buscar(@PathVariable Long id)
    {
        return restauranteService.buscarOuFalhar(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante)
    {
        return restauranteService.adicionar(restaurante);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id,@RequestBody Restaurante restaurante)
    {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id",
                    "formasPagamento", "endereco","dataCadastro", "produtos");
            return restauranteService.adicionar(restauranteAtual);
    }

    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id,
                                              @RequestBody Map<String, Object> campos){
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
        merge(campos, restauranteAtual);
        return atualizar(id, restauranteAtual);
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
