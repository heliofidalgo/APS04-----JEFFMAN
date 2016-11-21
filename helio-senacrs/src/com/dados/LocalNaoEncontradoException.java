package com.dados;
//classe excessao local nao encontrado
public class LocalNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 895919196069946577L;

	public LocalNaoEncontradoException() {
		super("LOCAL NÃO ENCONTRADO!");
	}

}