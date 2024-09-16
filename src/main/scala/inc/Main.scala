package inc

import ClassA.*

object Main {

  def main(args: Array[String]): Unit = {
    val s = state.State(E(Set.empty, Set.empty))
    val ns = s.simulate(0.1)
    println("Hello world!x")
  }
}
