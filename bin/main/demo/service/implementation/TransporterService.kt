package demo.service.implementation

import demo.dal.eventCollection
import demo.dal.marketCollection
import demo.dal.outcomeCollection
import demo.enums.HeaderType
import demo.enums.OperationType
import demo.kafka.ProducerHandler
import demo.model.Event
import demo.model.Market
import demo.model.Outcome
import demo.service.ITransporterService
import demo.util.gson
import demo.util.jsonMapper
import org.litote.kmongo.updateOne
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TransporterService: ITransporterService {
    val logger = LoggerFactory.getLogger("TransporterService")

    //Pass to kafka
    override fun transportToKafka (msg: String, handler: ProducerHandler) {
        logger.info("transporting message to kafka. [$msg]")
        handler.produce(2, msg)
    }

    //Process message from kafka to mongodb
   override fun transportToDb(msg: String) {
        logger.info("transporting message to mongodb. [$msg]")
        var header = jsonMapper.readTree(msg).get("header")
        var body = jsonMapper.writeValueAsString(jsonMapper.readTree(msg).get("body"))
        try {
            var type = header.get("type").asText()
            var operation = header.get("operation").asText()

            when (type) {
                HeaderType.OUTCOME.type -> {
                    var outcome = gson.fromJson(body, Outcome::class.java)

                    when (operation) {
                        OperationType.CREATE.type -> outcomeCollection.insertOne(outcome)
                        OperationType.UPDATE.type -> outcomeCollection.updateOne(outcome)
                    }
                }
                HeaderType.MARKET.type -> {
                    var market = gson.fromJson(body, Market::class.java)

                    when (operation) {
                        OperationType.CREATE.type -> marketCollection.insertOne(market)
                        OperationType.UPDATE.type -> marketCollection.updateOne(market)
                    }
                }

                HeaderType.EVENT.type -> {
                    var event = gson.fromJson(body, Event::class.java)

                    when (operation) {
                        OperationType.CREATE.type -> eventCollection.insertOne(event)
                        OperationType.UPDATE.type -> eventCollection.updateOne(event)
                    }
                }
                else -> logger.error("Invalid message found")
            }
        }
        catch(e: Exception) {
            logger.error("Kafka consumer process error", e)
        }
    }
}