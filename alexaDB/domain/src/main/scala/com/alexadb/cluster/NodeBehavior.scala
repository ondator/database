package com.alexadb
package cluster

object NodeState extends Enumeration{
    type NodeState = Value
    val Leader, Follower = Value
}

import NodeState._
trait NodeBehavior{
    def nodeState: NodeState
}