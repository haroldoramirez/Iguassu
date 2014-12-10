package br.com.emanuelvictor.test.service;

import br.com.emanuelvictor.iguassu.web.entity.*;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.repository.DAOCandidato;
import br.com.emanuelvictor.iguassu.web.repository.DAOEncaminhamento;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import br.com.emanuelvictor.iguassu.web.repository.job.vacancy.DAOVaga;
import br.com.emanuelvictor.iguassu.web.service.job.vacancy.ServiceVaga;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@TransactionConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPDFReports {


    @Autowired
    DAOCandidato daoCandidato;

    @Autowired
    DAOEncaminhamento daoEncaminhamento;

    @Autowired
    DAOVaga daoVaga;

    @Autowired
    DAOLancamento daoLancamento;

    @Test
    @Rollback(false)
    @Transactional
    public void getCandidatosInadimplentes() throws Exception {

        Calendar today = Calendar.getInstance();

        List<Candidato> candidatoList = this.daoCandidato.findAll();

        List<Candidato> candidatosInadimplentes = new LinkedList<Candidato>();

        for (int i = 0; i < candidatoList.size(); i++) {
            List<Lancamento> lancamentosDoCandidato = daoLancamento.getByIdPessoa(candidatoList.get(i).getId());
            for (int j = 0; j < lancamentosDoCandidato.size(); j++) {
                if (lancamentosDoCandidato.get(j).getDataDePagamento()==null){
                    if (lancamentosDoCandidato.get(j).getDataDeVencimento().before(today)){
                        candidatosInadimplentes.add(candidatoList.get(i));
                    }
                }
            }
        }

        Image topImageDoc = Image.getInstance("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/title.png");
        topImageDoc.setAlignment(Element.ALIGN_CENTER);

        Paragraph titleDoc = new Paragraph("Um mundo de oportunidades para VOCÊ!", new Font(Font.FontFamily.HELVETICA, 15, Font.UNDEFINED));
        titleDoc.setAlignment(Element.TITLE);

        Paragraph underline1 = new Paragraph("_________________________________________________________________________________________________________________________________________________________________________________________________________________", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL));
        underline1.setAlignment(Element.ALIGN_CENTER);
        underline1.setSpacingAfter(0);

        Paragraph nameDoc = new Paragraph("Candidatos inadimplentes", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
        nameDoc.setAlignment(Element.TITLE);
        nameDoc.setSpacingAfter(8);
        nameDoc.setSpacingBefore(0);


        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/candidatos_inadimplentes.pdf"));
        document.open();
        document.add(topImageDoc);
        document.add(titleDoc);
        document.add(underline1);
        document.add(nameDoc);

        for (int i = 0; i < candidatosInadimplentes.size(); i++) {
            Paragraph inadimplente = new Paragraph((i+1)+" - Nome: " + candidatosInadimplentes.get(i).getNome() + ", código: " + candidatosInadimplentes.get(i).getId(),
                    new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
            inadimplente.setIndentationLeft(20);
            inadimplente.setSpacingAfter(8);

            document.add(inadimplente);

        }

        document.close();

    }

    @Test
    @Rollback(false)
    @Transactional
    public void getContratosVencidos( ) throws  Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-6);

        List<Candidato> candidatoList = this.daoCandidato.findAll();

        Image topImageDoc = Image.getInstance("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/title.png");
        topImageDoc.setAlignment(Element.ALIGN_CENTER);

        Paragraph titleDoc = new Paragraph("Um mundo de oportunidades para VOCÊ!", new Font(Font.FontFamily.HELVETICA, 15, Font.UNDEFINED));
        titleDoc.setAlignment(Element.TITLE);

        Paragraph underline1 = new Paragraph("_________________________________________________________________________________________________________________________________________________________________________________________________________________", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL));
        underline1.setAlignment(Element.ALIGN_CENTER);
        underline1.setSpacingAfter(0);

        Paragraph nameDoc = new Paragraph("Contratos vencidos", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
        nameDoc.setAlignment(Element.TITLE);
        nameDoc.setSpacingAfter(8);
        nameDoc.setSpacingBefore(0);


        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/contratos_vencidos.pdf"));
        document.open();
        document.add(topImageDoc);
        document.add(titleDoc);
        document.add(underline1);
        document.add(nameDoc);

        for (int i = 0; i < candidatoList.size(); i++) {
            if (candidatoList.get(i).getDataDeContrato()!=null){
                if (candidatoList.get(i).getDataDeContrato().before(calendar)){
                    Paragraph inadimplente = new Paragraph((i+1)+" - Nome: " + candidatoList.get(i).getNome() + ", código: " + candidatoList.get(i).getId(),
                            new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
                    inadimplente.setIndentationLeft(20);
                    inadimplente.setSpacingAfter(8);

                    document.add(inadimplente);
                }
            }
        }

        document.close();
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getVagasDisponiveis( ) throws  Exception {


        List<Vaga> vagasDisponiveis = this.daoVaga.find(SituacaoVaga.DISPONIVEL);


        Image topImageDoc = Image.getInstance("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/title.png");
        topImageDoc.setAlignment(Element.ALIGN_CENTER);

        Paragraph titleDoc = new Paragraph("Um mundo de oportunidades para VOCÊ!", new Font(Font.FontFamily.HELVETICA, 15, Font.UNDEFINED));
        titleDoc.setAlignment(Element.TITLE);

        Paragraph underline1 = new Paragraph("_________________________________________________________________________________________________________________________________________________________________________________________________________________", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL));
        underline1.setAlignment(Element.ALIGN_CENTER);
        underline1.setSpacingAfter(0);

        Paragraph nameDoc = new Paragraph("Vagas disponíveis", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
        nameDoc.setAlignment(Element.TITLE);
        nameDoc.setSpacingAfter(8);
        nameDoc.setSpacingBefore(0);


        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/vagas_disponiveis.pdf"));
        document.open();
        document.add(topImageDoc);
        document.add(titleDoc);
        document.add(underline1);
        document.add(nameDoc);

        for (int i = 0; i < vagasDisponiveis.size(); i++) {
            Paragraph inadimplente = new Paragraph((i+1)+" - Cargo: " + vagasDisponiveis.get(i).getCargo().getNome() + ", salário: " + vagasDisponiveis.get(i).getSalario() + ", código: " + vagasDisponiveis.get(i).getId(),
                    new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
            inadimplente.setIndentationLeft(20);
            inadimplente.setSpacingAfter(8);

            document.add(inadimplente);
        }

        document.close();
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getVagasOcupadas( ) throws  Exception {

        List<Vaga> vagasDisponiveis = this.daoVaga.find(SituacaoVaga.OCUPADA);

        Image topImageDoc = Image.getInstance("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/title.png");
        topImageDoc.setAlignment(Element.ALIGN_CENTER);

        Paragraph titleDoc = new Paragraph("Um mundo de oportunidades para VOCÊ!", new Font(Font.FontFamily.HELVETICA, 15, Font.UNDEFINED));
        titleDoc.setAlignment(Element.TITLE);

        Paragraph underline1 = new Paragraph("_________________________________________________________________________________________________________________________________________________________________________________________________________________", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL));
        underline1.setAlignment(Element.ALIGN_CENTER);
        underline1.setSpacingAfter(0);

        Paragraph nameDoc = new Paragraph("Vagas ocupadas", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
        nameDoc.setAlignment(Element.TITLE);
        nameDoc.setSpacingAfter(8);
        nameDoc.setSpacingBefore(0);


        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/vagas_ocupadas.pdf"));
        document.open();
        document.add(topImageDoc);
        document.add(titleDoc);
        document.add(underline1);
        document.add(nameDoc);

        for (int i = 0; i < vagasDisponiveis.size(); i++) {
            Paragraph inadimplente = new Paragraph((i+1)+" - Cargo: " + vagasDisponiveis.get(i).getCargo().getNome() + ", salário: " + vagasDisponiveis.get(i).getSalario() + ", código: " + vagasDisponiveis.get(i).getId(),
                    new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
            inadimplente.setIndentationLeft(20);
            inadimplente.setSpacingAfter(8);

            document.add(inadimplente);
        }

        document.close();
    }

    @Test
    @Rollback(false)
    @Transactional
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

    @Test
    @Rollback(false)
    @Transactional
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
