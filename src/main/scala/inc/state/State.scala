package inc
package state

case class State(e: ClassA.E) {
  def simulate(dt: Double): State = {
    copy(e = e.update)
  }
}
