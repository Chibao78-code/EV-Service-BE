package com.swp.evmsystem.mapper;

import com.swp.evmsystem.dto.response.ServiceCenterDTO;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import com.swp.evmsystem.entity.address.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ServiceCenterMapper {
    // Entity → DTO
    @Mapping(source = "centerAddress", target = "centerAddress", qualifiedByName = "addressToString")
    ServiceCenterDTO toDTO(ServiceCenterEntity entity);

    // DTO → Entity (thường bỏ qua address vì cần xử lý riêng)
    @Mapping(target = "centerAddress", ignore = true)
    ServiceCenterEntity toEntity(ServiceCenterDTO dto);

    // Custom method để build địa chỉ đầy đủ
    @Named("addressToString")
    default String addressToString(AddressEntity address) {
        if (address == null) return null;

        StringBuilder sb = new StringBuilder();

        // 1️⃣ addressLine
        if (address.getAddressLine() != null)
            sb.append(address.getAddressLine());

        // 2️⃣ ward
        if (address.getWard() != null && address.getWard().getWardName() != null)
            sb.append(", ").append(address.getWard().getWardName());

        // 3️⃣ district
        if (address.getWard() != null
                && address.getWard().getDistrict() != null
                && address.getWard().getDistrict().getDistrictName() != null)
            sb.append(", ").append(address.getWard().getDistrict().getDistrictName());

        // 4️⃣ province
        if (address.getWard() != null
                && address.getWard().getDistrict() != null
                && address.getWard().getDistrict().getProvince() != null
                && address.getWard().getDistrict().getProvince().getProvinceName() != null)
            sb.append(", ").append(address.getWard().getDistrict().getProvince().getProvinceName());

        return sb.toString();
    }
}
