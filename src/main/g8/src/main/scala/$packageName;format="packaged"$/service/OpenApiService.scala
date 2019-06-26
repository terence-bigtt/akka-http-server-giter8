package $packageName$.service

import java.io.FileInputStream
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json._
import scala.io.Source


trait OpenApiService extends SprayJsonSupport {
  def openApiRoute: Route = pathPrefix("openapi") {
    pathEnd {
      get {
        complete {
          val jsonString: String = Source.fromResource("openapi.json").getLines().mkString("")
          jsonString.parseJson
        }
      }
    }
  }


}
