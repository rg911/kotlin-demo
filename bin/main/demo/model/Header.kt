package demo.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Header(
        val msgId: Int,
        val operation: String,
        val type: String,
        val timestamp: Long)