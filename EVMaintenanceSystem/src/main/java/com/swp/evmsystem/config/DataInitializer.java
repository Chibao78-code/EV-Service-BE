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
    // Data for Customer
    final String[] CUSTOMER_NAMES = {"Nguyễn Văn An", "Trần Văn Bình", "Chí Bảo 1", "Chí Bảo 2"};
    final String[] CUSTOMER_PHONES = {"0905111111", "0905222222", "0905333333", "0905444444"};
    final String[] CUSTOMER_PASSWORDS = {"user1111", "user2222", "user3333", "user4444"};
    final String[] CUSTOMER_EMAILS = {"user1@kafe.com", "user2@kafe.com", "quangphap931@gmail.com", "chibao1@gmail.com"};
    // Data for Employee
    final String[] EMPLOYEE_NAMES = {"Admin Số 1", "Staff Số 1", "Technician Số 1"};
    final String[] EMPLOYEE_PHONES = {"0123456789", "0987654321", "0111222333"};
    final String[] EMPLOYEE_PASSWORDS = {"admin123", "staff123", "tech123"};
    final String[] EMPLOYEE_EMAILS = {"dinhdinhchibao@gmail.com", "ddinhchibao@gmail.com", "zzz.dinhchibao.15@gmail.com"};
    final Role[] EMPLOYEE_ROLES = {Role.ADMIN, Role.STAFF, Role.TECHNICIAN};
    // Data for Address
    final String[] PROVINCE_NAMES = {"TP. HCM"};
    final String[] DISTRICT_NAMES = {"Quận 1", "Quận 7", "TP. Thủ Đức", "Quận Gò Vấp", "Quận Bình Thạnh"};
    final String[] WARD_NAMES = {"Phường Bến Nghé", "Phường Tân Hưng", "Phường Linh Chiểu", "Phường 5", "Phường 25"};
    final String[] ADDRESS_LINES = {"123 Nguyễn Huệ", "456 Nguyễn Văn Linh", "789 Võ Văn Ngân", "321 Phan Văn Trị", "654 Xô Viết Nghệ Tĩnh"};
    // Data for Center
    final String[] CENTER_NAMES = {"VinFast Service Center Quận 1", "VinFast Service Center Quận 7", "VinFast Service Center Thủ Đức", "VinFast Service Center Gò Vấp", "VinFast Service Center Bình Thạnh"};
    final LocalTime[] START_TIMES = {LocalTime.parse("08:00")};
    final LocalTime[] END_TIMES = {LocalTime.parse("18:00")};
    final String[] CENTER_PHONES = {"02812345678", "02823456789", "02834567890", "02845678901", "02856789012"};
    // Data for Vehicle
    final String[] MODEL_NAMES = {"Klara S (3.5 kWh)", "Feliz S (3.5 kWh)", "Vento S (3.5 kWh)", "Theon S (3.5 kWh)", "Evo 200 (2.5 kWh)", "Evo 200 Lite (2.5 kWh)", "Impes (2.0 kWh)", "Ludo (1.8 kWh)", "Klara A2 (2.5 kWh)"};
    final String[] EV_VINS = {"FAKEVIN0000000000", "TESTVIN0000000000", "PLACEHOLDER000000"};
    final String[] EV_LICENSE_PLATES = {"49MĐ685692", "59MĐ102563", "59MĐ155567"};
    // Data for Type of Service
    final String[] OFFER_TYPE_NAMES = {"Bảo dưỡng định kỳ", "Thay thế phụ tùng", "Sửa chữa"};
    final String[] OFFER_TYPE_DESCRIPTIONS = {"Bảo dưỡng toàn diện xe máy điện theo tiêu chuẩn VinFast", "Thay thế phụ tùng chính hãng VinFast", "Sửa chữa các vấn đề kỹ thuật"};

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
    CustomerService customerService;
    @Autowired
    OfferTypeService offerTypeService;


    // WANT TO ADD SOMETHING? CALL ME!
    @Override
    public void run(String... args) throws Exception {
        // This condition checks the databases and provides if nothing in it
        if (userService.getAllCount() > 0) {
            return;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i <= 2; i++) {
            UserEntity emp = EmployeeEntity.builder()
                    .fullName(EMPLOYEE_NAMES[i])
                    .role(EMPLOYEE_ROLES[i])
                    .phone(EMPLOYEE_PHONES[i])
                    .email(EMPLOYEE_EMAILS[i])
                    .password(passwordEncoder.encode(EMPLOYEE_PASSWORDS[i]))
                    .status(UserStatus.ACTIVE)
                    .build();
            userService.createUser(emp);
        }

        for (int i = 0; i <= 3; i++) {
            UserEntity user = CustomerEntity.builder()
                    .fullName(CUSTOMER_NAMES[i])
                    .role(Role.CUSTOMER)
                    .phone(CUSTOMER_PHONES[i])
                    .email(CUSTOMER_EMAILS[i])
                    .password((new BCryptPasswordEncoder()).encode(CUSTOMER_PASSWORDS[i]))
                    .status(UserStatus.ACTIVE)
                    .build();
            userService.createUser(user);
        }

        {
            ProvinceEntity provinceEntity = new ProvinceEntity();
            provinceEntity.setProvinceName(PROVINCE_NAMES[0]);
            provinceEntityService.createProvince(provinceEntity);

            for (int i = 0; i <= 4; i++) {
                // Quận
                DistrictEntity districtEntity = DistrictEntity.builder()
                        .districtName(DISTRICT_NAMES[i])
                        .province(provinceEntity)
                        .build();
                districtEntityService.createDistrict(districtEntity);

                // Phường
                WardEntity wardEntity = WardEntity.builder()
                        .wardName(WARD_NAMES[i])
                        .district(districtEntity)
                        .build();
                wardEntityService.createWard(wardEntity);

                // Địa chỉ
                AddressEntity addressEntity = AddressEntity.builder()
                        .addressLine(ADDRESS_LINES[i])
                        .ward(wardEntity)
                        .build();
                addressEntityService.createAddress(addressEntity);

                // Trung tâm
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

        for (String name : MODEL_NAMES) {
            EvModelEntity evModelEntity = EvModelEntity.builder()
                    .modelName(name)
                    .build();
            evModelService.createEVModel(evModelEntity);
        }

        for (int i = 0; i <= 2; i++) {
            ElectricVehicleEntity electricVehicleEntity = ElectricVehicleEntity.builder()
                    .vin(EV_VINS[i])
                    .licensePlate(EV_LICENSE_PLATES[i])
                    .maintenanceStatus(EvMaintenanceStatus.AVAILABLE)
                    .model(evModelService.getModelById(i + 2))
                    .owner(i > 0 ? customerService.getCustomerById(4) : customerService.getCustomerById(5))
                    .build();
            electricVehicleService.create(electricVehicleEntity);
        }

        for (int i = 0; i <= 2; i++) {
            OfferTypeEntity offerTypeEntity = OfferTypeEntity.builder()
                    .offerTypeName(OFFER_TYPE_NAMES[i])
                    .offerTypeDescription(OFFER_TYPE_DESCRIPTIONS[i])
                    .build();
            offerTypeService.createOfferType(offerTypeEntity);
        }
    }
}
