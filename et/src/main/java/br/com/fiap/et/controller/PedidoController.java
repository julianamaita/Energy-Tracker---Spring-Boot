/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/
package br.com.fiap.et.controller;

import br.com.fiap.et.model.dto.RequisicaoNovoPedido;
import br.com.fiap.et.model.entity.Pedido;
import br.com.fiap.et.model.entity.StatusPedido;
import br.com.fiap.et.model.repository.PedidoRepository;
import br.com.fiap.et.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;


@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UserRepository userRepository;

    // Método para exibir o formulário de novo pedido
    @GetMapping("/formulario")
    public String novoPedido(Model model) {
        // Preenche os valores iniciais
        model.addAttribute("requisicaoNovoPedido",
                new RequisicaoNovoPedido(
                "", "",
                BigDecimal.ZERO, "Ativo",
                "", "",
                "", StatusPedido.AGUARDANDO)
        );
        return "pedido/formulario";
    }

    // Método para processar o envio do formulário
    @PostMapping("/novo")
    public String cadastrarPedido(Principal principal, @ModelAttribute("requisicaoNovoPedido") RequisicaoNovoPedido requisicao) {
        // Cria um novo pedido a partir do DTO
        Pedido pedido = new Pedido(requisicao);

        // Associando o Usuário logado ao pedido
        pedido.setUser(userRepository.findByUsername(principal.getName()));

        // Salva o pedido no banco
        pedidoRepository.save(pedido);

        // Redireciona para a página de listagem de pedidos (ou qualquer outra página)
        return "redirect:/usuario/pedido";
    }
}
