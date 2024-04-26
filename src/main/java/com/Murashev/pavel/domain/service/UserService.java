package com.Murashev.pavel.domain.service;


import com.Murashev.pavel.domain.entity.Role;
import com.Murashev.pavel.domain.entity.User;
import com.Murashev.pavel.domain.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername( username );
        if (user == null){
            throw new UsernameNotFoundException( "Пользователь не найден" );
        }
        return user;
    }
    @Autowired
    MyMailSender mailSender;
    @Value( "${hostname}" )
    private String hostname;

    public boolean addUser(User user){
        User userFromDB = userRepo.findByUsername( user.getUsername() );

        if (userFromDB != null){
            return false;
        }
        user.setActive( true );
        user.setActivationCode( UUID.randomUUID().toString() );
        user.setRoles( Collections.singleton( Role.USER ) );
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        userRepo.save( user );

        sendMessage( user );

        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty( user.getMail() )){
            String message = String.format(
                    "Приветствую Вас, %s!  \n" +
                            "Для регистрации, пожалуйста, перейдите по следующей ссылке: http://%s/activate/%s",
                    user.getUsername(),
                    hostname,
                    user.getActivationCode()
            );
            mailSender.send( user.getMail(), "Код активации", message );
        }
    }


    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null){
            return false;
        }
        user.setActivationCode( null );
        userRepo.save( user );
        return true;
    }

    public List<User> findAll() {
        return (List<User>) userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername( username );
        final Set<String> roles = Arrays.stream( Role.values() )
                .map( Role::name )
                .collect( Collectors.toSet() );
        user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains( key )){
                user.getRoles().add( Role.valueOf( key ) );
            }
        }
        userRepo.save( user );
    }

    public void updateProfile(User user, String password, String email) {
        boolean isEmailChanged = (email != null && !email.equals( user.getMail() ))
                || (user.getMail() != null && !user.getMail().equals( email ));
        if (isEmailChanged) {
            user.setMail( email );
            if (!StringUtils.isEmpty( email ) ){
                user.setActivationCode( UUID.randomUUID().toString() );
            }
        }
        if (!StringUtils.isEmpty( password )) {
            user.setPassword( password );
        }
        userRepo.save( user );
        if (isEmailChanged) {
            sendMessage( user );
        }
    }
}
