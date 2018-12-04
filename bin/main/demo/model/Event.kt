package demo.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection="messageEvent")
data class Event(
        @Id
        @BsonId
        val eventId: String,
        val category: String,
        var subCategory: String,
        val startTime: Long,
        val name: String,
        val displayed: Boolean,
        var suspended: Boolean,
        var market: MutableList<Market> = mutableListOf()
)