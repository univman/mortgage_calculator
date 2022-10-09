package com.company.service;

import com.company.model.InputData;
import com.company.model.MortgageReference;
import com.company.model.Rate;

public class ReferenceCalculationServiceImpl implements ReferenceCalculationService{

    @Override
    public MortgageReference calculate(InputData inputData) {
        return new MortgageReference(inputData.getAmount(), inputData.getMonthsDuration());
    }
}
