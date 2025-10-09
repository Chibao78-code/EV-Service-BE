package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.OfferTypeDTO;
import com.swp.evmsystem.entity.OfferTypeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferTypeService {
    List<OfferTypeDTO> findAll();
    void createOfferType(OfferTypeEntity offerTypeEntity);
}
