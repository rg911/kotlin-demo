package demo.service

import com.mongodb.client.model.Filters
import demo.dal.eventCollection
import demo.dal.marketCollection
import demo.dal.outcomeCollection
import demo.model.Event
import org.springframework.stereotype.Service

@Service
class RestService {
    fun getEvents(): List<Event> {

        var event = eventCollection.find().toList()

        event.forEach { e ->
            var market = marketCollection.find(Filters.eq("eventId", e.eventId)).toMutableList()
            market.forEach {
                it.outcome = outcomeCollection.find(Filters.eq("marketId", it.marketId)).toMutableList()
            }

            e.market = market
        }
        return event
    }
}