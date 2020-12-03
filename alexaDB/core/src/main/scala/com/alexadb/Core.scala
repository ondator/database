package com.alexadb
package core

import WriteConcern._, ReadConcern._
import cats._

sealed trait Core[F[_]]{
    def upsert[T](key: Key, data: T, concern: WriteConcern): F[Unit]
    def delete(key: Key, concern: WriteConcern): F[Unit]
    def get[T](key: Key, concern: ReadConcern): F[T]
}


object Core{
    def dsl[F[_] : MonadError[*[_], WriteError]](): Core[F] = new Core[F] {
        def upsert[T](key: Key, data: T, concern: WriteConcern): F[Unit] = ???
        
        def delete(key: Key, concern: WriteConcern.WriteConcern): F[Unit] = ???
        
        def get[T](key: Key, concern: ReadConcern.ReadConcern): F[T] = ???
    }
}