package com.swp.evmsystem.controller;

                    import io.swagger.v3.oas.annotations.Operation;
                    import io.swagger.v3.oas.annotations.responses.ApiResponse;
                    import io.swagger.v3.oas.annotations.tags.Tag;
                    import org.springframework.beans.factory.annotation.Autowired;
                    import org.springframework.http.HttpStatus;
                    import org.springframework.http.ResponseEntity;
                    import org.springframework.web.bind.annotation.*;

                    @RestController
                    @RequestMapping("/api/auth")
                    @Tag(name = "Authentication", description = "APIs for authentication")
                    public class AuthController {

                        @Autowired
                        private AuthService authService;

                        @PostMapping("/login/phone")
                        @Operation(summary = "Login with phone", description = "Đăng nhập bằng số điện thoại")
                        @ApiResponse(responseCode = "200", description = "Đăng nhập thành công")
                        @ApiResponse(responseCode = "401", description = "Thông tin đăng nhập không hợp lệ")
                        public ResponseEntity<LoginResponse> loginWithPhone(
                                @RequestParam String phone,
                                @RequestParam String password) {
                            try {
                                String token = authService.loginWithPhone(phone, password);
                                String role = authService.getRoleFromToken(token);

                                if (role != null) {
                                    return ResponseEntity.ok(new LoginResponse(token, role));
                                } else {
                                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                                }
                            } catch (Exception e) {
                                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                        .body(new LoginResponse(null, "Invalid credentials"));
                            }
                        }

                        @PostMapping("/login/email")
                        @Operation(summary = "Login with email", description = "Đăng nhập bằng email")
                        @ApiResponse(responseCode = "200", description = "Đăng nhập thành công")
                        @ApiResponse(responseCode = "401", description = "Thông tin đăng nhập không hợp lệ")
                        public ResponseEntity<LoginResponse> loginWithEmail(
                                @RequestParam String email,
                                @RequestParam String password) {
                            try {
                                String token = authService.loginWithEmail(email, password);
                                String role = authService.getRoleFromToken(token);

                                if (role != null) {
                                    return ResponseEntity.ok(new LoginResponse(token, role));
                                } else {
                                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                                }
                            } catch (Exception e) {
                                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                        .body(new LoginResponse(null, "Invalid credentials"));
                            }
                        }
                    }