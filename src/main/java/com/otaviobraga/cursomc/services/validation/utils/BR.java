package com.otaviobraga.cursomc.services.validation.utils;

public class BR {

	public static boolean isValidCpf(String cpf) {

		cpf = cpf.replaceAll("[^0-9]", "");

		if (cpf.length() != 11) {
			return false;
		}
		
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		int remainder = 11 - (sum % 11);
		if (remainder == 10 || remainder == 11) {
			remainder = 0;
		}
		if (remainder != Character.getNumericValue(cpf.charAt(9))) {
			return false;
		}

		sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		remainder = 11 - (sum % 11);
		if (remainder == 10 || remainder == 11) {
			remainder = 0;
		}
		return remainder == Character.getNumericValue(cpf.charAt(10));
	}

	public static boolean isValidCnpj(String cnpj) {

		cnpj = cnpj.replaceAll("[^0-9]", "");

		if (cnpj.length() != 14) {
			return false;
		}

		int[] weights = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			sum += Character.getNumericValue(cnpj.charAt(i)) * weights[i];
		}
		int remainder = sum % 11;
		if (remainder < 2) {
			if (Character.getNumericValue(cnpj.charAt(12)) != 0) {
				return false;
			}
		} else {
			if (Character.getNumericValue(cnpj.charAt(12)) != 11 - remainder) {
				return false;
			}
		}

		weights = new int[] { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		sum = 0;
		for (int i = 0; i < 13; i++) {
			sum += Character.getNumericValue(cnpj.charAt(i)) * weights[i];
		}
		remainder = sum % 11;
		if (remainder < 2) {
			if (Character.getNumericValue(cnpj.charAt(13)) != 0) {
				return false;
			}
		} else {
			if (Character.getNumericValue(cnpj.charAt(13)) != 11 - remainder) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String cpf = "123.456.789-09";
		String cnpj = "12.345.678/0001-01";

		if (isValidCpf(cpf)) {
			System.out.println("CPF válido.");
		} else {
			System.out.println("CPF inválido.");
		}

		if (isValidCnpj(cnpj)) {
			System.out.println("CNPJ válido.");
		} else {
			System.out.println("CNPJ inválido.");
		}
	}
}
