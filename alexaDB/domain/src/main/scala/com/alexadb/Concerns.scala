package com.alexadb

case class Key(key: String)

object WriteConcern extends Enumeration{
    type WriteConcern = Value
    val NoOne, Leader, Majority = Value
}

object ReadConcern extends Enumeration{
    type ReadConcern = Value
    val Any, Leader, Majority = Value
}