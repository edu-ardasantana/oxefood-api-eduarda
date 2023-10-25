package br.com.ifpe.oxefood.modelo.cupomDesconto;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CupomDescontoService {
    @Autowired
    private CupomDescontoRepository repository;

    @Transactional
    public CupomDesconto save(CupomDesconto cupomDesconto){
        cupomDesconto.setHabilitado(Boolean.TRUE);
        cupomDesconto.setVersao(1L);
        cupomDesconto.setDataCriacao(LocalDate.now());
        return repository.save(cupomDesconto);
    }

    public List<CupomDesconto> findAll(){
        return repository.findAll();
    }

    public CupomDesconto findById(long id){
        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, CupomDesconto cupomDescontoAlterado){
        CupomDesconto cupomDesconto = repository.findById(id).get();

        cupomDesconto.setCodigoDesconto(cupomDescontoAlterado.getCodigoDesconto());
        cupomDesconto.setPercentualDesconto(cupomDescontoAlterado.getPercentualDesconto());
        cupomDesconto.setValorDesconto(cupomDescontoAlterado.getValorDesconto());
        cupomDesconto.setValorMinimoPedidoPermitido(cupomDescontoAlterado.getValorMinimoPedidoPermitido());
        cupomDesconto.setQuantidadeMaximaUso(cupomDescontoAlterado.getQuantidadeMaximaUso());
        cupomDesconto.setInicioVigencia(cupomDescontoAlterado.getInicioVigencia());
        cupomDesconto.setFimVigencia(cupomDescontoAlterado.getFimVigencia());

        cupomDesconto.setVersao(cupomDesconto.getVersao() + 1);

        repository.save(cupomDesconto);
    }

    @Transactional
    public void delete(Long id){
        CupomDesconto cupomDesconto = repository.findById(id).get();

        cupomDesconto.setHabilitado(Boolean.FALSE);

        cupomDesconto.setVersao(cupomDesconto.getVersao() +1);

        repository.save(cupomDesconto);
    }

}