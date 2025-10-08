package com.swp.evmsystem.mapper;

import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
import com.swp.evmsystem.entity.EVModelEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ElectricVehicleMapper {
    @Mapping(target = "model", qualifiedByName = "modelToString")
    ElectricVehicleDTO toDTO(ElectricVehicleEntity entity);
    @Mapping(target = "model", ignore = true)
    ElectricVehicleEntity toEntity(ElectricVehicleDTO dto);

    @Named("modelToString")
    default String modelToString(EVModelEntity entity) {
        return entity.getModelName();
    }
}
