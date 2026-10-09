package aplication;

import java.util.Scanner;

public class CreditAnalyzer {
	public static void main(String[] args) {

		String clienteNome;
		double rendaMensal;
		double valorEmprestimo;
		int parcelasDesejadas;
		int scoreCredito;

		double valorParcelaBase = 0.0;
		double jurosAplicados = 0.0;
		double valorParcelaFinal = 0.0;
		double totalPagarComJuros = 0.0;
		double percentualComprometimento = 0.0;

		String statusAnalise = "";
		String motivoNegativa = "";

		Scanner scanner = new Scanner(System.in);

		System.out.println("=== FINTECH SOLUTIONS - MOTOR DE ANÁLISE DE CRÉDITO ===");
		System.out.print("Nome completo do proponente: ");
		clienteNome = scanner.nextLine();

		System.out.print("Renda mensal comprovada (R$): ");
		rendaMensal = scanner.nextDouble();

		System.out.print("Valor do empréstimo solicitado (R$): ");
		valorEmprestimo = scanner.nextDouble();

		System.out.print("Quantidade de parcelas (meses): ");
		parcelasDesejadas = scanner.nextInt();

		System.out.print("Pontuação de Score de Crédito (0 a 1000): ");
		scoreCredito = scanner.nextInt();
		scanner.nextLine();

		if (scoreCredito < 300) {
			statusAnalise = "REPROVADO";
			motivoNegativa = "Score de crédito abaixo da linha de risco mínimo (Perfil Alto Risco).";
		} else {

			if (scoreCredito >= 700) {
				jurosAplicados = 0.02;
			} else {
				jurosAplicados = 0.05;
			}

			valorParcelaBase = valorEmprestimo / parcelasDesejadas;
			valorParcelaFinal = valorParcelaBase + (valorEmprestimo * jurosAplicados);
			totalPagarComJuros = valorParcelaFinal * parcelasDesejadas;

			percentualComprometimento = (valorParcelaFinal / rendaMensal) * 100;

			if (percentualComprometimento > 30.0) {
				statusAnalise = "REPROVADO";
				motivoNegativa = "O valor da parcela ultrapassa o limite prudencial de 30% da renda mensal.";
			} else {
				statusAnalise = "APROVADO";
			}
		}

		System.out.println("\n========================================");
		System.out.println("       PARECER TÉCNICO DE ANÁLISE       ");
		System.out.println("========================================");
		System.out.printf(" Solicitante      : %s\n", clienteNome);
		System.out.printf(" Score Cadastrado : %d pontos\n", scoreCredito);
		System.out.printf(" Renda Informada  : R$ %.2f\n", rendaMensal);
		System.out.println("----------------------------------------");
		System.out.printf(" STATUS DA ANÁLISE: [%s]\n", statusAnalise);
		System.out.println("----------------------------------------");

		if (statusAnalise.equals("APROVADO")) {
			System.out.printf(" [+] Valor da Parcela   : R$ %.2f /mês\n", valorParcelaFinal);
			System.out.printf(" [+] Taxa de Juros      : %.1f%% ao mês\n", (jurosAplicados * 100));
			System.out.printf(" [+] Total Financiado   : R$ %.2f (em %dx)\n", totalPagarComJuros, parcelasDesejadas);
			System.out.printf(" [+] Impacto na Renda   : %.1f%% de comprometimento\n", percentualComprometimento);
		} else {
			System.out.printf(" [!] Motivo da Recusa   : %s\n", motivoNegativa);
		}

		System.out.println("========================================");
		System.out.println("    Sistema Fintech Analytica © 2026   ");
		System.out.println("========================================");

		scanner.close();
	}
}
