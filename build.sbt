name := "cqrs-complete"

version := "1.1"

scalaVersion := "2.11.7"

val akkaVersion = "2.4.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)

libraryDependencies ++= Seq(
  "com.rbmhtechnology" %% "eventuate" % "0.3" withSources(),
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-query-experimental" % "2.4.0",
  "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion % Test,
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "org.iq80.leveldb"  % "leveldb" % "0.7",
  "org.fusesource.leveldbjni" %  "leveldbjni-all"   % "1.8",
  "org.scalatest" %% "scalatest" % "2.2.4" % Test
)


resolvers += "Eventuate Releases" at "https://dl.bintray.com/rbmhtechnology/maven"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies += "javax.inject" % "javax.inject" % "1"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1204-jdbc42"

fork := true
    
