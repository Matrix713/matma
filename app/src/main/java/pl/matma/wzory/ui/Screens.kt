package pl.matma.wzory.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text
import pl.matma.wzory.MainViewModel
import pl.matma.wzory.data.Formula
import pl.matma.wzory.data.FormulaDb
import pl.matma.wzory.data.Search
import pl.matma.wzory.math.Calculators
import pl.matma.wzory.math.MathMarkup

@Composable
fun FormulaChip(f: Formula, showCategory: Boolean = false, onClick: () -> Unit) {
    Chip(
        label = { Text(f.title, maxLines = 2, fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        secondaryLabel = {
            val cat = if (showCategory) FormulaDb.category(f.categoryId)?.name?.let { "$it · " } ?: "" else ""
            Text(cat + MathMarkup.plain(f.formula), maxLines = 1, fontSize = 11.sp, color = Muted)
        },
        icon = if (showCategory) ({ ColorDot(categoryColor(f.categoryId), 8) }) else null,
        onClick = onClick,
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun HomeScreen(vm: MainViewModel, nav: NavController) {
    val search = rememberTextInput("Szukaj wzoru") {
        vm.searchQuery = it
        nav.navigate(Routes.SEARCH)
    }
    ScreenList {
        header("Matma")
        item {
            ActionChip(
                "Szukaj wzoru", secondary = "${FormulaDb.formulas.size} wzorów · głos lub klawiatura",
                icon = Icons.Default.Search, primary = true,
            ) { search() }
        }
        item {
            ActionChip(
                "Kalkulatory", secondary = "${Calculators.all.size} · proste, okręgi, Δ…",
                icon = Icons.Default.Build,
            ) { nav.navigate(Routes.CALCS) }
        }
        item { ActionChip("Ulubione", secondary = "${vm.prefs.favorites.size}", icon = Icons.Default.Star) { nav.navigate(Routes.FAVORITES) } }
        if (vm.prefs.recent.isNotEmpty()) {
            item { ActionChip("Ostatnio oglądane", secondary = "${vm.prefs.recent.size}", icon = Icons.Default.Refresh) { nav.navigate(Routes.RECENT) } }
        }
        header("Działy")
        items(FormulaDb.sections) { s ->
            Chip(
                label = { Text(s.name, maxLines = 1, fontSize = 14.sp) },
                secondaryLabel = { Text("${s.formulaCount} · ${s.description}", maxLines = 1, fontSize = 11.sp) },
                icon = { ColorDot(sectionColor(s.id), 12) },
                onClick = { nav.navigate(Routes.section(s.id)) },
                colors = ChipDefaults.secondaryChipColors(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        header("Więcej")
        item { ActionChip("Fiszki", secondary = "ucz się wzorów na pamięć", icon = Icons.AutoMirrored.Filled.List) { nav.navigate(Routes.QUIZ) } }
        item { ActionChip("Pomoc", icon = Icons.Default.Info) { nav.navigate(Routes.HELP) } }
    }
}

@Composable
fun SectionScreen(nav: NavController, id: String) {
    val s = FormulaDb.section(id) ?: return
    val color = sectionColor(id)
    ScreenList {
        header(s.name, color)
        items(s.categories) { c ->
            Chip(
                label = { Text(c.name, maxLines = 2, fontSize = 14.sp) },
                secondaryLabel = { Text("${c.formulas.size} · ${c.description}", maxLines = 1, fontSize = 11.sp) },
                icon = { ColorDot(color, 10) },
                onClick = { nav.navigate(Routes.category(c.id)) },
                colors = ChipDefaults.secondaryChipColors(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun CategoryScreen(nav: NavController, id: String) {
    val c = FormulaDb.category(id) ?: return
    ScreenList {
        header(c.name, categoryColor(id))
        items(c.formulas) { f -> FormulaChip(f) { nav.navigate(Routes.formula(f.id)) } }
    }
}

@Composable
fun FormulaScreen(vm: MainViewModel, nav: NavController, id: String) {
    val f = FormulaDb.byId(id) ?: return
    LaunchedEffect(id) { vm.markViewed(id) }
    val favorite = vm.isFavorite(id)
    val category = FormulaDb.category(f.categoryId)
    ScreenList {
        item {
            Text(
                f.title, color = categoryColor(f.categoryId), fontWeight = FontWeight.Bold, fontSize = 14.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp),
            )
        }
        item { FormulaBox(f.formula) }
        f.description?.let { note(MathMarkup.plain(it), Color.White) }
        f.example?.let { ex ->
            item { Text("Przykład", fontSize = 11.sp, color = Muted, modifier = Modifier.padding(start = 6.dp)) }
            item { FormulaBox(ex, size = 13.sp, color = Color(0xFFDCE3E8)) }
        }
        f.tip?.let { note("💡 " + MathMarkup.plain(it), Yellow) }
        f.calc?.let { calcId ->
            Calculators.byId(calcId)?.let { calc ->
                item { ActionChip("Policz: ${calc.title}", secondary = "kalkulator", icon = Icons.Default.Build, primary = true) { nav.navigate(Routes.calc(calc.id)) } }
            }
        }
        item {
            ActionChip(
                if (favorite) "W ulubionych" else "Dodaj do ulubionych",
                icon = Icons.Default.Star,
                color = if (favorite) Yellow else null,
            ) { vm.toggleFavorite(id) }
        }
        category?.let { c ->
            item {
                ActionChip("Więcej: ${c.name}", secondary = "kategoria") {
                    nav.navigate(Routes.category(c.id)) { launchSingleTop = true }
                }
            }
        }
    }
}

@Composable
fun SearchScreen(vm: MainViewModel, nav: NavController) {
    val query = vm.searchQuery
    val results = Search.search(query)
    val newSearch = rememberTextInput("Szukaj wzoru") { vm.searchQuery = it }
    ScreenList {
        header(if (query.isBlank()) "Szukaj" else "„$query”")
        item { ActionChip("Szukaj", secondary = "nowe zapytanie: klawiatura lub głos", icon = Icons.Default.Search) { newSearch() } }
        if (query.isNotBlank()) note(if (results.isEmpty()) "Brak wyników. Spróbuj krócej, np. „odl prost”." else "Wyniki: ${results.size}")
        if (query.isBlank()) {
            note("Szukaj po nazwie lub treści, polskie znaki nie są wymagane.")
            listOf("odległość punktu od prostej", "delta", "prostopadłe", "okrąg", "pochodna", "heron").forEach { q ->
                item { ActionChip(q) { vm.searchQuery = q } }
            }
        }
        items(results) { f -> FormulaChip(f, showCategory = true) { nav.navigate(Routes.formula(f.id)) } }
    }
}

@Composable
fun FavoritesScreen(vm: MainViewModel, nav: NavController) {
    val favorites = vm.prefs.favorites.mapNotNull { FormulaDb.byId(it) }
    ScreenList {
        header("Ulubione")
        if (favorites.isEmpty()) note("Pusto. Otwórz wzór i dotknij „Dodaj do ulubionych”.")
        items(favorites) { f -> FormulaChip(f, showCategory = true) { nav.navigate(Routes.formula(f.id)) } }
    }
}

@Composable
fun RecentScreen(vm: MainViewModel, nav: NavController) {
    val recent = vm.prefs.recent.mapNotNull { FormulaDb.byId(it) }
    ScreenList {
        header("Ostatnio oglądane")
        if (recent.isEmpty()) note("Pusto.")
        items(recent) { f -> FormulaChip(f, showCategory = true) { nav.navigate(Routes.formula(f.id)) } }
        if (recent.isNotEmpty()) item { ActionChip("Wyczyść historię", icon = Icons.Default.Delete) { vm.clearRecent() } }
    }
}

/** Fiszki: losowy wzór, najpierw tylko nazwa – dotknij, aby zobaczyć. */
@Composable
fun QuizScreen(vm: MainViewModel) {
    val sectionId = vm.quizSection
    val pool = sectionId?.let { id -> FormulaDb.section(id)?.categories?.flatMap { it.formulas } } ?: FormulaDb.formulas
    var current by rememberSaveable(sectionId) { mutableStateOf(pool.random().id) }
    var revealed by rememberSaveable(sectionId) { mutableStateOf(false) }
    var count by rememberSaveable { mutableStateOf(0) }
    val f = FormulaDb.byId(current) ?: pool.first()
    ScreenList {
        header("Fiszki")
        item {
            ValueChip("Dział (dotknij, by zmienić)", sectionId?.let { FormulaDb.section(it)?.name } ?: "Wszystkie") {
                val ids = listOf<String?>(null) + FormulaDb.sections.map { it.id }
                vm.quizSection = ids[(ids.indexOf(sectionId) + 1) % ids.size]
            }
        }
        item {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(FormulaDb.category(f.categoryId)?.name.orEmpty(), fontSize = 11.sp, color = categoryColor(f.categoryId))
                Text(
                    f.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp),
                )
            }
        }
        if (revealed) {
            item { FormulaBox(f.formula) }
            f.description?.let { note(MathMarkup.plain(it), Color.White, 11) }
        }
        item {
            ActionChip(
                if (revealed) "Następny" else "Pokaż wzór",
                secondary = if (count > 0) "obejrzane: $count" else null,
                primary = true,
            ) {
                if (revealed) {
                    current = pool.random().id
                    revealed = false
                } else {
                    revealed = true
                    count++
                }
            }
        }
        if (revealed) {
            item {
                ActionChip(
                    if (vm.isFavorite(f.id)) "W ulubionych" else "Do ulubionych (powtórka)",
                    icon = Icons.Default.Star,
                    color = if (vm.isFavorite(f.id)) Yellow else null,
                ) { vm.toggleFavorite(f.id) }
            }
        }
    }
}

@Composable
fun HelpScreen() {
    ScreenList {
        header("Pomoc")
        note("Działy → kategorie → wzory. Dotknij wzoru, aby zobaczyć opis, przykład i wskazówkę.", Color.White)
        note("Bezel (obrotowa ramka) przewija listy. Przesunięcie w prawo – powrót.")
        note("Szukaj głosem lub klawiaturą – polskie znaki nie są potrzebne, działają też początki słów: „odl pkt prost”.")
        note("Kalkulatory liczą od razu: dotknij wartości, wpisz liczbę. Klawisz „/” pozwala wpisać ułamek (2/3), „±” zmienia znak, przytrzymanie ⌫ czyści.")
        note("Wyniki podawane są dokładnie (np. 7√13/13, 2/3) i w przybliżeniu.")
        note("Gwiazdka dodaje wzór do ulubionych. Fiszki pomagają powtarzać wzory przed sprawdzianem.")
        note("${FormulaDb.formulas.size} wzorów · ${FormulaDb.categories.size} kategorii · ${FormulaDb.sections.size} działów", Accent)
    }
}
