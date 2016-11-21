import java.util.List;
import java.util.Scanner;

import com.dados.HotelDataBase;
import com.modelo.Hotel;
// DADOS(CSV) retirados do site DATAPOA - Hoteis com acessibilidade

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// 1º PASSO: INICIA O PROCESSO - PEGA DADOS DO ARQUIVO E GUARDA NO HASH
		HotelDataBase.getInstance();

		// 2º PASSO: EXIBE OS BAIRROS DISPONIVEIS
		System.out.println("BAIRROS DISPONIVEIS:\n");
		for (String bairro : HotelDataBase.getInstance().getBairrosDisponiveis()) {
			System.out.println(bairro);
		}

		loading();

		// 3º SOLICITA QUE O USUARIO DIGITE UM BAIRRO
		System.err.print("\nDigite o nome do bairro: ");
		final String bairro = new Scanner(System.in).nextLine();

		// 4º EXIBE AS INFORMAÇÕES DOS HOTEIS, CONFORME O BAIRRO DESEJADO
		try {
			final List<Hotel> hoteis = HotelDataBase.getInstance().get(bairro.toUpperCase().trim());
			System.out.println(">>> LOCAIS ENCONTRADOS: ");
			for (final Hotel hotel : hoteis) {
				System.out.println("HOTEL: " + hotel.getNome());
				System.out.println("BAIRRO: " + hotel.getBairro());
				System.out.println("ENDEREÇO: " + hotel.getEndereco());
				System.out.println("TELEFONE: " + hotel.getTelefone());
				System.out.println();
			}
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("static-access")
	private static void loading() {
		try {
			Thread.currentThread().sleep(2000);
			for (int i = 0; i < 10; i++) {
				System.err.print("...");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}