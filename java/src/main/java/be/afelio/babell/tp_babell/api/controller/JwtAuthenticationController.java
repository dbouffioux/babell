package be.afelio.babell.tp_babell.api.controller;

import be.afelio.babell.tp_babell.api.config.JwtTokenUtil;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.jwt.model.JwtRequest;
import be.afelio.babell.tp_babell.api.jwt.model.JwtResponse;
import be.afelio.babell.tp_babell.api.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<JwtResponse>> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            ResponseDto<JwtResponse> responseDto =new ResponseDto<JwtResponse>(ResponseDtoStatus.SUCCESS, "token created");
            responseDto.setPayload(new JwtResponse(token));
            return ResponseEntity.ok(responseDto);
    }

}
