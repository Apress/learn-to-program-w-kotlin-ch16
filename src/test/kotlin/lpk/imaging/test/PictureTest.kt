package lpk.imaging.test

import org.junit.Assert
import org.junit.Test
import lpk.imaging.Picture
import lpk.imaging.loadPictureFromFile
import java.awt.Color
import java.io.File
import java.nio.file.Paths

val IMAGES = "src/test/resources/images/"

fun load(name: String): Picture {
    val file = Paths.get(IMAGES + name).toFile()
    val loaded = loadPictureFromFile(file)
    return loaded
}

class PictureTest {

    @Test
    fun cropToRedSquare() {
        val tiles100 = load("red_blue_tiles_50.png")
        val cropped = tiles100.cropTo(0, 0, 50, 50)
        val expectedColor = Color(255, 0, 0)
        Assert.assertEquals(cropped.height(), 50)
        Assert.assertEquals(cropped.width(), 50)
        for (row in 0..49) {
            for (column in 0..49) {
                Assert.assertEquals(cropped.pixelByRowColumn(row, column), expectedColor)
            }
        }
    }

    @Test
    fun cropCentre() {
        val tiles100 = load("red_blue_tiles_50.png")
        val cropped = tiles100.cropTo(25, 25, 50, 50)
        val expected = load("red_blue_tiles_25.png")
        checkPicture(expected, cropped)
    }

    fun checkPicture(picture: Picture, expected: Picture) {
        Assert.assertEquals(picture.height(), expected.height())
        Assert.assertEquals(picture.width(), expected.width())
        for (row in 0..picture.height() - 1) {
            for (column in 0..picture.width() - 1) {
                val actualPixel = picture.pixelByRowColumn(row, column)
                val expectedPixel = expected.pixelByRowColumn(row, column)
                Assert.assertEquals(actualPixel, expectedPixel)
            }
        }
    }
}