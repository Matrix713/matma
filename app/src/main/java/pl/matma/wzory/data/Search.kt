package pl.matma.wzory.data

import pl.matma.wzory.math.MathMarkup
import java.text.Normalizer

/** Wyszukiwanie wzorów po nazwie, treści, opisie i kategorii – bez polskich znaków też działa. */
object Search {
    fun normalize(text: String): String =
        Normalizer.normalize(text.lowercase().replace('ł', 'l'), Normalizer.Form.NFD)
            .replace(Regex("\\p{Mn}+"), "")

    private fun words(text: String): List<String> =
        normalize(text).split(Regex("[^a-z0-9²³√π]+")).filter { it.isNotEmpty() }

    /** Każde słowo zapytania musi być początkiem jakiegoś słowa tekstu („odl pun pros”). */
    private fun prefixMatch(query: List<String>, text: List<String>): Boolean =
        query.isNotEmpty() && query.all { q -> text.any { it.startsWith(q) } }

    fun score(f: Formula, query: String): Int {
        val q = normalize(query.trim())
        if (q.isEmpty()) return 0
        val title = normalize(f.title)
        val cat = FormulaDb.category(f.categoryId)
        val section = FormulaDb.sectionOf(f.categoryId)
        if (title == q) return 120
        if (title.startsWith(q)) return 100
        if (title.contains(q)) return 85
        val qw = words(q)
        val titleWords = words(f.title)
        if (prefixMatch(qw, titleWords)) return 75
        val tags = normalize(f.tags)
        if (tags.contains(q)) return 65
        val formula = normalize(MathMarkup.plain(f.formula))
        if (formula.replace(" ", "").contains(q.replace(" ", ""))) return 55
        val all = listOf(
            f.title, f.tags, f.description.orEmpty(), f.tip.orEmpty(),
            cat?.name.orEmpty(), section?.name.orEmpty(), MathMarkup.plain(f.formula),
        ).joinToString(" ")
        if (prefixMatch(qw, words(all))) return 30
        return 0
    }

    fun search(query: String, limit: Int = 80): List<Formula> =
        FormulaDb.formulas
            .map { it to score(it, query) }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .take(limit)
            .map { it.first }
}
