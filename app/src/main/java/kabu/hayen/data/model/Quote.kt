package kabu.hayen.data.model

data class Quote (
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: Double,
    val changeFromPreviousClose: Double,
    val percentChangeFromPreviousClose: Double,
    val marketName: String,
    val recommendation: Recommendation,
    val chart: Chart
)