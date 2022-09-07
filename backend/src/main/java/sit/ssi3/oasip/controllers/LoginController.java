package sit.ssi3.oasip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sit.ssi3.oasip.dtos.LoginDTO;
import sit.ssi3.oasip.dtos.TokenDTO;
import sit.ssi3.oasip.services.AuthenticationService;
import sit.ssi3.oasip.services.LoginService;

@RestController
@RequestMapping("/api/match")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO MatchPassword(@RequestBody LoginDTO requestUser) {
        return authenticationService.match(requestUser);
    }


}
