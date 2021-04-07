package br.com.tecban.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
/**
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
**/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecban.dto.ResponseDto;
import br.com.tecban.dto.ContatoDto;
import br.com.tecban.service.ContatoService;

@Controller
@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class ContatoController {
	
	private static final Logger log = LoggerFactory.getLogger(ContatoController.class);
	
	@Autowired
	private ContatoService contatoService;
	
	/**
	 * INCLUIR CONTATO
	 * @param contatoDto
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/contato",method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto insertContato(@RequestBody ContatoDto contatoDto)  throws Exception, Throwable {      

		log.info("ContatoController.salvar--------> INCLUINDO CONTATO!");		
		try {			 
			this.contatoService.addContatoDto(contatoDto);
			return new ResponseDto(1,"Contato gravado com sucesso!"); 
		}catch(Exception e) {
			log.error("ContatoController.salvar----------------- ERRO AO INCLUIR CONTATO: " + e.toString());
			return new ResponseDto(0,"Erro ocorrido na gravação do contato --> "+ e.getMessage());			
		}
	}
	
	
	/**
	 * ATUALIZAR CONTATO
	 * @param contatoDto
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */	
	@RequestMapping(value="/contato", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseDto updateContato(@RequestBody ContatoDto contatoDto)  throws Exception, Throwable {

		log.info("ContatoController.atualizar--------> ATUALIZAR CONTATO!");		
		try {			 
			this.contatoService.updateContatoDto(contatoDto);	
			return new ResponseDto(1,"Contato atualizado com sucesso!"); 
		}catch(Exception e) { 
			log.error("ContatoController.atualizar----------------- ERRO AO ATUALIZAR CONTATO: " , e.toString());
			return new ResponseDto(0,"Erro ocorrido na atualização do contato --> "+e.getMessage());
		}
	}
	
	
	/**
	 * LISTAR TODOS OS CONTATOS
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/contato", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ContatoDto> selectContatoAll()  throws Exception, Throwable {
		
		log.info("ContatoController.consultar--------> LISTAR DE CONTATOS!");
		try {			
			return this.contatoService.getAllContatos();
		} catch (Exception e) {
			log.error("ContatoController.consultar----------------- ERRO AO LISTAR CONTATO: " + e.toString());
		}
		return null;
	}


	/**
	 * CONSULTAR CONTATO POR ID
	 * @param idContato
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/contato/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ContatoDto selectContatoById(@PathVariable("id") int id)  throws Exception, Throwable {
		
		log.info("ContatoController.buscar--------> CONSULTAR CONTATO!");
		try {			
			return this.contatoService.getContatoById(id);
		} catch (Exception e) {
			log.error("ContatoController.buscar----------------- ERRO AO BUSCAR CONTATO POR ID: " + e.toString());
		}
		return null;
	}
	

	/**
	 * EXCLUIR CONTATO POR ID
	 * @param idContato
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/contato/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody ResponseDto deleteContatoById(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {
		
		log.info("ContatoController.excluir--------> EXCLUIR CONTATO!");
		try { 
			contatoService.deleteContatoById(id);
			return new ResponseDto(1, "Contato excluido com sucesso!"); 
		}catch(Exception e) {
			log.error("ContatoController.excluir----------------- ERRO AO EXCLUIR CONTATO POR ID: " + e.toString());
			return new ResponseDto(0,"Erro ocorrido na exclusão do contato --> "+ e.getMessage());
		}
	}	

}
