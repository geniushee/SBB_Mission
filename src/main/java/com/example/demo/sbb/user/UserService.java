package com.example.demo.sbb.user;

import com.example.demo.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public long count() {
        return this.userRepository.count();
    }

    public SiteUser create(String username, String password, String email){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username){
        Optional<SiteUser> opUser = userRepository.findByUsername(username);
        if(opUser.isPresent()){
            return opUser.get();
        } else{
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
