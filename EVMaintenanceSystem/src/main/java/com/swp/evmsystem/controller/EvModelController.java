package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.response.EvModelDTO;
import com.swp.evmsystem.service.EvModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EvModelController {
    @Autowired
    private EvModelService evModelService;
    // Gọi constructor của EvModelController
    public EvModelController(EvModelService evModelService) {
        this.evModelService = evModelService;
    }
    // Lấy danh sách các mẫu xe điện
    @GetMapping("/api/ev-models")
    public ResponseEntity<?> getEvModels() {
        // Gọi phương thức từ service để lấy danh sách mẫu xe điện
        List<EvModelDTO> evModelDTOs = evModelService.getAllModels();
        // Trả về danh sách mẫu xe điện với mã trạng thái 200 OK
        return new ResponseEntity<List<EvModelDTO>>(evModelDTOs, HttpStatus.OK);
    }
}
