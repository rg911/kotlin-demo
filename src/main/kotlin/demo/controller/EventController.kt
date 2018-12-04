package demo.controller

import demo.service.RestService
import org.springframework.web.bind.annotation.*

@RestController
class EventController (var restService: RestService){

    @CrossOrigin("http://localhost:3002")
    @GetMapping("/events")
    fun getMarkets() =
            restService.getEvents()

    @CrossOrigin("http://localhost:3002")
    @GetMapping("/events/{id}")
    fun greeting(@PathVariable("id") id: String)  = restService.getEvents().find { it.eventId == id }

}