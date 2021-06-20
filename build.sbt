name := "ZIOOptics101"

version := "0.1"

scalaVersion := "2.13.6"

scalacOptions := Seq(
  "-feature",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-deprecation",
  "-Xfatal-warnings"
)


val zioOpticsVersion = "0.1.0"
val zioVersion = "1.0.9"
//ZIO
libraryDependencies += "dev.zio" %% "zio" % zioVersion
libraryDependencies += "dev.zio" %% "zio-optics" % zioOpticsVersion

//Test
libraryDependencies ++= Seq(
  "dev.zio" %% "zio-test"          % zioVersion % "test",
  "dev.zio" %% "zio-test-sbt"      % zioVersion % "test",
  "dev.zio" %% "zio-test-magnolia" % zioVersion % "test" // optional
)
testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")