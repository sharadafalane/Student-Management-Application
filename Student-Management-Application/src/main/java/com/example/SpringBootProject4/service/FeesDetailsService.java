package com.example.SpringBootProject4.service;

import com.example.SpringBootProject4.model.FeesDetails;

import java.util.List;
public interface FeesDetailsService {
    String upsert(FeesDetails feesDetails);
    public List<FeesDetails> getById(Long studentId, Long courseId);
    public List<FeesDetails> getAllFeesDetails();
    public String deleteById(Long id);
}
