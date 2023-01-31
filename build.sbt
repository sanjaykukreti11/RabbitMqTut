ThisBuild / scalaVersion := "2.12.2"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava , PlayEbean)
  .settings(
    name := """AssignmentRabbitMQ""",
    libraryDependencies ++= Seq(
      guice,
      "org.postgresql" % "postgresql" % "9.4.1212",
      "com.rabbitmq" % "amqp-client" % "5.16.0",

      // https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
       "org.hibernate" % "hibernate-core" % "5.2.8.Final"

    )
  )
