package demo.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.gson.Gson
import org.apache.logging.log4j.LogManager

val jsonMapper = ObjectMapper().apply {
    registerKotlinModule()
    disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    dateFormat = StdDateFormat()
}

var gson = Gson()
