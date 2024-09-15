package converter.service

import converter.user.Balance
import converter.user.User
import converter.util.Command
import converter.util.CurrencyExchangePairs
import converter.util.ExchangeType
import java.util.*

interface InputConsoleService {
    fun login(): User
    fun inputCommand(): Command
    fun inputType(): ExchangeType
    fun inputCurrencyPair(): CurrencyExchangePairs
    fun inputAmount(): Double
}

interface OutputConsoleService {
    fun welcome()
    fun printBalance(balance: Balance)
    fun printRates()
    fun printErrorMessage(message: String?)
    fun outputCommands()
}

class ConsoleServiceImpl(val scanner: Scanner = Scanner(System.`in`)) : InputConsoleService, OutputConsoleService {

    companion object {
        private const val NUMBER_FORMAT = "%.6f"
    }

    override fun login(): User {
        println("Please enter your name")
        val name = readln()
        return User(name)
    }

    override fun inputCommand(): Command {
        return Command.getByOrdinal(scanner.nextInt())
    }

    override fun outputCommands() =
        println("Choose command:\n" +
                Command.values().joinToString("\n") { "${it.ordinal}. ${it.name}" }
        )

    override fun inputType(): ExchangeType {
        println("Choose exchange operation type:\n" +
                ExchangeType.values().joinToString("\n") { "${it.ordinal}. ${it.name}" })

        return ExchangeType.getByOrdinal(scanner.nextInt())
    }

    override fun inputCurrencyPair(): CurrencyExchangePairs {
        println("Input currency:\n" + CurrencyExchangePairs.values().joinToString("\n") { "${it.ordinal}. ${it.name}" })
        return CurrencyExchangePairs.getByOrdinal(scanner.nextInt())
    }

    override fun inputAmount(): Double {
        println("Input amount:")
        return scanner.nextDouble()
    }


    override fun welcome() =
        println("This is currency converter application")


    override fun printBalance(balance: Balance) =
        println(
            "Your balance is:\n" + balance.balanceByCurrency.map {
                "${it.key.name} - ${NUMBER_FORMAT.format(it.value)}"
            }.joinToString(",\n")
        )

    override fun printRates() =
        println("Exchange rates:\n" +
                CurrencyExchangePairs.values()
                    .joinToString(",\n") { "${it.name} - ${NUMBER_FORMAT.format(it.rate)}" })

    override fun printErrorMessage(message: String?) =
        println("Input error\n" + (message ?: "Wrong input format") + "\n" + "Try again")

}