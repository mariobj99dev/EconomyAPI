package org.maxrio22.economyapi.domain.model;
public class Currency {
    private int id;
    private String name;
    private String symbol;
    private float exchangeRate;
    private float inflationRate;
    private String createdAt;

    public Currency(int id, String name, String symbol, float exchangeRate, float inflationRate, String createdAt) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.exchangeRate = exchangeRate;
        this.inflationRate = inflationRate;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public float getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(float exchangeRate) { this.exchangeRate = exchangeRate; }
    public float getInflationRate() { return inflationRate; }
    public void setInflationRate(float inflationRate) { this.inflationRate = inflationRate; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
