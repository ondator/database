package com.alexadb

sealed trait Api[F[_]]{
    def upsert[T](key: String, data: T): F[Unit]
    def delete(key: String): F[Unit]
    def get[T](key: String): F[T]
} 