package com.brainstorm.Egen.ai.controller;

import com.brainstorm.Egen.ai.data.ResponseDTO;
import com.brainstorm.Egen.ai.data.TransactionDTO;
import com.brainstorm.Egen.ai.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaction/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(value= "/createTransaction")
    public ResponseEntity<ResponseDTO> createTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.createTransaction(transactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201","Created"));
    }

}
