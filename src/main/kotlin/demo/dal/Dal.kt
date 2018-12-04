package demo.dal

import demo.model.Event
import demo.model.Market
import demo.model.Outcome
import org.litote.kmongo.*

val mongoClient = KMongo.createClient()
val database = mongoClient.getDatabase("techTest")
val eventCollection = database.getCollection<Event>()
val marketCollection = database.getCollection<Market>()
val outcomeCollection = database.getCollection<Outcome>()