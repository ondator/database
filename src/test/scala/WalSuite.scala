import scala.collection.mutable
import java.io._
import org.scalatest.flatspec.AnyFlatSpec

class WalSpec extends AnyFlatSpec {

  "An empty Wal" should "return None" in {
    implicit val walName: String = "wal"+java.time.LocalDateTime.now().getNano()
    implicit val index: mutable.Map[String, Long] = mutable.Map()

    val wal = new WalComponentImpl {val wal: Wal = new TextFileWal()}.wal
    assert(wal.get("2") == None)
  }

  "Wal" should "return None if doesn't contains element" in {
    implicit val walName: String = "wal"+java.time.LocalDateTime.now().getNano()
    implicit val index: mutable.Map[String, Long] = mutable.Map()

    val wal = new WalComponentImpl {val wal: Wal = new TextFileWal()}.wal
    wal.set("1", "hello")
    assert(wal.get("2") == None)
    
    val file = new File(walName)    
    file.delete();
  }

  "Wal" should "return value if contains element" in {
    implicit val walName: String = "wal"+java.time.LocalDateTime.now().getNano()
    implicit val index: mutable.Map[String, Long] = mutable.Map()

    val wal = new WalComponentImpl {val wal: Wal = new TextFileWal()}.wal
    wal.set("1", "hello 1")
    wal.set("2", "hello 2")
    wal.set("3", "hello 3")
    var res = wal.get("2")
    assert(res == Some("hello 2"))
    
    val file = new File(walName)    
    file.delete();
  }  
}