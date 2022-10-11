package com.company.service;

import com.company.model.InputData;
import com.company.model.MortgageReference;
import com.company.model.Rate;
import com.company.model.RateAmounts;

public interface ReferenceCalculationService {

    MortgageReference calculate(InputData inputData);

    MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}
