package demo.kafka

import demo.config.Config
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component
import java.util.*

@Component

class ProducerHandler {

    private var brokers = Config().kafkaHost

    private val producer = createProducer(brokers)

    fun createProducer(brokers: String): Producer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = brokers
        props["key.serializer"] = StringSerializer::class.java
        props["value.serializer"] = StringSerializer::class.java
        return KafkaProducer<String, String>(props)
    }

    fun produce(ratePerSecond: Int, message: String) {
        val waitTimeBetweenIterationsMs = 1000L / ratePerSecond
        val futureResult = producer.send(ProducerRecord(Config().kafkaTopic, message))
        Thread.sleep(waitTimeBetweenIterationsMs)
        // wait for the write acknowledgment
        futureResult.get()
    }
}