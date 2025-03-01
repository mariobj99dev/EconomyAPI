package org.maxrio22.economyapi.domain.model;
public class Loan {
    private int id;
    private int borrowerAccount;
    private int lenderAccount;
    private int bankId;
    private String loanType;
    private float amount;
    private float interestRate;
    private String dueDate;
    private boolean paid;
    private String createdAt;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBorrowerAccount() {
        return borrowerAccount;
    }
    public void setBorrowerAccount(int borrowerAccount) {
        this.borrowerAccount = borrowerAccount;
    }
    public int getLenderAccount() {
        return lenderAccount;
    }
    public void setLenderAccount(int lenderAccount) {
        this.lenderAccount = lenderAccount;
    }
    public int getBankId() {
        return bankId;
    }
    public void setBankId(int bankId) {
        this.bankId = bankId;
    }
    public String getLoanType() {
        return loanType;
    }
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public float getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isPaid() {
        return paid;
    }
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}