package org.maxrio22.economyapi.domain.model;
public class Account {
    private int id;
    private String iban;
    private String ownerType;
    private String ownerId;
    private int bankId;
    private float balance;
    private String type;
    private boolean blocked;
    private String createdAt;

    public Account(int id, String iban, String ownerType, String ownerId, int bankId, float balance, String type, boolean blocked, String createdAt) {
        this.id = id;
        this.iban = iban;
        this.ownerType = ownerType;
        this.ownerId = ownerId;
        this.bankId = bankId;
        this.balance = balance;
        this.type = type;
        this.blocked = blocked;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}