package br.com.ifpe.oxefood.api.produto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.ifpe.oxefood.modelo.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

   @NotBlank(message = "O id da categoria é de preenchimento obrigatório")
    private Long idCategoria;

   @NotBlank(message = "O Título é de preenchimento obrigatório")
   @Length(max = 100, message = "O Título deverá ter no máximo {max} caracteres")
    private String titulo;

    @NotBlank(message = "O Código do produto é de preenchimento obrigatório")
    private String codigo;

    private String descricao;

    @NotBlank(message = "O valor unitário é de preenchimento obrigatório")
    private Double valorUnitario;

    private Integer tempoEntregaMinimo;

    private Integer tempoEntregaMaximo;

    public Produto build() {

       return Produto.builder()
               .titulo(titulo)
                .codigo(codigo)
                .descricao(descricao)
                .valorUnitario(valorUnitario)
                .tempoEntregaMinimo(tempoEntregaMinimo)
                .tempoEntregaMaximo(tempoEntregaMaximo)
                .build();
   }

}
