package demo.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.bson.codecs.pojo.annotations.BsonId

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Market(
        @BsonId
        val marketId: String,
        val eventId: String,
        val name: String,
        val displayed: Boolean,
        var suspended: Boolean,
        var outcome: MutableList<Outcome> = mutableListOf()
)