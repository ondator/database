/*=================================================*/
//Global
ThisBuild / scalaVersion := "2.13.3"
ThisBuild / name := "alexaDB"
ThisBuild / version := "0.0.1"


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
                       .settings(libraryDependencies += "org.typelevel.cats-core")
lazy val domain = project.in(file("domain"))
lazy val storage = project.in(file("storage"))
