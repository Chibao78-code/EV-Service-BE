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

        // Setup Customer
        for (int i = 0; i <= 1; i++) {
            UserEntity user = CustomerEntity.builder()
                    .fullName(CUSTOMER_NAMES[i])
                    .role(Role.CUSTOMER)
                    .phone(CUSTOMER_PHONES[i])
                    .password((new BCryptPasswordEncoder()).encode(CUSTOMER_PASSWORDS[i]))
                    .status(UserStatus.ACTIVE)
                    .build();
            userService.createUser(user);
        }

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

        // Setup EV
        for (int i = 0; i <= 2; i++) {
            ElectricVehicleEntity electricVehicleEntity = ElectricVehicleEntity.builder()
                    .vin(EV_VINS[i])
                    .licensePlate(EV_LICENSE_PLATES[i])
                    .maintenanceStatus(EvMaintenanceStatus.AVAILABLE)
                    .model(evModelService.getModelById(i+2))
                    .owner(i > 0 ? customerService.getCustomerById(2) : customerService.getCustomerById(1))
                    .build();
            electricVehicleService.create(electricVehicleEntity);
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
