package demo

import demo.kafka.ProcessorHandler
import demo.service.IOService
import demo.service.implementation.TransformerService
import demo.service.implementation.TransporterService
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.net.Socket
import java.util.concurrent.Executors


@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {

    SpringApplication.run(DemoApplication::class.java, *args)

    Thread {
        var socket = Socket("localhost", 8282)
        var service = IOService(socket, TransformerService(), TransporterService())
        service.run()
    }.start()

    val executor = Executors.newFixedThreadPool(4)

    for (i in 0..4) {
        println("this is thread: $i" )
        val consumer = ProcessorHandler()
        executor.submit(consumer)
       // executor.execute(consumer)
    }


    executor.shutdown()
    while (!executor.isTerminated) {
    }
}
