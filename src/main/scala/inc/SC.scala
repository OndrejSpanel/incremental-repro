package inc

sealed trait SC

object SC {
  def derivedClasses = Schema.listDerivedClasses[SC]

  object A extends SC
  object B extends SC
}
