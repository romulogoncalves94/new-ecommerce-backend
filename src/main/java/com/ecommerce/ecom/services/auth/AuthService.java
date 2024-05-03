package com.ecommerce.ecom.services.auth;

import com.ecommerce.ecom.dto.SignupRequestDTO;
import com.ecommerce.ecom.dto.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequestDTO requestDTO);
    Boolean hasUserWithEmail(String email);

}
