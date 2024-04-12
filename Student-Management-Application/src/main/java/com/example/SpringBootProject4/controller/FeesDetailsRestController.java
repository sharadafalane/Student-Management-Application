package com.example.SpringBootProject4.controller;

import com.example.SpringBootProject4.service.FeesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootProject4.model.FeesDetails;

import java.util.List;

@RestController
public class FeesDetailsRestController{

    @Autowired
    private FeesDetailsService feesDetailsService;

    @PostMapping("${endpoint.feesDetails.create}")
    public ResponseEntity<String> createFeesDetails(@RequestBody FeesDetails feesDetails) 
    {
        String status = feesDetailsService.upsert(feesDetails);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("${endpoint.feesDetails.getById}")
    public ResponseEntity<List<FeesDetails>> getFeesDetails(@PathVariable Long studentId,@RequestParam(required=false) Long courseId ) 
    {
        List<FeesDetails> feesDetails = feesDetailsService.getById(studentId,courseId);
        if(feesDetails.isEmpty()) {
            return new ResponseEntity<>(feesDetails, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feesDetails, HttpStatus.OK);
    }

    @GetMapping("${endpoint.feesDetails.getAll}")
    public ResponseEntity<List<FeesDetails>> getAllFeesDetails() 
    {
        List<FeesDetails> allFeesDetails = feesDetailsService.getAllFeesDetails();
        return new ResponseEntity<>(allFeesDetails, HttpStatus.OK);
    }

    @PutMapping("${endpoint.feesDetails.update}")
    public ResponseEntity<String> updateFeesDetails(@RequestBody FeesDetails feesDetails) 
    {
        String status = feesDetailsService.upsert(feesDetails);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("${endpoint.feesDetails.delete}")
    public ResponseEntity<String> deleteFeesDetails(@PathVariable Long id) 
    {
        String status = feesDetailsService.deleteById(id);
        if(status.equals("Success: Fees details deleted successfully.")) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(status,HttpStatus.NOT_FOUND);
        }
    }

   
}

