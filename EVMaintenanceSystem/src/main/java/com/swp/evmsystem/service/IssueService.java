package com.swp.evmsystem.service;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public List<IssueEntity> getAllIssues() {
        return issueRepository.findAll();
    }

    public List<IssueEntity> getIssuesByOfferType(Integer offerTypeId) {
        return issueRepository.findByOfferType_OfferTypeId(offerTypeId);
    }

    public IssueEntity createIssue(IssueEntity issue) {
        return issueRepository.save(issue);
    }

    public IssueEntity getIssueById(Integer id) {
        return issueRepository.findById(id).orElse(null);
    }
}