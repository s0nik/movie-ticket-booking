package com.cotiviti.movieticketbooking.controller;

import com.cotiviti.movieticketbooking.dto.LoginDto;
import com.cotiviti.movieticketbooking.dto.GlobalResponse;
import com.cotiviti.movieticketbooking.util.JWTTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTTokenUtil jwtTokenUtil;


    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginDto loginDto) throws Exception {
        authenticate(loginDto.getUsername(), loginDto.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new GlobalResponse<String>().successResponse(token, "Authorization token"));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
