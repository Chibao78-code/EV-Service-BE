package com.swp.evmsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Xử lý lỗi xác thực đối số phương thức
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Tạo một bản đồ để lưu trữ lỗi
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors() // Lấy danh sách lỗi của các trường
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        // Trả về phản hồi với mã trạng thái 400 Bad Request cùng với bản đồ lỗi
        return ResponseEntity.badRequest().body(errors);
    }
}
