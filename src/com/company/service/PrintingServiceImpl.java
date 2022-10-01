package com.company.service;

import com.company.model.InputData;

public class PrintingServiceImpl implements PrintingService{

    @SuppressWarnings("StringBufferReplaceableByString") // delete warning from msg variable
    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        printMessage(msg.toString());
    }

    private void printMessage(String sb) {
        System.out.println(sb);
    }
}
