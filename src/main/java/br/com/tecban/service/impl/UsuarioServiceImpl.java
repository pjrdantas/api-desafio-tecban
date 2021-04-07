package br.com.tecban.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecban.dto.UsuarioDto;
import br.com.tecban.repository.UsuarioRepository;
import br.com.tecban.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void addUsuarioDto(UsuarioDto usuarioDto) throws Exception, Throwable {		
		usuarioRepository.addUsuarioDto(usuarioDto);
	}

	@Override
	public void updateUsuarioDto(UsuarioDto usuarioDto) throws Exception, Throwable {
		usuarioRepository.updateUsuarioDto(usuarioDto);
		
	}

	@Override
	public List<UsuarioDto> getAllUsuarios() throws Exception, Throwable {
	
		return usuarioRepository.getAllUsuarios();
	}

	@Override
	public UsuarioDto getUsuarioById(int id) throws Exception, Throwable {
		UsuarioDto obj = usuarioRepository.getUsuarioById(id);
		return obj;
	}

	@Override
	public UsuarioDto getUsuarioByLogin(String usuarioLogin) throws Exception, Throwable {
		UsuarioDto obj = usuarioRepository.getUsuarioByLogin(usuarioLogin);
		return obj;
	}

	

	@Override
	public void deleteUsuarioById(int id) throws Exception, Throwable {
		usuarioRepository.deleteUsuarioById(id);
		
	}


}
