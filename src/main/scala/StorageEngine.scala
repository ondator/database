package storage
import scala.collection.mutable

trait StorageEngineComponent{
    val engine : StorageEngine

    trait StorageEngine {
        def get(key: String): Option[String]
        def set(key: String, value: String): Unit
        def delete(key: String): Unit
    }
}


trait LSMStorageEngineComponent extends StorageEngineComponent{    
  import scala.collection.mutable.SortedMap            

    class LSM extends StorageEngine{
      

      final val ssTableSize: Int = 3000
      private var memtable: SortedMap[String, String] = SortedMap[String, String]()

      override def get(key: String): Option[String] = ???

      override def set(key: String, value: String): Unit = {
        memtable += (key -> value)
        if(memtable.size >= ssTableSize) {
           val ssTable = new SSTable(memtable)
           ssTable.persist()
        }
      }

      override def delete(key: String): Unit = ???
    }

    class SSTable(val memtable: SortedMap[String, String]){
      def persist(): Unit = {
        import java.io._

        val payload = memtable.foldLeft("")((acc, p) => acc ++ s"${p._1},${p._2}\n")

        val filename = "./storage/"+java.time.LocalDateTime.now().toString()
        val file = new File(filename)
        if (!file.exists()) file.createNewFile()
        val bw = new BufferedWriter(new FileWriter(file, true))        
        bw.write(payload)
        bw.close()
      }
    }
}
  
trait BTreeStorageEngineComponent extends StorageEngineComponent{    
    class BTree extends StorageEngine{

      override def get(key: String): Option[String] = ???

      override def set(key: String, value: String): Unit = ???

      override def delete(key: String): Unit = ???
    }
  }