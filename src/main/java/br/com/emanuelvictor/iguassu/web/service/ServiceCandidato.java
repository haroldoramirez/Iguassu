package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.entity.SituacaoCandidato;
import br.com.emanuelvictor.iguassu.web.entity.TipoLancamento;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import br.com.emanuelvictor.iguassu.web.repository.DAOCandidato;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import br.com.emanuelvictor.iguassu.web.repository.job.DAOExperiencia;
import br.com.emanuelvictor.iguassu.web.repository.schooling.DAOCandidatoCurso;
import br.com.emanuelvictor.iguassu.web.util.ConstructorContract;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
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



    public Candidato save(Candidato candidato, Lancamento lancamento) {
        if (candidato.getId() == null) {
            candidato.setSituacao(SituacaoCandidato.BLOQUEADO);
            lancamento.setValor(30.00);
            lancamento.setDataDeVencimento(Calendar.getInstance());
            lancamento.setTipoLancamento(TipoLancamento.ENTRADA);
            lancamento.setDescricao("Cadastro de candidato");
            candidato = daoCandidato.save(candidato);
            lancamento.setPessoa(candidato);
            this.daoLancamento.save(lancamento);
            return candidato;
        }else if (candidato.getSituacao()== SituacaoCandidato.DISPONIVEL){

            Candidato candidatoAux = this.daoCandidato.findOne(candidato.getId());
            if (candidatoAux.getSituacao()==SituacaoCandidato.BLOQUEADO){
                Lancamento lancamentoAux = this.daoLancamento.getByIdPessoa(candidato.getId()).getLast();
                if (lancamentoAux!=null){
                    lancamentoAux.setUsuario(lancamento.getUsuario());
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

    public Candidato save(Candidato candidato) {
            return this.daoCandidato.save(candidato);
    }

    public Candidato uploadPhoto(String id,MultipartFile file) {
        Candidato candidato = this.daoCandidato.findOne(Long.parseLong(id));
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
//        Candidato candidato  =this.daoCandidato.findOne(id);
//
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuel/Projetos/Iguassu/src/main/webapp/app/reports/candidatos/Contrato_" + candidato.getId() + ".pdf"));
//        document.open();
//
//        Paragraph titleDoc = new Paragraph("Um mundo de oportunidades para VOCÊ", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
//        titleDoc.setAlignment(Element.ALIGN_CENTER);
//
//        Paragraph underline1 = new Paragraph("________________________________________________________________", new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL));
//        underline1.setAlignment(Element.ALIGN_CENTER);
//        underline1.setSpacingAfter(5);
//        Paragraph nameDoc = new Paragraph("Contrato de prestação de serviços", new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL));
//        nameDoc.setAlignment(Element.ALIGN_CENTER);
//        nameDoc.setSpacingAfter(8);
//
//        Paragraph p1 = (new Paragraph("Pelo presente instrumento particular de contrato de prestação de serviços, de um " +
//                "lado \b"+candidato.getNome()+", portador (a) do RG: \n" +
//                "_______________________ e inscrito (a) no CPF sob o nº _______________________, residente na \n" +
//                "________________________________________________________________________ doravante \n" +
//                "denominado simplesmente CONTRATANTE; de outro lado IGUASSU AGÊNCIA DE EMPREGOS, \n" +
//                "localizada na AVENIDA JUSCELINO KUBITSCHEK – 201 – GALERIA WANNI – SALA 14 - CENTRO – FOZ \n" +
//                "DO IGUAÇU – PR, doravante denominada simplesmente CONTRATADA, convencionam:", new Font(Font.FontFamily.COURIER, 10, Font.ITALIC)));
//        p1.setAlignment(Element.ALIGN_JUSTIFIED);
//
//        document.add(titleDoc);
//        document.add(underline1);
//        document.add(nameDoc);
//        document.add(p1);
//
////        document.add
//        document.close();
//        //TODO GAMBIA
//        String[] reponses = new String[]{"/app/Iguassu/app/home/emanuel/Projetos/Iguassu/src/main/webapp/app/reports/candidatos/Contrato_"+candidato.getId()+".pdf"};
////        reponses[0] = "/app/Iguassu/app/home/emanuel/Projetos/Iguassu/src/main/webapp/app/reports/contrato_candidato_"+id+".pdf";
////        return "redirect:/app/Iguassu/app/home/emanuel/Projetos/Iguassu/src/main/webapp/app/reports/contrato_candidato_"+id+".pdf";
        return ConstructorContract.getContractCandidate(this.daoCandidato.findOne(id));
    }

    public List<Candidato> find() {
        return daoCandidato.findAll();
    }

    public Candidato find(Long id) {
        return daoCandidato.findOne(id);
    }


    public Experiencia save(Experiencia experiencia) {
        return daoExperiencia.save(experiencia);
    }

    public CandidatoCurso save(CandidatoCurso candidatoCurso) {
        return daoCandidatoCurso.save(candidatoCurso);
    }

    public List<Experiencia> findExperiencias(Long id) {
        return daoExperiencia.find(id);
    }

    public List<CandidatoCurso> findCursos(Long id) {
        return daoCandidatoCurso.find(id);
    }

    public void deleteExperiencia(Long id){
        this.daoExperiencia.delete(id);
    }

    public void deleteCurso(Long id){
        this.daoCandidatoCurso.delete(id);
    }

}