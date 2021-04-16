package br.com.tecban.dto;

import javax.validation.constraints.NotBlank;

public class ContatoDto {
	
	
	private  int id;	
	@NotBlank
	private  String contatoNome;	
	@NotBlank
	private  String contatoEmail;
	@NotBlank
	private  String contatoTelefone;
	
	
	
	public  ContatoDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContatoNome() {
		return contatoNome;
	}

	public void setContatoNome(String contatoNome) {
		this.contatoNome = contatoNome;
	}

	public String getContatoEmail() {
		return contatoEmail;
	}

	public void setContatoEmail(String contatoEmail) {
		this.contatoEmail = contatoEmail;
	}

	public String getContatoTelefone() {
		return contatoTelefone;
	}

	public void setContatoTelefone(String contatoTelefone) {
		this.contatoTelefone = contatoTelefone;
	}

	


}
