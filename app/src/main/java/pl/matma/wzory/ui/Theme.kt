package pl.matma.wzory.ui

import android.app.RemoteInput
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListScope
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.input.RemoteInputIntentHelper
import pl.matma.wzory.data.FormulaDb

val Accent = Color(0xFFFFB74D)
val Blue = Color(0xFF4FC3F7)
val Good = Color(0xFF81C784)
val Bad = Color(0xFFE57373)
val Muted = Color(0xFF9AA7B0)
val Yellow = Color(0xFFFFE082)
val SurfaceDark = Color(0xFF1E2A31)
val FormulaBg = Color(0xFF0E171C)

/** Kolory działów (kropki przy kategoriach i wzorach). */
val SectionPalette = listOf(
    Color(0xFF4FC3F7), Color(0xFFFFB74D), Color(0xFFA5D6A7), Color(0xFFCE93D8), Color(0xFFFFAB91),
    Color(0xFF80CBC4), Color(0xFFF48FB1), Color(0xFFFFE082), Color(0xFF9FA8DA), Color(0xFFB0BEC5),
)

fun sectionColor(sectionId: String): Color = SectionPalette[FormulaDb.sectionIndex(sectionId) % SectionPalette.size]

fun categoryColor(categoryId: String): Color =
    FormulaDb.sectionOf(categoryId)?.let { sectionColor(it.id) } ?: Muted

@Composable
fun MatmaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = Colors(
            primary = Accent,
            primaryVariant = Color(0xFFF57C00),
            secondary = Blue,
            secondaryVariant = Color(0xFF0288D1),
            error = Bad,
            onPrimary = Color.Black,
            onSecondary = Color.Black,
            surface = SurfaceDark,
            onSurface = Color.White,
            onSurfaceVariant = Muted,
        ),
        content = content,
    )
}

/** Standardowy ekran z przewijaną listą: zegar, wskaźnik pozycji, obsługa bezela. */
@Composable
fun ScreenList(
    state: ScalingLazyListState = rememberScalingLazyListState(initialCenterItemIndex = 0),
    content: ScalingLazyListScope.() -> Unit,
) {
    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = state) },
    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = state,
            // Lista wyrównana do góry (nagłówek pod zegarem), z zapasem na dole na okrągłym ekranie.
            autoCentering = null,
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 28.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            content = content,
        )
    }
}

fun ScalingLazyListScope.header(text: String, color: Color = Accent) {
    item { ListHeader { Text(text, textAlign = TextAlign.Center, color = color, fontWeight = FontWeight.Bold) } }
}

fun ScalingLazyListScope.note(text: String, color: Color = Muted, size: Int = 12) {
    item {
        Text(
            text, color = color, fontSize = size.sp, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        )
    }
}

@Composable
fun InfoRow(label: String, value: String, valueColor: Color = Color.White) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceDark)
            .padding(horizontal = 12.dp, vertical = 5.dp),
    ) {
        Text(label, fontSize = 11.sp, color = Muted)
        Text(value, fontSize = 15.sp, color = valueColor, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ValueChip(label: String, value: String, primary: Boolean = false, onClick: () -> Unit) {
    Chip(
        label = { Text(label, fontSize = 12.sp, color = if (primary) Color.Black else Muted, maxLines = 1) },
        secondaryLabel = {
            Text(
                value, fontSize = 15.sp, fontWeight = FontWeight.Bold, maxLines = 1,
                color = if (primary) Color.Black else Color.White,
            )
        },
        onClick = onClick,
        colors = if (primary) ChipDefaults.primaryChipColors() else ChipDefaults.secondaryChipColors(),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun ActionChip(
    label: String,
    secondary: String? = null,
    icon: ImageVector? = null,
    primary: Boolean = false,
    color: Color? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Chip(
        label = { Text(label, maxLines = 1, color = color ?: Color.Unspecified) },
        secondaryLabel = secondary?.let { { Text(it, maxLines = 2, fontSize = 11.sp) } },
        icon = icon?.let { { Icon(it, null, tint = color ?: (if (primary) Color.Black else Accent)) } },
        onClick = onClick,
        enabled = enabled,
        colors = if (primary) ChipDefaults.primaryChipColors() else ChipDefaults.secondaryChipColors(),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun ColorDot(color: Color, size: Int = 10) {
    Box(Modifier.size(size.dp).background(color, CircleShape))
}

/** Uruchamia systemową klawiaturę/dyktowanie Wear OS. */
@Composable
fun rememberTextInput(label: String, onResult: (String) -> Unit): () -> Unit {
    val latest = rememberUpdatedState(onResult)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent = result.data ?: return@rememberLauncherForActivityResult
        val text = RemoteInput.getResultsFromIntent(data)?.getCharSequence(TEXT_KEY)?.toString()
        if (!text.isNullOrBlank()) latest.value(text.trim().take(40))
    }
    return remember(label) {
        {
            val intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
            RemoteInputIntentHelper.putRemoteInputsExtra(
                intent, listOf(RemoteInput.Builder(TEXT_KEY).setLabel(label).build()),
            )
            launcher.launch(intent)
        }
    }
}

private const val TEXT_KEY = "text"
