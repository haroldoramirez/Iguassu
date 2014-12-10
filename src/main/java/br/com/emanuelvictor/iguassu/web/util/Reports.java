package br.com.emanuelvictor.iguassu.web.util;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import br.com.emanuelvictor.iguassu.web.entity.Encaminhamento;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * Created by emanuel on 07/11/14.
 */
public abstract class Reports {

    public final static String[] getCandidatosInadimplentes() throws  Exception {
        return new String[]{"/app/Iguassu/app/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos/Encaminhamento_.pdf"};
    }

    public final static String[] getContratosVencidos( ) throws  Exception {
        return new String[]{"/app/Iguassu/app/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos/Encaminhamento_.pdf"};
    }

    public final static String[] getVagasDisponiveis( ) throws  Exception {
        return new String[]{"/app/Iguassu/app/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos/Encaminhamento_.pdf"};
    }

    public final static String[] getVagasOcupadas( ) throws  Exception {
        return new String[]{"/app/Iguassu/app/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos/Encaminhamento_.pdf"};
    }

    public final static String[] getEncaminhamentosNaoPagos( ) throws  Exception {
        return new String[]{"/app/Iguassu/app/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos/Encaminhamento_.pdf"};
    }

    public final static String[] getEncaminhamentosEmAndamento( ) throws  Exception {
        return new String[]{"/app/Iguassu/app/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos/Encaminhamento_.pdf"};
    }

}
