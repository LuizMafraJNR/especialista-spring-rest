package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Pedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>
{
	Optional<Pedido> findByCodigo(String codigo);
	/**
	 * Realiza a busca de pedidos otimizada utilizando Fetch Join.
	 * * Resolve o problema do N+1 ao carregar em uma única consulta SQL
	 * as associações de Cliente, Restaurante e a Cozinha do restaurante.
	 * * @return Lista de pedidos com relacionamentos pré-carregados.
	 */

	// @ManyToOne: Lado que possui a FK (Muitos Pedidos -> 1 Cliente). Padrão EAGER.
	// @OneToMany: Lado inverso (1 Restaurante -> Muitos Produtos). Padrão LAZY.
	// join fetch: Comando em query para trazer o relacionamento de forma atômica (evita N+1).
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
}
