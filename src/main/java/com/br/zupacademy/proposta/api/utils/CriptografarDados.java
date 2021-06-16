package com.br.zupacademy.proposta.api.utils;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class CriptografarDados {
	private final static CharSequence segredo = "Byb3YXBpLXBvc3";
	private final static CharSequence salto = "f028d35175f4";
	
	
	public static String criptografaTexto(String texto) {
		TextEncryptor encryptor = Encryptors.queryableText(segredo, salto);
		return encryptor.encrypt(texto);
	}
	
	public static String descriptografarTexto(String texto) {
		TextEncryptor encryptor = Encryptors.queryableText(segredo, salto);
		return encryptor.decrypt(texto);
	}
}
