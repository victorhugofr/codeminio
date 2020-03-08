package com.codeminio.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenhaHelper {

//	@Autowired
//	private PasswordEncoder encoder;
//		
//	/**
//	 * Verifica 
//	 * 
//	 * @param senhaReferencia Senha de referncia para ser comparada com a senha informada
//	 * @param senhaBruta Senha que deve ser codificada e comparada com a de referncia
//	 * @return
//	 */
//    public boolean verificarSenha(String senhaReferencia, String senhaBruta){
//    		if(encoder.matches(senhaBruta, senhaReferencia))
//    			return true;
//    		return false;
//    }
//    
//    /** Gera uma senha utilizando o login e e a senha como base da criptografia*/
//	public String gerarSenha(String senha) {
//		return encoder.encode(senha);
//	}
	
	/**
	 * Mtodo que ir gerar uma senha aleatria.
	 * 
	 * @param login
	 * @return
	 */
	public String gerarSenhaAleatoria(){
		int i1 = (int) (Math.random()*10);
		int i2 = (int) (Math.random()*10);
		int i3 = (int) (Math.random()*10);
		int i4 = (int) (Math.random()*10);
		int i5 = (int) (Math.random()*10);
		int i6 = (int) (Math.random()*10);
		
		return ""+ i1+ i2 + i3 + i4+ i5+ i6;
	}
}
