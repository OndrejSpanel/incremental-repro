package inc

import scala.annotation.tailrec
import Schema.hiddenType

object ClassA {
  def a: String = "b"

  @hiddenType
  case class E(
    active: Set[Int], frozen: Set[Int]
  ) {
    val sa = SC.A
    val sd = SC.derivedClasses

    extension (ids: Set[Int]) {

      def transitiveClosure(children: Int => IterableOnce[Int]): Set[Int] = {
        @tailrec
        def recurse(todo: Set[Int], done: Set[Int]): Set[Int] = {
          val toAdd = todo.flatMap(id => children(id)).diff(done)
          if (toAdd.isEmpty) done
          else recurse(toAdd, done ++ toAdd)
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
