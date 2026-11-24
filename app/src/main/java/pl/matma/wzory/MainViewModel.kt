package pl.matma.wzory

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import pl.matma.wzory.data.FormulaDb
import pl.matma.wzory.math.Calculator
import pl.matma.wzory.ui.NumberRequest

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val storage = Storage(app)

    var prefs by mutableStateOf(storage.load())
        private set

    var searchQuery by mutableStateOf("")

    /** Aktualne żądanie wpisania liczby (klawiatura). */
    var input by mutableStateOf<NumberRequest?>(null)
        private set

    /** Sekcja wybrana w fiszkach (null = wszystkie). */
    var quizSection by mutableStateOf<String?>(null)

    private fun update(p: Prefs) {
        prefs = p
        storage.save(p)
    }

    fun isFavorite(id: String) = id in prefs.favorites

    fun toggleFavorite(id: String) {
        val f = prefs.favorites
        update(prefs.copy(favorites = if (id in f) f - id else listOf(id) + f))
    }

    fun markViewed(id: String) {
        if (FormulaDb.byId(id) == null) return
        val r = listOf(id) + prefs.recent.filter { it != id }
        update(prefs.copy(recent = r.take(MAX_RECENT)))
    }

    fun clearRecent() = update(prefs.copy(recent = emptyList()))

    fun calcValues(calc: Calculator): Map<String, Double> =
        calc.fields.associate { it.key to (prefs.calcValues["${calc.id}.${it.key}"] ?: it.default) }

    fun setCalcValue(calc: Calculator, key: String, value: Double) {
        update(prefs.copy(calcValues = prefs.calcValues + ("${calc.id}.$key" to value)))
    }

    fun resetCalc(calc: Calculator) {
        update(prefs.copy(calcValues = prefs.calcValues.filterKeys { !it.startsWith("${calc.id}.") }))
    }

    fun openInput(request: NumberRequest) {
        input = request
    }

    private companion object {
        const val MAX_RECENT = 20
    }
}
