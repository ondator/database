package com.alexadb
package engine

sealed trait Engine[F[_]]{
    def upsert(key: Array[Byte],data: Array[Byte]): F[Unit]
    def delete(key: Array[Byte]): F[Unit]
    def get(key: Array[Byte]): F[Array[Byte]]
}

