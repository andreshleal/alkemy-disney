package com.alkemy.disney.dto;

public class JWTAuthResponseDTO {

	private String tokenAcceso;
	private String tipoDeToken = "Bearer";
	
	public JWTAuthResponseDTO(String tokenAcceso, String tipoDeToken) {
		super();
		this.tokenAcceso = tokenAcceso;
		this.tipoDeToken = tipoDeToken;
	}
	
	public JWTAuthResponseDTO(String tokenAcceso) {
		super();
		this.tokenAcceso = tokenAcceso;		
	}

	public String getTokenAcceso() {
		return tokenAcceso;
	}

	public void setTokenAcceso(String tokenAcceso) {
		this.tokenAcceso = tokenAcceso;
	}

	public String getTipoDeToken() {
		return tipoDeToken;
	}

	public void setTipoDeToken(String tipoDeToken) {
		this.tipoDeToken = tipoDeToken;
	}
	
	
	
	
}
