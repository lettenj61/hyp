import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv

lazy val scalaV   = "2.12.8"
lazy val libV     = "0.1.0-SNAPSHOT"
lazy val vueV     = "2.5.22"

crossScalaVersions := Seq("2.10.6", "2.11.12", "2.12.8")

lazy val commonSettings = Seq(
  scalaVersion  := scalaV,
  version       := libV,
  organization  := "com.github.thisisvesca",

  scalacOptions in (Compile, compile) ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    // "-Xfatal-warnings",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Ywarn-unused-import",
    "-Ywarn-unused",
    "-P:scalajs:sjsDefinedByDefault"
  ),

  scalaJSLinkerConfig ~= { lc =>
    lc.withSourceMap(false)
      .withModuleKind(ModuleKind.CommonJSModule)
  },

  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.6",
    "org.scalatest" %%% "scalatest" % "3.0.5" % "test"
  )
)

lazy val domSettings = Seq(
  jsEnv in Test := new JSDOMNodeJSEnv,
  jsDependencies ++= Seq(
    // use minified, compiler+runtime build for tests
    "org.webjars.npm" % "vue" % vueV / "dist/vue.min.js" % "test"
  )
)

lazy val vesca = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings, domSettings)
  .settings(
    name := "vesca",
    description := "Elm like toolkit to build UI on top of Vue.js"
  )
