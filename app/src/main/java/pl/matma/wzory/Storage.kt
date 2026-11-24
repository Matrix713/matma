package pl.matma.wzory

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class Prefs(
    val favorites: List<String> = emptyList(),
    val recent: List<String> = emptyList(),
    /** Wartości pól kalkulatorów: „idKalkulatora.pole” → liczba. */
    val calcValues: Map<String, Double> = emptyMap(),
)

class Storage(context: Context) {
    private val prefs = context.getSharedPreferences("matma", Context.MODE_PRIVATE)

    fun load(): Prefs = try {
        prefs.getString(KEY, null)?.let { decode(JSONObject(it)) } ?: Prefs()
    } catch (_: Exception) {
        Prefs()
    }

    fun save(p: Prefs) {
        prefs.edit().putString(KEY, encode(p).toString()).apply()
    }

    private fun encode(p: Prefs) = JSONObject().apply {
        put("favorites", JSONArray(p.favorites))
        put("recent", JSONArray(p.recent))
        put("calc", JSONObject().apply { p.calcValues.forEach { (k, v) -> put(k, v) } })
    }

    private fun strings(a: JSONArray?) = if (a == null) emptyList() else (0 until a.length()).map { a.getString(it) }

    private fun decode(o: JSONObject): Prefs {
        val calc = o.optJSONObject("calc")
        return Prefs(
            favorites = strings(o.optJSONArray("favorites")),
            recent = strings(o.optJSONArray("recent")),
            calcValues = calc?.keys()?.asSequence()?.associateWith { calc.getDouble(it) } ?: emptyMap(),
        )
    }

    private companion object {
        const val KEY = "prefs_v1"
    }
}
