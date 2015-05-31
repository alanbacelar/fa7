name := """app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
	javaJdbc,
	"commons-beanutils" % "commons-beanutils" % "1.9.2",
	"org.hibernate" % "hibernate-c3p0" % "4.3.10.Final",
	"org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
	"org.hibernate" % "hibernate-ehcache" % "4.3.10.Final",
	"mysql" % "mysql-connector-java" % "5.1.18"
)