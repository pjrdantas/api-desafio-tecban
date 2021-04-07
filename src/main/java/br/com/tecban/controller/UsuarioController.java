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
import br.com.tecban.dto.UsuarioDto;
import br.com.tecban.service.UsuarioService;

@Controller
@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * INCLUIR USUARIO
	 * @param usuarioDto
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/usuario",method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto insertUsuario(@RequestBody UsuarioDto usuarioDto, @AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {      

		log.info("UsuarioController.salvar--------> INCLUINDO USUARIO!");		
		try {			 
			this.usuarioService.addUsuarioDto(usuarioDto);
			return new ResponseDto(1,"Usuario salvo com sucesso!"); 
		}catch(Exception e) {
			log.error("UsuarioController.salvar----------------- ERRO AO INCLUIR USUARIO: " + e.toString());
			return new ResponseDto(0,e.getMessage());			
		}
	}
	
	
	/**
	 * ATUALIZAR USUARIO
	 * @param usuarioDto
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */	
	@RequestMapping(value="/usuario", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseDto updateUsuario(@RequestBody UsuarioDto usuarioDto, @AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {

		log.info("UsuarioController.atualizar--------> ATUALIZAR USUARIO!");		
		try {			 
			this.usuarioService.updateUsuarioDto(usuarioDto);	
			return new ResponseDto(1,"Usuario atualizado com sucesso!"); 
		}catch(Exception e) { 
			log.error("UsuarioController.atualizar----------------- ERRO AO ATUALIZAR USUARIO: " , e.toString());
			return new ResponseDto(0,e.getMessage());
		}
	}
	
	
	/**
	 * LISTAR TODOS OS USUARIOS
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/usuario", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<UsuarioDto> selectUsuario(@AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {
		
		log.info("UsuarioController.consultar--------> LISTAR DE USUARIOS!");
		try {			
			return this.usuarioService.getAllUsuarios();
		} catch (Exception e) {
			log.error("UsuarioController.consultar----------------- ERRO AO LISTAR USUARIO: " + e.toString());
		}
		return null;
	}


	/**
	 * CONSULTAR USUARIO POR LOGIN
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/usuario/{usuarioLogin}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UsuarioDto selectUsuarioById(@PathVariable("usuarioLogin") String usuarioLogin, @AuthenticationPrincipal UserDetails userDetails )  throws Exception, Throwable {
		
		log.info("UsuarioController.buscar--------> CONSULTAR USUARIO!");
		try {		
			return this.usuarioService.getUsuarioByLogin(usuarioLogin);
		} catch (Exception e) {
			log.error("UsuarioController.buscar----------------- ERRO AO BUSCAR USUARIO POR ID: " + e.toString());
		}
		return null;
	}

	/**
	 * CONSULTAR USUARIO POR ID
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UsuarioDto selectUpdateById(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails )  throws Exception, Throwable {
		
		log.info("UsuarioController.buscar--------> CONSULTAR USUARIO!");
		try {	
			return this.usuarioService.getUsuarioById(id);
		} catch (Exception e) {
			log.error("UsuarioController.buscar----------------- ERRO AO BUSCAR USUARIO POR ID: " + e.toString());
		}
		return null;
	}
	

	/**
	 * EXCLUIR USUARIO POR ID
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody ResponseDto deleteUsuarioById(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {
		
		log.info("UsuarioController.excluir--------> EXCLUIR USUARIO!");		 
		try { 
			usuarioService.deleteUsuarioById(id);
			return new ResponseDto(1, "Usuario excluido com sucesso!"); 
		}catch(Exception e) {
			log.error("UsuarioController.excluir----------------- ERRO AO EXCLUIR USUARIO POR ID: " + e.toString());
			return new ResponseDto(0, e.getMessage());
		}
	}	

}
