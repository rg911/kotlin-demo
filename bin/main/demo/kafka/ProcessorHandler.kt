package demo.kafka

import demo.config.Config
import demo.service.implementation.TransporterService
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

class ProcessorHandler : Runnable{

    private var brokers = Config().kafkaHost

    private val consumer = createConsumer(brokers)

    private fun createConsumer(brokers: String): Consumer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = brokers
        props["group.id"] = "message-processor"
        props["key.deserializer"] = StringDeserializer::class.java
        props["value.deserializer"] = StringDeserializer::class.java
        return KafkaConsumer<String, String>(props)
    }

    override fun run() {
        consumer.subscribe(listOf(Config().kafkaTopic))
        while (true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            records.forEach {
                TransporterService().transportToDb(it.value())
            }
        }
    }
}