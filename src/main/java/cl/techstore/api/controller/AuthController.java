package cl.techstore.api.controller;

import cl.techstore.api.dto.LoginRequest;
import cl.techstore.api.dto.LoginResponse;
import cl.techstore.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    private final String USERNAME = "admin";
    private final String PASSWORD = "1234";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        if (!USERNAME.equals(request.getUsername()) ||
            !PASSWORD.equals(request.getPassword())) {

            return ResponseEntity.status(401)
                    .body("Usuario o contraseña incorrectos");
        }

        String token = jwtUtil.generarToken(request.getUsername());

        LoginResponse response = new LoginResponse(
                token,
                "Bearer",
                "1 hora"
        );

        return ResponseEntity.ok(response);
    }
}