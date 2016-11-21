package com.dados;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.modelo.Hotel;
// classe para ler o arquivo e pegar as informacoes e listar com array
public class ReadFile {

	private List<Hotel> hotel = null;

	public ReadFile() {
		this.hotel = new ArrayList<>();
		this.getDadosDoArquivo();
	}

	@SuppressWarnings("resource")
	private void getDadosDoArquivo() {
		InputStream is = getClass().getResourceAsStream("../../hoteis.csv");
		try {
			Scanner entrada;
			entrada = new Scanner(is, "utf-8");
			while (entrada.hasNext()) {
				final String[] linhaDoArquivo = entrada.nextLine().split(";");
				final Hotel coleta = new Hotel();
				coleta.setNome(linhaDoArquivo[0]);
				coleta.setTelefone(linhaDoArquivo[1]);
				coleta.setEndereco((linhaDoArquivo[2]));
				coleta.setBairro(linhaDoArquivo[3]);
				this.hotel.add(coleta);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public List<Hotel> getInformacoesDosHoteis() {
		if (hotel == null) {
			return new ArrayList<Hotel>();
		}
		return hotel;
	}

}