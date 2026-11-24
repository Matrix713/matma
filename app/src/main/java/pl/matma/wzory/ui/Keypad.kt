package pl.matma.wzory.ui

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text

/** Żądanie wpisania liczby. [apply] zwraca komunikat błędu albo null. */
class NumberRequest(
    val title: String,
    val initial: String,
    val apply: (Double) -> String?,
)

/**
 * Logika pola liczby: minus przełącza znak, przecinek tylko raz, max 12 znaków.
 * Akceptuje także ułamek zwykły z „/” (np. 2/3).
 */
object NumberEditor {
    fun append(text: String, ch: Char): String = when {
        ch == '±' -> if (text.startsWith("-")) text.drop(1) else "-$text"
        ch == ',' -> {
            val part = text.substringAfterLast('/')
            when {
                part.contains(',') -> text
                part.isEmpty() || part == "-" -> text + "0,"
                else -> "$text,"
            }
        }
        ch == '/' -> if (text.contains('/') || text.isEmpty() || text == "-" || text.endsWith(",")) text else "$text/"
        text.length >= 12 -> text
        ch.isDigit() -> {
            val part = text.substringAfterLast('/')
            if (part == "0" || part == "-0") text.dropLast(1) + ch else text + ch
        }
        else -> text
    }

    fun parse(text: String): Double? {
        val t = text.replace(',', '.').trim()
        if (t.contains('/')) {
            val (a, b) = t.split('/', limit = 2)
            val p = a.toDoubleOrNull() ?: return null
            val q = b.toDoubleOrNull() ?: return null
            if (q == 0.0) return null
            return p / q
        }
        return t.toDoubleOrNull()
    }

    /** Liczba do edycji: 2.0 → „2”, -0.5 → „-0,5”. */
    fun format(x: Double): String {
        if (x == Math.rint(x) && kotlin.math.abs(x) < 1e12) return x.toLong().toString()
        return x.toBigDecimal().stripTrailingZeros().toPlainString().replace('.', ',')
    }
}

private data class Key(val label: String, val char: Char?, val kind: Kind) {
    enum class Kind { DIGIT, SYMBOL, BACK, OK }
}

/**
 * Klawiatura liczbowa pod okrągły ekran: 1–5 / 6–0 / ± , / ⌫ OK.
 * Pierwsza cyfra zastępuje poprzednią wartość, przytrzymanie ⌫ czyści.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NumberKeypad(request: NumberRequest, onDone: () -> Unit) {
    var text by remember { mutableStateOf(request.initial) }
    var fresh by remember { mutableStateOf(request.initial.isNotEmpty()) }
    var error by remember { mutableStateOf<String?>(null) }
    val view = LocalView.current

    fun tap() = view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)

    fun press(key: Key) {
        tap()
        error = null
        when (key.kind) {
            Key.Kind.DIGIT -> {
                text = NumberEditor.append(if (fresh) "" else text, key.char!!)
                fresh = false
            }
            Key.Kind.SYMBOL -> {
                val base = if (fresh && key.char == ',') "" else text
                text = NumberEditor.append(base, key.char!!)
                fresh = false
            }
            Key.Kind.BACK -> {
                text = text.dropLast(1)
                fresh = false
            }
            Key.Kind.OK -> {
                val value = NumberEditor.parse(text)
                if (value == null) {
                    error = "Niepoprawna liczba"
                    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                    return
                }
                val result = request.apply(value)
                if (result == null) onDone() else error = result
            }
        }
    }

    val digitsTop = "12345".map { Key(it.toString(), it, Key.Kind.DIGIT) }
    val digitsBottom = "67890".map { Key(it.toString(), it, Key.Kind.DIGIT) }
    val actions = listOf(
        Key("±", '±', Key.Kind.SYMBOL),
        Key(",", ',', Key.Kind.SYMBOL),
        Key("/", '/', Key.Kind.SYMBOL),
        Key("⌫", null, Key.Kind.BACK),
        Key("OK", null, Key.Kind.OK),
    )

    BoxWithConstraints(Modifier.fillMaxSize().background(Color.Black)) {
        val d = minOf(maxWidth, maxHeight)
        val keySize = d * 0.168f
        val pitch = d * 0.182f

        @Composable
        fun KeyButton(key: Key, cx: Dp, cy: Dp, size: Dp = keySize) {
            val bg = when (key.kind) {
                Key.Kind.OK -> Accent
                Key.Kind.BACK -> Color(0xFF4A3434)
                Key.Kind.SYMBOL -> Color(0xFF2C3E48)
                Key.Kind.DIGIT -> SurfaceDark
            }
            val fg = if (key.kind == Key.Kind.OK) Color.Black else Color.White
            Box(
                modifier = Modifier
                    .offset(cx - size / 2, cy - size / 2)
                    .size(size)
                    .clip(CircleShape)
                    .background(bg)
                    .combinedClickable(
                        onClick = { press(key) },
                        onLongClick = if (key.kind == Key.Kind.BACK) {
                            { tap(); text = ""; fresh = false; error = null }
                        } else null,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    key.label, color = fg, fontWeight = FontWeight.Bold,
                    fontSize = if (key.kind == Key.Kind.OK) 13.sp else 18.sp,
                )
            }
        }

        val shown = if (text.isEmpty()) request.title else text
        Column(
            modifier = Modifier.width(d * 0.76f).offset(d * 0.12f, d * 0.11f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (text.isNotEmpty()) Text(request.title, fontSize = 10.sp, color = Muted, maxLines = 1)
            Text(
                shown,
                fontSize = if (text.length > 9) 15.sp else 19.sp,
                color = when {
                    text.isEmpty() -> Muted
                    fresh -> Accent
                    else -> Color.White
                },
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                textAlign = TextAlign.Center,
            )
            val preview = error ?: NumberEditor.parse(text)?.takeIf { text.contains('/') }?.let { "= " + NumberEditor.format(it) }
            if (preview != null) {
                Text(preview, fontSize = 10.sp, maxLines = 1, color = if (error != null) Bad else Good)
            }
        }

        val center = d / 2
        val rowY = listOf(d * 0.43f, d * 0.615f, d * 0.80f)
        digitsTop.forEachIndexed { i, k -> KeyButton(k, center + pitch * (i - 2), rowY[0]) }
        digitsBottom.forEachIndexed { i, k -> KeyButton(k, center + pitch * (i - 2), rowY[1]) }
        // Dolny rząd: 5 mniejszych klawiszy po łuku (krzywizna ekranu).
        val small = keySize * 0.88f
        val actionPitch = d * 0.158f
        actions.forEachIndexed { i, k ->
            val off = i - 2
            val lift = when (off) {
                -2, 2 -> d * 0.045f
                -1, 1 -> d * 0.008f
                else -> d * 0f
            }
            KeyButton(k, center + actionPitch * off, rowY[2] - lift, small)
        }
    }
}
