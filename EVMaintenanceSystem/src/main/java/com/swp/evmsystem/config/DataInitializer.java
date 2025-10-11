package com.swp.evmsystem.config;

import com.swp.evmsystem.entity.*;
import com.swp.evmsystem.entity.address.AddressEntity;
import com.swp.evmsystem.entity.address.DistrictEntity;
import com.swp.evmsystem.entity.address.ProvinceEntity;
import com.swp.evmsystem.entity.address.WardEntity;
import com.swp.evmsystem.enums.EvMaintenanceStatus;
import com.swp.evmsystem.enums.Role;
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

import java.time.LocalTime;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataInitializer implements CommandLineRunner {
    final String[] CUSTOMER_NAMES = {"Customer 1", "Customer 2"};
    final String[] CUSTOMER_PHONES = {"0905111111", "0905222222"};
    final String[] CUSTOMER_PASSWORDS = {"user1@", "user2@"};
    
    // Admin, Staff, Technician users
    final String ADMIN_PHONE = "0123456789";
    final String ADMIN_PASSWORD = "admin123";
    final String STAFF_PHONE = "0987654321";
    final String STAFF_PASSWORD = "staff123";
    final String TECH_PHONE = "0111222333";
    final String TECH_PASSWORD = "tech123";
    final String[] PROVINCE_NAMES = {"TP. HCM"};
    final String[] DISTRICT_NAMES = {"Quận 1", "Quận 7", "TP. Thủ Đức", "Quận Gò Vấp", "Quận Bình Thạnh"};
    final String[] WARD_NAMES = {"Phường Bến Nghé", "Phường Tân Hưng", "Phường Linh Chiểu", "Phường 5", "Phường 25"};
    final String[] ADDRESS_LINES = {"123 Nguyễn Huệ", "456 Nguyễn Văn Linh", "789 Võ Văn Ngân", "321 Phan Văn Trị", "654 Xô Viết Nghệ Tĩnh"};
    final String[] CENTER_NAMES = {"VinFast Service Center Quận 1", "VinFast Service Center Quận 7", "VinFast Service Center Thủ Đức", "VinFast Service Center Gò Vấp", "VinFast Service Center Bình Thạnh"};
    final LocalTime[] START_TIMES = {LocalTime.parse("08:00")};
    final LocalTime[] END_TIMES = {LocalTime.parse("18:00")};
    final String[] CENTER_PHONES = {"02812345678", "02823456789", "02834567890", "02845678901", "02856789012"};
    final String[] MODEL_NAMES = {"Evo200", "Evo200 Lite", "Feliz S", "Feliz Neo", "Vento S"};
    final String[] EV_VINS = {"FAKEVIN0000000000", "TESTVIN0000000000", "PLACEHOLDER000000"};
    final String[] EV_LICENSE_PLATES = {"49MĐ685692", "59MĐ102563", "59MĐ155567"};
    final String[] OFFER_TYPE_NAMES = {"Bảo dưỡng định kỳ", "Thay thế phụ tùng", "Sửa chữa"};
    final String[] OFFER_TYPE_DESCRIPTIONS = {"Bảo dưỡng toàn diện xe máy điện theo tiêu chuẩn VinFast" , "Thay thế phụ tùng chính hãng VinFast", "Sửa chữa các vấn đề kỹ thuật"};

    @Autowired
    UserService userService;
    @Autowired
    ElectricVehicleService electricVehicleService;
    @Autowired
    ServiceCenterService serviceCenterService;
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
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OfferTypeService offerTypeService;

    @Override
    public void run(String... args) throws Exception {

        if (userService.getAllCount() > 0) {
            return;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        // Setup Admin
        UserEntity admin = EmployeeEntity.builder()
                .fullName("Admin User")
                .role(Role.ADMIN)
                .phone(ADMIN_PHONE)
                .email("dinhdinhchibao@gmail.com")
                .password(passwordEncoder.encode(ADMIN_PASSWORD))
                .status(UserStatus.ACTIVE)
                .build();
        userService.createUser(admin);
        
        // Setup Staff
        UserEntity staff = EmployeeEntity.builder()
                .fullName("Staff User")
                .role(Role.STAFF)
                .phone(STAFF_PHONE)
                .email("ddinhchibao@gmail.com")
                .password(passwordEncoder.encode(STAFF_PASSWORD))
                .status(UserStatus.ACTIVE)
                .build();
        userService.createUser(staff);
        
        // Setup Technician
        UserEntity technician = EmployeeEntity.builder()
                .fullName("Technician User")
                .role(Role.TECHNICIAN)
                .phone(TECH_PHONE)
                .email("zzz.dinhchibao.15@gmail.com")
                .password(passwordEncoder.encode(TECH_PASSWORD))
                .status(UserStatus.ACTIVE)
                .build();
        userService.createUser(technician);
        
        // Setup Customer 1
        UserEntity customer1 = CustomerEntity.builder()
                .fullName("Customer 1")
                .role(Role.CUSTOMER)
                .phone(CUSTOMER_PHONES[0])
                .email("quangphap931@gmail.com")
                .password(passwordEncoder.encode(CUSTOMER_PASSWORDS[0]))
                .status(UserStatus.ACTIVE)
                .build();
        userService.createUser(customer1);
        
        // Setup Customer 2
        UserEntity customer2 = CustomerEntity.builder()
                .fullName("Customer 2")
                .role(Role.CUSTOMER)
                .phone(CUSTOMER_PHONES[1])
                .password(passwordEncoder.encode(CUSTOMER_PASSWORDS[1]))
                .status(UserStatus.ACTIVE)
                .build();
        userService.createUser(customer2);

        // Setup Center
        {
            ProvinceEntity provinceEntity = new ProvinceEntity();
            provinceEntity.setProvinceName(PROVINCE_NAMES[0]);
            provinceEntityService.createProvince(provinceEntity);

            for (int i = 0; i <= 4; i++) {
                // District
                DistrictEntity districtEntity = DistrictEntity.builder()
                        .districtName(DISTRICT_NAMES[i])
                        .province(provinceEntity)
                        .build();
                districtEntityService.createDistrict(districtEntity);

                // Ward
                WardEntity wardEntity = WardEntity.builder()
                        .wardName(WARD_NAMES[i])
                        .district(districtEntity)
                        .build();
                wardEntityService.createWard(wardEntity);

                // Address
                AddressEntity addressEntity = AddressEntity.builder()
                        .addressLine(ADDRESS_LINES[i])
                        .ward(wardEntity)
                        .build();
                addressEntityService.createAddress(addressEntity);

                // Center
                ServiceCenterEntity serviceCenterEntity = ServiceCenterEntity.builder()
                        .centerName(CENTER_NAMES[i])
                        .centerPhone(CENTER_PHONES[i])
                        .centerAddress(addressEntity)
                        .startTime(START_TIMES[0])
                        .endTime(END_TIMES[0])
                        .maxCapacity(10)
                        .build();
                serviceCenterService.create(serviceCenterEntity);
            }
        }

        // Setup EV Model
        for (String name : MODEL_NAMES) {
            EvModelEntity evModelEntity = EvModelEntity.builder()
                    .modelName(name)
                    .build();
            evModelService.createEVModel(evModelEntity);
        }


        // Setup OfferType
        for (int i = 0; i <= 2; i++) {
            OfferTypeEntity offerTypeEntity = OfferTypeEntity.builder()
                    .offerTypeName(OFFER_TYPE_NAMES[i])
                    .offerTypeDescription(OFFER_TYPE_DESCRIPTIONS[i])
                    .build();
            offerTypeService.createOfferType(offerTypeEntity);
        }
    }
}
