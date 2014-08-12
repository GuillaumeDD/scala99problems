import sbt._
import Keys._

object ScalaProblemsBuild extends Build {
    lazy val root = Project(id = "scala99problems",
                            base = file(".")) dependsOn(scalaGraph)

    lazy val scalaGraph = Project(id = "scalaGraph",
                           base = file("ScalaGraph"))
}
