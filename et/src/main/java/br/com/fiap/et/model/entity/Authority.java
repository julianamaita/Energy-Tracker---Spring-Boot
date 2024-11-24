/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/
package br.com.fiap.et.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// Essa classe só existe para fazer o Spring Boot criar a tabela "authorities" automaticamente,
// que o Spring Security usa, mas não cria sozinho. Folgado.
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    private String username;
    private String authority;
}