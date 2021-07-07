package com.nabi.personalfinance.controller;

import java.net.URI;
import java.util.List;
import com.nabi.personalfinance.model.Transaction;
import com.nabi.personalfinance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/Transactions/a")
    public boolean a() {
        return transactionService == null;
    }

    @GetMapping("/Transactions/b")
    public boolean b() {
        return transactionService != null;
    }

    @GetMapping("/Transactions")
    public List<Transaction> retrieveAllTransactions(int pageNo, int pageSize) {
        return transactionService.search(pageNo, pageSize);
    }

    @GetMapping("/Transactions/{id}")
    public Transaction retrieveTransaction(@PathVariable long id) {
        Transaction transaction = transactionService.retrieveById(id);

        if (transaction == null)
            return null; //TransactionNotFoundException

        return transaction;
    }

    @DeleteMapping("/Transactions/{id}")
    public void deleteTransaction(@PathVariable long id) {
        transactionService.delete(id);
    }

    @PostMapping("/Transactions")
    public ResponseEntity<Object> createTransaction(@RequestBody Transaction Transaction) {
        Transaction savedTransaction = transactionService.create(Transaction);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTransaction.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/Transactions/{id}")
    public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction, @PathVariable long id) {

        Transaction savedTransaction = transactionService.retrieveById(id);

        if (savedTransaction == null)
            return ResponseEntity.notFound().build();

        transactionService.update(transaction);

        return ResponseEntity.noContent().build();
    }
}
