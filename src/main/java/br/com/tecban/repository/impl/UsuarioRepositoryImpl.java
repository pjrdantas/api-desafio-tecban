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

import br.com.tecban.dto.UsuarioDto;
import br.com.tecban.repository.UsuarioRepository;

@Transactional
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioRepositoryImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void addUsuarioDto(UsuarioDto usuarioDto) throws Exception, Throwable {

		StringBuilder sql = new StringBuilder();
		
		sql.append("  INSERT INTO ");
		sql.append("  tb_usuario (");
		sql.append("  id, ");
		sql.append("  tb_usuario_login, ");
		sql.append("  tb_usuario_senha, ");
		sql.append("  tb_usuario_nome, ");
		sql.append("  tb_usuario_admin) ");

		sql.append("  values (:id, :tbUsuarioLogin, :tbUsuarioSenha, :tbUsuarioNome, :tbUsuarioAdmin)");
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", usuarioDto.getId())
				.addValue("tbUsuarioLogin", usuarioDto.getUsuarioLogin())
				.addValue("tbUsuarioSenha", usuarioDto.getUsuarioSenha())
				.addValue("tbUsuarioNome", usuarioDto.getUsuarioNome())
				.addValue("tbUsuarioAdmin", usuarioDto.isUsuarioAdmin());

		try {
			jdbcTemplate.update(sql.toString(), params);
			log.info("UsuarioRepositoryImpl.addUsuarioDto--------> USUARIO INCLUIDO COM SUCESSO!");
		} catch (Exception e) {
			log.error("UsuarioRepositoryImpl.addUsuarioDto----------------- ERRO NA INCLUSÃO DO USUARIO: " + e.toString());
		}		
	}

	@Override
	public void updateUsuarioDto(UsuarioDto usuarioDto) throws Exception, Throwable {

StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_usuario ");
		sql.append(" SET  ");
		sql.append(" tb_usuario_login = :tbUsuarioLogin, ");
		sql.append(" tb_usuario_nome = :tbUsuarioNome, ");
		sql.append(" tb_usuario_senha = :tbUsuarioSenha, ");
		sql.append(" tb_usuario_login = :tbUsuarioAdmin ");	
		sql.append(" WHERE id = :id");

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", usuarioDto.getId())
				.addValue("tbUsuarioLogin", usuarioDto.getUsuarioLogin())
				.addValue("tbUsuarioSenha", usuarioDto.getUsuarioSenha())
				.addValue("tbUsuarioNome", usuarioDto.getUsuarioNome())
				.addValue("tbUsuarioAdmin", usuarioDto.isUsuarioAdmin());
		
		try {
			jdbcTemplate.update(sql.toString(), params);
			log.info("UsuarioRepositoryImpl.updateUsuarioDto--------> USUARIO ATUALIZADO COM SUCESSO!");
		} catch (Exception e) {
			log.error("UsuarioRepositoryImpl.updateUsuarioDto----------------- ERRO NA ATUALIZAÇÃO DO USUARIO: " + e.toString());
		}
	}

	@Override
	public List<UsuarioDto> getAllUsuarios() throws Exception, Throwable {

		try {
			StringBuilder sql = new StringBuilder(sqlSelectPrincipal).append(" order by tb_usuario_login ");
			log.info("UsuarioRepositoryImpl.getAllUsuarios--------> LISTA DE USUARIOS EMITIDA COM SUCESSO!");
			return devolveListaObjetos(sql, null);
		} catch (Exception e) {
			log.error("UsuarioRepositoryImpl.getAllUsuarios----------------- ERRO NA EMISSÃO DA LISTA DE USUARIOS: " + e.toString());
		}
		return null;

	}

	@Override
	public UsuarioDto getUsuarioById(int id) throws Exception, Throwable {

		try {
			StringBuilder sql = new StringBuilder(sqlSelectPrincipal);
			sql.append(" WHERE id = :id ");
			SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
			log.info("UsuarioRepositoryImpl.getUsuarioById--------> USUARIO LOCALIZADO COM SUCESSO!!!");
			return devolveObjeto(sql, params);
		} catch (Exception e) {
			log.error("UsuarioRepositoryImpl.getUsuarioById----------------- ERRO AO TENTAR LOCALIZAR O USUARIO: " + e.toString());
		}
		return null;

	}

	@Override
	public UsuarioDto getUsuarioByLogin(String usuarioLoginDto) throws Exception, Throwable {

		try {
			StringBuilder sql = new StringBuilder(sqlSelectPrincipal);
			sql.append(" WHERE tb_usuario_login = :usuarioLogin ");
			SqlParameterSource params = new MapSqlParameterSource().addValue("usuarioLogin", usuarioLoginDto);
			log.info("UsuarioRepositoryImpl.getUsuarioByLogin--------> USUARIO LOCALIZADO COM SUCESSO!!!");
			return devolveObjeto(sql, params);
		} catch (Exception e) {
			log.error("UsuarioRepositoryImpl.getUsuarioByLogin----------------- ERRO AO TENTAR LOCALIZAR O USUARIO: " + e.toString());
		}
		return null;

	}

	@Override
	public void deleteUsuarioById(int id) throws Exception, Throwable {

		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM ");
		sql.append(" tb_usuario ");
		sql.append(" WHERE id = :id");
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
		try {
			jdbcTemplate.update(sql.toString(), params);
			log.info("UsuarioRepositoryImpl.deleteUsuario--------> USUARIO EXCLUIDO COM SUCESSO!!!");
		} catch (Exception e) {
			log.error("UsuarioRepositoryImpl.deleteUsuario----------------- ERRO NA EXCLUSÃO DO USUARIO! " + e.toString());
		}	
	}
	
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder()
			.append("  SELECT ")
			.append("  id")
			.append("  ,tb_usuario_login")
			.append("  ,tb_usuario_senha")
			.append("  ,tb_usuario_nome")
			.append("  ,tb_usuario_admin")
			.append("  FROM tb_usuario ");
	
	private List<UsuarioDto> devolveListaObjetos(StringBuilder sql, SqlParameterSource params) throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
			
			UsuarioDto usuarioDto = new UsuarioDto();
			
			usuarioDto.setId(rs.getInt("id"));
			usuarioDto.setUsuarioLogin(rs.getString("tb_usuario_login"));
			usuarioDto.setUsuarioSenha(rs.getString("tb_usuario_senha"));
			usuarioDto.setUsuarioNome(rs.getString("tb_usuario_nome"));
			usuarioDto.setUsuarioAdmin(rs.getBoolean("tb_usuario_admin"));
			return usuarioDto;
		});
	}

	private UsuarioDto devolveObjeto(StringBuilder sql, SqlParameterSource params) throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
			
			UsuarioDto usuarioDto = new UsuarioDto();
			
			usuarioDto.setId(rs.getInt("id"));
			usuarioDto.setUsuarioLogin(rs.getString("tb_usuario_login"));
			usuarioDto.setUsuarioSenha(rs.getString("tb_usuario_senha"));
			usuarioDto.setUsuarioNome(rs.getString("tb_usuario_nome"));
			usuarioDto.setUsuarioAdmin(rs.getBoolean("tb_usuario_admin"));
			return usuarioDto;
		});
	}

}
