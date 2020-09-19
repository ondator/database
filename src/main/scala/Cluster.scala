trait ClusterComponent{
    val cluster: Cluster

    trait Cluster{
        def get(key: String): Option[String]
        def set(key: String, value: String): Unit
        def delete(key: String): Unit
    }
}

trait ClusterComponentImpl extends ClusterComponent{
    class SingleInstance extends Cluster{

      override def get(key: String): Option[String] = ???

      override def set(key: String, value: String): Unit = ???

      override def delete(key: String): Unit = ???
    }

    class MasterSlave extends Cluster{
      override def get(key: String): Option[String] = ???

      override def set(key: String, value: String): Unit = ???

      override def delete(key: String): Unit = ???
    }
}