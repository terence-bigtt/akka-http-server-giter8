package $packageName$.service

import akka.actor.{ActorRef, ActorSystem}
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import $packageName$.util.CorsSupport
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

class $serviceName$()(implicit val system: ActorSystem) extends OpenApiService with CorsSupport {
  implicit val ec: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val timeout: Timeout = Timeout(10 seconds)

//  val $serviceName;format="Camel"$Actor: ActorRef = ???

  val routes: Route = Route.seal(
    cors {
      openApiRoute
    }
  )
}
