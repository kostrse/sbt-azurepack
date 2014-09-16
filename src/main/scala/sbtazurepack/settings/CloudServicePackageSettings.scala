package sbtazurepack.settings

import java.io.File

class CloudServicePackageSettings(val csdef: File) extends CsPackageSettings {

  override val toString = String.format("csdef %s", csdef.getAbsolutePath)
}
