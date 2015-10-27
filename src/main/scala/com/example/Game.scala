package com.example

import akka.actor.ActorRef

import scala.util.Random

case class Game(parent: ActorRef) {
  val seed = Random.nextLong()
  println(s"Started worker with random seed: $seed")
  val random = new Random(seed)

  def makePieces(items: List[String], offset: Int = 0): Piece = {
    val firstPiece =
      Random.shuffle(
        items.zipWithIndex.map {
          case (data, nr) => Piece(data, offset + nr + 1)
        }
      ).sliding(2).toList.map { case List(a, b) =>
        a.next = b
        b.previous = a
        a
      }.head

    val startPiece = Piece("????", -1)

    startPiece.next = firstPiece
    firstPiece.previous = startPiece

    startPiece
  }

  val cornerPieces =
    makePieces(
      List(
        "AQXX",
        "AEXX",
        "IQXX",
        "QIXX"
      )
    )


  val borderPieces =
    makePieces(
      List(
        "BAXA",
        "JIXA",
        "FAXA",
        "FMXA",
        "KQXA",
        "GEXA",
        "OIXA",
        "HEXA",
        "HMXA",
        "UEXA",
        "JAXI",
        "RQXI",
        "NMXI",
        "SMXI",
        "GIXI",
        "OIXI",
        "DEXI",
        "LAXI",
        "LMXI",
        "TAXI",
        "UAXI",
        "BIXQ",
        "BQXQ",
        "JQXQ",
        "RQXQ",
        "GMXQ",
        "OIXQ",
        "TQXQ",
        "HIXQ",
        "HEXQ",
        "PMXQ",
        "VEXQ",
        "RAXE",
        "CMXE",
        "KMXE",
        "SIXE",
        "SQXE",
        "OAXE",
        "OIXE",
        "OQXE",
        "DAXE",
        "TEXE",
        "HEXE",
        "PEXE",
        "BMXM",
        "JAXM",
        "JIXM",
        "FAXM",
        "GEXM",
        "DEXM",
        "DMXM",
        "HQXM",
        "PAXM",
        "PMXM",
        "UIXM",
        "VQXM"
      ),
      4
    )

  val restPieces =
    makePieces(
      List(
        "FRBB",
        "NGBB",
        "JCBJ",
        "BHBR",
        "RVBR",
        "NNBR",
        "KJBR",
        "TFBR",
        "VHBR",
        "CGBC",
        "GLBC",
        "NRBK",
        "ODBK",
        "TOBK",
        "HCBK",
        "NOBS",
        "SOBS",
        "CPBG",
        "TCBG",
        "PUBG",
        "SRBO",
        "RRBD",
        "KDBD",
        "RSBL",
        "FNBL",
        "HLBL",
        "PTBL",
        "BUBT",
        "FVBT",
        "DPBT",
        "KLBH",
        "SOBH",
        "SDBH",
        "DUBH",
        "LNBH",
        "UCBU",
        "DSBV",
        "THBV",
        "UFBV",
        "VUBV",
        "LOJJ",
        "LPJJ",
        "PSJJ",
        "VFJJ",
        "DOJR",
        "CHJF",
        "SHJF",
        "DOJF",
        "PKJF",
        "OLJN",
        "LOJN",
        "TSJC",
        "TPJC",
        "NDJK",
        "GLJK",
        "LKJK",
        "VPJK",
        "CUJS",
        "PLJG",
        "HVJO",
        "NVJD",
        "FPJT",
        "NSJT",
        "TOJT",
        "LVJH",
        "UOJH",
        "NFJP",
        "SUJP",
        "DCJP",
        "THJP",
        "FTJU",
        "LNJU",
        "NPJV",
        "KDJV",
        "DCJV",
        "PTJV",
        "TGRR",
        "FCRF",
        "FKRF",
        "FLRF",
        "SURF",
        "OFRF",
        "PLRF",
        "UURF",
        "CDRN",
        "RLRC",
        "RVRC",
        "CNRC",
        "OLRC",
        "FKRS",
        "DVRS",
        "KKRG",
        "KSRG",
        "VPRG",
        "GGRD",
        "GLRD",
        "VGRD",
        "GPRT",
        "HFRT",
        "UURH",
        "FTRP",
        "NTRP",
        "OKRV",
        "DPRV",
        "CDFN",
        "DHFN",
        "CCFK",
        "KOFS",
        "SUFS",
        "DHFG",
        "TPFG",
        "UKFG",
        "OOFO",
        "LTFO",
        "GUFD",
        "GSFL",
        "NDFT",
        "LPFH",
        "HOFH",
        "GPFP",
        "KPFU",
        "GKFU",
        "SHNN",
        "VGNC",
        "SLNK",
        "HHNK",
        "UGNS",
        "NUNG",
        "CSNG",
        "PSNG",
        "CCNO",
        "OTNO",
        "KGND",
        "UKNL",
        "UVNL",
        "VONL",
        "KVNT",
        "SHNT",
        "TTNT",
        "SCNH",
        "UHNP",
        "VGNP",
        "LSNU",
        "LHNU",
        "PCNU",
        "VUNU",
        "VGCC",
        "SVCK",
        "HOCK",
        "KSCG",
        "POCG",
        "CPCO",
        "HHCD",
        "CTCL",
        "DVCL",
        "VUCL",
        "SOCT",
        "DLCP",
        "KDCU",
        "KPCV",
        "UUCV",
        "UVCV",
        "LVKK",
        "TGKK",
        "POKK",
        "SOKG",
        "LLKG",
        "SHKD",
        "GVKT",
        "PHKT",
        "LTKH",
        "LUKH",
        "STSS",
        "PDSG",
        "GDSD",
        "GTSD",
        "LOSD",
        "DPSL",
        "OVST",
        "UOST",
        "GUSH",
        "DUSH",
        "OLGO",
        "THGO",
        "VTGD",
        "PVGU",
        "UVOO",
        "LDOD",
        "DUOL",
        "PUOT",
        "VHDD",
        "HLDL",
        "PTLH",
        "UPTP",
        "PVTV",
        "UVHV"
      ),
      60
    )

  var recordDepth = 0

  val board =
    (1 to 16).map { i =>
      ('A' to 'P').map { c =>
        BoardCell(c, i)
      }
    }

  def pieces(x: Int, y: Int): Piece = {
    if (x == 0 && y == 0 || x == 15 && y == 0 || x == 0 && y == 15 || x == 15 && y == 15) cornerPieces
    else if (x == 0 || x == 15 || y == 0 || y == 15) borderPieces
    else restPieces
  }

  case class Placement(up: Char, right: Char, down: Char, left: Char, rotation: Int) {
    val string = s"$up$right$down$left"
  }

  case class Piece(data: String, number: Int) {
    val placements = (0 until 4).map { i =>
      val (a, b) = data.splitAt(i)
      val ss = b + a
      Placement(ss(0), ss(1), ss(2), ss(3), i)
    }

    var next: Piece = null
    var previous: Piece = null

    def remove(): Unit = {
      if (next != null && previous != null) {
        next.previous = previous
        previous.next = next
      } else if (next != null) {
        next.previous = null
      } else if (previous != null) {
        previous.next = null
      }

      previous = null
      next = null
    }

    override def toString: String = {
      if (next != null) s"$data ${next.toString}"
      else data
    }
  }

  val right = (1, 0)
  val down = (0, 1)
  val left = (-1, 0)
  val up = (0, -1)

  val nextMovePattern = Map (
    right -> down,
    down -> left,
    left -> up,
    up -> right
  )

  case class BoardCell(c: Char, i: Int, var piece: Placement = null)

  def compare(searchString: String, pieceString: String): Boolean = {
    (searchString(0) == ' ' || searchString(0) == pieceString(0)) &&
    (searchString(1) == ' ' || searchString(1) == pieceString(1)) &&
    (searchString(2) == ' ' || searchString(2) == pieceString(2)) &&
    (searchString(3) == ' ' || searchString(3) == pieceString(3))
  }

  def run(): Unit = {
    var piece = pieces(1, 1).next
    while (piece.number != 139) { piece = piece.next }
    piece.remove()
    val (x, y) = (7, 8)
    val placement = piece.placements(2)
    board(x)(y).piece = placement
    iterate(trail = List((x + y * 16, placement.string, piece.number, placement.rotation)))
  }

  def iterate(dX: Int = 1, dY: Int = 0, x: Int = 0, y: Int = 0, trail: List[(Int, String, Int, Int)] = Nil, depth: Int = 1): Unit = {
    if (depth > recordDepth) {
      recordDepth = depth
      parent ! trail
    }

    if (restPieces.next == null) {
      println("Solved it! Wow...")
      System.exit(0)
    } else {
      val up    = try { board(x)(y - 1).piece.down  } catch { case _: NullPointerException => ' ' case _: IndexOutOfBoundsException => 'X' }
      val right = try { board(x + 1)(y).piece.left  } catch { case _: NullPointerException => ' ' case _: IndexOutOfBoundsException => 'X' }
      val down  = try { board(x)(y + 1).piece.up    } catch { case _: NullPointerException => ' ' case _: IndexOutOfBoundsException => 'X' }
      val left  = try { board(x - 1)(y).piece.right } catch { case _: NullPointerException => ' ' case _: IndexOutOfBoundsException => 'X' }

      try {
        if (board(x + dX)(y + dY).piece != null && restPieces.next.next != null)
          throw new IndexOutOfBoundsException

        var currentPiece = pieces(x, y).next
        val searchString = s"$up$right$down$left"

        while (currentPiece != null) {
          val nextPiece = currentPiece.next
          val previousPiece = currentPiece.previous


          currentPiece.placements.foreach { placement =>
            if (compare(searchString, placement.string)) {
              board(x)(y).piece = placement
              currentPiece.remove()

              iterate(dX, dY, x + dX, y + dY, (x + y * 16, placement.string, currentPiece.number, placement.rotation) :: trail, depth + 1)

              if (nextPiece != null) {
                nextPiece.previous = currentPiece
              }

              currentPiece.next = nextPiece
              previousPiece.next = currentPiece
              currentPiece.previous = previousPiece
            }
          }

          board(x)(y).piece = null
          currentPiece = nextPiece
        }
      } catch {
        case _: IndexOutOfBoundsException =>
          val (nx, ny) = nextMovePattern(dX -> dY)
          iterate(nx, ny, x, y, trail, depth)
      }
    }
  }
}