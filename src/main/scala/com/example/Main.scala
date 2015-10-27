package com.example

import java.io.{File, FileWriter}
import java.text.SimpleDateFormat

import akka.actor.{Actor, ActorLogging, Props}

class Main extends Actor with ActorLogging {
  val processorCount = Runtime.getRuntime.availableProcessors()

  println(s"Spawning $processorCount workers.")

  (0 until processorCount)
    .foreach(i => context.actorOf(Worker.props, s"worker-$i"))

  var globalMax = 0
  val gui = new GUI

  def receive = {
    case result: List[(Int, String, Int, Int)] =>
      if (result.length > globalMax) {
        globalMax = result.length

        val writer = new FileWriter(new File("output.txt"), true)
        writer.append(s"Length: $globalMax (${new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date())}})\n")
        writer.append("Cell\tPiece\tRotation\tData\n")
        result.sortBy(_._1).foreach { case (cellNumber, dataString, number, rotation) =>
          writer.append(s"${('A' + cellNumber/16).toChar} ${s" ${(cellNumber % 16) + 1}".takeRight(2)}\t$number\t\t$rotation\t\t\t$dataString\n")
        }
        writer.append("\n---\n\n")
        writer.close()

        gui.Paint.updateCoordinates(
          result.map { case (cellNumber, dataString, _, _) =>
            cellNumber -> dataString.replaceAll("X", "@")
          }
        )
      }
  }	
}

object Main {
  val props = Props[Main]
}