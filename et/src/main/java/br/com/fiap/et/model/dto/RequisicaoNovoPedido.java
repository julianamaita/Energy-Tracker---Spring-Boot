/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/
package br.com.fiap.et.model.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import br.com.fiap.et.model.entity.StatusPedido;


public record RequisicaoNovoPedido(
        @NotBlank(message = "{nomeProduto.obrigatorio}")
        @Size(max = 100, message = "{nomeProduto.tamanho}")
        String nomeProduto,

        @NotBlank(message = "{tipo.obrigatorio}")
        @Size(max = 50, message = "{tipo.tamanho}")
        String tipo,

        @NotNull(message = "{consumoEnergia.obrigatorio}")
        @DecimalMin(value = "0.01", message = "{consumoEnergia.minimo}")
        BigDecimal consumoEnergia,

        @NotBlank(message = "{statusAtivo.obrigatorio}")
        @Pattern(regexp = "^(Ativo|Inativo)$", message = "{statusAtivo.pattern}")
        String statusAtivo,

        @NotBlank(message = "{urlProduto.obrigatorio}")
        @Size(max = 512, message = "{urlProduto.tamanho}")
        @URL(message = "{urlProduto.valida}")
        String urlProduto,

        @NotBlank(message = "{urlImagem.obrigatorio}")
        @Size(max = 512, message = "{urlImagem.tamanho}")
        @URL(message = "{urlImagem.valida}")
        String urlImagem,

        @NotBlank(message = "{descricao.obrigatorio}")
        @Size(max = 2000, message = "{descricao.tamanho}")
        String descricao,

        @NotNull(message = "{status.obrigatorio}")
        StatusPedido status
) {
}
