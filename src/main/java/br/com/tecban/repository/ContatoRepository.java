package br.com.tecban.repository;

import java.util.List;

import br.com.tecban.dto.ContatoDto;



public interface ContatoRepository {
	
	/**
	 * INCLUIR CONTATO
	 * @param contatoDto
	 * @throws Exception
	 * @throws Throwable
	 */
	void addContatoDto(ContatoDto contatoDto)  throws Exception, Throwable;
	
	/**
	 * EDITAR CONTATO
	 * @param contatoDto
	 * @throws Exception
	 * @throws Throwable
	 */
	void updateContatoDto(ContatoDto contatoDto)  throws Exception, Throwable;
	
	/**
	 * LISTAR TODOS CONTATOS
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	List<ContatoDto> getAllContatos()  throws Exception, Throwable;
	
	/**
	 * CONSULTAR CONTATO POR ID
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	ContatoDto getContatoById(int id)  throws Exception, Throwable;
	
	/**
	 * APAGAR CONTATO POR ID
	 * @param id
	 * @throws Exception
	 * @throws Throwable
	 */
	void deleteContatoById(int id)  throws Exception, Throwable;
}
