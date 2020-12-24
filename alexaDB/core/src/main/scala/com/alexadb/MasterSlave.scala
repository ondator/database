package com.alexadb
package core

import WriteConcern._, ReadConcern._
import cats._
import cluster._
import com.alexadb.Engine
import com.alexadb.codec.Encoder
import cluster.NodeState._


object MasterSlave{
    def dsl[F[_] : MonadError[*[_], WriteError]](
        behavior: NodeBehavior, 
        engine: Engine[F],
        keyCodec: Encoder[Key],
        payloadCodec: Encoder[Any],
        ): Node[F] = new Node[F] {
        def upsert[T](key: Key, data: T, concern: WriteConcern): F[Unit] = {
            behavior.nodeState match{
                case Follower => F.raiseError(NotLeaderWrite())
                case _ => {
                    val result = for {
                        k <- keyCodec.encode(key);
                        d <- payloadCodec.encode(data)
                    } yield engine.upsert(k, d)

                    result match {
                        case Left(e) => F.raiseError(EncodingError(e))
                        case Right(value) => value
                    }
                }
            }
        }
        
        def delete(key: Key, concern: WriteConcern.WriteConcern): F[Unit] = ???
        
        def get[T](key: Key, concern: ReadConcern.ReadConcern): F[T] = ???
    }
}