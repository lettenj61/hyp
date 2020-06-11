import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv

lazy val thisLibraryVersion   = "0.1.0-SNAPSHOT"
lazy val vueJSVersion         = "2.6.10"

lazy val commonSettings = Seq(
  crossScalaVersions  := Seq("2.12.11", "2.13.2"),
  scalaVersion        := crossScalaVersions.value.last,
  organization        := "com.github.letitscale",
  version             := thisLibraryVersion,

  scalacOptions in (Compile, compile) ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked"
  ),

  scalaJSLinkerConfig ~= { lc =>
    lc.withSourceMap(false)
      .withModuleKind(ModuleKind.CommonJSModule)
  },

  libraryDependencies ++= Seq(
    "io.monix" %%% "minitest" % "2.8.2" % "test"
  )
)

lazy val domSettings = Seq(
  jsEnv in Test := new JSDOMNodeJSEnv,
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "1.0.0"
  )
)

lazy val virtualdom = crossProject(JSPlatform, JVMPlatform)
  .settings(commonSettings)
  .settings(
    name := "vesca-virtualdom",
    skip in publish := true
  )

lazy val vesca = project
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(virtualdom.js)
  .settings(commonSettings, domSettings)
  .settings(
    name := "vesca",
    description := "Elm like toolkit to build UI on top of Vue.js"
  )


lazy val root = project.in(file("."))
  .settings(
    publish := {},
    publishLocal := {},
  )
  .aggregate(
    virtualdom.js,
    virtualdom.jvm,
    vesca
  )
