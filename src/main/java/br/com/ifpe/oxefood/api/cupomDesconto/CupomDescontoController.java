package br.com.ifpe.oxefood.api.cupomDesconto;

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

import br.com.ifpe.oxefood.modelo.cupomDesconto.CupomDesconto;
import br.com.ifpe.oxefood.modelo.cupomDesconto.CupomDescontoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cupomdesconto")
@CrossOrigin
public class CupomDescontoController {
    @Autowired
    private CupomDescontoService cupomDescontoService;

    @ApiOperation(value = "Serviço responsável por salvar um cupom de desconto no sistema.")
    @PostMapping
    public ResponseEntity<CupomDesconto> save(@RequestBody @Valid CupomDescontoRequest request) {
        System.out.println(request);
        CupomDesconto cupomDesconto = cupomDescontoService.save(request.build());
        return new ResponseEntity<CupomDesconto>(cupomDesconto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por listar todos os cupons do sistema.")
    @GetMapping
    public List<CupomDesconto> listarTodos() {
        return cupomDescontoService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por obter um cupom referente ao Id passado na URL.")
    @GetMapping("/{id}")
    public CupomDesconto obterPorID(@PathVariable Long id){
        return cupomDescontoService.findById(id);
    }

    
    @ApiOperation(value = "Serviço responsável por alterar um cupom referente ao Id passado na URL.")
    @PutMapping("/{id}")
    public ResponseEntity<CupomDesconto> update(@PathVariable("id") Long id, @RequestBody CupomDescontoRequest request) {
        cupomDescontoService.update(id, request.build());
        return ResponseEntity.ok().build();    
    }

    @ApiOperation(value = "Serviço responsável por deletar um cupom referente ao Id passado na URL.")   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        cupomDescontoService.delete(id);
        return ResponseEntity.ok().build();
        
    }

}