package com.swp.evmsystem.repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    /**
     * Find all employees (technicians) by service center
     */
    List<EmployeeEntity> findByCenterId(ServiceCenterEntity centerId);

    /**
     * Find all employees with TECHNICIAN role
     */
    @Query("SELECT e FROM EmployeeEntity e WHERE e.role = 'TECHNICIAN'")
    List<EmployeeEntity> findAllTechnicians();
}
