package inc

import scala.annotation.tailrec
import Schema.hiddenType

object ClassA {
  def a: String = "b"

  @hiddenType
  case class E(
    active: Set[Int], frozen: Set[Int]
  ) {
    extension (ids: Set[Int]) {

      def transitiveClosure(children: Int => IterableOnce[Int]): Set[Int] = {
        @tailrec
        def recurse(todo: Set[Int], done: Set[Int]): Set[Int] = {
          val add = todo.flatMap(id => children(id)).diff(done)
          if (add.isEmpty) done
          else recurse(add, done ++ add)
        }

        recurse(ids, ids)
      }

      def withRelated: Set[Int] = {
        transitiveClosure(_ => None)
      }
    }

    def -(e: Int): E = {
      this.copy(active - e, frozen - e)
    }

    def update: E = {
      val newActive = active.withRelated
      val newFrozen = frozen -- newActive
      copy(newActive, newFrozen)
    }
  }

}
