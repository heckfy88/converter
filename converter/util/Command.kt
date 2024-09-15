package converter.util

import converter.exception.NotFoundByOrdinalException

enum class Command {
    GET_BALANCE,
    GET_RATES,
    EXCHANGE,
    ;

    companion object {
        fun getByOrdinal(ordinal: Int): Command =
            try {
                Command.values()[ordinal]
            } catch (e: Exception) {
                throw NotFoundByOrdinalException()
            }
    }
}