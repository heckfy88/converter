package converter.user

import converter.util.Currency

data class User(
    val name: String,
    val balance: Balance = Balance()
)

data class Balance(val balanceByCurrency: MutableMap<Currency, Double> = INITIAL_USER_BALANCE) {
    companion object {
        private val INITIAL_USER_BALANCE: MutableMap<Currency, Double> =
            mutableMapOf(
                Currency.RUB to 1_000_000.0,
                Currency.USD to 0.0,
                Currency.EUR to 0.0,
                Currency.USDT to 0.0,
                Currency.BTC to 0.0,
            )
    }
}