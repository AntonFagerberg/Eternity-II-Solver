package com.example

import akka.actor.{Actor, ActorLogging, Props}

class Worker extends Actor with ActorLogging {
  Game(context.parent).run()

  def receive = { case _ => }
}

object Worker {
  val props = Props[Worker]
}
