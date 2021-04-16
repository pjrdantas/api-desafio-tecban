package br.com.tecban.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

import br.com.tecban.dto.ResponseDto;
import br.com.tecban.dto.UsuarioDto;
import br.com.tecban.service.UsuarioService;

@Controller
@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("v1")
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
	@RequestMapping(value="admin/usuario",method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody ResponseDto insertUsuario(@RequestBody UsuarioDto usuarioDto, @AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {      

		log.info("UsuarioController.insertUsuario--------> INCLUIR USUARIO!");		
		try {			 
			this.usuarioService.addUsuarioDto(usuarioDto);
			return new ResponseDto(200,"Usuario salvo com sucesso!"); 
		}catch(Exception e) {
			log.error("UsuarioController.insertUsuario----------------- ERRO AO INCLUIR USUARIO: " + e.toString());
			if(HttpStatus.FORBIDDEN != null) {
				return new ResponseDto(403,"Acesso negado!");
			} else {
				return new ResponseDto(0,e.getMessage());	
			}			
		}
	}
	
	
	/**
	 * ATUALIZAR USUARIO
	 * @param usuarioDto
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */	
	@RequestMapping(value="admin/usuario", method = RequestMethod.PUT, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody ResponseDto updateUsuario(@RequestBody UsuarioDto usuarioDto, @AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {

		log.info("UsuarioController.updateUsuario--------> ATUALIZAR USUARIO!");		
		try {			 
			this.usuarioService.updateUsuarioDto(usuarioDto);	
			return new ResponseDto(200,"Usuario atualizado com sucesso!"); 
		}catch(Exception e) { 
			log.error("UsuarioController.updateUsuario----------------- ERRO AO ATUALIZAR USUARIO: " , e.toString());
			if(HttpStatus.FORBIDDEN != null) {
				return new ResponseDto(403,"Acesso negado!");
			} else {
				return new ResponseDto(0,e.getMessage());	
			}
		}
	}
	
	
	/**
	 * LISTAR TODOS OS USUARIOS
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="admin/usuario", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody List<UsuarioDto> selectUsuario(@AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {
		log.info("UsuarioController.selectUsuario--------> LISTAR USUARIOS!");
		try {			
			return this.usuarioService.getAllUsuarios();
		} catch (Exception e) {
			log.error("UsuarioController.selectUsuario----------------- ERRO AO LISTAR USUARIO: " + e.toString());
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
	@RequestMapping(value="admin/usuario/{usuarioLogin}", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody UsuarioDto selectUsuarioByLogin(@PathVariable("usuarioLogin") String usuarioLogin, @AuthenticationPrincipal UserDetails userDetails )  throws Exception, Throwable {
		
		log.info("UsuarioController.selectUsuarioByLogin--------> CONSULTAR USUARIO!");
		try {		
			return this.usuarioService.getUsuarioByLogin(usuarioLogin);
		} catch (Exception e) {
			log.error("UsuarioController.selectUsuarioByLogin----------------- ERRO AO BUSCAR USUARIO POR ID: " + e.toString());
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
	@RequestMapping(value="admin/usuario/{id}", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody UsuarioDto selectUpdateById(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails )  throws Exception, Throwable {
		
		log.info("UsuarioController.selectUpdateById--------> CONSULTAR USUARIO!");
		try {	
			return this.usuarioService.getUsuarioById(id);
		} catch (Exception e) {
			log.error("UsuarioController.selectUpdateById----------------- ERRO AO BUSCAR USUARIO POR ID: " + e.toString());
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
	@RequestMapping(value="admin/usuario/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody ResponseDto deleteUsuarioById(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails)  throws Exception, Throwable {
		
		log.info("UsuarioController.deleteUsuarioById--------> EXCLUIR USUARIO!");		 
		try { 
			usuarioService.deleteUsuarioById(id);
			return new ResponseDto(200, "Usuario excluido com sucesso!"); 
		}catch(Exception e) {
			log.error("UsuarioController.deleteUsuarioById----------------- ERRO AO EXCLUIR USUARIO POR ID: " + e.toString());
			if(HttpStatus.FORBIDDEN != null) {
				return new ResponseDto(403,"Acesso negado!");
			} else {
				return new ResponseDto(0,e.getMessage());	
			}			
		}
	}	

}
