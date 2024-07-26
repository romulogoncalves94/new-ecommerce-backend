package com.ecommerce.ecom.services.auth;

import com.ecommerce.ecom.dto.SignupRequestDTO;
import com.ecommerce.ecom.dto.UserDTO;
import com.ecommerce.ecom.entity.User;
import com.ecommerce.ecom.enums.UserRole;
import com.ecommerce.ecom.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDTO createUser(SignupRequestDTO requestDTO) {

        User user = new User();
        user.setEmail(requestDTO.getEmail());
        user.setName(requestDTO.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(requestDTO.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        User createdUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());

        return userDTO;
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

//    @PostConstruct
//    public void createAdminAccount() {
//        User userAdmin = userRepository.findByRole(UserRole.ADMIN);
//
//        if (nonNull(userAdmin)) {
//            User user = new User();
//            user.setEmail("admin@admin");
//            user.setName("admin");
//            user.setRole(UserRole.ADMIN);
//            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
//            userRepository.save(user);
//        }
//    }

}
