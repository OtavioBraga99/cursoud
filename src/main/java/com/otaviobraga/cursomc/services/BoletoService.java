package com.otaviobraga.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Service;

import com.otaviobraga.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Data instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
