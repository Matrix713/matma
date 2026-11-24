package pl.matma.wzory.data

/** Dział (np. „Geometria analityczna”) z kategoriami. */
data class Section(val id: String, val name: String, val description: String, val categories: List<Category>) {
    val formulaCount: Int get() = categories.sumOf { it.formulas.size }
}

data class Category(
    val id: String,
    val sectionId: String,
    val name: String,
    val description: String,
    val formulas: List<Formula>,
)

/**
 * Wzór. [formula] i [example] w zapisie MathMarkup (ułamki @f{}{}, pierwiastki @s{}, potęgi ^{}).
 * [calc] – identyfikator kalkulatora, który liczy ten wzór.
 */
data class Formula(
    val categoryId: String,
    val title: String,
    val formula: String,
    val description: String? = null,
    val example: String? = null,
    val tip: String? = null,
    val tags: String = "",
    val calc: String? = null,
) {
    val id: String get() = "$categoryId|$title"
}

class SectionBuilder(private val sectionId: String) {
    val categories = mutableListOf<Category>()

    fun category(id: String, name: String, description: String, block: CategoryBuilder.() -> Unit) {
        categories += Category(id, sectionId, name, description, CategoryBuilder(id).apply(block).formulas.toList())
    }
}

class CategoryBuilder(private val categoryId: String) {
    val formulas = mutableListOf<Formula>()

    /**
     * Dodaje wzór.
     * @param t tytuł, @param f wzór, @param d opis, @param ex przykład (MathMarkup), @param tip wskazówka,
     * @param tags dodatkowe słowa do wyszukiwania, @param calc id kalkulatora.
     */
    fun f(
        t: String,
        f: String,
        d: String? = null,
        ex: String? = null,
        tip: String? = null,
        tags: String = "",
        calc: String? = null,
    ) {
        formulas += Formula(categoryId, t, f, d, ex, tip, tags, calc)
    }
}

fun section(id: String, name: String, description: String, block: SectionBuilder.() -> Unit): Section =
    Section(id, name, description, SectionBuilder(id).apply(block).categories.toList())
