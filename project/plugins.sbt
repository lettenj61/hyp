resolvers += Classpaths.sbtPluginReleases
resolvers += "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.28")
// addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.14.0")
// addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.9.0")