package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.entity.TipoLancamento;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServiceLancamento {

	@Autowired
	DAOLancamento daoLancamento;


	public Lancamento save(Lancamento lancamento) {
        return daoLancamento.save(lancamento);
	}

	public void delete(Long id) {
		daoLancamento.delete(id);
	}

	public List<Lancamento> find() {
		return daoLancamento.findAll();
	}

    public List<Lancamento> find(Date data, Date dataDeVencimento, Date dataDePagamento, PageRequest pageRequest) {
        Calendar calendarData =  null;
        Calendar calendarDataDeVencimento =  null;
        Calendar calendarDataDePagamento =  null;
        if (data!=null){
            calendarData = Calendar.getInstance();
            calendarData.setTime(data);
            System.out.println(new SimpleDateFormat().format(calendarData.getTime()));
            System.out.println(new SimpleDateFormat().format(data));
        }
        if (dataDeVencimento!=null){
            calendarDataDeVencimento = Calendar.getInstance();
            calendarDataDeVencimento.setTime(dataDeVencimento);
        }
        if (dataDePagamento!=null){
            calendarDataDePagamento = Calendar.getInstance();
            calendarDataDePagamento.setTime(dataDePagamento);
        }
        return daoLancamento.query(calendarData, calendarDataDeVencimento, calendarDataDePagamento, pageRequest);
    }

    public Double find(Date data, Date dataDeVencimento, Date dataDePagamento) {
        Calendar calendarData =  null;
        Calendar calendarDataDeVencimento =  null;
        Calendar calendarDataDePagamento =  null;
        if (data!=null){
            calendarData = Calendar.getInstance();
            calendarData.setTime(data);
            System.out.println(new SimpleDateFormat().format(calendarData.getTime()));
            System.out.println(new SimpleDateFormat().format(data));
        }
        if (dataDeVencimento!=null){
            calendarDataDeVencimento = Calendar.getInstance();
            calendarDataDeVencimento.setTime(dataDeVencimento);
        }
        if (dataDePagamento!=null){
            calendarDataDePagamento = Calendar.getInstance();
            calendarDataDePagamento.setTime(dataDePagamento);
        }

        Double total = 0.0;
        List<Lancamento> lancamentos = daoLancamento.query(calendarData, calendarDataDeVencimento, calendarDataDePagamento);
        for (int i = 0; i < lancamentos.size(); i++) {
            if (lancamentos.get(i).getTipoLancamento()== TipoLancamento.ENTRADA){
                total = total + lancamentos.get(i).getValor();
            }else{
                total = total - lancamentos.get(i).getValor();
            }
        }
        System.out.println("Total " + String.valueOf(total));
        return total;

    }



    public Lancamento find(Long id) {
		return daoLancamento.findOne(id);
	}

}