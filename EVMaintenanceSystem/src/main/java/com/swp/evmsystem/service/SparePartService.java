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

    // DTO-based methods
    public List<SparePartResponseDTO> getAllSparePartsDTO() {
        return sparePartRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public SparePartResponseDTO createSparePartDTO(SparePartRequestDTO request) { // chức năng tạo phụ tùng
        SparePartEntity entity = SparePartEntity.builder()
                .sparePartName(request.getPartName())
                .partNumber(request.getPartNumber())
                .category(request.getCategory())
                .price(request.getUnitPrice().intValue())
                .unitCost(request.getUnitPrice().intValue())
                .quantity(request.getStockQuantity())
                .minimumStock(request.getMinStockLevel() != null ? request.getMinStockLevel() : 10)
                .supplier(request.getSupplier())
                .description(request.getDescription())
                .inStock(request.getStockQuantity() > 0)
                .build();

        SparePartEntity saved = sparePartRepository.save(entity); // Lưu entity vào cơ sở dữ liệu
        return convertToDTO(saved);
    }

    public SparePartResponseDTO updateSparePartDTO(Integer id, SparePartRequestDTO request) { // chức năng cập nhật phụ tùng
        SparePartEntity existing = sparePartRepository.findById(id) // Tìm kiếm phụ tùng theo ID
                .orElseThrow(() -> new RuntimeException("Spare part not found with id: " + id));

        existing.setSparePartName(request.getPartName());
        existing.setPartNumber(request.getPartNumber());
        existing.setCategory(request.getCategory());
        existing.setPrice(request.getUnitPrice().intValue());
        existing.setUnitCost(request.getUnitPrice().intValue());
        existing.setQuantity(request.getStockQuantity());
        existing.setMinimumStock(request.getMinStockLevel() != null ? request.getMinStockLevel() : 10);
        existing.setSupplier(request.getSupplier());
        existing.setDescription(request.getDescription());
        existing.setInStock(request.getStockQuantity() > 0);

        SparePartEntity updated = sparePartRepository.save(existing);
        return convertToDTO(updated); // Trả về DTO của phụ tùng đã cập nhật
    }

    public void deleteSparePartById(Integer id) { // chức năng xóa phụ tùng
        if (!sparePartRepository.existsById(id)) {
            throw new RuntimeException("Spare part not found with id: " + id);
        }
        sparePartRepository.deleteById(id); // Xóa phụ tùng khỏi cơ sở dữ liệu
    }

}