package demo.extension

import org.amshove.kluent.`should equal`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object StringExtensionTest: Spek({
    val SPE = "\ufffe"

    describe("replaceMsgTokenSpecialChar") {

        it("should replace escape char") {
            val input = "|\\|Wolves\\||1|"
            val result = input.replaceMsgEscapeChar()
            result `should equal`  "|${SPE}Wolves${SPE}|1|"
        }
    }

    describe("replaceMsgTokenSpecialChar") {

        it("should restore escape char") {
            val input = "|${SPE}Wolves${SPE}|1|"
            val result = input.replaceMsgTokenSpecialChar()
            result `should equal`  "|\\|Wolves\\||1|"
        }
    }

    describe("removeFirstAndLastPipe") {

        it("should remove first and last pipe") {
            val input = "|1|"
            val result = input.removeFirstAndLastPipe()
            result `should equal`  "1"
        }
    }
})