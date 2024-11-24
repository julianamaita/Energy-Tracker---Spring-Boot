/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/

package br.com.fiap.et.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

}
