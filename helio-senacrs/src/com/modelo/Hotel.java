package com.modelo;

import java.io.Serializable;
//classe hotel que vai ser criado o objeto generico
public class Hotel implements Serializable {

	private static final long serialVersionUID = 854864654654654L;

	private String nome;

	private String bairro;

	private String endereco;

	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Override
	public String toString() {
		return "ColetaOleo [nome=" + nome + ", bairro=" + bairro + ", endereco=" + endereco + ", telefone=" + telefone
				+ "]";
	}

}