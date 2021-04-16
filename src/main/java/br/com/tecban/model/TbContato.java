package br.com.tecban.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_contato")
public class TbContato {
	
	@Id
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(name="tb_contato_nome")
	private String tbContatoNome;

	@Column(name="tb_contato_email")
	private String tbContatoEmail;

	@Column(name="tb_contato_telefone")
	private String tbContatoTelefone;
	
	public TbContato() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTbContatoNome() {
		return tbContatoNome;
	}

	public void setTbContatoNome(String tbContatoNome) {
		this.tbContatoNome = tbContatoNome;
	}

	public String getTbContatoEmail() {
		return tbContatoEmail;
	}

	public void setTbContatoEmail(String tbContatoEmail) {
		this.tbContatoEmail = tbContatoEmail;
	}

	public String getTbContatoTelefone() {
		return tbContatoTelefone;
	}

	public void setTbContatoTelefone(String tbContatoTelefone) {
		this.tbContatoTelefone = tbContatoTelefone;
	}


}
