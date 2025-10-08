package com.swp.evmsystem.config;

import com.swp.evmsystem.entity.*;
import com.swp.evmsystem.entity.address.AddressEntity;
import com.swp.evmsystem.entity.address.DistrictEntity;
import com.swp.evmsystem.entity.address.ProvinceEntity;
import com.swp.evmsystem.entity.address.WardEntity;
import com.swp.evmsystem.enums.RoleName;
import com.swp.evmsystem.enums.UserStatus;
import com.swp.evmsystem.service.*;
import com.swp.evmsystem.service.address.AddressEntityService;
import com.swp.evmsystem.service.address.DistrictEntityService;
import com.swp.evmsystem.service.address.ProvinceEntityService;
import com.swp.evmsystem.service.address.WardEntityService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataInitializer implements CommandLineRunner {
    final String[] ROLE_NAMES = {"ADMIN", "STAFF", "TECHNICIAN", "CUSTOMER"};
    final String[] CUSTOMER_NAMES = {"Customer 1", "Customer 2"};
    final String[] CUSTOMER_PHONES = {"0905111111", "0905222222"};
    final String[] CUSTOMER_PASSWORDS = {"user1@", "user2@"};
    final String[] PROVINCE_NAMES = {"TP. HCM"};
    final String[] DISTRICT_NAMES = {"Quận 1", "Quận 7", "TP. Thủ Đức", "Quận Gò Vấp", "Quận Bình Thạnh"};
    final String[] WARD_NAMES = {"Phường Bến Nghé", "Phường Tân Hưng", "Phường Linh Chiểu", "Phường 5", "Phường 25"};
    final String[] ADDRESS_LINES = {"123 Nguyễn Huệ", "456 Nguyễn Văn Linh", "789 Võ Văn Ngân", "321 Phan Văn Trị", "654 Xô Viết Nghệ Tĩnh"};
    final String[] CENTER_NAMES = {"VinFast Service Center Quận 1", "VinFast Service Center Quận 7", "VinFast Service Center Thủ Đức", "VinFast Service Center Gò Vấp", "VinFast Service Center Bình Thạnh"};
    final String[] CENTER_PHONES = {"02812345678", "02823456789", "02834567890", "02845678901", "02856789012"};
    final String[] MODEL_NAMES = {"Evo200", "Evo200 Lite", "Feliz S", "Feliz Neo", "Vento S"};

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    ElectricVehicleService electricVehicleService;
    @Autowired
    ServiceCenterService serviceCenterService;
    @Autowired
    TimeSlotAvailabilityService timeSlotAvailabilityService;
    @Autowired
    ProvinceEntityService provinceEntityService;
    @Autowired
    DistrictEntityService districtEntityService;
    @Autowired
    WardEntityService wardEntityService;
    @Autowired
    AddressEntityService addressEntityService;
    @Autowired
    EvModelService evModelService;

    @Override
    public void run(String... args) throws Exception {
        if (roleService.getAllCount() > 0) {
            return;
        }

        // Setup Role
        for (int i = 0; i <= 3; i++) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(RoleName.valueOf(ROLE_NAMES[i]));
            roleService.createRole(roleEntity);
        }

        // Setup Customer
        for (int i = 0; i <= 1; i++) {
            UserEntity user = new CustomerEntity();
            user.setFullName(CUSTOMER_NAMES[i]);
            user.setRole(roleService.getRoleByName(ROLE_NAMES[3]));
            user.setPhone(CUSTOMER_PHONES[i]);
            user.setPassword((new BCryptPasswordEncoder()).encode(CUSTOMER_PASSWORDS[i]));
            user.setStatus(UserStatus.ACTIVE);
            userService.createUser(user);
        }

        // Setup Center
        {
            ProvinceEntity provinceEntity = new ProvinceEntity();
            provinceEntity.setProvinceName(PROVINCE_NAMES[0]);
            provinceEntityService.createProvince(provinceEntity);

            for (int i = 0; i <= 4; i++) {
                // District
                DistrictEntity districtEntity = new DistrictEntity();
                districtEntity.setDistrictName(DISTRICT_NAMES[i]);
                districtEntity.setProvince(provinceEntity);
                districtEntityService.createDistrict(districtEntity);

                // Ward
                WardEntity wardEntity = new WardEntity();
                wardEntity.setWardName(WARD_NAMES[i]);
                wardEntity.setDistrict(districtEntity);
                wardEntityService.createWard(wardEntity);

                // Address
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setAddressLine(ADDRESS_LINES[i]);
                addressEntity.setWard(wardEntity);
                addressEntityService.createAddress(addressEntity);

                // Center
                ServiceCenterEntity serviceCenterEntity = new ServiceCenterEntity();
                serviceCenterEntity.setCenterName(CENTER_NAMES[i]);
                serviceCenterEntity.setCenterPhone(CENTER_PHONES[i]);
                serviceCenterEntity.setCenterAddress(addressEntity);
                serviceCenterService.create(serviceCenterEntity);
            }
        }

        // Setup EV Model
        for (String name : MODEL_NAMES) {
            EvModelEntity evModelEntity = new EvModelEntity();
            evModelEntity.setModelName(name);
            evModelService.createEVModel(evModelEntity);
        }
    }
}
