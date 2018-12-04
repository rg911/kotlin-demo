package demo.service

import demo.model.MessageViewModel


interface ITransformerService {
    fun parse(input: String): String

}