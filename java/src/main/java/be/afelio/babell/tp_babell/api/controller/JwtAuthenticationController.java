package be.afelio.babell.tp_babell.api.controller;

import be.afelio.babell.tp_babell.api.config.JwtTokenUtil;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.jwt.model.JwtRequest;
import be.afelio.babell.tp_babell.api.jwt.model.JwtResponse;
import be.afelio.babell.tp_babell.api.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ResponseDto<JwtResponse>> createAuthenticationToken(
    		HttpServletRequest request/*@RequestBody JwtRequest authenticationRequest*/) throws Exception {
        try{
            JwtRequest authenticationRequest = (JwtRequest) request.getAttribute("jwtRequest");
            ResponseDto<JwtResponse> responseDto = getTokenWithJwtResponse(request,authenticationRequest.getUsername(), authenticationRequest.getPassword());
            return ResponseEntity.ok(responseDto);

        }catch (Exception e){
            ResponseDto<JwtResponse> responseDto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "failure to connect");
            return ResponseEntity.ok(responseDto);
        }
    }

    public ResponseDto<JwtResponse> getTokenWithJwtResponse(HttpServletRequest request, String username, String password) throws Exception {
        authenticate(username, password);
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        ResponseDto<JwtResponse> responseDto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, "token created");
        responseDto.setPayload(new JwtResponse(token));
        return responseDto;
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
