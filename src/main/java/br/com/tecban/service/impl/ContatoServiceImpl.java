package br.com.tecban.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecban.dto.ContatoDto;
import br.com.tecban.repository.ContatoRepository;
import br.com.tecban.service.ContatoService;


@Service
public class ContatoServiceImpl implements ContatoService {
	
	@Autowired
	private ContatoRepository ContatoRepository;

	@Override
	public void addContatoDto(ContatoDto ContatoDto) throws Exception, Throwable {		
		ContatoRepository.addContatoDto(ContatoDto);
	}

	@Override
	public void updateContatoDto(ContatoDto ContatoDto) throws Exception, Throwable {
		ContatoRepository.updateContatoDto(ContatoDto);
		
	}

	@Override
	public List<ContatoDto> getAllContatos() throws Exception, Throwable {
	
		return ContatoRepository.getAllContatos();
	}

	@Override
	public ContatoDto getContatoById(int id) throws Exception, Throwable {
		ContatoDto obj = ContatoRepository.getContatoById(id);
		return obj;
	}

	@Override
	public void deleteContatoById(int id) throws Exception, Throwable {
		ContatoRepository.deleteContatoById(id);
		
	}



	

}
