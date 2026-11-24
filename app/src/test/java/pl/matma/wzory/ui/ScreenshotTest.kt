package pl.matma.wzory.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performRotaryScrollInput
import androidx.compose.ui.test.performScrollToNode
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import pl.matma.wzory.MainViewModel
import java.io.File

/** Renderuje ekrany na okrągłym ekranie 198 dp (test dymny + zrzuty do build/screenshots). */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w198dp-h198dp-round-watch-xhdpi")
class ScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private fun vm() = MainViewModel(ApplicationProvider.getApplicationContext())

    private fun shot(name: String, route: String, setup: MainViewModel.() -> Unit = {}, action: () -> Unit = {}) {
        val vm = vm().apply(setup)
        compose.setContent { MatmaApp(vm, route) }
        compose.waitForIdle()
        action()
        compose.waitForIdle()
        save(name)
    }

    private fun save(name: String) {
        val dir = System.getProperty("screenshot.dir") ?: return
        var bitmap: Bitmap? = null
        compose.runOnIdle {
            val root = compose.activity.window.decorView
            val bmp = Bitmap.createBitmap(root.width, root.height, Bitmap.Config.ARGB_8888)
            root.draw(Canvas(bmp))
            val path = Path().apply {
                addRect(0f, 0f, bmp.width.toFloat(), bmp.height.toFloat(), Path.Direction.CW)
                addCircle(bmp.width / 2f, bmp.height / 2f, bmp.width / 2f, Path.Direction.CCW)
            }
            Canvas(bmp).drawPath(path, Paint().apply { color = Color.BLACK })
            bitmap = bmp
        }
        File(dir).mkdirs()
        File(dir, "$name.png").outputStream().use { bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }

    private fun scrollAndClick(text: String) {
        compose.onNode(hasScrollToNodeAction()).performScrollToNode(hasText(text))
        compose.onNodeWithText(text).performClick()
        compose.waitForIdle()
    }

    private fun scrollTo(text: String) {
        compose.onNode(hasScrollToNodeAction()).performScrollToNode(hasText(text, substring = true))
        compose.waitForIdle()
    }

    /** Nawigacja klikaniem od ekranu głównego: dział → kategoria (→ wzór). */
    private fun openCategory(section: String, category: String) {
        scrollAndClick(section)
        scrollAndClick(category)
    }

    private fun openFormula(section: String, category: String, formula: String) {
        openCategory(section, category)
        scrollAndClick(formula)
    }

    private fun openCalc(title: String) {
        scrollAndClick("Kalkulatory")
        scrollAndClick(title)
    }

    @Test fun home() = shot("01_home", Routes.HOME)
    @Test fun sections() = shot("02_dzialy", Routes.HOME, action = { scrollTo("Funkcje") })
    @Test fun section() = shot("03_geometria_analityczna", Routes.HOME, action = { scrollAndClick("Geometria analityczna") })
    @Test fun category() = shot("04_odleglosci", Routes.HOME, action = { openCategory("Geometria analityczna", "Odległości") })
    @Test fun distance() = shot("05_odleglosc_punktu_od_prostej", Routes.HOME, action = {
        openFormula("Geometria analityczna", "Odległości", "Odległość punktu od prostej")
    })
    @Test fun distanceExample() = shot("06_przyklad", Routes.HOME, action = {
        openFormula("Geometria analityczna", "Odległości", "Odległość punktu od prostej")
        scrollTo("Przykład")
    })
    @Test fun angle() = shot("07_kat_miedzy_prostymi", Routes.HOME, action = {
        openFormula("Geometria analityczna", "Wzajemne położenie prostych", "Kąt między prostymi (ogólne)")
    })
    @Test fun quadratic() = shot("08_pierwiastki", Routes.HOME, action = {
        openFormula("Funkcje", "Funkcja kwadratowa", "Pierwiastki równania kwadratowego")
    })
    @Test fun circles() = shot("09_dwa_okregi", Routes.HOME, action = {
        openFormula("Geometria analityczna", "Okrąg w układzie", "Wzajemne położenie dwóch okręgów")
    })
    @Test fun derivative() = shot("10_definicja_pochodnej", Routes.HOME, action = {
        openFormula("Analiza", "Pochodne – reguły", "Definicja pochodnej")
    })
    @Test fun bernoulli() = shot("11_bernoulli", Routes.HOME, action = {
        openFormula("Kombinatoryka i statystyka", "Prawdopodobieństwo", "Schemat Bernoulliego")
    })
    @Test fun calcs() = shot("12_kalkulatory", Routes.CALCS)
    @Test fun calcDistance() = shot("13_kalk_punkt_prosta", Routes.HOME, action = {
        openCalc("Odległość punktu od prostej")
        scrollTo("Odległość d")
    })
    @Test fun calcQuadratic() = shot("14_kalk_kwadratowa", Routes.HOME, action = {
        openCalc("Funkcja kwadratowa")
        scrollTo("x₁ = ")
    })
    @Test fun calcTriangle() = shot("15_kalk_trojkat", Routes.HOME, action = {
        openCalc("Trójkąt z 3 punktów")
        scrollTo("Pole P")
    })
    @Test fun keypad() = shot("16_klawiatura", Routes.HOME, action = {
        openCalc("Odległość punktu od prostej")
        scrollAndClick("B")
        compose.onNodeWithText("±").performClick()
        compose.onNodeWithText("/").performClick()
        compose.onNodeWithText("2").performClick()
    })
    @Test fun search() = shot("17_szukaj", Routes.SEARCH, setup = { searchQuery = "prostopadle" })
    @Test fun quiz() = shot("18_fiszki", Routes.QUIZ, action = { scrollAndClick("Pokaż wzór") })
    @Test fun integral() = shot("19_calka", Routes.HOME, action = {
        openFormula("Analiza", "Całki – metody i zastosowania", "Wzór Newtona–Leibniza")
    })
    @Test fun trig() = shot("20_wartosci_30", Routes.HOME, action = {
        openFormula("Trygonometria", "Definicje i wartości", "Wartości dla 30°")
    })
    @Test fun favorites() = shot("21_ulubione", Routes.FAVORITES, setup = {
        toggleFavorite("odleglosci|Odległość punktu od prostej")
        toggleFavorite("kwadratowa|Wyróżnik (delta)")
    })

    @Test fun keypadUpdatesCalculator() {
        val vm = vm()
        compose.setContent { MatmaApp(vm, Routes.HOME) }
        openCalc("Odległość punktu od prostej")
        scrollAndClick("x₀")
        listOf("1", ",", "5").forEach { compose.onNodeWithText(it).performClick() }
        compose.onNodeWithText("OK").performClick()
        compose.waitForIdle()
        assertEquals(1.5, vm.prefs.calcValues["punkt-prosta.x0"]!!, 1e-9)
    }

    @Test fun favoriteToggle() {
        val vm = vm()
        compose.setContent { MatmaApp(vm, Routes.HOME) }
        openFormula("Geometria analityczna", "Odległości", "Odległość punktu od prostej")
        scrollAndClick("Dodaj do ulubionych")
        assertTrue(vm.prefs.favorites.any { it.endsWith("|Odległość punktu od prostej") })
        assertTrue(vm.prefs.recent.isNotEmpty())
    }


    @OptIn(ExperimentalTestApi::class)
    @Test fun bezelScrollsHome() {
        compose.setContent { MatmaApp(vm(), Routes.HOME) }
        compose.waitForIdle()
        compose.onNodeWithText("Pomoc").assertDoesNotExist()
        repeat(25) {
            compose.onRoot().performRotaryScrollInput { rotateToScrollVertically(200f) }
            compose.mainClock.advanceTimeBy(100)
        }
        compose.waitForIdle()
        compose.onNodeWithText("Pomoc").assertExists()
    }
}
