package sbtazurepack

import scala.xml.{Text, Elem}

package object csdef {

  implicit class CsDefElementExtensions(element: Elem) {

    def optionalAttribute(name: String): Option[String] = element.attribute(name) match {
      case Some(Text(value)) => Some(value)
      case _ => None
    }

    def requiredAttribute(name: String): String = optionalAttribute(name) match {
      case Some(value) => value
      case None => throw new CsDefFormatException(s"Element '${element.label}' doesn't have required attribute '$name'.")
    }

    def childElements: Seq[Elem] = element.child collect {
      case childElement: Elem => childElement
    }

    def childElements(name: String): Seq[Elem] = childElements filter(_.label == name)
  }
}
