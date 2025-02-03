package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.Groups;
import com.algaworks.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
    public Restaurante adicionar(@RequestBody  @Validated(Groups.CadastroRestaurante.class) Restaurante restaurante)
    {

        try
        {
            return restauranteService.adicionar(restaurante);
        }
        catch (CozinhaNaoEncontradaException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id,@RequestBody Restaurante restaurante)
    {
        try
        {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id",
                    "formasPagamento", "endereco","dataCadastro", "produtos");
            return restauranteService.adicionar(restauranteAtual);
        }
        catch (CozinhaNaoEncontradaException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id,
                                              @RequestBody Map<String, Object> campos, HttpServletRequest request){
        try
        {
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
        merge(campos, restauranteAtual, request);

            return atualizar(id, restauranteAtual);
        }
        catch (CozinhaNaoEncontradaException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteAtual,
        HttpServletRequest request) {

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
	    try
	    {
		    ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

		    Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

		    camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
		        Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
		        field.setAccessible(true);

		        Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
		        ReflectionUtils.setField(field,restauranteAtual, novoValor);
		    });
	    }
	    catch (IllegalArgumentException e)
	    {
		    Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
	    }
    }
}
