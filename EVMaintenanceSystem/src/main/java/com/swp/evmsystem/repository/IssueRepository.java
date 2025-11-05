package com.swp.evmsystem.repository;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Integer> {
    List<IssueEntity> findByOfferType_OfferTypeId(Integer offerTypeId);
}