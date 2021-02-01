package lpk.imaging

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun loadPictureFromFile(imageFile: File): Picture {
    val image = ImageIO.read(imageFile)
    val width = image.width
    val height = image.height
    val pixels = Array(height) {
        row ->
        Array(width) {
            column ->
            Color(image.getRGB(column, row))
        }
    }
    return Picture(pixels)
}

class Picture(val pixels: Array<Array<Color>>) {
    fun height(): Int {
        return pixels.size
    }

    fun width(): Int {
        return pixels[0].size
    }

    fun pixelByRowColumn(row: Int, column: Int): Color {
        return pixels[row][column]
    }

    fun cropTo(rowAt: Int, columnAt: Int, h: Int, w: Int): Picture {
        val cropArray = Array(1) {
            row ->
            Array(1) {
                column ->
                Color(0, 0, 0)
            }
        }
        return Picture(cropArray)
    }
}
