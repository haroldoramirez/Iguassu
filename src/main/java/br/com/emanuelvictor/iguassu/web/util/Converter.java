package br.com.emanuelvictor.iguassu.web.util;

import java.io.UnsupportedEncodingException;

public class Converter {
	public final static String enconding(String str) {
		if (str != null) {
			try {
				return new String(str.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "Erro ao converter " + str + " " + e.getMessage();
			}
		} else {
			return str;
		}

	}

//	public final static Empresa enconding(Empresa empresa) {
//		if (empresa.getComplemento() != null) {
//			empresa.setComplemento(enconding(empresa.getComplemento()));
//		}
//		if (empresa.getRua() != null) {
//			empresa.setRua(enconding(empresa.getRua()));
//		}
//		if (empresa.getNome() != null) {
//			empresa.setNome(enconding(empresa.getNome()));
//		}
//		return empresa;
//	}
}
