package demo.extension

const val SPE = "\ufffe"

fun String.replaceMsgTokenSpecialChar(): String = this.replace(SPE, "|")
fun String.replaceMsgEscapeChar(): String = this.replace("\\|", SPE)
fun String.removeFirstAndLastPipe():String = this.replace(Regex("^\\||\\|$"), "")