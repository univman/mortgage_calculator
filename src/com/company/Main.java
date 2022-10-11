package com.company;

import com.company.model.InputData;
import com.company.model.Overpayment;
import com.company.model.RateType;
import com.company.service.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        // To jest rodzaj mapy, który w tym przypadku trzyma klucze posortowane rosnąco.
        // Wiem, że nie tłumaczyłem go wcześniej. Spokojnie, jeszcze będzie ;)
        Map<Integer, BigDecimal> overpaymentSchema = new TreeMap<>();
        overpaymentSchema.put(5, BigDecimal.valueOf(12000));
        overpaymentSchema.put(19, BigDecimal.valueOf(10000));
        overpaymentSchema.put(28, BigDecimal.valueOf(11000));
        overpaymentSchema.put(64, BigDecimal.valueOf(16000));
        overpaymentSchema.put(78, BigDecimal.valueOf(18000));

        InputData defaultInputData = new InputData()
                .withAmount(new BigDecimal("296192.11"))
                .withMonthsDuration(BigDecimal.valueOf(360))
                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE)
                .withRateType(RateType.DECREASING)
                .withOverpaymentSchema(overpaymentSchema);

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
        mortgageCalculationService.calculate(defaultInputData);
    }
}
