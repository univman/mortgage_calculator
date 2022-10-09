package com.company.service;

import com.company.model.InputData;
import com.company.model.Overpayment;
import com.company.model.Rate;
import com.company.model.Summary;
import com.company.model.exception.MortgageException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService{

    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        Optional.ofNullable(inputData.getOverpaymentSchema())
                .filter(schema -> schema.size() > 0)
                .ifPresent(schema -> logOverpayment(msg, inputData));

        printMessage(msg.toString());
    }

    private void logOverpayment(StringBuilder msg, InputData inputData) {
        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                msg.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                msg.append(OVERPAYMENT_REDUCE_RATE);
                break;
            default:
                throw new MortgageException();
        }

        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY);
        msg.append(NEW_LINE);
        for (Map.Entry<Integer, BigDecimal> entry : inputData.getOverpaymentSchema().entrySet()) {
            msg.append(OVERPAYMENT_MONTH).append(entry.getKey()).append(SPACE);
            msg.append(OVERPAYMENT_VALUE).append(entry.getValue()).append(CURRENCY);
            msg.append(NEW_LINE);
        }
        msg.append(NEW_LINE);
    }

    @Override
    public void printRates(List<Rate> rates) {
        String format = "%4s%3s | " +
                "%4s%3s | " +
                "%3s%2s | " +
                "%4s%2s | " +
                "%4s%7s | " +
                "%7s%6s | " +
                "%7s%7s | " +
                "%7s%8s | " +
                "%7s%9s | " +
                "%7s%3s | ";

        for (Rate rate : rates) {
            String message = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST, rate.getRateAmounts().getInterestAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                    OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(),
                    LEFT_AMOUNT, rate.getMortgageResidual().getAmount(),
                    LEFT_MONTH, rate.getMortgageResidual().getDuration()
            );
            printMessage(message);

            if(rate.getRateNumber().intValue() % 12 == 0) {
                System.out.println();
            }
        }
    }

    @SuppressWarnings("StringBufferReplaceableByString") // delete warning from msg variable
    @Override
    public void printSummary(Summary summary) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisions()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOSTS_SUM).append(summary.getTotalLosts()).append(CURRENCY);
        msg.append(NEW_LINE);

        printMessage(msg.toString());
    }

    private void printMessage(String sb) {
        System.out.println(sb);
    }
}
