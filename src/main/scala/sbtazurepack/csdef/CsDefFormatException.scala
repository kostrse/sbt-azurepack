package sbtazurepack.csdef

class CsDefFormatException(message: String, cause: Throwable) extends RuntimeException(message, cause) {

  def this() = this(null, null)
  def this(message: String) = this(message, null)
}
