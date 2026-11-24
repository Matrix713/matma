package pl.matma.wzory.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text
import pl.matma.wzory.MainViewModel
import pl.matma.wzory.data.FormulaDb
import pl.matma.wzory.math.Calculators

@Composable
fun CalcListScreen(nav: NavController) {
    ScreenList {
        header("Kalkulatory")
        Calculators.groups.forEachIndexed { gi, group ->
            item {
                Text(
                    group, color = SectionPalette[gi % SectionPalette.size], fontSize = 12.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                )
            }
            items(Calculators.all.filter { it.group == group }) { c ->
                Chip(
                    label = { Text(c.title, maxLines = 2, fontSize = 13.sp, fontWeight = FontWeight.Bold) },
                    secondaryLabel = { Text(c.subtitle, maxLines = 1, fontSize = 11.sp, color = Muted) },
                    onClick = { nav.navigate(Routes.calc(c.id)) },
                    colors = ChipDefaults.secondaryChipColors(),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun CalcScreen(vm: MainViewModel, nav: NavController, id: String) {
    val calc = Calculators.byId(id) ?: return
    val values = vm.calcValues(calc)
    val result = calc.run(values)
    ScreenList {
        header(calc.title)
        calc.form?.let { form -> item { FormulaBox(form, size = 13.sp, color = Muted) } }
        items(calc.fields) { field ->
            ValueChip(field.label, NumberEditor.format(values.getValue(field.key))) {
                vm.openInput(
                    NumberRequest(field.label, NumberEditor.format(values.getValue(field.key))) { v ->
                        vm.setCalcValue(calc, field.key, v)
                        null
                    },
                )
                nav.navigate(Routes.KEYPAD)
            }
        }
        item {
            Text("Wynik", color = Accent, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp, top = 4.dp))
        }
        result.fold(
            onSuccess = { rows ->
                items(rows) { r -> InfoRow(r.label, r.value, valueColor = if (r.strong) Good else androidx.compose.ui.graphics.Color.White) }
            },
            onFailure = { e -> note(e.message ?: "Błąd obliczeń", Bad) },
        )
        val related = FormulaDb.forCalc(calc.id)
        if (related.isNotEmpty()) {
            item { Text("Wzory", color = Muted, fontSize = 11.sp, modifier = Modifier.padding(start = 8.dp, top = 4.dp)) }
            items(related) { f -> FormulaChip(f) { nav.navigate(Routes.formula(f.id)) } }
        }
        item { ActionChip("Przywróć przykład", icon = Icons.Default.Refresh) { vm.resetCalc(calc) } }
    }
}
