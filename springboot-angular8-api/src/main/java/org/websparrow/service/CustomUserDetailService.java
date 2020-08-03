package org.websparrow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.websparrow.dao.SignupDAO;
import org.websparrow.entity.User;

 
 

@Component
public class CustomUserDetailService implements UserDetailsService {

	private List<GrantedAuthority> role;
//    private final UserRepository userRepository;

    @Autowired
    private SignupDAO signupDAO;

//    public CustomUserDetailService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = Optional.ofNullable(userRepository.findByUsername(username))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
//        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
//        return new org.springframework.security.core.userdetails.User
//                (user.getUsername(), user.getPassword(), user.getAdmin() != null ? authorityListAdmin : authorityListUser);
//
//    }
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	User user = Optional.ofNullable(signupDAO.getByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        if(user.getRole()=="admin") {
        	role=authorityListAdmin;
        }else {
        	role = authorityListUser;
        }
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(),role);
    }
}
