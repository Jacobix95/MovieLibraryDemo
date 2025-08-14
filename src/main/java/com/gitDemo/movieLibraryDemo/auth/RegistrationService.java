package com.gitDemo.movieLibraryDemo.auth;

import com.gitDemo.movieLibraryDemo.auth.jwt.JwtTokenService;
import com.gitDemo.movieLibraryDemo.auth.user.RoleRepository;
import com.gitDemo.movieLibraryDemo.auth.user.UserRepository;
import com.gitDemo.movieLibraryDemo.auth.user.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public RegistrationService(UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder,
                               JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    @Transactional
    public JwtTokenResponseDto register(RegisterRequestDto dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailAlreadyUsedException("Email already in use");
        }

        var user = new User(dto.email(), passwordEncoder.encode(dto.password()));

        var role = roleRepository.findByName("DEVELOPER_READ")
                .orElseThrow(() -> new IllegalStateException("Default role DEVELOPER_READ not found"));
        user.assignNewRole(role);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new EmailAlreadyUsedException("Email already in use");
        }

        return new JwtTokenResponseDto(jwtTokenService.generateJwtToken(dto.email()));
    }

    public static class EmailAlreadyUsedException extends RuntimeException {
        public EmailAlreadyUsedException(String message) { super(message); }
    }
}
