package demo.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MessageViewModel<T>(
        val header: Header,
        val body: T
)