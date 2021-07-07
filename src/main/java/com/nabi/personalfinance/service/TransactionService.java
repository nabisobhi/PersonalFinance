package com.nabi.personalfinance.service;

import java.util.List;
import java.util.Optional;
import com.nabi.personalfinance.model.Transaction;
import com.nabi.personalfinance.repository.TransactionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }
    public List<Transaction> search(int pageNo, int pageSize) {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public Transaction retrieveById(long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(!transaction.isPresent())
            return null;
        return transaction.get();
    }

    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction update(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public void delete(long transactionId){
        transactionRepository.deleteById(transactionId);
    }
}