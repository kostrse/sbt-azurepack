package sbtazurepack.tasks

import sbt.Logger
import sbtazurepack.settings._

object GenerateCloudServiceRole {

  def apply(roleSettings: CloudServiceRoleSettings)(implicit logger: Logger): CloudServiceRoleDefinition = {

    require(roleSettings != null)
    require(logger != null)

    logger.info("cspkgRole started")

    new CloudServiceRoleDefinition()
  }
}
