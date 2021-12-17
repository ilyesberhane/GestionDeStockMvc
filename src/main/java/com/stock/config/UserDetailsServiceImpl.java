package com.stock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stock.entities.Utilisateur;
import com.stock.repositories.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("\n\n\n");
		System.out.println("Username = "+username);
		System.out.println("\n\n\n");
		Utilisateur utilisateur = utilisateurRepository.findByMail(username).get();
		
//		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		
//		utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
		
		if(utilisateur == null) {
			throw new UsernameNotFoundException("Username not found in the database");
		}
		
		
		return new MyUserDetails(utilisateur);
	}

}
