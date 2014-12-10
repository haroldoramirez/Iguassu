package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.*;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.repository.DAOCandidato;
import br.com.emanuelvictor.iguassu.web.repository.DAOEncaminhamento;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import br.com.emanuelvictor.iguassu.web.repository.job.vacancy.DAOVaga;
import br.com.emanuelvictor.iguassu.web.util.Contracts;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class ServiceEncaminhamento {

    @Autowired
    DAOLancamento daoLancamento;

    @Autowired
    DAOEncaminhamento daoEncaminhamento;

    @Autowired
    DAOVaga daoVaga;

    @Autowired
    DAOCandidato daoCandidato;

    public Encaminhamento save(Encaminhamento encaminhamento) throws Exception {
        // Se o usuário estiver bloqueado ele não salva
        if (encaminhamento.getCandidato().getSituacao() == SituacaoCandidato.BLOQUEADO) {
            throw new Exception();
        }

        if (encaminhamento.getVaga().getSituacao() == SituacaoVaga.OCUPADA) {
            throw new Exception();
        }

        if (encaminhamento.getVaga().getSituacao() == SituacaoVaga.CANCELADA) {
            throw new Exception();
        }

        if (encaminhamento.getId() == null) { // Novo encaminhamento
            encaminhamento.setSituacao(SituacaoEncaminhamento.ANDAMENTO);
            return daoEncaminhamento.save(encaminhamento);
        } else if (encaminhamento.getSituacao() == SituacaoEncaminhamento.SUCESSO) {

            // Atualiza o status do candidato para 'EMPREGADO'
            Candidato candidato = encaminhamento.getCandidato();
            candidato.setSituacao(SituacaoCandidato.EMPREGADO);
            if (candidato.getEndereco().getBairro().getId()==null){
                candidato.getEndereco().setBairro(null);
            }
            if (candidato.getNacionalidade().getId()==null){
                candidato.setNacionalidade(null);
            }
            candidato = this.daoCandidato.save(candidato);

            // Atualiza o status da vaga para 'OCUPADA'
            Vaga vaga = encaminhamento.getVaga();
            vaga.setSituacao(SituacaoVaga.OCUPADA);
            try{
                vaga = daoVaga.save(vaga);
            }catch (Exception e){
                if(vaga.getEmpresa().getId()==null){
                    vaga.setEmpresa(null);
                }
                if(vaga.getEndereco().getBairro().getId()==null){
                    vaga.getEndereco().setBairro(null);
                }
                vaga = daoVaga.save(vaga);
            }

            // Deve gerar conta a receber para daqui 1 mes, com base em 30 por cento do salário que o candidato irá receber
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 30);
            Lancamento lancamento = new Lancamento();
            if (encaminhamento.getLancamento().getId() != null) {
                //TODO
                lancamento = encaminhamento.getLancamento();
                Lancamento lancamentoAux = this.daoLancamento.findOne(lancamento.getId());
                if (lancamentoAux.getDataDePagamento() != null) {
                    System.out.print(lancamento.getDataDePagamento());
                    if (lancamento.getDataDePagamento() == null) {
                        lancamento.setDataDeCadastro(lancamentoAux.getDataDePagamento());
                    }
                }
            }
            lancamento.setValor(vaga.getSalario() / 3);
            lancamento.setDataDeVencimento(calendar);
            lancamento.setTipoLancamento(TipoLancamento.ENTRADA);
            lancamento.setDescricao("Encaminhamento do candidato " + candidato.getNome() + " para a vaga de código " + vaga.getId());
            lancamento.setPessoa(candidato);
            lancamento.setUsuario(encaminhamento.getUsuario());

            lancamento = this.daoLancamento.save(lancamento);

            //SETA O LANÇAMENTO NO ENCAMINHAMENTO
            encaminhamento.setLancamento(lancamento);
            return daoEncaminhamento.save(encaminhamento);
        } else if (encaminhamento.getSituacao() == SituacaoEncaminhamento.FALHA) {// Handler de falha "SE O ENCAMINHAMENTO É CADASTRADO COMO FALHA, NÃO LANÇA LANÇAMENTO E NEM FAZ NADA, SÓ FICA COMO FALHA"
            encaminhamento.setLancamento(null);
            return daoEncaminhamento.save(encaminhamento);
        } else {
            throw new Exception();
        }

    }

    public String[] contrato(Long id) throws Exception {
        Encaminhamento encaminhamento = this.daoEncaminhamento.findOne(id);
        if (encaminhamento != null) {
            return Contracts.getContractForward(encaminhamento);
        }
        return new String[]{};
    }

    public void delete(Long id) {
        daoEncaminhamento.delete(id);
    }

    public List<Encaminhamento> find() {
        return daoEncaminhamento.findAll();
    }

    public Encaminhamento find(Long id) {
        return daoEncaminhamento.findOne(id);
    }

    public List<Encaminhamento> find(Encaminhamento encaminhamento, PageRequest pageRequest) {

        return daoEncaminhamento.find(pageRequest);
//        return daoEncaminhamento.find( TODO
//                encaminhamento.getId(),
//                encaminhamento.getObservacoes(),
//                encaminhamento.getSituacao(),
//                encaminhamento.getVaga().getId(),
//                encaminhamento.getCandidato().getId(),
//                encaminhamento.getUsuario().getId(),
//                encaminhamento.getLancamento().getDataDePagamento(),
//                encaminhamento.getLancamento().getValor(),
//                pageRequest);

    }


    public void getEncaminhamentosNaoPagos( ) throws  Exception {

        List<Encaminhamento> encaminhamentos = this.daoEncaminhamento.findAll();

        Image topImageDoc = Image.getInstance("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/title.png");
        topImageDoc.setAlignment(Element.ALIGN_CENTER);

        Paragraph titleDoc = new Paragraph("Um mundo de oportunidades para VOCÊ!", new Font(Font.FontFamily.HELVETICA, 15, Font.UNDEFINED));
        titleDoc.setAlignment(Element.TITLE);

        Paragraph underline1 = new Paragraph("_________________________________________________________________________________________________________________________________________________________________________________________________________________", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL));
        underline1.setAlignment(Element.ALIGN_CENTER);
        underline1.setSpacingAfter(0);

        Paragraph nameDoc = new Paragraph("Encaminhamentos não pagos", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
        nameDoc.setAlignment(Element.TITLE);
        nameDoc.setSpacingAfter(8);
        nameDoc.setSpacingBefore(0);


        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos_nao_pagos.pdf"));
        document.open();
        document.add(topImageDoc);
        document.add(titleDoc);
        document.add(underline1);
        document.add(nameDoc);

        for (int i = 0; i < encaminhamentos.size(); i++) {
            if (encaminhamentos.get(i).getSituacao()== SituacaoEncaminhamento.SUCESSO){
                if (encaminhamentos.get(i).getLancamento().getDataDePagamento()==null){
                    Paragraph inadimplente = new Paragraph((i+1)+" - Nome do candidato: " + encaminhamentos.get(i).getCandidato().getNome() + ", cargo: " + encaminhamentos.get(i).getVaga().getCargo().getNome() + ", código: " + encaminhamentos.get(i).getId(),
                            new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
                    inadimplente.setIndentationLeft(20);
                    inadimplente.setSpacingAfter(8);

                    document.add(inadimplente);
                }
            }
        }

        document.close();

    }

    public void getEncaminhamentosEmAndamento( ) throws  Exception {

        List<Encaminhamento> encaminhamentos = this.daoEncaminhamento.findAll();

        Image topImageDoc = Image.getInstance("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/title.png");
        topImageDoc.setAlignment(Element.ALIGN_CENTER);

        Paragraph titleDoc = new Paragraph("Um mundo de oportunidades para VOCÊ!", new Font(Font.FontFamily.HELVETICA, 15, Font.UNDEFINED));
        titleDoc.setAlignment(Element.TITLE);

        Paragraph underline1 = new Paragraph("_________________________________________________________________________________________________________________________________________________________________________________________________________________", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL));
        underline1.setAlignment(Element.ALIGN_CENTER);
        underline1.setSpacingAfter(0);

        Paragraph nameDoc = new Paragraph("Encaminhamentos em andamento", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
        nameDoc.setAlignment(Element.TITLE);
        nameDoc.setSpacingAfter(8);
        nameDoc.setSpacingBefore(0);


        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos_em_andamento.pdf"));
        document.open();
        document.add(topImageDoc);
        document.add(titleDoc);
        document.add(underline1);
        document.add(nameDoc);

        for (int i = 0; i < encaminhamentos.size(); i++) {
            if (encaminhamentos.get(i).getSituacao()== SituacaoEncaminhamento.ANDAMENTO){
                Paragraph inadimplente = new Paragraph((i+1)+" - Nome do candidato: " + encaminhamentos.get(i).getCandidato().getNome() + ", cargo: " + encaminhamentos.get(i).getVaga().getCargo().getNome() + ", código: " + encaminhamentos.get(i).getId(),
                        new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
                inadimplente.setIndentationLeft(20);
                inadimplente.setSpacingAfter(8);

                document.add(inadimplente);
            }
        }
        document.close();

    }
}