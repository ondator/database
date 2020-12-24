package com.alexadb
package cluster
import WriteConcern._, ReadConcern._

trait Node[F[_]]{
    def upsert[T](key: Key, data: T, concern: WriteConcern): F[Unit]
    def delete(key: Key, concern: WriteConcern): F[Unit]
    def get[T](key: Key, concern: ReadConcern): F[T]
}
