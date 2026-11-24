package pl.matma.wzory.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.matma.wzory.math.Calculators
import pl.matma.wzory.math.MathMarkup

class FormulaDbTest {
    @Test fun everyFormulaAndExampleParses() {
        val errors = FormulaDb.formulas.flatMap { f ->
            listOfNotNull(f.formula, f.example, f.description, f.tip).mapNotNull { src ->
                runCatching { MathMarkup.layout(src) }.exceptionOrNull()?.let { "${f.id}: ${it.message}" }
            }
        }
        assertTrue(errors.joinToString("\n"), errors.isEmpty())
    }

    @Test fun idsAreUnique() {
        val dup = FormulaDb.formulas.groupBy { it.id }.filterValues { it.size > 1 }.keys
        assertTrue("Powtórzone: $dup", dup.isEmpty())
        val catDup = FormulaDb.categories.groupBy { it.id }.filterValues { it.size > 1 }.keys
        assertTrue("Powtórzone kategorie: $catDup", catDup.isEmpty())
    }

    @Test fun calculatorLinksExist() {
        FormulaDb.formulas.mapNotNull { it.calc }.distinct().forEach { assertNotNull("Brak kalkulatora $it", Calculators.byId(it)) }
    }

    @Test fun noEmptyCategories() {
        FormulaDb.categories.forEach { assertTrue(it.id, it.formulas.isNotEmpty()) }
        assertTrue("Za mało wzorów: ${FormulaDb.formulas.size}", FormulaDb.formulas.size >= 400)
    }

    @Test fun searchFindsDistanceFormula() {
        val r = Search.search("odleglosc punktu od prostej")
        assertEquals("Odległość punktu od prostej", r.first().title)
        assertTrue(Search.search("delta").any { it.title.startsWith("Wyróżnik") })
        assertTrue(Search.search("odl pkt prost").isNotEmpty() || Search.search("odl punk prost").isNotEmpty())
        assertTrue(Search.search("heron").first().title == "Wzór Herona")
    }

    @Test fun plainPreview() {
        assertEquals("d = |Ax₀ + By₀ + C|/√(A² + B²)", MathMarkup.plain("d = @f{|Ax₀ + By₀ + C|}{@s{A² + B²}}"))
        assertEquals("aⁿ⁻¹", MathMarkup.plain("a^{n−1}"))
    }
}

class LayoutTest {
    private fun texts(src: String) = MathMarkup.layout(src).map { line ->
        line.map { seg -> seg.map { w -> w.joinToString("") { (it as? pl.matma.wzory.math.MText)?.text ?: "#" } } }
    }

    @Test fun segmentsWordsAndGlue() {
        // dwie spacje = osobne segmenty, „=” przyklejone do następnego słowa, nawiasy nie są łamane
        assertEquals(
            listOf(listOf(listOf("a", "= b,"), listOf("c", "= (1 − p)"))),
            texts("a = b,  c = (1 − p)"),
        )
        assertEquals(2, MathMarkup.layout("x\ny").size)
        assertEquals(listOf(listOf(listOf("d", "= #"))), texts("d = @f{1}{2}"))
    }
}
