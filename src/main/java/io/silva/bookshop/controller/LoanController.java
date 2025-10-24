package io.silva.bookshop.controller;

import io.silva.bookshop.dto.CreateLoanRequest;
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

    @PostMapping
    public void createLoan(@RequestBody CreateLoanRequest createLoanRequest) {
        loanService.saveLoan(createLoanRequest);
    }

    @GetMapping("{id}")
    public Loan searchLoan(@PathVariable UUID id) {
        return loanService.searchLoan(id);
    }

    @GetMapping
    public List<Loan> searchLoans(@RequestParam(required = false) UUID bookId, @RequestParam(required = false) UUID userId) {
        return loanService.listLoans(bookId, userId);
    }

    @PutMapping("{id}")
    public void updateLoan(@PathVariable UUID id, @RequestBody CreateLoanRequest createLoanRequest){
        loanService.updateLoan(createLoanRequest, id);
    }

    @DeleteMapping("{id}")
    public void deleteLoan(@PathVariable UUID id) {
        loanService.deleteLoan(id);
    }

}