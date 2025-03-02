package org.maxrio22.economyapi.domain.model;
public class Bank {
    private int id;
    private String name;
    private String ownerAccount;
    private int currencyId;
    private float reserveRatio;
    private float interestRate;
    private float loanInterestRate;
    private float transactionFee;
    private String status;
    private String createdAt;

    public Bank(int id, String name, String ownerAccount, int currencyId, float reserveRatio, float interestRate, float loanInterestRate, float transactionFee, String status, String createdAt) {
        this.id = id;
        this.name = name;
        this.ownerAccount = ownerAccount;
        this.currencyId = currencyId;
        this.reserveRatio = reserveRatio;
        this.interestRate = interestRate;
        this.loanInterestRate = loanInterestRate;
        this.transactionFee = transactionFee;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerAccount() {
        return ownerAccount;
    }

    public void setOwnerAccount(String ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public float getReserveRatio() {
        return reserveRatio;
    }

    public void setReserveRatio(float reserveRatio) {
        this.reserveRatio = reserveRatio;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public float getLoanInterestRate() {
        return loanInterestRate;
    }

    public void setLoanInterestRate(float loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    public float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(float transactionFee) {
        this.transactionFee = transactionFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}