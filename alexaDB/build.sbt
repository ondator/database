/*=================================================*/
//Global
ThisBuild / scalaVersion := "2.13.3"
ThisBuild / name := "alexaDB"
ThisBuild / version := "0.0.1"
// ThisBuild / scalacOptions += "-Ypartial-unification"

lazy val commonSettings = Seq(
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    addCompilerPlugin("org.augustjune" %% "context-applied" % "0.1.4"),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.1" cross CrossVersion.full)
)

/*=================================================*/
//Projects
lazy val alexaDB = project.in(file("."))
                          .aggregate(
                              api,
                              core,
                              domain,
                              storage
                          )

lazy val api = project.in(file("api"))
                      .dependsOn(core, domain, storage)

lazy val core = project.in(file("core"))
                       .dependsOn(domain)
                       .settings(commonSettings)
                       .settings(libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.1")

lazy val domain = project.in(file("domain"))
lazy val storage = project.in(file("storage"))
