import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv

// lazy val vueJSVersion         = "2.6.10"
lazy val preactVersion        = "10.4.4"

lazy val commonSettings = Seq(
  crossScalaVersions  := Seq("2.12.11", "2.13.2"),
  scalaVersion        := crossScalaVersions.value.last,
  organization        := "com.github.letitscale",

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
    "org.scalatest" %%% "scalatest" % "3.1.2" % Test
  )
)

lazy val domSettings = Seq(
  // jsEnv in Test := new JSDOMNodeJSEnv(),
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "1.0.0"
  )
)

lazy val virtualdom = crossProject(JSPlatform, JVMPlatform)
  .settings(commonSettings)
  .settings(
    name := "hyp-virtualdom",
    skip in publish := true
  )

lazy val hyp = project
  .enablePlugins(ScalaJSPlugin, JSDependenciesPlugin)
  .dependsOn(virtualdom.js)
  .settings(commonSettings, domSettings)
  .settings(
    name := "hyp",
    description := "Make HTML5 UI with The Elm Architecture on Scala.js",
    // jsDependencies ++= Seq(
    //   "org.webjars.npm" % "preact" % preactVersion / s"$preactVersion/dist/preact.umd.js" % "test",
    //   "org.webjars.npm" % "preact" % preactVersion / s"$preactVersion/hooks/dist/hooks.umd.js" % "test"
    // )
  )

lazy val examples = project
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(hyp)
  .settings(commonSettings, domSettings)
  .settings(
    name := "hyp-examples",
    skip in publish := true,
    scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.NoModule)),
    scalaJSUseMainModuleInitializer := true,
    mainClass in (Compile, fastOptJS) := Some("hyp.example.Example")
  )

lazy val root = project.in(file("."))
  .settings(
    publish := {},
    publishLocal := {},
  )
  .aggregate(
    virtualdom.js,
    virtualdom.jvm,
    hyp
  )
