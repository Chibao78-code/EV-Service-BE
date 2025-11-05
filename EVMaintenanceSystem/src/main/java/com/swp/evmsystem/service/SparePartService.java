package com.swp.evmsystem.service;

@Service
public class SparePartService {

    @Autowired
    private SparePartRepository sparePartRepository;

    public List<SparePartEntity> getAllSpareParts() {
        return sparePartRepository.findAll();
    }

    public List<SparePartEntity> getInStockParts() {
        return sparePartRepository.findByInStock(true);
    }

    public SparePartEntity getSparePartById(Integer id) {
        return sparePartRepository.findById(id).orElse(null);
    }

    public SparePartEntity createSparePart(SparePartEntity sparePart) {
        return sparePartRepository.save(sparePart);
    }

    public SparePartEntity updateSparePart(SparePartEntity sparePart) {
        return sparePartRepository.save(sparePart);
    }

    public void deleteSparePart(Integer id) {
        sparePartRepository.deleteById(id);
    }
