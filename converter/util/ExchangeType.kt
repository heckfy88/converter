package converter.util

import converter.exception.NotFoundByOrdinalException

enum class ExchangeType {
    SELL,
    BUY,
    ;

    companion object {
        fun getByOrdinal(ordinal: Int): ExchangeType =
            try {
                ExchangeType.values()[ordinal]
            } catch (e: Exception) {
                throw NotFoundByOrdinalException()
            }
    }
}