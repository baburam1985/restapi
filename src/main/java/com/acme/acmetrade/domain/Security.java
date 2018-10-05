package com.acme.acmetrade.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.validation.constraints.NotNull;

@EntityScan
public class Security {

    @NotNull
    private String symbol;

    @NotNull
    private String companyName;

    @NotNull
    private int sectorId;

    private String description;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {

        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
