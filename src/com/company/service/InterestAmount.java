package com.company.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class InterestAmount {

    protected static final BigDecimal YEAR = BigDecimal.valueOf(12);

    protected static BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }
}
