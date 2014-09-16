import sbtazurepack.CsPkg._

lazy val consoleapp = project

lazy val sprayapp1 = project

lazy val sprayapp2 = project

cloudServicePackage := packageFromCsdef(file("ServiceDefinition.csdef"))
