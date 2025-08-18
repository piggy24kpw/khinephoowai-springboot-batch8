package com.talentprogram.batch_8.thymeleafapp.repository;

import com.talentprogram.batch_8.thymeleafapp.model.Transaction;
import com.talentprogram.batch_8.thymeleafapp.model.enumType.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

    @Query("select sum(t.amount) from Transaction t where t.accountId = :accountId" +
            " AND t.transactionCategory= :transactionCategory AND t.transactionType='expense' AND t.deleteFlag =0")
    double findAllExpenseByCategory(@Param("accountId") String accountId,
                                    @Param("transactionCategory") TransactionCategory transactionCategory);

    @Query("select sum(t.amount) from Transaction t where t.accountId = :accountId" +
            " AND t.transactionCategory= :transactionCategory AND t.transactionType='income' AND t.deleteFlag =0")
    double findAllIncomeByCategory(@Param("accountId") String accountId,
                                    @Param("transactionCategory") TransactionCategory transactionCategory);

    @Query("select t from Transaction t where t.accountId = :accountId AND t.deleteFlag =0")
    List<Transaction> findAllTransaction(@Param("accountId") String accountId);

    @Query("select t from Transaction t where t.accountId = :accountId AND MONTH(t.createdAt) = :month " +
            "AND YEAR(t.createdAt) = :year " +
            "AND t.deleteFlag = 0")
    List<Transaction> findTransactionByMonth(@Param("accountId") String accountId,
                                             @Param("month") int month,
                                             @Param("year") int year);
}