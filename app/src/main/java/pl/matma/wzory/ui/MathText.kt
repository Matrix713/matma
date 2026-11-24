package pl.matma.wzory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import pl.matma.wzory.math.MBigOp
import pl.matma.wzory.math.MBinom
import pl.matma.wzory.math.MFrac
import pl.matma.wzory.math.MNode
import pl.matma.wzory.math.MOver
import pl.matma.wzory.math.MRoot
import pl.matma.wzory.math.MSub
import pl.matma.wzory.math.MSup
import pl.matma.wzory.math.MSys
import pl.matma.wzory.math.MText
import pl.matma.wzory.math.MVec
import pl.matma.wzory.math.MathMarkup

/**
 * Rysuje wzór zapisany w MathMarkup: piętrowe ułamki, pierwiastki z kreską, wykładniki, wektory.
 * Długie wzory zawijają się w miejscach spacji.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MathFormula(
    src: String,
    modifier: Modifier = Modifier,
    size: TextUnit = 16.sp,
    color: Color = Color.White,
    centered: Boolean = true,
) {
    val lines = remember(src) { runCatching { MathMarkup.layout(src) }.getOrNull() }
    if (lines == null) {
        Text(src, modifier = modifier, fontSize = size, color = color)
        return
    }
    val gap = with(LocalDensity.current) { (size * 0.3f).toDp() }
    val arrangement = if (centered) Alignment.CenterHorizontally else Alignment.Start
    Column(
        modifier,
        horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        lines.forEach { segments ->
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(gap * 2.5f, arrangement),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                segments.forEach { words ->
                    FlowRow(
                        Modifier.align(Alignment.CenterVertically),
                        horizontalArrangement = Arrangement.spacedBy(gap, arrangement),
                        verticalArrangement = Arrangement.spacedBy(3.dp),
                    ) {
                        words.forEach { word ->
                            Row(Modifier.align(Alignment.CenterVertically), verticalAlignment = Alignment.CenterVertically) {
                                word.forEach { MathNode(it, size, color) }
                            }
                        }
                    }
                }
            }
        }
    }
}

/** Rozmiar czcionki dobrany do długości najdłuższej linii wzoru. */
fun autoSize(src: String): TextUnit {
    val longest = src.split('\n').maxOf { MathMarkup.plain(it).length }
    return when {
        longest <= 26 -> 16.sp
        longest <= 40 -> 15.sp
        else -> 14.sp
    }
}

/** Wzór w ciemnej ramce (jak w zeszycie / na tablicy). */
@Composable
fun FormulaBox(src: String, size: TextUnit = autoSize(src), color: Color = Color.White) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(FormulaBg)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        MathFormula(src, Modifier.fillMaxWidth(), size, color)
    }
}

@Composable
private fun Nodes(nodes: List<MNode>, size: TextUnit, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        nodes.forEach { MathNode(it, size, color) }
    }
}

@Composable
private fun Plain(text: String, size: TextUnit, color: Color, weight: FontWeight? = null) {
    Text(text, fontSize = size, color = color, maxLines = 1, softWrap = false, fontWeight = weight)
}

private fun smaller(size: TextUnit, factor: Float): TextUnit = (size.value * factor).coerceAtLeast(8f).sp

@Composable
private fun TextUnit.dp(): Dp = with(LocalDensity.current) { this@dp.toDp() }

@Composable
private fun MathNode(node: MNode, size: TextUnit, color: Color) {
    when (node) {
        is MText -> Plain(node.text, size, color)

        is MFrac -> {
            val inner = smaller(size, 0.86f)
            Column(
                Modifier.width(IntrinsicSize.Max).padding(horizontal = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Nodes(node.num, inner, color)
                Box(Modifier.fillMaxWidth().padding(vertical = 1.dp).height(1.dp).background(color))
                Nodes(node.den, inner, color)
            }
        }

        is MRoot -> {
            val radical = size.dp() * 0.6f
            Row(verticalAlignment = Alignment.CenterVertically) {
                node.index?.let { idx ->
                    Box(Modifier.padding(bottom = size.dp() * 0.55f)) { Nodes(idx, smaller(size, 0.55f), color) }
                }
                Box(
                    Modifier
                        .drawBehind {
                            val stroke = 1.3.dp.toPx()
                            val w = radical.toPx()
                            val h = this.size.height
                            val path = Path().apply {
                                moveTo(0f, h * 0.62f)
                                lineTo(w * 0.28f, h * 0.52f)
                                lineTo(w * 0.58f, h - stroke)
                                lineTo(w * 0.95f, stroke / 2)
                                lineTo(this@drawBehind.size.width, stroke / 2)
                            }
                            drawPath(path, color, style = Stroke(width = stroke, join = StrokeJoin.Round))
                        }
                        .padding(start = radical + 1.dp, top = 3.dp, end = 1.dp),
                ) { Nodes(node.body, size, color) }
            }
        }

        is MSup -> Box(Modifier.padding(bottom = size.dp() * 0.5f)) { Nodes(node.body, smaller(size, 0.68f), color) }

        is MSub -> Box(Modifier.padding(top = size.dp() * 0.42f)) { Nodes(node.body, smaller(size, 0.68f), color) }

        is MVec, is MOver -> {
            val arrow = node is MVec
            val body = if (node is MVec) node.body else (node as MOver).body
            Column(Modifier.width(IntrinsicSize.Max), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier.fillMaxWidth().height(5.dp).drawBehind {
                        val stroke = 1.2.dp.toPx()
                        val y = this.size.height / 2
                        val w = this.size.width
                        drawLine(color, Offset(1f, y), Offset(w - 1f, y), stroke)
                        if (arrow) {
                            val head = 3.dp.toPx()
                            drawLine(color, Offset(w - 1f, y), Offset(w - 1f - head, y - head * 0.8f), stroke)
                            drawLine(color, Offset(w - 1f, y), Offset(w - 1f - head, y + head * 0.8f), stroke)
                        }
                    },
                )
                Nodes(body, size, color)
            }
        }

        is MBigOp -> {
            val isLim = node.symbol == "lim"
            Column(
                Modifier.padding(end = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                node.above?.let { Nodes(it, smaller(size, 0.6f), color) }
                Plain(node.symbol, if (isLim) size else smaller(size, 1.45f), color)
                node.below?.let { Nodes(it, smaller(size, 0.6f), color) }
            }
        }

        is MBinom -> Row(verticalAlignment = Alignment.CenterVertically) {
            Plain("(", smaller(size, 1.9f), color, FontWeight.Light)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Nodes(node.n, smaller(size, 0.9f), color)
                Nodes(node.k, smaller(size, 0.9f), color)
            }
            Plain(")", smaller(size, 1.9f), color, FontWeight.Light)
        }

        is MSys -> Row(verticalAlignment = Alignment.CenterVertically) {
            val rows = node.rows.size
            Plain("{", smaller(size, 1f + 0.95f * (rows - 1)), color, FontWeight.Light)
            Column(Modifier.padding(start = 2.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                node.rows.forEach { Nodes(it, smaller(size, 0.92f), color) }
            }
        }
    }
}
