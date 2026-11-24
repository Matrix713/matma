package pl.matma.wzory.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import pl.matma.wzory.MainViewModel

object Routes {
    const val HOME = "home"
    const val SECTION = "section/{id}"
    const val CATEGORY = "category/{id}"
    const val FORMULA = "formula?id={id}"
    const val SEARCH = "search"
    const val FAVORITES = "favorites"
    const val RECENT = "recent"
    const val CALCS = "calcs"
    const val CALC = "calc/{id}"
    const val KEYPAD = "keypad"
    const val QUIZ = "quiz"
    const val HELP = "help"

    fun section(id: String) = "section/$id"
    fun category(id: String) = "category/$id"
    fun formula(id: String) = "formula?id=${Uri.encode(id)}"
    fun calc(id: String) = "calc/$id"
}

@Composable
fun MatmaApp(vm: MainViewModel = viewModel(), startDestination: String = Routes.HOME) {
    val nav = rememberSwipeDismissableNavController()
    val idArg = listOf(navArgument("id") { type = NavType.StringType; defaultValue = "" })
    MatmaTheme {
        SwipeDismissableNavHost(navController = nav, startDestination = startDestination) {
            composable(Routes.HOME) { HomeScreen(vm, nav) }
            composable(Routes.SECTION, arguments = idArg) { SectionScreen(nav, it.arguments?.getString("id").orEmpty()) }
            composable(Routes.CATEGORY, arguments = idArg) { CategoryScreen(nav, it.arguments?.getString("id").orEmpty()) }
            composable(Routes.FORMULA, arguments = idArg) { FormulaScreen(vm, nav, it.arguments?.getString("id").orEmpty()) }
            composable(Routes.SEARCH) { SearchScreen(vm, nav) }
            composable(Routes.FAVORITES) { FavoritesScreen(vm, nav) }
            composable(Routes.RECENT) { RecentScreen(vm, nav) }
            composable(Routes.CALCS) { CalcListScreen(nav) }
            composable(Routes.CALC, arguments = idArg) { CalcScreen(vm, nav, it.arguments?.getString("id").orEmpty()) }
            composable(Routes.KEYPAD) {
                val request = vm.input
                if (request != null) key(request) { NumberKeypad(request) { nav.popBackStack() } }
            }
            composable(Routes.QUIZ) { QuizScreen(vm) }
            composable(Routes.HELP) { HelpScreen() }
        }
    }
}
