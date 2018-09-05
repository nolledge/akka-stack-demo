package de.codingchallenge

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.scalatest.BeforeAndAfterAll

trait AkkaSpec extends BeforeAndAfterAll{ self: BaseSpec =>

  implicit val system = ActorSystem("test")

  implicit val dispatcher = system.dispatcher

  implicit val materializer = ActorMaterializer()

  override protected def afterAll(): Unit = {
    super.afterAll()
    system.terminate()
  }
}
