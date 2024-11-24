/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/

package br.com.fiap.et.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class IndexController {

    @GetMapping({"", "/"})
    public ModelAndView redirectToHome() {
        return new ModelAndView("redirect:/home"); // Redireciona para o HomeController
    }

}
