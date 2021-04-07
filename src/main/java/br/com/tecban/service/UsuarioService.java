package br.com.tecban.service;

import java.util.List;

import br.com.tecban.dto.UsuarioDto;




public interface UsuarioService {
	
	/**
	 * SERVIÇO PARA INCLUIR USUARIO
	 * @param usuarioDto
	 * @throws Exception
	 * @throws Throwable
	 */
	void addUsuarioDto(UsuarioDto usuarioDto)   throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA ATUALIZAR USUARIO
	 * @param usuarioDto
	 * @throws Exception
	 * @throws Throwable
	 */
	void updateUsuarioDto(UsuarioDto usuarioDto)  throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA LISTAR TOS USUARIOS
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	List<UsuarioDto> getAllUsuarios()  throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA CONSULTAR USUARIO POR ID
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	UsuarioDto getUsuarioById(int id)  throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA CONSULTAR USUARIO POR LOGIN
	 * @param usuarioLoginDto
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	UsuarioDto getUsuarioByLogin(String usuarioLogin) throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA EXCLUIR USUARIO POR ID
	 * @param id
	 * @throws Exception
	 * @throws Throwable
	 */
	void deleteUsuarioById(int id)  throws Exception, Throwable;
}
