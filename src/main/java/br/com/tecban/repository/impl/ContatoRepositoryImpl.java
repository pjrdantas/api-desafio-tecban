package br.com.tecban.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecban.dto.ContatoDto;
import br.com.tecban.repository.ContatoRepository;

@Transactional
@Repository
public class ContatoRepositoryImpl implements ContatoRepository {
	
	private static final Logger log = LoggerFactory.getLogger(ContatoRepositoryImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void addContatoDto(ContatoDto ContatoDto) throws Exception, Throwable {

		StringBuilder sql = new StringBuilder();
		
		sql.append("  INSERT INTO ");
		sql.append("  tb_Contato (");
		sql.append("  id, ");
		sql.append("  tb_contato_nome, ");
		sql.append("  tb_contato_email, ");
		sql.append("  tb_contato_telefone) ");

		sql.append("  values (:id, :tbContatoNome, :tbContatoEmail, :tbContatoTelefone)");
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", ContatoDto.getId())
				.addValue("tbContatoNome", ContatoDto.getContatoNome())
				.addValue("tbContatoEmail", ContatoDto.getContatoEmail())
				.addValue("tbContatoTelefone", ContatoDto.getContatoTelefone());

		try {
			jdbcTemplate.update(sql.toString(), params);
			log.info("ContatoRepositoryImpl.addContatoDto--------> CONTATO INCLUIDO COM SUCESSO!");
		} catch (Exception e) {
			log.error("ContatoRepositoryImpl.addContatoDto----------------- ERRO NA INCLUSÃO DO CONTATO: " + e.toString());
		}		
	}

	@Override
	public void updateContatoDto(ContatoDto ContatoDto) throws Exception, Throwable {

StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_Contato ");
		sql.append(" SET  ");
		sql.append(" tb_Contato_nome = :tbContatoNome, ");
		sql.append(" tb_Contato_email = :tbContatoEmail, ");
		sql.append(" tb_Contato_telefone = :tbContatoTelefone ");	
		sql.append(" WHERE id = :id");

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", ContatoDto.getId())
				.addValue("ContatoNome", ContatoDto.getContatoNome())
				.addValue("ContatoEmail", ContatoDto.getContatoEmail())
				.addValue("ContatoTelefone", ContatoDto.getContatoTelefone());
		
		try {
			jdbcTemplate.update(sql.toString(), params);
			log.info("ContatoRepositoryImpl.updateContatoDto--------> CONTATO ATUALIZADO COM SUCESSO!");
		} catch (Exception e) {
			log.error("ContatoRepositoryImpl.updateContatoDto----------------- ERRO NA ATUALIZAÇÃO DO CONTATO: " + e.toString());
		}
	}

	@Override
	public List<ContatoDto> getAllContatos() throws Exception, Throwable {

		try {
			StringBuilder sql = new StringBuilder(sqlSelectPrincipal).append(" order by tb_Contato_nome ");
			log.info("ContatoRepositoryImpl.getAllContatos--------> LISTA DE CONTATOS EMITIDA COM SUCESSO!");
			return devolveListaObjetos(sql, null);
		} catch (Exception e) {
			log.error("ContatoRepositoryImpl.getAllContatos----------------- ERRO NA EMISSÃO DA LISTA DE CONTATOS: " + e.toString());
		}
		return null;

	}

	@Override
	public ContatoDto getContatoById(int id) throws Exception, Throwable {

		try {
			StringBuilder sql = new StringBuilder(sqlSelectPrincipal);
			sql.append(" WHERE id = :id ");
			SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
			log.info("ContatoRepositoryImpl.getContatoById--------> CONTATO LOCALIZADO COM SUCESSO!!!");
			return devolveObjeto(sql, params);
		} catch (Exception e) {
			log.error("ContatoRepositoryImpl.getContatoById----------------- ERRO AO TENTAR LOCALIZAR O CONTATO: " + e.toString());
		}
		return null;

	}

	@Override
	public void deleteContatoById(int id) throws Exception, Throwable {

		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM ");
		sql.append(" tb_Contato ");
		sql.append(" WHERE id = :id");
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
		try {
			jdbcTemplate.update(sql.toString(), params);
			log.info("ContatoRepositoryImpl.deleteContato--------> CONTATO EXCLUIDO COM SUCESSO!!!");
		} catch (Exception e) {
			log.error("ContatoRepositoryImpl.deleteContato----------------- ERRO NA EXCLUSÃO DO CONTATO! " + e.toString());
		}	
	}
	
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder()
			.append("  SELECT ")
			.append("  id")
			.append("  ,tb_Contato_nome")
			.append("  ,tb_Contato_email")
			.append("  ,tb_Contato_telefone")
			.append("  FROM tb_Contato ");
	
	private List<ContatoDto> devolveListaObjetos(StringBuilder sql, SqlParameterSource params) throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
			
			ContatoDto ContatoDto = new ContatoDto();
			
			ContatoDto.setId(rs.getInt("id"));
			ContatoDto.setContatoNome(rs.getString("tb_Contato_nome"));
			ContatoDto.setContatoEmail(rs.getString("tb_Contato_email"));
			ContatoDto.setContatoTelefone(rs.getString("tb_Contato_telefone"));
			return ContatoDto;
		});
		
	}

	private ContatoDto devolveObjeto(StringBuilder sql, SqlParameterSource params) throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
			
			ContatoDto ContatoDto = new ContatoDto();
			
			ContatoDto.setId(rs.getInt("id"));
			ContatoDto.setContatoNome(rs.getString("tb_Contato_nome"));
			ContatoDto.setContatoEmail(rs.getString("tb_Contato_email"));
			ContatoDto.setContatoTelefone(rs.getString("tb_Contato_telefone"));
			return ContatoDto;
		});
	}

}
