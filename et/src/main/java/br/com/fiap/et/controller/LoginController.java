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
public class LoginController {
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/login");
    }
}
