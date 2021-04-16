package br.com.tecban.service;

import java.util.List;

import br.com.tecban.dto.ContatoDto;




public interface ContatoService {
	
	/**
	 * SERVIÇO PARA INCLUIR CONTATO
	 * @param contatoDto
	 * @throws Exception
	 * @throws Throwable
	 */
	void addContatoDto(ContatoDto contatoDto)   throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA ATUALIZAR CONTATO
	 * @param contatoDto
	 * @throws Exception
	 * @throws Throwable
	 */
	void updateContatoDto(ContatoDto contatoDto)  throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA LISTAR TOS CONTATOS
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	List<ContatoDto> getAllContatos()  throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA CONSULTAR CONTATO POR ID
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	ContatoDto getContatoById(int id)  throws Exception, Throwable;
	
	/**
	 * SERVIÇO PARA EXCLUIR CONTATO POR ID
	 * @param id
	 * @throws Exception
	 * @throws Throwable
	 */
	void deleteContatoById(int id)  throws Exception, Throwable;

	
}
