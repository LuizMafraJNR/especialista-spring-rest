package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.model.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService
{

	private final UsuarioRepository usuarioRepository;
	private final GrupoService  grupoService;

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
		/*
		Esse aqui é importante, pois ele "desanexa" a entidade do contexto de persistência
		Assim, quando fazemos a busca pelo email, não estamos buscando a própria entidade que estamos tentando salvar
		Se não fizermos isso, a verificação de existência de email pode falhar, pois a entidade já está no contexto de persistência
		Se o email for o mesmo da própria entidade, ele vai considerar que não há duplicidade
		*/
		usuarioRepository.detach(usuario);
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent() && usuarioExistente.get().equals(usuario))
		{
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}

		return usuarioRepository.save(usuario);
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

	@Transactional
	public Boolean desassociarGrupo(Long usuarioId, Long grupoId) {
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		Usuario usuario = this.buscarOuFalhar(usuarioId);
		return usuario.desassociarGrupo(grupo);
	}

	@Transactional
	public Boolean associarGrupo(Long usuarioId, Long grupoId) {
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		Usuario usuario = this.buscarOuFalhar(usuarioId);
		return usuario.associarGrupo(grupo);
	}
}
