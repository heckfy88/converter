package converter

import converter.observer.RateObserver
import converter.service.ConsoleServiceImpl
import converter.service.ConverterServiceImpl
import converter.util.Command

fun main() {
    val consoleService = ConsoleServiceImpl()
    consoleService.welcome()

    val user = consoleService.login()
    val converterService = ConverterServiceImpl(user)
    val rateObserver = RateObserver()
    converterService.addObserver(rateObserver)

    while (true) {
        consoleService.outputCommands()
        try {
            when (consoleService.inputCommand()) {
                Command.GET_BALANCE -> consoleService.printBalance(user.balance)
                Command.GET_RATES -> consoleService.printRates()
                Command.EXCHANGE -> doExchange(consoleService, converterService)
            }
        } catch (e: Exception) {
            consoleService.printErrorMessage(e.message)
            consoleService.scanner.next()
        }
    }
}

fun doExchange(consoleService: ConsoleServiceImpl, converterService: ConverterServiceImpl) {
    val currency = consoleService.inputCurrencyPair()
    val exchangeType = consoleService.inputType()
    val amount = consoleService.inputAmount()
    converterService.exchange(currency, amount, exchangeType)
    consoleService.printBalance(converterService.user.balance)
    consoleService.printRates()
}