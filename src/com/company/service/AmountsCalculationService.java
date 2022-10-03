package com.company.service;

import com.company.model.InputData;
import com.company.model.Rate;
import com.company.model.RateAmounts;

public interface AmountsCalculationService {
    RateAmounts calculate(InputData inputData);

    RateAmounts calculate(InputData inputData, Rate previousRate);
}
