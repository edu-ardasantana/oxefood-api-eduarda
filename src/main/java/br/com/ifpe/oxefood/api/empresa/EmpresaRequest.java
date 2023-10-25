package br.com.ifpe.oxefood.api.empresa;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.empresa.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // substitui getter e setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequest {

    @NotBlank(message = "O e-mail é de preenchimento obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String password;

    private String chave;

    private String site;

    private String cnpj;

    private String inscricaoEstadual;

    private String nomeEmpresarial;

    private String nomeFantasia;

    private String fone;

    private String foneAlternativo;

    private String perfil;

    public Empresa build() {

        return Empresa.builder()
        .usuario(buildUsuario())
        .chave(chave)
        .site(site)
        .cnpj(cnpj)
        .inscricaoEstadual(inscricaoEstadual)
        .nomeEmpresarial(nomeEmpresarial)
        .nomeFantasia(nomeFantasia)
        .fone(fone)
        .foneAlternativo(foneAlternativo)
        .perfil(perfil)
        .build();
        
    }
    
    public Usuario buildUsuario() {
	
	return Usuario.builder()
		.username(email)
		.password(password)
		.build();
    }


}
