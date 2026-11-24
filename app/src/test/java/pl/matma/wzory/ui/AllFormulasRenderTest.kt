package pl.matma.wzory.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import pl.matma.wzory.data.FormulaDb

/** Rysuje każdy wzór i przykład z bazy – wykrywa błędy układu (np. pomiar intrinsic). */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], qualifiers = "w198dp-h198dp-round-watch-xhdpi")
class AllFormulasRenderTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun renderAll() {
        val all = FormulaDb.formulas.flatMap { listOfNotNull(it.formula, it.example) }
        compose.setContent {
            MatmaTheme {
                Column(Modifier.width(170.dp)) {
                    all.forEach { FormulaBox(it) }
                }
            }
        }
        compose.waitForIdle()
    }
}
