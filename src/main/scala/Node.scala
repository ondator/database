trait NodeComponent {
  val node: Node

  trait Node {
    def get(key: String): Option[String]
    def set(key: String, value: String): Unit
    def delete(key: String): Unit
  }
}

trait NodeComponentImpl extends NodeComponent{
    this: storage.StorageEngineComponent =>
  
  class NodeImpl extends Node {
    def get(key: String): Option[String] = ???
    def set(key: String, value: String): Unit = ???
    def delete(key: String): Unit = ???
  }
}
