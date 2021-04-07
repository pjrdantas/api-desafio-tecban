package br.com.tecban.dto;

public class UsuarioDto {
	
	private int id;
	private String usuarioLogin;
	private String usuarioSenha;
	private String usuarioNome;
	private boolean usuarioAdmin;
	
	public UsuarioDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getUsuarioSenha() {
		return usuarioSenha;
	}

	public void setUsuarioSenha(String usuarioSenha) {
		this.usuarioSenha = usuarioSenha;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public boolean isUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(boolean usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}


}
