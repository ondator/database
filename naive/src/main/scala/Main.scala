import scala.collection.mutable
object Main extends App {
  implicit val walName: String = "wal"
  implicit val index: mutable.Map[String, Long] = mutable.Map()

  Naive.set("2", "hello")
  println(Naive.get("2"))
}

object Naive {
  import java.io._
  def get[T](key: String)(implicit walName: String, index: mutable.Map[String, Long]): Option[String] = {
    val file = new File(walName)
    val offset = index get key
    offset.flatMap(o => {
      val bw = new BufferedReader(new FileReader(file))
      bw.skip(o)
      var line = bw.readLine()
      bw.close()
      Option(line).map(x => x.split(",").last)
    })
  }

  def set(key: String, value: String)(implicit walName: String, index: mutable.Map[String, Long]) {
    val file = new File(walName)
    if (!file.exists()) file.createNewFile()
    val offset = file.length()
    val bw = new BufferedWriter(new FileWriter(file, true))
    bw.write(s"$key,$value\n")
    bw.close()
    index.addOne((key, offset))
  }
}
