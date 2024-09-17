scalaVersion := "3.5.0"

Global / excludeLintKeys += ThisBuild / githubTokenSource // prevent warning in SBT

ThisBuild / githubTokenSource := TokenSource.GitConfig("github.token") || TokenSource.Environment("BUILD_TOKEN") || TokenSource.Environment("GITHUB_TOKEN")

ThisBuild / resolvers += Resolver.githubPackages("OpenGrabeso", "_")

libraryDependencies += "org.opengrabeso" %% "light-surface" % "0.5.5"
