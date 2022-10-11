package com.company;

import com.company.model.InputData;
import com.company.model.Overpayment;
import com.company.model.RateType;
import com.company.service.*;

import java.math.BigDecimal;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("300000"))
                .withOverpaymentSchema(Map.of(
//                        12, BigDecimal.valueOf(10000),
//                        24, BigDecimal.valueOf(10000)
                ))
                .withMonthsDuration(BigDecimal.valueOf(360))
                .withRateType(RateType.DECREASING)
                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE);

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculationServiceImpl(
                        new ConstantAmountsCalculationServiceImpl(),
                        new DecreasingAmountsCalculationServiceImpl()
                ),
                new OverpaymentCalculationServiceImpl(),
                new ResidualCalculationServiceImpl(),
                new ReferenceCalculationServiceImpl()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create()
        );
        mortgageCalculationService.calculate(inputData);
    }
}
