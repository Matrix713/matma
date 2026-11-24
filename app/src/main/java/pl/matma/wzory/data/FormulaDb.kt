package pl.matma.wzory.data

import pl.matma.wzory.data.content.Algebra
import pl.matma.wzory.data.content.AnalyticGeometry
import pl.matma.wzory.data.content.Calculus
import pl.matma.wzory.data.content.Functions
import pl.matma.wzory.data.content.Other
import pl.matma.wzory.data.content.Planimetry
import pl.matma.wzory.data.content.Probability
import pl.matma.wzory.data.content.Sequences
import pl.matma.wzory.data.content.Stereometry
import pl.matma.wzory.data.content.Trigonometry

/** Baza wzorów (offline). */
object FormulaDb {
    val sections: List<Section> = listOf(
        AnalyticGeometry.section,
        Functions.section,
        Algebra.section,
        Trigonometry.section,
        Planimetry.section,
        Stereometry.section,
        Sequences.section,
        Calculus.section,
        Probability.section,
        Other.section,
    )

    val categories: List<Category> = sections.flatMap { it.categories }
    val formulas: List<Formula> = categories.flatMap { it.formulas }

    private val sectionById = sections.associateBy { it.id }
    private val categoryById = categories.associateBy { it.id }
    private val formulaById = formulas.associateBy { it.id }

    fun section(id: String) = sectionById[id]
    fun category(id: String) = categoryById[id]
    fun byId(id: String) = formulaById[id]
    fun sectionOf(categoryId: String) = categoryById[categoryId]?.let { sectionById[it.sectionId] }
    fun sectionIndex(id: String) = sections.indexOfFirst { it.id == id }.coerceAtLeast(0)
    fun forCalc(calcId: String) = formulas.filter { it.calc == calcId }
}
