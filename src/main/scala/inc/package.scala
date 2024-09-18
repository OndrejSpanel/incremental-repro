import java.util.concurrent.atomic.AtomicInteger

package object inc {
  val lastId = new AtomicInteger(0)

  def newId: Int = {
    lastId.addAndGet(1)
  }
}
