package io.silva.bookshop.controller;

import io.silva.bookshop.model.Loan;
import io.silva.bookshop.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("loans")
@AllArgsConstructor
public class LoanController {
    LoanService loanService;

    @PostMapping("save/{bookId}/{userId}")
    public void createLoan(@PathVariable UUID bookId, @PathVariable UUID userId){
        loanService.saveLoan(bookId, userId);
    }

    @DeleteMapping("delete/{id}")
    public void deleteLoan(@PathVariable UUID id){
        loanService.deleteLoan(id);
    }

    @GetMapping("search_loan/{id}")
    public Loan searchLoan(@PathVariable UUID id){
        return loanService.searchLoan(id);
    }

    @GetMapping("all_loan")
    public List<Loan> listAllLoans(){
        return loanService.listLoans();
    }

    @GetMapping("loan_book/{id}")
    public Loan listBookLoans(@PathVariable UUID id){
        return loanService.listBookLoans(id);
    }

    @GetMapping("loan_user/{id}")
    public List<Loan> listUserLoans(@PathVariable UUID id){
        return loanService.listUserLoans(id);
    }
}
