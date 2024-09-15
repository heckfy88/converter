package converter.util

import converter.exception.NotFoundByOrdinalException

enum class CurrencyExchangePairs(val left: Currency, val right: Currency, var rate: Double) {
    RUB_USD(Currency.RUB, Currency.USD, 88.000),
    RUB_EUR(Currency.RUB, Currency.EUR, 110.200),
    USD_EUR(Currency.USD, Currency.EUR, 0.798),
    USD_USDT(Currency.USD, Currency.USDT, 0.9),
    USD_BTC(Currency.USD, Currency.BTC, 0.00002),
    ;

    companion object {
        fun getByOrdinal(ordinal: Int): CurrencyExchangePairs =
            try {
                CurrencyExchangePairs.values()[ordinal]
            } catch (e: Exception) {
                throw NotFoundByOrdinalException()
            }
    }
}