package com.example

import akka.actor.ActorSystem

object ApplicationMain extends App {
  val system = ActorSystem("EternityIIActorSystem")
  system.actorOf(Main.props, "main")
  system.awaitTermination()
}