/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/
package br.com.fiap.et.model.entity;

import br.com.fiap.et.model.dto.RequisicaoNovoPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity(name = "Pedido")
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto")  // Nome do produto
    private String nomeProduto;

    @Column(name = "tipo")  // Tipo do produto
    private String tipo;

    @Column(name = "consumo_energia")  // Consumo de energia
    private BigDecimal consumoEnergia;

    @Column(name = "status_ativo")  // Status ativo
    private String statusAtivo;

    @Column(name = "url_produto", length = 512)  // URL do produto
    private String urlProduto;

    @Column(name = "url_imagem", length = 512)  // URL da imagem
    private String urlImagem;

    @Column(name = "descricao", length = 2000)  // Descrição do produto
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")  // Status do pedido (Aguardando, etc.)
    private StatusPedido status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_username", referencedColumnName = "username")  // Relacionamento com User
    private User user;

    // Construtor vazio (obrigatório para JPA)
    public Pedido() {
    }

    // Construtor com DTO
    public Pedido(RequisicaoNovoPedido requisicao) {
        this.nomeProduto = requisicao.nomeProduto();
        this.tipo = requisicao.tipo();
        this.consumoEnergia = requisicao.consumoEnergia();
        this.statusAtivo = requisicao.statusAtivo();
        this.urlProduto = requisicao.urlProduto();
        this.urlImagem = requisicao.urlImagem();
        this.descricao = requisicao.descricao();
        this.status = requisicao.status();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(BigDecimal consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public String getStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(String statusAtivo) {
        this.statusAtivo = statusAtivo;
    }

    public String getUrlProduto() {
        return urlProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
