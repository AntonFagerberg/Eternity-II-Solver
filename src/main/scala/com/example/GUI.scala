package com.example

import java.awt.geom.AffineTransform
import java.awt.{Dimension, Graphics, Graphics2D}
import java.io.File
import javax.imageio.ImageIO
import javax.swing.{JComponent, JFrame}

import scala.collection.mutable.ArrayBuffer

class GUI {
  val window = new JFrame()
  val scale = 40
  val image = ImageIO.read(new File(getClass.getClassLoader.getResource("pieces.png").getFile))
  val pieceHeight = 128
  val pieceWidth = 256
  val at = new AffineTransform()
  val frame = new JFrame("Eternity II")


  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  window.getContentPane.add(Paint)
  window.getContentPane.setPreferredSize(new Dimension(16 * scale, 16*scale))
  window.pack()
  window.setVisible(true)

  object Paint extends JComponent {
    var coordinates = new ArrayBuffer[(Int, Int, Int, Int)]
    var pieces: List[(Int, String)] = Nil

    def updateCoordinates(newPieces: List[(Int, String)]): Unit = {
      pieces = newPieces
      super.repaint()
    }

    def drawPiece(nr: Int, rotation: Int, x: Int, y: Int, graphics: Graphics2D): Unit = {
      val xx = pieceWidth * (nr % 4)
      val yy = pieceHeight * (nr / 4)
      val subImage = image.getSubimage(xx, yy, pieceWidth, pieceHeight)
      val xRotation = if (rotation == 1 || rotation == 2) 1 else 0
      val yRotation = if (rotation == 2 || rotation == 3) 1 else 0
      at.setToTranslation((x + xRotation) * scale, (y + yRotation) * scale)
      at.scale(scale.toDouble / pieceWidth, scale.toDouble / (2 * pieceHeight))
      at.rotate(rotation * Math.PI / 2)
      graphics.drawImage(subImage, at, null)
    }

    override def paint(graphics: Graphics) {
      pieces.foreach { case (i, p) =>
        val x = i % 16
        val y = i / 16

        p.map(_.toInt - 64).zipWithIndex.foreach { case (n, ii) =>
          drawPiece(n, ii, x, y, graphics.asInstanceOf[Graphics2D])
        }
      }
    }

  }
}
