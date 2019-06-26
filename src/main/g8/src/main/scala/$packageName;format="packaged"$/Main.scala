package $packageName$

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import $packageName$.service.$serviceName$

import scala.concurrent.duration._
import scala.concurrent.ExecutionContextExecutor

object Main extends App {
   implicit val system : ActorSystem =  ActorSystem("$serviceName;format="Camel"$System")
   implicit val materializer: ActorMaterializer = ActorMaterializer()
   implicit val executionContext: ExecutionContextExecutor = system.dispatcher
   implicit val timeout: Timeout = Timeout(10.seconds)

   val routes: Route = new $serviceName;format="Camel"$().routes
   val interface = "$interface$"
   val port = $port$
   Http().bindAndHandle(routes, interface, port)

   println(s"Listening to \$interface:\$port")
}
