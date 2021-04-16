package br.com.tecban.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecban.dto.ContatoDto;
import br.com.tecban.dto.ResponseDto;
import br.com.tecban.service.ContatoService;

@Controller
@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("v1")
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
	@RequestMapping(value="admin/contato",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ContatoDto> insertContato(@RequestBody @Valid ContatoDto contatoDto)  throws Exception, Throwable {      
		
		log.info("ContatoController.insertContato--------> INCLUINDO CONTATO!");		
				 
		this.contatoService.addContatoDto(contatoDto);		
		return new ResponseEntity<>( HttpStatus.CREATED);
	}
	
	
	/**
	 * ATUALIZAR CONTATO
	 * @param contatoDto
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */	
	@RequestMapping(value="admin/contato", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseDto updateContato(@RequestBody ContatoDto contatoDto)  throws Exception, Throwable {

		log.info("ContatoController.updateContato--------> ATUALIZAR CONTATO!");		
		try {			 
			this.contatoService.updateContatoDto(contatoDto);	
			return new ResponseDto(1,"Contato atualizado com sucesso!"); 
		}catch(Exception e) { 
			log.error("ContatoController.updateContato----------------- ERRO AO ATUALIZAR CONTATO: " , e.toString());
			return new ResponseDto(0,"Erro ocorrido na atualização do contato --> "+e.getMessage());
		}
	}
	
	
	/**
	 * LISTAR TODOS OS CONTATOS
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="protected/contato", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ContatoDto> selectContatoAll()  throws Exception, Throwable {
		
		log.info("ContatoController.selectContatoAll--------> LISTAR TODOS OS CONTATOS!");
		try {			
			return this.contatoService.getAllContatos();
		} catch (Exception e) {
			log.error("ContatoController.selectContatoAll----------------- ERRO AO LISTAR CONTATO: " + e.toString());
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
	@RequestMapping(value="protected/contato/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ContatoDto selectContatoById(@PathVariable("id") int id)  throws Exception, Throwable {
		
		log.info("ContatoController.selectContatoById--------> CONSULTAR CONTATO!");
		try {			
			return this.contatoService.getContatoById(id);
		} catch (Exception e) {
			log.error("ContatoController.selectContatoById----------------- ERRO AO BUSCAR CONTATO POR ID: " + e.toString());
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
	@RequestMapping(value="admin/contato/{id}", method = RequestMethod.DELETE, produces = "application/json")
//	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody ResponseDto deleteContatoById(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {
		
		log.info("ContatoController.deleteContatoById--------> EXCLUIR CONTATO!");
		try { 
			contatoService.deleteContatoById(id);
			return new ResponseDto(1, "Contato excluido com sucesso!"); 
		}catch(Exception e) {
			log.error("ContatoController.deleteContatoById----------------- ERRO AO EXCLUIR CONTATO POR ID: " + e.toString());
			return new ResponseDto(0,"Erro ocorrido na exclusão do contato --> "+ e.getMessage());
		}
	}	

}
