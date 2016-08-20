name := "poc-scalameta"

organization := "info.rgomes"

lazy val commonSettings =
  Seq(
    scalaVersion := "2.11.8",
    scalacOptions += "-Xplugin-require:macroparadise",
    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    libraryDependencies ++=
      Seq(
        "net.jcip"      %  "jcip-annotations" % "1.0",
        "com.lihaoyi"   %% "utest"            % "0.4.3" % "test"),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    addCompilerPlugin("org.scalamacros" % "paradise" % "3.0.0-M3" cross CrossVersion.full)
  )

lazy val root = (project in file("."))
  .settings(commonSettings:_*)
  .aggregate(macros, tests)

lazy val macros = (project in file("macros"))
  .settings(commonSettings:_*)
  .settings(
    libraryDependencies += "org.scalameta" %% "scalameta" % "1.0.0")

lazy val tests = (project in file("tests"))
  .settings(commonSettings:_*)
  .dependsOn(macros)
