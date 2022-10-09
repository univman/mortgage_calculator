package com.company.service;

import com.company.model.Rate;
import com.company.model.Summary;

import java.util.List;

public interface SummaryService {

    Summary calculate(List<Rate> rates);
}
