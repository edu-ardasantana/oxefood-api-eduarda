package br.com.ifpe.oxefood.api.empresa;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.empresa.Empresa;
import br.com.ifpe.oxefood.modelo.empresa.EmpresaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @ApiOperation(value = "Serviço responsável por salvar uma empresa no sistema.")
    @PostMapping
    public ResponseEntity<Empresa> save(@RequestBody @Valid EmpresaRequest request) {

        Empresa empresa = empresaService.save(request.build());
        if (request.getPerfil() != null && !"".equals(request.getPerfil())) {
            if (request.getPerfil().equals("Usuario")) {
                empresa.getUsuario().getRoles().add(Usuario.ROLE_EMPRESA_USER);
            } else if (request.getPerfil().equals("Admin")) {
                empresa.getUsuario().getRoles().add(Usuario.ROLE_EMPRESA);
            }
        }

        Empresa empresaCriada = empresaService.save(empresa);
        return new ResponseEntity<Empresa>(empresaCriada, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Serviço responsável por listar todos as empresas do sistema.")
    @GetMapping
    public List<Empresa> findAll() {

        return empresaService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por obter uma empresa referente ao Id passado na URL.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna  a empresa."),
            @ApiResponse(code = 401, message = "Acesso não autorizado."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
            @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
    })

    @GetMapping("/{id}")
    public Empresa findById(@PathVariable Long id) {

        return empresaService.findById(id);
    }

    @ApiOperation(value = "Serviço responsável por alterar uma empresa no sistema.")
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@PathVariable("id") Long id, @RequestBody @Valid EmpresaRequest request) {

        empresaService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Serviço responsável por excluir uma empresa do sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        empresaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
