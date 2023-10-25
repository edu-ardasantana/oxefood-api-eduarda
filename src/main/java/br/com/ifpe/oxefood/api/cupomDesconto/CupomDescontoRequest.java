package br.com.ifpe.oxefood.api.cupomDesconto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cupomDesconto.CupomDesconto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CupomDescontoRequest {

    private String codigoDesconto;

    private Double percentualDesconto;

     private Double valorDesconto;

    private Double valorMinimoPedidoPermitido;

     private int quantidadeMaximaUso;

     @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate inicioVigencia;

    @JsonFormat(pattern = "dd/MM/yyyy")
     private LocalDate fimVigencia;

     public CupomDesconto build(){
        return CupomDesconto.builder()
        .codigoDesconto(codigoDesconto)
        .fimVigencia(fimVigencia)
        .valorDesconto(valorDesconto)
        .percentualDesconto(percentualDesconto)
        .quantidadeMaximaUso(quantidadeMaximaUso)
        .inicioVigencia(inicioVigencia)
        .valorMinimoPedidoPermitido(valorMinimoPedidoPermitido)
        .build();
    
     }
    
}