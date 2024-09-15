package converter.observer

import converter.util.CurrencyExchangePairs
import java.util.*

interface Observer {
    fun onConvert(change: Double = 0.05)
}

class RateObserver : Observer {
    override fun onConvert(change: Double) {
        CurrencyExchangePairs.values().forEach { it.rate *= Random().nextDouble(1 - change, 1 + change) }
    }
}