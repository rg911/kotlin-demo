package demo.service

import demo.kafka.ProducerHandler
import demo.service.implementation.TransformerService
import demo.service.implementation.TransporterService
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class IOService(private val socket: Socket,
                private val transformer: TransformerService,
                private val transporter: TransporterService
                ): Runnable {
    override fun run() {
        val logger = LoggerFactory.getLogger("IOService")
        var handler = ProducerHandler()

        var bufferReader =  BufferedReader(InputStreamReader(socket.getInputStream()))
        var str = "Init"
        logger.info("Start consuming packets from localhost:8282")
        while (str != null) {
            str = bufferReader.readLine()
            logger.info("Incoming packet: $str")
            transporter.transportToKafka(transformer.parse(str), handler)
        }
    }

}