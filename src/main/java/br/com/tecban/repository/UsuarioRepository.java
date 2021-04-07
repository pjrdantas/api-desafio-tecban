package br.com.tecban.repository;

import java.util.List;

import br.com.tecban.dto.UsuarioDto;



public interface UsuarioRepository {
	
	/**
	 * INCLUIR USUARIO
	 * @param usuarioDto
	 * @throws Exception
	 * @throws Throwable
	 */
	void addUsuarioDto(UsuarioDto usuarioDto)  throws Exception, Throwable;
	
	/**
	 * EDITAR USUARIO
	 * @param usuarioDto
	 * @throws Exception
	 * @throws Throwable
	 */
	void updateUsuarioDto(UsuarioDto usuarioDto)  throws Exception, Throwable;
	
	/**
	 * LISTAR TODOS USUARIOS
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	List<UsuarioDto> getAllUsuarios()  throws Exception, Throwable;
	
	/**
	 * CONSULTAR USUARIO POR ID
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	UsuarioDto getUsuarioById(int id)  throws Exception, Throwable;
	
	/**
	 * CONSULTAR USUARIO POR LOGIN
	 * @param usuarioLoginDto
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	UsuarioDto getUsuarioByLogin(String usuarioLogin) throws Exception, Throwable;
	
	/**
	 * APAGAR USUARIO POR ID
	 * @param id
	 * @throws Exception
	 * @throws Throwable
	 */
	void deleteUsuarioById(int id)  throws Exception, Throwable;
}
