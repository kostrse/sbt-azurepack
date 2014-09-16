package sbtazurepack.tasks

import java.io.File

import sbt.Logger
import sbtazurepack.settings._

object GenerateCloudServicePackage {

  def apply(packageSettings: CloudServicePackageSettings, roleDefinitions: Seq[CloudServiceRoleDefinition])
           (implicit logger: Logger): File = {

    require(packageSettings != null)
    require(roleDefinitions != null)
    require(logger != null)

    logger.info("cspkg started")

    new File(".")
  }
}
