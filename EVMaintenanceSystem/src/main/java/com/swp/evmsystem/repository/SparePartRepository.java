package com.swp.evmsystem.repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePartEntity, Integer> {
    List<SparePartEntity> findByInStock(Boolean inStock);
}
