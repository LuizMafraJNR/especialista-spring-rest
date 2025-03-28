package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.RestauranteDTOAssembler;
import com.algaworks.algafoodapi.api.model.CozinhaDTO;
import com.algaworks.algafoodapi.api.model.RestauranteDTO;
import com.algaworks.algafoodapi.api.model.input.RestaurantInput;
import com.algaworks.algafoodapi.core.validation.ValidacaoException;
import com.algaworks.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
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
	@Autowired
	private SmartValidator validator;
	@Autowired
	private RestauranteDTOAssembler restauranteDTOAssembler;


    @GetMapping()
    public List<RestauranteDTO> listar(){
       return restauranteDTOAssembler.toCollectDto(restauranteRepository.findAll());
        /*// Testando o Lazy Loading
        System.out.println("o nome da cozinha é:");
        System.out.println(restaurantes.get(0).getCozinha().getNome());
*/
    }

    @GetMapping("/{id}")
    public RestauranteDTO buscar(@PathVariable Long id)
    {
		Restaurante restaurante = restauranteService.buscarOuFalhar(id);
	    RestauranteDTO restauranteDTO = restauranteDTOAssembler.toRestauranteDTO(restaurante);
	    return restauranteDTO; // conversão da entidade Restaurante para RestauranteDTO
    }

	@PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar(@RequestBody  @Valid RestaurantInput restaurante)
    {

        try
        {
			Restaurante restaurante1 = toRestaurante(restaurante);
            return restauranteDTOAssembler.toRestauranteDTO(restauranteService.adicionar(restaurante1));
        }
        catch (CozinhaNaoEncontradaException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteDTO atualizar(@PathVariable Long id, @Valid @RequestBody RestaurantInput restauranteInput)
    {
        try
        {
			Restaurante restaurante = toRestaurante(restauranteInput);

            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id",
                    "formasPagamento", "endereco","dataCadastro", "produtos");
            return restauranteDTOAssembler.toRestauranteDTO(restauranteService.adicionar(restauranteAtual));
        }
        catch (CozinhaNaoEncontradaException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

	/*
	*
	* Para a proxima aula 11.11
	*
	* */

    /*@PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id,
                                              @RequestBody Map<String, Object> campos, HttpServletRequest request){
        try
        {
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
        merge(campos, restauranteAtual, request);
		validate(restauranteAtual,"restaurante");

            return atualizar(id, toRestauranteDTO(restauranteAtual));
        }
        catch (CozinhaNaoEncontradaException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }*/

	private void validate(Restaurante restauranteAtual, String objectName)
	{
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restauranteAtual, objectName);
		validator.validate(restauranteAtual, bindingResult);
		if (bindingResult.hasErrors())
		{
			throw new ValidacaoException(bindingResult);
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

	private Restaurante toRestaurante(RestaurantInput restaurantInput)
	{
		Restaurante restaurante = new Restaurante();
		Cozinha cozinha = new Cozinha();

		cozinha.setId(restaurantInput.getCozinha().getId());
		restaurante.setNome(restaurantInput.getNome());
		restaurante.setTaxaFrete(restaurantInput.getTaxaFrete());
		restaurante.setCozinha(cozinha);

		return restaurante;
	}
}
