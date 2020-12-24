package com.alexadb.cluster

sealed trait WriteError

case class NotLeaderWrite() extends WriteError{}
case class EncodingError(error: com.alexadb.codec.EncodingError) extends WriteError{}