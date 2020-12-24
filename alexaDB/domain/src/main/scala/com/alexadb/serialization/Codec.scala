package com.alexadb
package codec

case class DecodingError(message: String)
case class EncodingError(message: String)

trait Decoder[F]{
    def decode(data: Array[Byte]): Either[DecodingError, F]
}

trait Encoder[F]{
    def encode(data: F): Either[EncodingError, Array[Byte]]
}