package br.com.ifpe.oxefood.modelo.empresa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
// import br.com.ifpe.oxefood.modelo.mensagens.EmailService;
import br.com.ifpe.oxefood.util.exception.EntidadeNaoEncontradaException;

@Service
public class EmpresaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    // private EmailService emailService;

    @Transactional
    public Empresa save(Empresa empresa) {

        usuarioService.save(empresa.getUsuario());

        empresa.setHabilitado(Boolean.TRUE);
        empresa.setVersao(1L);
        empresa.setDataCriacao(LocalDate.now());

        // super.preencherCamposAuditoria(empresa);

        // emailService.enviarEmailConfirmacaoCadastroEmpresa(empresa);

        return repository.save(empresa);
    }

    public List<Empresa> findAll() {

        return repository.findAll();
    }

    public Empresa findById(Long id) {

        Optional<Empresa> consulta = repository.findById(id);

        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new EntidadeNaoEncontradaException("Empresa", id);
        }

    }

    @Transactional
    public void update(Long id, Empresa empresaAlterado) {

        Empresa empresa = repository.findById(id).get();
        empresa.setChave(empresaAlterado.getChave());
        empresa.setSite(empresaAlterado.getSite());
        empresa.setCnpj(empresaAlterado.getCnpj());
        empresa.setInscricaoEstadual(empresaAlterado.getInscricaoEstadual());
        empresa.setNomeEmpresarial(empresaAlterado.getNomeEmpresarial());
        empresa.setNomeFantasia(empresaAlterado.getNomeFantasia());
        empresa.setFone(empresaAlterado.getFone());
        empresa.setFoneAlternativo(empresaAlterado.getFoneAlternativo());
        empresa.setEmail(empresaAlterado.getEmail());
        empresa.setPerfil(empresaAlterado.getPerfil());

        empresa.setVersao(empresa.getVersao() + 1);
        repository.save(empresa);
    }

    @Transactional
    public void delete(Long id) {

        Empresa empresa = repository.findById(id).get();
        empresa.setHabilitado(Boolean.FALSE);
        empresa.setVersao(empresa.getVersao() + 1);

        repository.save(empresa);
    }

}
