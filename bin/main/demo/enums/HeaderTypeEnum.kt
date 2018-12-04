package demo.enums

enum class HeaderType (var type: String) {
    OUTCOME("outcome"),
    MARKET("market"),
    EVENT("event")
}

enum class OperationType(var type: String) {
    CREATE("create"),
    UPDATE("update")
}