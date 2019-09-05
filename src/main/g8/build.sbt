
name := "$name;format="lower"$"
scalaVersion := "2.12.8"

val akkaVersion = "$akkaVersion$"
val akkaHttpVersion = "$akkaHttpVersion$"
val sprayVersion = "$sprayVersion$"
val scalaTestVersion = "$scalaTestVersion$"

mainClass in(Compile, run) := Some("$packageName$.Main")
mainClass in(Compile, packageBin) := Some("$packageName$.Main")
mainClass in(Compile, packageBin) := Some("$packageName$.Main")

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
  case PathList("META-INF", "services", "org.apache.hadoop.fs.FileSystem") => MergeStrategy.filterDistinctLines
  case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.first
  case n if n.startsWith("reference.conf") => MergeStrategy.concat
  case n if n.endsWith(".conf") => MergeStrategy.concat
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}


libraryDependencies := Seq(
  "io.spray" %%  "spray-json" % sprayVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" %  akkaVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",

  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.5" % Test,
  "be.cetic.inah.commons" % "commons" % "0.0.1" from "https://github.com/cetic/inah-commons/raw/master/releases/inah-commons-0.0.1.jar"
)


packageName in Docker := packageName.value

version in Docker := version.value

dockerExposedPorts := List(8000)

//dockerLabels := Map("maintainer" -> "NoReply@steveking.site")

dockerBaseImage := "openjdk"

dockerRepository := Some( "$dockerRepo" )

defaultLinuxInstallLocation in Docker := "/usr/local"

daemonUser in Docker := "daemon"

mappings in Universal ++= directory( baseDirectory.value / "src" / "main" / "resources" )
