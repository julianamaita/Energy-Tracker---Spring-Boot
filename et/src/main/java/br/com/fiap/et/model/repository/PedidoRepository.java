/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/
package br.com.fiap.et.model.repository;

import br.com.fiap.et.model.entity.Pedido;
import br.com.fiap.et.model.entity.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Cacheable("pedidos")
    Page<Pedido> findByStatus(StatusPedido status, Pageable pageable);

    List<Pedido> findAllByUserUsername(String username);

    List<Pedido> findAllByStatusAndUserUsername(StatusPedido status, String username);
}
