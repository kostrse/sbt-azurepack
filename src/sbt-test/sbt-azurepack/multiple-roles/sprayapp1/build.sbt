name  := "sprayapp1"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= {
  val akkaV = "2.3.0"
  val sprayV = "1.3.1"
  Seq(
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV
  )
}
