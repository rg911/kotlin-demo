package demo.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.annotation.Id

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Outcome(
    @BsonId
    val outcomeId: String,
    val marketId: String,
    val name: String,
    var price: String,
    val displayed: Boolean,
    var suspended: Boolean
)