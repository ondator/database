import scala.collection.mutable
import java.io._
import org.scalatest.flatspec.AnyFlatSpec
import storage._

class LsmSpec extends AnyFlatSpec {

  "An empty lsm storage" should "return None" in {
    val lsm = new LSMStorageEngineComponent {val engine: StorageEngine = new LSM}.engine
    assert(lsm.get("2") == None)
  }

  "Storage" should "return None if doesn't contains element" in {
    val lsm = new LSMStorageEngineComponent {val engine: StorageEngine = new LSM}.engine
    lsm.set("1", "hello")
    assert(lsm.get("2") == None)
    
    val file = new File("./storage/")    
    file.delete();
  }

  "Storage" should "return value if contains element" in {
    val lsm = new LSMStorageEngineComponent {val engine: StorageEngine = new LSM}.engine
    lsm.set("1", "hello 1")
    lsm.set("2", "hello 2")
    lsm.set("3", "hello 3")
    var res = lsm.get("2")
    assert(res == Some("hello 2"))
    
    val file = new File("./storage/")    
    file.delete();
  }  
}