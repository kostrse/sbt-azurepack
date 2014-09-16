package sbtazurepack.test.sprayapp1

import akka.actor._
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http
import spray.http.MediaTypes._
import spray.routing._

import scala.concurrent.duration._
import scala.util.Properties

class AzureService extends HttpServiceActor {

  override def receive = runRoute {
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <p>sprayapp1</p>
              </body>
            </html>
          }
        }
      }
    }
  }
}

object Main extends App {

  implicit val system = ActorSystem("on-spray-can")

  val service = system.actorOf(Props[AzureService], "azure-service")

  // Retrieving address and port from environment variables
  // or falling back to default values
  val serviceAddress = Properties.envOrElse("SERVICE_ADDRESS", "localhost")
  val servicePort = Properties.envOrElse("SERVICE_PORT", "8080").toInt

  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = serviceAddress, port = servicePort)
}
