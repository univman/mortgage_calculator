package com.company.service;

import com.company.model.InputData;
import com.company.model.Overpayment;
import com.company.model.Rate;
import com.company.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface AmountsCalculationService {

    BigDecimal YEAR = BigDecimal.valueOf(12);

    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);

    static BigDecimal calculateQ(final BigDecimal interestPercent) {
        return interestPercent
                .divide(AmountsCalculationService.YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }

    static BigDecimal compareCapitalWithResidual(final BigDecimal capitalAmount, final BigDecimal residualAmount) {
        if (capitalAmount.compareTo(residualAmount) >= 0) {
            return residualAmount;
        }
        return capitalAmount;
    }
}
