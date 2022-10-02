package com.company.service;

import com.company.model.InputData;
import com.company.model.TimePoint;

import java.math.BigDecimal;

public interface TimePointService {

    TimePoint calculate(BigDecimal rateNumber, InputData inputData);
}
