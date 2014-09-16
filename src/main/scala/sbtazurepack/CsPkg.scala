package sbtazurepack

import java.io.File

import sbtazurepack.settings._

object CsPkg {

  def noPackage(): CsPackageSettings = new CsPackageSettings { }

  def packageFromCsdef(csdef: File): CsPackageSettings = {

    require(csdef != null)

    new CloudServicePackageSettings(csdef)
  }

  def noRole(): CsRoleSettings = new CsRoleSettings { }

  def packageRole(): CsRoleSettings = {

    new CloudServiceRoleSettings()
  }
}
