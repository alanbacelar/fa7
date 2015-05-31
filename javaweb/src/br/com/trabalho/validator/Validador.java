package br.com.trabalho.validator;

public class Validador {

	public static boolean validarCPF(String cpf) {
		/**if (cpf.equals("00000000000") || cpf.equals("11111111111")
				|| cpf.equals("22222222222") || cpf.equals("33333333333")
				|| cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777")
				|| cpf.equals("88888888888") || cpf.equals("99999999999")
				|| cpf.length() < 11)
			return (false);*/

		char dig1, dig2;
		int soma, i, r, num, peso;

		soma = 0;
		peso = 10;

		// Cálculo do 1º digito verificador
		for (i = 0; i < 9; i++) {
			num = (int) (cpf.charAt(i) - 48);
			soma = soma + (num * peso);
			peso--;
		}

		r = 11 - (soma % 11);
		if ((r == 10) || (r == 11))
			dig1 = '0';
		else
			dig1 = (char) (r + 48);

		soma = 0;
		peso = 11;
		for (i = 0; i < 10; i++) {
			num = (int) (cpf.charAt(i) - 48);
			soma = soma + (num * peso);
			peso = peso - 1;
		}
		r = 11 - (soma % 11);
		if ((r == 10) || (r == 11))
			dig2 = '0';
		else
			dig2 = (char) (r + 48);

		if ((dig1 == cpf.charAt(9)) && (dig2 == cpf.charAt(10)))
			return (true);
		else
			return (false);
	}

	public static boolean validarCep(String regiao, String sufixo) {
		if (regiao.trim().length() == 5 && sufixo.trim().length() == 3)
			return true;
		else
			return false;
	}
}
