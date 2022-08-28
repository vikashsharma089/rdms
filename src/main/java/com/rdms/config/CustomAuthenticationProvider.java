package com.rdms.config;

import com.rdms.model.Users;
import com.rdms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
       Users usermodel = userService.findUserByUserName(authentication.getPrincipal().toString());
        Map<String , Object> villageDetails = new HashMap();
        villageDetails.put("userDetail", usermodel);
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));

         if(usermodel != null){
             if(usermodel.getUserName().equalsIgnoreCase(authentication.getPrincipal() == null? "": authentication.getPrincipal().toString() )  && passwordEncoder.matches((String)authentication.getCredentials(), usermodel.getPassword()) ){
                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                         authentication.getPrincipal(), authentication.getCredentials(), authorities);
                 usernamePasswordAuthenticationToken.setDetails(villageDetails);
                 return usernamePasswordAuthenticationToken;
             }else{
                 throw new BadCredentialsException("Incorrect user credentials !!");
             }

         }else{
             throw new BadCredentialsException("Incorrect user credentials !!");
         }






    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
