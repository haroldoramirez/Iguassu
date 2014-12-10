package br.com.emanuelvictor.iguassu.web.service.job.vacancy;

import br.com.emanuelvictor.iguassu.web.entity.SituacaoVaga;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.repository.job.vacancy.DAOVaga;
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
public class ServiceVaga {

	@Autowired
	DAOVaga daoVaga;

	public Vaga save(Vaga vaga) {

//		System.out.println("Salvando vaga" + vaga);
//		try {
//			if (vaga.getEmpresa().getId()==null) {
//				vaga.setEmpresa(null);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			vaga.setEmpresa(null);
//		}
		

		if (vaga.getSituacao() == null) {
			vaga.setSituacao(SituacaoVaga.DISPONIVEL);
		}
        vaga.setDataDeAlteracao(Calendar.getInstance());
        try{
            vaga = daoVaga.save(vaga);
        }catch (Exception e){
            vaga.setEmpresa(null);
            vaga = daoVaga.save(vaga);
        }
		return vaga;
	}

	public void delete(Long id) {
		daoVaga.delete(id);
	}

	public List<Vaga> find() {
		return daoVaga.findAll();
	}

    public List<Vaga> find(Vaga vaga, PageRequest pageRequest) {
        if (vaga.getEndereco().getBairro().getCidade().getEstado().getPais().getId() == null) {
            return daoVaga.find(vaga.getId(), vaga.getSituacao(), vaga.getCargo().getId(),
                    vaga.getSalario(), vaga.getEmpresa().getId(), vaga.getObservacoes(),
                    vaga.getEndereco().getRua(), vaga.getEndereco().getNumero(),
                    vaga.getEndereco().getCep(), vaga.getEndereco().getComplemento(),
                    pageRequest);
        }

        return daoVaga.find(vaga.getId(), vaga.getSituacao(), vaga.getCargo().getId(),
                vaga.getSalario(), vaga.getEmpresa().getId(), vaga.getObservacoes(),
                vaga.getEndereco().getRua(), vaga.getEndereco().getNumero(),
                vaga.getEndereco().getCep(), vaga.getEndereco().getComplemento(),
                vaga.getEndereco().getBairro().getId(),
                vaga.getEndereco().getBairro().getCidade().getId(),
                vaga.getEndereco().getBairro().getCidade().getEstado().getId(),
                vaga.getEndereco().getBairro().getCidade().getEstado().getPais().getId(),
                pageRequest);
    }

	public Vaga find(Long id) {
		return daoVaga.findOne(id);
	}


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
}