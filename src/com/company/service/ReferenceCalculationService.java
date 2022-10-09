package com.company.service;

import com.company.model.InputData;
import com.company.model.MortgageReference;
import com.company.model.Rate;

public interface ReferenceCalculationService {

    MortgageReference calculate(InputData inputData);
}
