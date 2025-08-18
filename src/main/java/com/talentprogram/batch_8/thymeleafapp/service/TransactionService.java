package com.talentprogram.batch_8.thymeleafapp.service;

import com.talentprogram.batch_8.thymeleafapp.model.Transaction;
import com.talentprogram.batch_8.thymeleafapp.model.enumType.TransactionCategory;
import com.talentprogram.batch_8.thymeleafapp.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final Logger LOGGER= LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository  transactionRepository;

    public  boolean saveNewTransaction(Transaction saveTransaction){

        try {
            Optional<Transaction> transactionOptional = transactionRepository.findById(saveTransaction.getTransactionId());
            if(transactionOptional.isPresent()){
                LOGGER.info("Your transaction is already exist.");
                return  false;
            }

            transactionRepository.save(saveTransaction);
            return  true;

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return  false;
        }
    }

    public  boolean deleteTransaction(long saveTransactionId){

        try {
            Optional<Transaction> transactionOptional = transactionRepository.findById(saveTransactionId);
            if(transactionOptional.isEmpty()){
                LOGGER.info("Your transaction is not exist.");
                return  false;
            }
            Transaction saveTransaction = transactionOptional.get();
            saveTransaction.setDeleteFlag(1);
            saveTransaction.setDeletedAt(LocalDateTime.now());

            transactionRepository.save(saveTransaction);
            return  true;

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return  false;
        }
    }

    public  boolean editTransaction(Transaction editTransaction){

        try {
            Optional<Transaction> transaction=  transactionRepository.findById(editTransaction.getTransactionId());
            if(transaction.isEmpty()){
                LOGGER.info("Your transaction doesn't not exist");
                return  false;
            }
            // todo
            transactionRepository.save(editTransaction);

            return  true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return  false;
        }
    }

    public double getAllExpenseByTransactionCategory(String accountId, TransactionCategory transactionCategory){
        try {
            return transactionRepository.findAllExpenseByCategory(accountId,transactionCategory);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return  0;
        }

    }

    public double getAllIncomeByTransactionCategory(String accountId, TransactionCategory transactionCategory){
        try {
            return transactionRepository.findAllIncomeByCategory(accountId,transactionCategory);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return  0;
        }

    }

    public List<Transaction> getAllTransaction(String accountId) {
        try {
            return transactionRepository.findAllTransaction(accountId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public List<Transaction> getTransactionByMonth(String accountId, int month, int year) {
        try {
            return transactionRepository.findTransactionByMonth(accountId, month, year);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }



}