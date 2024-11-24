/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/

package br.com.fiap.et.model.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    private Boolean enabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    public String getUsername(){return username; }

    public void setUsername(String username) {this.username = username;}

    public String getPassword(){return password; }

    public void setPassword(String password){this.password=password;}

    public Boolean getEnabled() {return enabled;}

    public void setEnabled(Boolean enabled) {this.enabled = enabled;}

}
