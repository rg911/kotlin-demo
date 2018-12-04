package demo.service

import demo.service.implementation.TransformerService
import org.amshove.kluent.`should equal`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object TransformerServiceTest: Spek({

    describe("parse") {

        it("should return correct model instance") {
            val packet = "|9999|create|outcome|1234567890|03d8085a-e9f1-4b8e-a6f8-a6d94b6105ca|1ab7a384-070f-455a-9496-09dbe2334819|\\|Wolves\\||9/2|0|1|"
            val result = TransformerService().parse(packet)
            result `should equal`  "{\"header\":{\"msgId\":9999,\"operation\":\"create\",\"type\":\"event\",\"timestamp\":1234567890},\"body\":{\"outcomeId\":\"1ab7a384-070f-455a-9496-09dbe2334819\",\"marketId\":\"03d8085a-e9f1-4b8e-a6f8-a6d94b6105ca\",\"name\":\"|Wolves|\",\"price\":\"9/2\",\"displayed\":false,\"suspended\":true}}"
        }
    }
})