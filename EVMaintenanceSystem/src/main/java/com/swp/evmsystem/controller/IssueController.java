package com.swp.evmsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<IssueEntity>> getAllIssues() {
        List<IssueEntity> issues = issueService.getAllIssues();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
    @GetMapping("/by-offer-type/{offerTypeId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<IssueEntity>> getIssuesByOfferType(@PathVariable Integer offerTypeId) {
        List<IssueEntity> issues = issueService.getIssuesByOfferType(offerTypeId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<IssueEntity> getIssueById(@PathVariable Integer id) {
        IssueEntity issue = issueService.getIssueById(id);
        if (issue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }