package demo.service

import com.fasterxml.jackson.databind.JsonNode
import demo.kafka.ProducerHandler

interface ITransporterService {
    fun transportToDb(msg: String)

    fun transportToKafka (msg: String, handler: ProducerHandler)
}