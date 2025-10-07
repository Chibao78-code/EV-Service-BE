package com.swp.evmsystem.config;

import com.swp.evmsystem.entity.*;
import com.swp.evmsystem.enums.RoleName;
import com.swp.evmsystem.enums.TimeSlot;
import com.swp.evmsystem.enums.UserStatus;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import com.swp.evmsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer  implements CommandLineRunner {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ElectricVehicleService electricVehicleService;
    @Autowired
    private ServiceCenterService serviceCenterService;
    @Autowired
    private TimeSlotAvailabilityService timeSlotAvailabilityService;

    @Override
    public void run(String... args) throws Exception {
        if (roleService.getAllCount() > 0) {
            return;
        }
        //Setup Role
        RoleEntity role = new RoleEntity();
        role.setName(RoleName.valueOf("ADMIN"));
        roleService.createRole(role);
        role.setName(RoleName.valueOf("STAFF"));
        roleService.createRole(role);
        role.setName(RoleName.valueOf("TECHNICIAN"));
        roleService.createRole(role);
        role.setName(RoleName.valueOf("CUSTOMER"));
        roleService.createRole(role);

        //Setup User
        UserEntity user = new CustomerEntity();
        user.setRole(role);
        user.setPhone("0378523693");
        user.setPassword("$2a$12$YDETcayOevvKoDns/qKiReBXk3kAbrwhgBpOqq.Biv/P4.RFxCxpi"); //"user12345"
        user.setFullName("User Tai Nang");
        user.setStatus(UserStatus.ACTIVE);
        userService.createUser(user);

        //Setup EV
        ElectricVehicleEntity ev = new ElectricVehicleEntity();
        ev.setModel("Electric Vehicle");
        ev.setVIN("12345687512");
        ev.setOwner((CustomerEntity) user);
        ev.setLicensePlate("12345678A");
        electricVehicleService.create(ev);

        ev.setModel("Electric Vehicle 1");
        ev.setVIN("4568312afsa5462");
        ev.setOwner((CustomerEntity) user);
        ev.setLicensePlate("123456B8A");
        electricVehicleService.create(ev);

        //Setup Center
        ServiceCenterEntity svc = new ServiceCenterEntity();
        svc.setName("Service Center");
        svc.setPhone("123465852");
        svc.setEmail("center@ev.com");
        svc.setAddress("D9, High Tech, HCMC");
        serviceCenterService.create(svc);

        //Setup TimeSlot
        TimeSlotAvailability tsav = new TimeSlotAvailability();
        tsav.setDate(LocalDate.now());
        tsav.setBookingNumbers(0);
        tsav.setAvailableTechnician(10);
        tsav.setServiceCenter(svc);
        tsav.setTimeSlot(TimeSlot.SLOT_09_00);
        tsav.setAvailable();
        timeSlotAvailabilityService.addTimeSlotAvailabilityService(tsav);
        tsav.setTimeSlot(TimeSlot.SLOT_08_00);
        timeSlotAvailabilityService.addTimeSlotAvailabilityService(tsav);
    }
}
