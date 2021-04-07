package br.com.tecban.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_usuario")
public class TbUsuario {
	
	@Id
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(name="tb_usuario_login", unique=true)
	private String tbUsuarioLogin;
	
	@Column(name="tb_usuario_senha")
	private String tbUsuarioSenha;
	
	@Column(name="tb_usuario_nome")
	private String tbUsuarioNome;
	
	@Column(name="tb_usuario_admin")
	private boolean tbUsuarioAdmin;
	
	public TbUsuario() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTbUsuarioLogin() {
		return tbUsuarioLogin;
	}

	public void setTbUsuarioLogin(String tbUsuarioLogin) {
		this.tbUsuarioLogin = tbUsuarioLogin;
	}

	public String getTbUsuarioSenha() {
		return tbUsuarioSenha;
	}

	public void setTbUsuarioSenha(String tbUsuarioSenha) {
		this.tbUsuarioSenha = tbUsuarioSenha;
	}

	public String getTbUsuarioNome() {
		return tbUsuarioNome;
	}

	public void setTbUsuarioNome(String tbUsuarioNome) {
		this.tbUsuarioNome = tbUsuarioNome;
	}

	public boolean isTbUsuarioAdmin() {
		return tbUsuarioAdmin;
	}

	public void setTbUsuarioAdmin(boolean tbUsuarioAdmin) {
		this.tbUsuarioAdmin = tbUsuarioAdmin;
	}


	
}
