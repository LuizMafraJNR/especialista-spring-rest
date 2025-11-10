package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService
{
	@Autowired
	private UsuarioRepository usuarioRepository;

	//	aqui ele vai salvar no banco de dados por conta do contexto de persistência
	/*
	Então por mais que fazemos alteração por fora da transição, quando o método transacional terminar ele vai fazer o flush
	da entidade que está no contexto de persistência
	é por isso que não precisamos chamar o save do repository
	é uma funcionalidade do JPA chamada de dirty checking
	é uma funcionalidade que monitora as entidades que estão no contexto de persistência
	quando o método transacional termina ele verifica se houve alguma alteração nessas entidades
	*/
	@Transactional
	public Usuario salvar(Usuario usuario)
	{
		//return usuarioRepository.save(usuario);
		return usuario;
	}

	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String senhaNova) {
		Usuario usuario = buscarOuFalhar(usuarioId);

		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}

		usuario.setSenha(senhaNova);
	}

	public Usuario buscarOuFalhar(Long usuarioId)
	{
		return usuarioRepository.findById(usuarioId)
			.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
}
