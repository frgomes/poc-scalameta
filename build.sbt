name := "poc-scalameta"

organization := "info.rgomes"

lazy val commonSettings =
  Seq(
    scalaVersion := "2.11.8",
    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    libraryDependencies ++=
      Seq(
        "net.jcip"      %  "jcip-annotations" % "1.0",
        "com.lihaoyi"   %% "utest"            % "0.4.4" % "test"),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

lazy val macroSettings = Seq(
  libraryDependencies += "org.scalameta" %% "scalameta" % "1.3.0",
  resolvers += 
    Resolver.url(
      "scalameta", url("http://dl.bintray.com/scalameta/maven"))(Resolver.ivyStylePatterns),
  addCompilerPlugin(
    "org.scalameta" % "paradise" % "3.0.0.132" cross CrossVersion.full),
  scalacOptions += "-Xplugin-require:macroparadise"
)


lazy val root = (project in file("."))
  .settings(commonSettings:_*)
  .aggregate(macros, tests)

lazy val macros = (project in file("macros"))
  .settings(commonSettings:_*)
  .settings(macroSettings:_*)

lazy val tests = (project in file("tests"))
  .settings(commonSettings:_*)
  .settings(macroSettings:_*)
  .dependsOn(macros)
