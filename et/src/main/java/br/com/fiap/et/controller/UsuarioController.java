/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/
package br.com.fiap.et.controller;

import br.com.fiap.et.model.entity.Pedido;
import br.com.fiap.et.model.entity.StatusPedido;
import br.com.fiap.et.model.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("pedido")
    public ModelAndView home(Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findAllByUserUsername(principal.getName());
        ModelAndView mv = new ModelAndView("usuario/home");
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("pedido/{status}")
    public ModelAndView porStatus(@PathVariable("status") String status, Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findAllByStatusAndUserUsername(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
        ModelAndView mv = new ModelAndView("usuario/home");
        mv.addObject("pedidos", pedidos);
        mv.addObject("status", status);
        return mv;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView onError() {
        ModelAndView mv = new ModelAndView("redirect:/usuario/home");
        return mv;
    }
}
