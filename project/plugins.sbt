resolvers += Resolver.url("Artifactory Ivy", url("https://zeotap.jfrog.io/artifactory/sbt-local/"))(Resolver.ivyStylePatterns)
credentials += Credentials(new File(sys.env("HOME") + "/.sbt/.credentials"))

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.1")
// ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
addSbtPlugin("io.github.fosstree" % "sbt-play-ebean" % "1.0.1-P27")
