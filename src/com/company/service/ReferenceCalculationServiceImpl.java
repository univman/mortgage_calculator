package com.company.service;

import com.company.model.*;
import com.company.model.exception.MortgageException;

import java.math.BigDecimal;

import static com.company.utils.CalculationUtils.calculateResidualAmount;

public class ReferenceCalculationServiceImpl implements ReferenceCalculationService {

    @Override
    public MortgageReference calculate(InputData inputData) {
        return new MortgageReference(inputData.getAmount(), inputData.getMonthsDuration());
    }

    @Override
    public MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate) {
        if (BigDecimal.ZERO.equals(previousRate.getMortgageResidual().getAmount())){
            return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
        }

        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                return new MortgageReference(inputData.getAmount(), inputData.getMonthsDuration());
            case Overpayment.REDUCE_RATE:
                return reduceRateMortgageReference(rateAmounts, previousRate);
            default:
                throw new MortgageException();
        }

    }

    private MortgageReference reduceRateMortgageReference(RateAmounts rateAmounts, Rate previousRate) {
        if (rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal referenceAmount
                    = calculateResidualAmount(previousRate.getMortgageReference().getReferenceAmount(), rateAmounts);
            BigDecimal referenceDuration = previousRate.getMortgageResidual().getDuration().subtract(BigDecimal.ONE);
            return new MortgageReference(referenceAmount, referenceDuration);
        }

        return new MortgageReference(
                previousRate.getMortgageReference().getReferenceAmount(),
                previousRate.getMortgageReference().getReferenceDuration()
        );
    }
}
