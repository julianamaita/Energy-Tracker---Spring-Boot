/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/
package br.com.fiap.et.model.repository;

import br.com.fiap.et.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String user);
}
