package sbtazurepack

import java.io.File
import sbt._
import sbtazurepack.settings._

object AzurePackPlugin extends AutoPlugin {

  import autoImport._

  object autoImport {

    val cloudServiceRole = SettingKey[CsRoleSettings]("cloudServiceRole")
    val cloudServicePackage = SettingKey[CsPackageSettings]("cloudServicePackage")
  }

  val cspkgRoleTask = TaskKey[Option[CloudServiceRoleDefinition]]("cspkgRole")
  val cspkgTask = TaskKey[Option[File]]("cspkg")

  override def trigger: PluginTrigger = allRequirements

  override def globalSettings: Seq[Def.Setting[_]] = Seq(
    cloudServiceRole := CsPkg.noRole,
    cloudServicePackage := CsPkg.noPackage
  )

  override def projectSettings: Seq[Def.Setting[_]] = Seq(

    cspkgRoleTask := {

      implicit val logger = Keys.streams.value.log

      cloudServiceRole.value match {
        case roleSettings: CloudServiceRoleSettings => Some(tasks.GenerateCloudServiceRole(roleSettings))
        case _ => None
      }
    },

    cspkgTask := {

      implicit val logger = Keys.streams.value.log

      cloudServicePackage.value match {
        case packageSettings: CloudServicePackageSettings => {
          val roleDefinitions = cspkgRoleTask.all(ScopeFilter(inAggregates(ThisProject))).value.flatten
          Some(tasks.GenerateCloudServicePackage(packageSettings, roleDefinitions))
        }
        case _ => None
      }
    }
  )
}
