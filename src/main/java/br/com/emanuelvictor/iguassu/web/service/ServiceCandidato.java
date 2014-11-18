package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.*;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import br.com.emanuelvictor.iguassu.web.repository.DAOCandidato;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import br.com.emanuelvictor.iguassu.web.repository.job.DAOExperiencia;
import br.com.emanuelvictor.iguassu.web.repository.schooling.DAOCandidatoCurso;
import br.com.emanuelvictor.iguassu.web.util.Contracts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class ServiceCandidato {

    @Autowired
    DAOCandidatoCurso daoCandidatoCurso;

    @Autowired
    DAOExperiencia daoExperiencia;

    @Autowired
    DAOCandidato daoCandidato;

    @Autowired
    DAOLancamento daoLancamento;

    public Candidato save(Candidato candidato, Usuario usuario) {
        if (candidato.contratoVencido()){
            return candidato;
        }
        Lancamento lancamento = new Lancamento();
        candidato.setDataDeAlteracao(Calendar.getInstance());
        if (candidato.getId() == null) {
            candidato.setSituacao(SituacaoCandidato.BLOQUEADO);
            lancamento.setValor(30.00);
            lancamento.setDataDeVencimento(Calendar.getInstance());
            lancamento.setTipoLancamento(TipoLancamento.ENTRADA);
            lancamento.setDescricao("Contrato de candidato");
            candidato = daoCandidato.save(candidato);
            lancamento.setPessoa(candidato);
            lancamento.setUsuario(usuario);
            this.daoLancamento.save(lancamento);
            return candidato;
        }else if (candidato.getSituacao()== SituacaoCandidato.DISPONIVEL){

            Candidato candidatoAux = this.daoCandidato.findOne(candidato.getId());
            if (candidatoAux.getSituacao()==SituacaoCandidato.BLOQUEADO){
                Lancamento lancamentoAux = this.daoLancamento.getByIdPessoa(candidato.getId()).getLast();
                if (lancamentoAux!=null){
                    lancamentoAux.setUsuario(usuario);
                    lancamentoAux.setDataDePagamento(Calendar.getInstance());
                    this.daoLancamento.save(lancamentoAux);
                }
            }
            candidato.setDataDeContrato(Calendar.getInstance());
            return daoCandidato.save(candidato);
        }else{
            candidato.setSituacao(this.daoCandidato.findOne(candidato.getId()).getSituacao());
            return this.daoCandidato.save(candidato);
        }

    }

    public List<Lancamento> getLancamentos(Long id) throws Exception{
        return this.daoLancamento.getByIdPessoa(id);
    }

    public String[] getLancamentosString(Long id/*, Usuario usuario*/) throws Exception{
        List<Lancamento> lancamentos = this.getLancamentos(id);
        int cont = 0;
        for (int i = 0; i < lancamentos.size(); i++) {
            if (lancamentos.get(i).getDataDePagamento()==null){
                cont ++;
            }
        }
        if (cont==0){
            return new String[]{"Esse candidato não tem contas a pagar, porém, uma nova conta a receber com o valor da taxa de cadastro será criada e contabilizada como paga."};
        }else{
            return new String[]{"Esse candidato tem "+cont+" contas a pagar em aberto. Caso o contrato desse candidato seja renovado, tais contas serão contabilizadas como pagas no caixa da empresa e uma nova conta a receber com o valor da taxa de cadastro será criada e também contabilizada como paga."};
        }
    }

    public Candidato renovarContrato(Long id, Usuario usuario) throws Exception{
        List<Lancamento> lancamentos = this.daoLancamento.getByIdPessoa(id);

        for (int i = 0; i < lancamentos.size(); i++) {
            if (lancamentos.get(i).getDataDePagamento()==null){
                lancamentos.get(i).setDataDePagamento(Calendar.getInstance());
                lancamentos.get(i).setUsuario(usuario);
                this.daoLancamento.save(lancamentos.get(i));
            }
        }

        Candidato candidato = this.daoCandidato.findOne(id);
        candidato.setDataDeAlteracao(Calendar.getInstance());
        candidato.setDataDeContrato(Calendar.getInstance());
        candidato.setSituacao(SituacaoCandidato.DISPONIVEL);

        Lancamento lancamento = new Lancamento();
        lancamento.setUsuario(usuario);
        lancamento.setValor(30.00);
        lancamento.setDataDeVencimento(Calendar.getInstance());
        lancamento.setTipoLancamento(TipoLancamento.ENTRADA);
        lancamento.setDataDePagamento(Calendar.getInstance());
        lancamento.setDescricao("Renovação de contrato de candidato");
        lancamento.setPessoa(candidato);
        this.daoLancamento.save(lancamento);
        return daoCandidato.save(candidato);
    }


    public Candidato save(Candidato candidato) {
        candidato.setDataDeAlteracao(Calendar.getInstance());
        return this.daoCandidato.save(candidato);
    }

    public Candidato uploadPhoto(String id,MultipartFile file) {
        Candidato candidato = this.daoCandidato.findOne(Long.parseLong(id));
        candidato.setDataDeAlteracao(Calendar.getInstance());
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = "/home/emanuel/Projetos/Iguassu/src/main/webapp/app/images"/*System.getProperty("catalina.base")*/;
                System.out.println(" path " + rootPath);
                File dir = new File(rootPath + File.separator + "candidatos");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + id);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                candidato.setPathFoto(serverFile.getAbsolutePath());

                this.daoCandidato.save(candidato);

                System.out.print("Server File Location="
                        + serverFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } return candidato;
    }

    public String[] contrato(Long id)  throws Exception{
        Candidato candidato = this.daoCandidato.findOne(id);
        if (candidato.getSituacao()!=SituacaoCandidato.BLOQUEADO){
            return Contracts.getContractCandidate(candidato);
        }
        return new String[]{};
    }

    public List<Candidato> find() {
        return daoCandidato.findAll();
    }

    public Candidato find(Long id) {
        return daoCandidato.findOne(id);
    }


    public Experiencia save(Experiencia experiencia) {
        Candidato candidato = this.daoCandidato.findOne(experiencia.getCandidato().getId());
        candidato.setDataDeAlteracao(Calendar.getInstance());
        this.daoCandidato.save(candidato);
        return daoExperiencia.save(experiencia);
    }

    public CandidatoCurso save(CandidatoCurso candidatoCurso) {
        Candidato candidato = this.daoCandidato.findOne(candidatoCurso.getCandidato().getId());
        candidato.setDataDeAlteracao(Calendar.getInstance());
        this.daoCandidato.save(candidato);
        return daoCandidatoCurso.save(candidatoCurso);
    }

    public List<Experiencia> findExperiencias(Long id) {
        return daoExperiencia.find(id);
    }

    public List<CandidatoCurso> findCursos(Long id) {
        return daoCandidatoCurso.find(id);
    }

    public void deleteExperiencia(Long id){
        Experiencia experiencia = this.daoExperiencia.findOne(id);
        Candidato candidato = this.daoCandidato.findOne(experiencia.getCandidato().getId());
        candidato.setDataDeAlteracao(Calendar.getInstance());
        this.daoCandidato.save(candidato);
        this.daoExperiencia.delete(id);
    }

    public void deleteCurso(Long id){
        CandidatoCurso candidatoCurso = this.daoCandidatoCurso.findOne(id);
        Candidato candidato = this.daoCandidato.findOne(candidatoCurso.getCandidato().getId());
        candidato.setDataDeAlteracao(Calendar.getInstance());
        this.daoCandidato.save(candidato);
        this.daoCandidatoCurso.delete(id);
    }

//    @ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="Esse candidato tem contas a pagar com a empresa e seu contrato não pode ser renovado")
//    public class CandidatoDevedor extends RuntimeException {
//        private static final long serialVersionUID = 1121147605912406759L;
//        private List<Lancamento> lancamentos = new LinkedList<Lancamento>();
//
//        public CandidatoDevedor(List<Lancamento> lancamentos) {
//            this.lancamentos = lancamentos;
//        }
//    }


}