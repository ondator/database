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
      import java.io._
      
      final val dataFolder = "./storage/"
      final val ssTableSize: Int = 3000
      private var memtable: SortedMap[String, String] = SortedMap[String, String]()

      override def get(key: String): Option[String] = {
        memtable
          .get(key)
          .orElse(getPersisted(key))
      }

      private def getPersisted(key: String): Option[String] = {

        val filename = dataFolder
        val file = new File(filename)
        val files = file.listFiles().sortBy(_.getName())(Ordering[String].reverse)
        files.map(x=>searchInFile(x, key)).find(_.isDefined).flatMap(x=>x)
      }

      private def searchInFile(file: File, key: String): Option[String] = {
        val bw = new BufferedReader(new FileReader(file))
        var result: Option[String] = None
        var line: String = null
        do{
          line = bw.readLine()
          result = Option(line).map(x => x.split(",")).map(x=>(x.head, x.reverse.head)).flatMap(x=> if(x._1 == key) Some(x._2) else None)
        }while(line != null && !result.isDefined)        
          bw.close()
          result
      }

      override def set(key: String, value: String): Unit = {
        memtable += (key -> value)
        if(memtable.size >= ssTableSize) {
           val ssTable = new SSTable(memtable, dataFolder)
           ssTable.persist()
        }
      }

      override def delete(key: String): Unit = ???
    }

    class SSTable(val memtable: SortedMap[String, String], final val dataFolder: String){
      def persist(): Unit = {
        import java.io._

        val payload = memtable.foldLeft("")((acc, p) => acc ++ s"${p._1},${p._2}\n")

        val filename = dataFolder + java.time.LocalDateTime.now().toString()
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