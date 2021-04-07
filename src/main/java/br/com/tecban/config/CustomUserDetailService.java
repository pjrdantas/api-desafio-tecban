package br.com.tecban.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.tecban.dto.UsuarioDto;
import br.com.tecban.repository.impl.UsuarioRepositoryImpl;

@Component
public class CustomUserDetailService implements UserDetailsService{
	
	private final UsuarioRepositoryImpl usuarioRepositoryImpl;
	
	@Autowired
	public CustomUserDetailService(UsuarioRepositoryImpl usuarioRepositoryImpl) {
		this.usuarioRepositoryImpl = usuarioRepositoryImpl;
	}

	@Override
	public UserDetails loadUserByUsername(String usuarioLoginDto) throws UsernameNotFoundException {
		
		UsuarioDto user = null;
		try {
			user = Optional.ofNullable(usuarioRepositoryImpl.getUsuarioByLogin(usuarioLoginDto))
			.orElseThrow(() -> new UsernameNotFoundException("login not found"));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLER_USER", "ROLE_ADMIN" );
		List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLER_USER");
		
		return new org.springframework.security.core.userdetails.User(user.getUsuarioLogin(), user.getUsuarioSenha(), user.isUsuarioAdmin() ? authorityListAdmin : authorityListUser );
	}
}
