package com.epam.mjc.currency.service.impl;

import com.epam.mjc.currency.entity.Currency;
import com.epam.mjc.currency.service.CurrencyConversionService;

public class HardcodedCurrencyConversionService implements CurrencyConversionService {
  @Override
  public double getConversionRatio(Currency from, Currency to) {
    switch (from) {
      case KZT:
        switch (to) {
          case USD:
            return (float)1 / 427;
          case EUR:
            return (float)1 / 504;
          case KZT:
            return 1;
          case RUB:
            return 1 / 5.8;
        }
      case EUR:
        switch (to) {
          case USD:
            return 1.18;
          case EUR:
            return 1;
          case KZT:
            return 504;
          case RUB:
            return 86.9;
        }
      case USD:
        switch (to) {
          case USD:
            return 1;
          case EUR:
            return 1 / 1.18;
          case KZT:
            return 427;
          case RUB:
            return 73.6;
        }
      case RUB:
        switch (to) {
          case USD:
            return 1 / 73.62;
          case EUR:
            return 1 / 86.9;
          case KZT:
            return 5.8;
          case RUB:
            return 1;
        }
    }
    return 1;
  }
}
