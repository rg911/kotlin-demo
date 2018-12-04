package demo.repository

import demo.model.Event
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
@Component
interface EventRepository : MongoRepository<Event, String>