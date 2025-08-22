package com.talentprogram.batch_8.thymeleafapp.controller;

import com.talentprogram.batch_8.thymeleafapp.dto.TransactionDto;
import com.talentprogram.batch_8.thymeleafapp.model.Account;
import com.talentprogram.batch_8.thymeleafapp.model.Transaction;
import com.talentprogram.batch_8.thymeleafapp.model.enumType.TransactionCategory;
import com.talentprogram.batch_8.thymeleafapp.model.enumType.TransactionType;
import com.talentprogram.batch_8.thymeleafapp.service.AccountService;
import com.talentprogram.batch_8.thymeleafapp.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
    private final AccountService accountService;

    @Autowired
    private final TransactionService transactionService;

    @GetMapping("/home")
    public String home(HttpSession session,
                       Model model) {
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("name",account.getUserName());
        model.addAttribute("balance", account.getBalance());

        String accountId = account.getAccountId();
        List<Transaction> transactionList = transactionService.getAllTransaction(accountId);

        model.addAttribute("transactions", transactionList);
        return "home";
    }

    @PostMapping("/addTransaction")
    public String saveTransaction(@RequestParam("transactionType") String tranType,
                                  @RequestParam("amount") Double amount,
                                  @RequestParam("transactionCategory") String category,
                                  HttpSession session) {

        Account account = (Account) session.getAttribute("account");

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.valueOf(tranType));
        transaction.setTransactionCategory(TransactionCategory.valueOf(category));
        transaction.setAccountId(account.getAccountId());

        boolean saved = transactionService.saveNewTransaction(transaction);

        if (transaction.getTransactionType() == TransactionType.income) {
            account.setBalance(account.getBalance() + amount);
        } else {
            account.setBalance(account.getBalance() - amount);
        }


        accountService.updateBalance(account.getAccountId(), amount,
                transaction.getTransactionType(), 0);


        session.setAttribute("account", account);

        return "redirect:/home";
    }

    @GetMapping()
    public String transactionByMonth(@RequestParam("month") int month,
                                     @RequestParam("year") int year,
                                     HttpSession session,
                                     Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        List<Transaction> transactions = transactionService.getTransactionByMonth(account.getAccountId(), month, year);

        model.addAttribute("transactions", transactions);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("balance", account.getBalance());
        model.addAttribute("name", account.getUserName());

        return "redirect:/home";
    }


}


