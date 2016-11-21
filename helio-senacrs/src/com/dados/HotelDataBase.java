package com.dados;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.modelo.Hotel;

import estruturadados.CuckooHashing;
import estruturadados.KeyNotFoundException;

public class HotelDataBase {

	private static HotelDataBase instance = null;

	private static CuckooHashing<String, List<Hotel>> cuckooHash = null;

	private static Set<String> bairros = null;

	// criando um HASH CUKOO e pegando as informações e pesquisa por chave
	static {
		cuckooHash = new CuckooHashing<>(100);
		bairros = new HashSet<>(100);

		// lendo o arquivo
		
		ReadFile dadosDoArquivo = new ReadFile();

		for (final Hotel coleta : dadosDoArquivo.getInformacoesDosHoteis()) {

			try {
				final List<Hotel> hoteis = cuckooHash.get(coleta.getBairro().toUpperCase());
				hoteis.add(coleta);
				cuckooHash.put(coleta.getBairro(), hoteis);
			} catch (final KeyNotFoundException e) {
				final List<Hotel> hoteis = new ArrayList<>();
				hoteis.add(coleta);
				cuckooHash.put(coleta.getBairro().toUpperCase(), hoteis);
				bairros.add(coleta.getBairro().toUpperCase());
			}

		}
	}

	public static HotelDataBase getInstance() {
		if (instance == null) {
			instance = new HotelDataBase();
		}
		return instance;
	}

	public List<Hotel> get(final String key) {
		try {
			return cuckooHash.get(key);
		} catch (final KeyNotFoundException e) {
			throw new LocalNaoEncontradoException();
		}
	}

	public Set<String> getBairrosDisponiveis() {
		return bairros;
	}
}