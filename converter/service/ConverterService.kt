package converter.service

import converter.exception.NotEnoughBalanceException
import converter.observer.observable.ConverterObservable
import converter.user.User
import converter.util.Currency
import converter.util.CurrencyExchangePairs
import converter.util.ExchangeType

fun interface ConverterService {
    fun exchange(currencyPair: CurrencyExchangePairs, amount: Double, exchangeType: ExchangeType)
}

class ConverterServiceImpl(val user: User) : ConverterService, ConverterObservable() {

    override fun exchange(currencyPair: CurrencyExchangePairs, amount: Double, exchangeType: ExchangeType) {
        val userBalanceByCurrency = user.balance.balanceByCurrency
        if (exchangeType == ExchangeType.BUY) {
            buy(userBalanceByCurrency, currencyPair, amount)
        } else {
            sell(userBalanceByCurrency, currencyPair, amount)
        }

        notifyObservers()
    }

    private fun buy(
        balanceByCurrency: MutableMap<Currency, Double>,
        currencyPair: CurrencyExchangePairs,
        amount: Double
    ) {
        if (balanceByCurrency.getValue(currencyPair.left) < amount) {
            throw NotEnoughBalanceException()
        }
        balanceByCurrency[currencyPair.left] = balanceByCurrency.getValue(currencyPair.left).minus(amount)
        balanceByCurrency[currencyPair.right] =
            balanceByCurrency.getValue(currencyPair.right)
                .plus(if (currencyPair.rate < 1) amount * currencyPair.rate else amount / currencyPair.rate)
    }

    private fun sell(
        balanceByCurrency: MutableMap<Currency, Double>,
        currencyPair: CurrencyExchangePairs,
        amount: Double
    ) {
        if (balanceByCurrency.getValue(currencyPair.right) < amount) {
            throw NotEnoughBalanceException()
        }
        balanceByCurrency[currencyPair.right] = balanceByCurrency.getValue(currencyPair.right).minus(amount)
        balanceByCurrency[currencyPair.left] =
            balanceByCurrency.getValue(currencyPair.left)
                .plus(if (currencyPair.rate > 1) amount * currencyPair.rate else amount / currencyPair.rate)
    }
}