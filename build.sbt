name := "MonthlyWageCalculator"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
	"com.github.tototoshi" %% "scala-csv" % "1.2.2" ,
	"joda-time" % "joda-time" % "2.2",
	"org.slf4j" % "slf4j-api" % "1.7.12",
	"ch.qos.logback" % "logback-classic" % "1.1.3",
	"junit" % "junit" % "4.12" exclude("org.hamcrest", "hamcrest-core"),
	"org.scalatest" % "scalatest_2.11" % "2.2.2",
	"org.mockito" % "mockito-all" % "1.10.19"
)