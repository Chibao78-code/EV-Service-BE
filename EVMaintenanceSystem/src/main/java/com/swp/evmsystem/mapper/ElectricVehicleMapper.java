package com.swp.evmsystem.mapper;

import com.swp.evmsystem.dto.response.ElectricVehicleDTO;
import com.swp.evmsystem.entity.EvModelEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface   { // Mapper for ElectricVehicleEntity and ElectricVehicleDTO
    @Mapping(target = "model", qualifiedByName = "modelToString") // Convert EvModelEntity to String using custom method
    ElectricVehicleDTO toDTO(ElectricVehicleEntity entity); // Entity to DTO mapping
    @Mapping(target = "model", ignore = true) // Ignore model field when mapping from DTO to Entity
    ElectricVehicleEntity toEntity(ElectricVehicleDTO dto); // DTO to Entity mapping

    @Named("modelToString") // Custom mapping method to convert EvModelEntity to String
    default String modelToString(EvModelEntity entity) { // Convert EvModelEntity to its model name
        return entity.getModelName(); // Return the model name
    }

}
