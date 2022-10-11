package com.company.utils;

import com.company.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtils {

    private CalculationUtils() {
    }

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    public static BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateResidualAmount(BigDecimal amount, RateAmounts rateAmounts) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
