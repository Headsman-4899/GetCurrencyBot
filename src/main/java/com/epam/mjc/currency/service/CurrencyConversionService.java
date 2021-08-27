package com.epam.mjc.currency.service;

import com.epam.mjc.currency.entity.Currency;
import com.epam.mjc.currency.service.impl.HardcodedCurrencyConversionService;

public interface CurrencyConversionService {

  static CurrencyConversionService getInstance() {
    return new HardcodedCurrencyConversionService();
  }

  double getConversionRatio(Currency original, Currency target);
}
