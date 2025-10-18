package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import com.swp.evmsystem.mapper.ElectricVehicleMapper;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectricVehicleServiceImpl implements ElectricVehicleService {
    @Autowired
    ElectricVehicleRepository electricVehicleRepository;
    @Autowired
    ElectricVehicleMapper mapper;

    @Override
    public List<ElectricVehicleDTO> findByOwner(CustomerEntity owner) { // Lấy danh sách xe điện của chủ sở hữu
        return electricVehicleRepository.findByOwner(owner)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList()); // Chuyển đổi danh sách thực thể xe điện thành DTO và trả về
    }

    @Override
    public boolean create(ElectricVehicleEntity ev) {
        if (ev == null) { // Kiểm tra nếu đối tượng xe điện là null
            return false; // Trả về false nếu không thể tạo
        }
        return electricVehicleRepository.save(ev) != null; // Lưu xe điện vào cơ sở dữ liệu và trả về true nếu thành công
    }
}
