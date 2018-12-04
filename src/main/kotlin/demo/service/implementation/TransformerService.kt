package demo.service.implementation


import demo.enums.HeaderType
import demo.extension.removeFirstAndLastPipe
import demo.extension.replaceMsgEscapeChar
import demo.extension.replaceMsgTokenSpecialChar
import demo.model.*
import demo.service.ITransformerService
import demo.util.gson
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Exception


@Service
class TransformerService : ITransformerService {
    val logger = LoggerFactory.getLogger("TransformerService")

    //Parse incoming packet
    override fun parse(input: String): String {
        try {

            var element = getElement(input)

            var header = this.parseHeader(element)

            return when(header.type) {
                HeaderType.OUTCOME.type -> parseOutcomeMessage(element, header)
                HeaderType.MARKET.type -> parseMarketMessage(element, header)
                HeaderType.EVENT.type -> parseEventMessage(element, header)
                else -> throw IllegalArgumentException("Unknown packet type '${header.type}'")
            }
        }
        catch (e: Exception) {
            logger.error("Parse packet error", e.message)
            throw e
        }
    }

    /*
    * Private Methods
    * */
    private fun getElement(input: String): List<String>{
        return (input.removeFirstAndLastPipe().replaceMsgEscapeChar()
                .split("|"))
                .map { it.replaceMsgTokenSpecialChar() }
    }


    private fun parseMarketMessage(element: List<String>, header: Header): String {
        var body = Market(
                element[5],
                element[4],
                element[6],
                element[7] == "1",
                element[8] == "1"
        )
        return gson.toJson(MessageViewModel (
                header = header,
                body = body
        ))
    }

    private fun parseOutcomeMessage(element: List<String>, header: Header): String {
        var body = Outcome(
                element[5],
                element[4],
                element[6],
                element[7],
                element[8] == "1",
                element[9] == "1"
        )
        return gson.toJson(MessageViewModel (
                header = header,
                body = body
        ))
    }


    private fun parseEventMessage(element: List<String>, header: Header): String {
        var body = Event(
                element[4],
                element[5],
                element[6],
                element[8].toLong(),
                element[7],
                element[9] == "1",
                element[10] == "1"
        )
        return gson.toJson(MessageViewModel (
                header = header,
                body = body
        ))
    }

    private fun parseHeader(element: List<String>): Header = Header(
            element[0].toInt(),
            element[1],
            element[2],
            element[3].toLong()
    )
}
