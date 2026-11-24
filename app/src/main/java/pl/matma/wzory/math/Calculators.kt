package pl.matma.wzory.math

import pl.matma.wzory.math.Num.MINUS
import pl.matma.wzory.math.Num.deg
import pl.matma.wzory.math.Num.dec
import pl.matma.wzory.math.Num.point
import pl.matma.wzory.math.Num.short
import pl.matma.wzory.math.Num.show
import java.math.BigInteger
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

data class Field(val key: String, val label: String, val default: Double)

/** Wiersz wyniku. [strong] – wynik główny (wyróżniony kolorem). */
data class Out(val label: String, val value: String, val strong: Boolean = false)

class CalcError(message: String) : Exception(message)

class Values(private val map: Map<String, Double>) {
    operator fun get(key: String): Double = map[key] ?: error("Brak pola $key")
}

class Calculator(
    val id: String,
    val group: String,
    val title: String,
    val subtitle: String,
    /** Wzór/postać, której dotyczą pola (zapis MathMarkup). */
    val form: String?,
    val fields: List<Field>,
    val compute: Values.() -> List<Out>,
) {
    fun run(values: Map<String, Double>): Result<List<Out>> = runCatching { Values(values).compute() }
    fun defaults(): Map<String, Double> = fields.associate { it.key to it.default }
}

private const val EPS = 1e-12

private fun zero(x: Double) = abs(x) < 1e-12
private fun rad2deg(r: Double) = r * 180 / PI
private fun fail(msg: String): Nothing = throw CalcError(msg)

private class OutBuilder {
    val rows = mutableListOf<Out>()
    fun row(label: String, value: String, strong: Boolean = false) {
        rows += Out(label, value, strong)
    }
}

private fun outputs(block: OutBuilder.() -> Unit): List<Out> = OutBuilder().apply(block).rows

/** Kąt między wektorami w stopniach (0–180). */
private fun angleBetween(ux: Double, uy: Double, vx: Double, vy: Double): Double {
    val c = (ux * vx + uy * vy) / (hypot(ux, uy) * hypot(vx, vy))
    return rad2deg(acos(c.coerceIn(-1.0, 1.0)))
}

/** Dokładny pierwiastek równania kwadratowego o całkowitych współczynnikach, np. „(3 − 2√2)/7”. */
fun quadRootExact(a: Long, b: Long, delta: Long, plus: Boolean): String? {
    if (delta < 0 || a == 0L) return null
    val (k0, m) = Num.sqrtSplit(delta)
    if (m == 1L) {
        val num = -b + (if (plus) k0 else -k0)
        val g = Num.gcd(num, 2 * a)
        var p = num / g
        var q = 2 * a / g
        if (q < 0) { p = -p; q = -q }
        return Num.frac(p, q)
    }
    var g = Num.gcd(Num.gcd(abs(b), k0), abs(2 * a))
    if (g == 0L) g = 1
    var n0 = -b / g
    var k = k0 / g
    var d = 2 * a / g
    var plusSign = plus
    if (d < 0) { n0 = -n0; d = -d; plusSign = !plusSign }
    val rootPart = (if (k == 1L) "" else "$k") + "√$m"
    val numerator = when {
        n0 == 0L -> (if (plusSign) "" else MINUS) + rootPart
        else -> Num.int(n0) + (if (plusSign) " + " else " $MINUS ") + rootPart
    }
    return when {
        d == 1L -> numerator
        n0 == 0L -> "$numerator/$d"
        else -> "($numerator)/$d"
    }
}

private fun intOrNull(x: Double): Long? = if (Num.isInt(x)) round(x).toLong() else null

/** Wartość z postacią dokładną, gdy da się ją wyznaczyć. */
private fun withExact(exact: String?, x: Double) =
    if (exact == null || exact == dec(x)) show(x) else "$exact ≈ ${dec(x)}"

object Calculators {
    const val G_ANALYTIC = "Geometria analityczna"
    const val G_FUNCTIONS = "Funkcje i równania"
    const val G_OTHER = "Trójkąty, ciągi i inne"

    val all: List<Calculator> = listOf(
        Calculator(
            "punkt-prosta", G_ANALYTIC, "Odległość punktu od prostej",
            "rzut, punkt symetryczny, ∥ i ⟂", "Ax + By + C = 0,  P(x₀; y₀)",
            listOf(Field("A", "A", 3.0), Field("B", "B", 4.0), Field("C", "C", -5.0), Field("x0", "x₀", 2.0), Field("y0", "y₀", 3.0)),
        ) {
            val a = this["A"]; val b = this["B"]; val c = this["C"]; val x0 = this["x0"]; val y0 = this["y0"]
            if (zero(a) && zero(b)) fail("A i B nie mogą być jednocześnie równe 0")
            val num = a * x0 + b * y0 + c
            val den2 = a * a + b * b
            val d = abs(num) / sqrt(den2)
            val t = num / den2
            outputs {
                row("Prosta", Num.lineGeneral(a, b, c))
                row("Punkt P", point(x0, y0))
                row("|Ax₀ + By₀ + C|", short(abs(num)))
                row("√(A² + B²)", show(sqrt(den2)))
                row("Odległość d", show(d), strong = true)
                if (zero(num)) row("Położenie", "P leży na prostej")
                row("Rzut P na prostą (spodek)", point(x0 - a * t, y0 - b * t))
                row("Punkt symetryczny do P", point(x0 - 2 * a * t, y0 - 2 * b * t))
                row("Prosta ∥ przez P", Num.lineGeneral(a, b, -(a * x0 + b * y0)))
                row("Prosta ⟂ przez P", Num.lineGeneral(b, -a, a * y0 - b * x0))
            }
        },
        Calculator(
            "prosta-2pkt", G_ANALYTIC, "Prosta przez 2 punkty",
            "równanie, |AB|, środek, symetralna", "A(x₁; y₁),  B(x₂; y₂)",
            listOf(Field("x1", "x₁", 1.0), Field("y1", "y₁", 2.0), Field("x2", "x₂", 4.0), Field("y2", "y₂", 8.0)),
        ) {
            val x1 = this["x1"]; val y1 = this["y1"]; val x2 = this["x2"]; val y2 = this["y2"]
            val dx = x2 - x1; val dy = y2 - y1
            if (zero(dx) && zero(dy)) fail("Punkty muszą być różne")
            outputs {
                if (zero(dx)) {
                    row("Postać kierunkowa", "brak – prosta pionowa")
                    row("Równanie", "x = ${short(x1)}", strong = true)
                } else {
                    val a = dy / dx
                    val b = y1 - a * x1
                    row("Postać kierunkowa", Num.lineSlope(a, b), strong = true)
                    row("Współczynnik a", show(a))
                    row("Kąt nachylenia α", deg(rad2deg(atan(a)).let { if (it < 0) it + 180 else it }))
                    row("Przecięcie z OY", point(0.0, b))
                    if (!zero(a)) row("Miejsce zerowe", point(-b / a, 0.0))
                }
                row("Postać ogólna", Num.lineGeneral(dy, -dx, dx * y1 - dy * x1), strong = zero(dx))
                row("Wektor AB", "[${short(dx)}; ${short(dy)}]")
                row("Długość |AB|", show(hypot(dx, dy)))
                row("Środek odcinka", point((x1 + x2) / 2, (y1 + y2) / 2))
                row("Symetralna AB", Num.lineGeneral(dx, dy, -(x2 * x2 - x1 * x1 + y2 * y2 - y1 * y1) / 2))
            }
        },
        Calculator(
            "postacie-prostej", G_ANALYTIC, "Postacie prostej",
            "ogólna → kierunkowa, odcinkowa, wektory", "Ax + By + C = 0",
            listOf(Field("A", "A", 2.0), Field("B", "B", -3.0), Field("C", "C", 6.0)),
        ) {
            val a = this["A"]; val b = this["B"]; val c = this["C"]
            if (zero(a) && zero(b)) fail("A i B nie mogą być jednocześnie równe 0")
            outputs {
                row("Postać ogólna", Num.lineGeneral(a, b, c))
                if (zero(b)) {
                    row("Postać kierunkowa", "brak – prosta pionowa x = ${short(-c / a)}", strong = true)
                } else {
                    val k = -a / b
                    val m = -c / b
                    row("Postać kierunkowa", Num.lineSlope(k, m), strong = true)
                    row("Współczynnik kierunkowy a", show(k))
                    row("Kąt nachylenia α", deg(rad2deg(atan(k)).let { if (it < 0) it + 180 else it }))
                }
                if (!zero(a) && !zero(b) && !zero(c)) {
                    row("Postać odcinkowa", "x/${den(-c / a)} + y/${den(-c / b)} = 1")
                }
                if (!zero(a)) row("Przecięcie z OX", point(-c / a, 0.0))
                if (!zero(b)) row("Przecięcie z OY", point(0.0, -c / b))
                row("Wektor normalny n", "[${short(a)}; ${short(b)}]")
                row("Wektor kierunkowy u", "[${short(-b)}; ${short(a)}]")
                row("Odległość od (0; 0)", show(abs(c) / hypot(a, b)))
            }
        },
        Calculator(
            "dwie-proste", G_ANALYTIC, "Dwie proste",
            "przecięcie, kąt, ∥, ⟂, odległość", "A₁x + B₁y + C₁ = 0\nA₂x + B₂y + C₂ = 0",
            listOf(
                Field("A1", "A₁", 1.0), Field("B1", "B₁", -1.0), Field("C1", "C₁", 1.0),
                Field("A2", "A₂", 2.0), Field("B2", "B₂", 1.0), Field("C2", "C₂", -4.0),
            ),
        ) {
            val a1 = this["A1"]; val b1 = this["B1"]; val c1 = this["C1"]
            val a2 = this["A2"]; val b2 = this["B2"]; val c2 = this["C2"]
            if ((zero(a1) && zero(b1)) || (zero(a2) && zero(b2))) fail("W każdej prostej A i B nie mogą być oba 0")
            val w = a1 * b2 - a2 * b1
            outputs {
                row("Prosta k", Num.lineGeneral(a1, b1, c1))
                row("Prosta l", Num.lineGeneral(a2, b2, c2))
                row("W = A₁B₂ − A₂B₁", short(w))
                if (!zero(w)) {
                    val x = (b1 * c2 - b2 * c1) / w
                    val y = (a2 * c1 - a1 * c2) / w
                    val perp = zero(a1 * a2 + b1 * b2)
                    row("Położenie", if (perp) "przecinają się pod kątem prostym (⟂)" else "przecinają się")
                    row("Punkt przecięcia", point(x, y), strong = true)
                    row("Kąt między prostymi", deg(rad2deg(acos((abs(a1 * a2 + b1 * b2) / (hypot(a1, b1) * hypot(a2, b2))).coerceIn(-1.0, 1.0)))))
                } else {
                    val k = if (!zero(a2)) a1 / a2 else b1 / b2
                    if (zero(c1 - k * c2)) {
                        row("Położenie", "pokrywają się", strong = true)
                    } else {
                        row("Położenie", "równoległe (∥)", strong = true)
                        row("Odległość prostych", show(abs(c1 - k * c2) / hypot(a1, b1)))
                    }
                }
            }
        },
        Calculator(
            "prosta-kierunkowa", G_ANALYTIC, "Proste ∥ i ⟂ przez punkt",
            "dla y = ax + b", "y = ax + b,  P(x₀; y₀)",
            listOf(Field("a", "a", 2.0), Field("b", "b", 1.0), Field("x0", "x₀", 3.0), Field("y0", "y₀", -1.0)),
        ) {
            val a = this["a"]; val b = this["b"]; val x0 = this["x0"]; val y0 = this["y0"]
            outputs {
                row("Prosta", Num.lineSlope(a, b))
                row("Równoległa przez P", Num.lineSlope(a, y0 - a * x0), strong = true)
                if (zero(a)) row("Prostopadła przez P", "x = ${short(x0)}", strong = true)
                else row("Prostopadła przez P", Num.lineSlope(-1 / a, y0 + x0 / a), strong = true)
                row("a prostopadłej", if (zero(a)) "brak (pionowa)" else show(-1 / a))
                val d = abs(a * x0 - y0 + b) / sqrt(a * a + 1)
                row("Odległość P od prostej", show(d))
                row("Wartość w x₀", "f(${short(x0)}) = ${short(a * x0 + b)}")
                row("Czy P leży na prostej?", if (zero(a * x0 + b - y0)) "tak" else "nie")
            }
        },
        Calculator(
            "trojkat-3pkt", G_ANALYTIC, "Trójkąt z 3 punktów",
            "pole, boki, kąty, okręgi, środki", "A(x₁; y₁), B(x₂; y₂), C(x₃; y₃)",
            listOf(
                Field("x1", "x₁", 0.0), Field("y1", "y₁", 0.0), Field("x2", "x₂", 4.0),
                Field("y2", "y₂", 0.0), Field("x3", "x₃", 0.0), Field("y3", "y₃", 3.0),
            ),
        ) {
            val x1 = this["x1"]; val y1 = this["y1"]; val x2 = this["x2"]; val y2 = this["y2"]; val x3 = this["x3"]; val y3 = this["y3"]
            val det = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)
            if (zero(det)) fail("Punkty są współliniowe – to nie jest trójkąt")
            val a = hypot(x3 - x2, y3 - y2)
            val b = hypot(x3 - x1, y3 - y1)
            val c = hypot(x2 - x1, y2 - y1)
            val p = abs(det) / 2
            val alpha = angleBetween(x2 - x1, y2 - y1, x3 - x1, y3 - y1)
            val beta = angleBetween(x1 - x2, y1 - y2, x3 - x2, y3 - y2)
            val gamma = 180 - alpha - beta
            val d = 2 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))
            val s1 = x1 * x1 + y1 * y1; val s2 = x2 * x2 + y2 * y2; val s3 = x3 * x3 + y3 * y3
            val ux = (s1 * (y2 - y3) + s2 * (y3 - y1) + s3 * (y1 - y2)) / d
            val uy = (s1 * (x3 - x2) + s2 * (x1 - x3) + s3 * (x2 - x1)) / d
            val per = a + b + c
            val maxAngle = maxOf(alpha, beta, gamma)
            outputs {
                row("Pole P = ½|det(AB, AC)|", show(p), strong = true)
                row("Bok a = |BC|", show(a))
                row("Bok b = |AC|", show(b))
                row("Bok c = |AB|", show(c))
                row("Obwód", show(per))
                row("Kąty α, β, γ", "${deg(alpha)}, ${deg(beta)}, ${deg(gamma)}")
                row(
                    "Rodzaj",
                    when {
                        abs(maxAngle - 90) < 1e-7 -> "prostokątny"
                        maxAngle > 90 -> "rozwartokątny"
                        else -> "ostrokątny"
                    } + when {
                        abs(a - b) < 1e-9 && abs(b - c) < 1e-9 -> ", równoboczny"
                        abs(a - b) < 1e-9 || abs(b - c) < 1e-9 || abs(a - c) < 1e-9 -> ", równoramienny"
                        else -> ""
                    },
                )
                row("Środek ciężkości S", point((x1 + x2 + x3) / 3, (y1 + y2 + y3) / 3))
                row("Środek okręgu opisanego", point(ux, uy))
                row("Promień R", show(a * b * c / (4 * p)))
                row("Środek okręgu wpisanego", point((a * x1 + b * x2 + c * x3) / per, (a * y1 + b * y2 + c * y3) / per))
                row("Promień r", show(2 * p / per))
                row("Ortocentrum H", point(x1 + x2 + x3 - 2 * ux, y1 + y2 + y3 - 2 * uy))
                row("Wysokości hₐ, h_b, h_c", "${dec(2 * p / a)}; ${dec(2 * p / b)}; ${dec(2 * p / c)}")
            }
        },
        Calculator(
            "okrag-ogolny", G_ANALYTIC, "Okrąg z równania ogólnego",
            "środek i promień", "x² + y² + Dx + Ey + F = 0",
            listOf(Field("D", "D", -4.0), Field("E", "E", 6.0), Field("F", "F", -12.0)),
        ) {
            val a = -this["D"] / 2; val b = -this["E"] / 2
            val r2 = a * a + b * b - this["F"]
            outputs {
                row("Środek S = (−D/2; −E/2)", point(a, b))
                row("r² = a² + b² − F", short(r2))
                when {
                    r2 > EPS -> {
                        row("Promień r", show(sqrt(r2)), strong = true)
                        row("Postać kanoniczna", "(x ${sgnShift(a)})² + (y ${sgnShift(b)})² = ${short(r2)}")
                        row("Obwód 2πr", show(2 * PI * sqrt(r2)))
                        row("Pole πr²", "${short(r2)}π ≈ ${dec(PI * r2)}")
                    }
                    abs(r2) <= EPS -> row("Wynik", "to tylko punkt S (r = 0)", strong = true)
                    else -> row("Wynik", "r² < 0 – to nie jest okrąg", strong = true)
                }
            }
        },
        Calculator(
            "okrag-prosta", G_ANALYTIC, "Okrąg i prosta",
            "położenie, punkty wspólne, cięciwa", "(x − a)² + (y − b)² = r²\nAx + By + C = 0",
            listOf(
                Field("a", "a", 1.0), Field("b", "b", 2.0), Field("r", "r", 5.0),
                Field("A", "A", 1.0), Field("B", "B", 1.0), Field("C", "C", -3.0),
            ),
        ) {
            val sa = this["a"]; val sb = this["b"]; val r = this["r"]; val la = this["A"]; val lb = this["B"]; val lc = this["C"]
            if (r <= 0) fail("Promień musi być dodatni")
            if (zero(la) && zero(lb)) fail("A i B nie mogą być jednocześnie równe 0")
            val n2 = la * la + lb * lb
            val num = la * sa + lb * sb + lc
            val d = abs(num) / sqrt(n2)
            val t = num / n2
            val fx = sa - la * t; val fy = sb - lb * t
            outputs {
                row("Odległość środka od prostej d", show(d))
                when {
                    abs(d - r) < 1e-9 -> {
                        row("Położenie", "prosta styczna (d = r)", strong = true)
                        row("Punkt styczności", point(fx, fy), strong = true)
                    }
                    d > r -> row("Położenie", "prosta rozłączna (d > r)", strong = true)
                    else -> {
                        val h = sqrt(r * r - d * d)
                        val ux = -lb / sqrt(n2); val uy = la / sqrt(n2)
                        row("Położenie", "prosta sieczna (d < r)", strong = true)
                        row("Punkt wspólny 1", point(fx + h * ux, fy + h * uy), strong = true)
                        row("Punkt wspólny 2", point(fx - h * ux, fy - h * uy), strong = true)
                        row("Długość cięciwy 2√(r² − d²)", show(2 * h))
                    }
                }
                row("Spodek prostopadłej ze środka", point(fx, fy))
            }
        },
        Calculator(
            "dwa-okregi", G_ANALYTIC, "Dwa okręgi",
            "wzajemne położenie, punkty wspólne", "S₁(a₁; b₁), r₁   S₂(a₂; b₂), r₂",
            listOf(
                Field("a1", "a₁", 0.0), Field("b1", "b₁", 0.0), Field("r1", "r₁", 5.0),
                Field("a2", "a₂", 6.0), Field("b2", "b₂", 0.0), Field("r2", "r₂", 5.0),
            ),
        ) {
            val a1 = this["a1"]; val b1 = this["b1"]; val r1 = this["r1"]; val a2 = this["a2"]; val b2 = this["b2"]; val r2 = this["r2"]
            if (r1 <= 0 || r2 <= 0) fail("Promienie muszą być dodatnie")
            val d = hypot(a2 - a1, b2 - b1)
            val sum = r1 + r2
            val diff = abs(r1 - r2)
            fun eq(x: Double, y: Double) = abs(x - y) < 1e-9
            outputs {
                row("|S₁S₂|", show(d))
                row("r₁ + r₂  ·  |r₁ − r₂|", "${short(sum)}  ·  ${short(diff)}")
                val pos = when {
                    zero(d) && eq(r1, r2) -> "okręgi pokrywają się"
                    zero(d) -> "współśrodkowe (rozłączne wewnętrznie)"
                    d > sum + 1e-9 -> "rozłączne zewnętrznie (d > r₁ + r₂)"
                    eq(d, sum) -> "styczne zewnętrznie (d = r₁ + r₂)"
                    d < diff - 1e-9 -> "rozłączne wewnętrznie (d < |r₁ − r₂|)"
                    eq(d, diff) -> "styczne wewnętrznie (d = |r₁ − r₂|)"
                    else -> "przecinają się w 2 punktach"
                }
                row("Położenie", pos, strong = true)
                if (!zero(d) && d <= sum + 1e-9 && d >= diff - 1e-9) {
                    val l = (r1 * r1 - r2 * r2 + d * d) / (2 * d)
                    val h = Num.sqrtSafe(r1 * r1 - l * l)
                    val ex = (a2 - a1) / d; val ey = (b2 - b1) / d
                    val mx = a1 + l * ex; val my = b1 + l * ey
                    if (h < 1e-9) {
                        row("Punkt styczności", point(mx, my), strong = true)
                    } else {
                        row("Punkt wspólny 1", point(mx - h * ey, my + h * ex), strong = true)
                        row("Punkt wspólny 2", point(mx + h * ey, my - h * ex), strong = true)
                        row("Długość wspólnej cięciwy", show(2 * h))
                    }
                }
            }
        },
        Calculator(
            "wektory", G_ANALYTIC, "Wektory",
            "długość, iloczyn skalarny, kąt", "u = [u₁; u₂],  v = [v₁; v₂]",
            listOf(Field("ux", "u₁", 3.0), Field("uy", "u₂", 4.0), Field("vx", "v₁", 4.0), Field("vy", "v₂", -3.0)),
        ) {
            val ux = this["ux"]; val uy = this["uy"]; val vx = this["vx"]; val vy = this["vy"]
            val dot = ux * vx + uy * vy
            val det = ux * vy - uy * vx
            val lu = hypot(ux, uy); val lv = hypot(vx, vy)
            outputs {
                row("|u|", show(lu))
                row("|v|", show(lv))
                row("u + v", "[${short(ux + vx)}; ${short(uy + vy)}]")
                row("u − v", "[${short(ux - vx)}; ${short(uy - vy)}]")
                row("Iloczyn skalarny u∘v", short(dot), strong = true)
                row("Wyznacznik det(u, v)", short(det))
                if (!zero(lu) && !zero(lv)) {
                    row("cos∠(u, v)", show(dot / (lu * lv)))
                    row("Kąt między wektorami", deg(angleBetween(ux, uy, vx, vy)), strong = true)
                }
                row("Prostopadłe? (u∘v = 0)", if (zero(dot)) "tak" else "nie")
                row("Równoległe? (det = 0)", if (zero(det)) "tak" else "nie")
                row("Pole równoległoboku |det|", show(abs(det)))
                row("Pole trójkąta ½|det|", show(abs(det) / 2))
                if (!zero(lu)) row("Wersor u", "[${dec(ux / lu)}; ${dec(uy / lu)}]")
            }
        },

        // ---------------- Funkcje i równania ----------------
        Calculator(
            "kwadratowa", G_FUNCTIONS, "Funkcja kwadratowa",
            "Δ, pierwiastki, wierzchołek, postacie", "f(x) = ax² + bx + c",
            listOf(Field("a", "a", 1.0), Field("b", "b", -2.0), Field("c", "c", -3.0)),
        ) {
            val a = this["a"]; val b = this["b"]; val c = this["c"]
            if (zero(a)) fail("a ≠ 0 (inaczej to funkcja liniowa)")
            val delta = b * b - 4 * a * c
            val p = -b / (2 * a)
            val q = -delta / (4 * a)
            val ia = intOrNull(a); val ib = intOrNull(b); val id = intOrNull(delta)
            outputs {
                row("f(x)", "f(x) = " + Num.terms(a to "x²", b to "x", const = c))
                row("Δ = b² − 4ac", short(delta), strong = true)
                when {
                    delta > EPS -> {
                        val sd = sqrt(delta)
                        row("√Δ", show(sd))
                        val ex1 = if (ia != null && ib != null && id != null) quadRootExact(ia, ib, id, plus = false) else null
                        val ex2 = if (ia != null && ib != null && id != null) quadRootExact(ia, ib, id, plus = true) else null
                        val x1 = (-b - sd) / (2 * a); val x2 = (-b + sd) / (2 * a)
                        row("x₁ = (−b − √Δ)/2a", withExact(ex1, x1), strong = true)
                        row("x₂ = (−b + √Δ)/2a", withExact(ex2, x2), strong = true)
                        row("Postać iloczynowa", "f(x) = ${coefPrefix(a)}(x ${sgnShift(x1)})(x ${sgnShift(x2)})")
                    }
                    abs(delta) <= EPS -> {
                        row("x₀ = −b/2a (podwójny)", show(p), strong = true)
                        row("Postać iloczynowa", "f(x) = ${coefPrefix(a)}(x ${sgnShift(p)})²")
                    }
                    else -> row("Pierwiastki", "brak (Δ < 0)", strong = true)
                }
                row("Wierzchołek W(p; q)", point(p, q), strong = true)
                row("Postać kanoniczna", "f(x) = ${coefPrefix(a)}(x ${sgnShift(p)})²" + if (zero(q)) "" else " ${signed(q)}")
                row("Oś symetrii", "x = ${short(p)}")
                row("Zbiór wartości", if (a > 0) "⟨${short(q)}; +∞)" else "(−∞; ${short(q)}⟩")
                row(
                    "Monotoniczność",
                    if (a > 0) "malejąca w (−∞; ${short(p)}⟩, rosnąca w ⟨${short(p)}; +∞)"
                    else "rosnąca w (−∞; ${short(p)}⟩, malejąca w ⟨${short(p)}; +∞)",
                )
                if (delta >= -EPS) {
                    row("x₁ + x₂ = −b/a", short(-b / a))
                    row("x₁ · x₂ = c/a", short(c / a))
                }
                row("Przecięcie z OY", point(0.0, c))
                row("Ramiona", if (a > 0) "w górę (a > 0)" else "w dół (a < 0)")
            }
        },
        Calculator(
            "liniowa", G_FUNCTIONS, "Funkcja liniowa",
            "miejsce zerowe, monotoniczność, kąt", "f(x) = ax + b",
            listOf(Field("a", "a", -2.0), Field("b", "b", 4.0)),
        ) {
            val a = this["a"]; val b = this["b"]
            outputs {
                row("f(x)", "f(x) = " + Num.terms(a to "x", const = b))
                if (zero(a)) {
                    row("Miejsce zerowe", if (zero(b)) "każdy x ∈ ℝ" else "brak", strong = true)
                    row("Monotoniczność", "stała")
                } else {
                    row("Miejsce zerowe x₀ = −b/a", show(-b / a), strong = true)
                    row("Monotoniczność", if (a > 0) "rosnąca (a > 0)" else "malejąca (a < 0)")
                    row("f(x) > 0 dla", if (a > 0) "x > ${short(-b / a)}" else "x < ${short(-b / a)}")
                }
                row("Przecięcie z OY", point(0.0, b))
                row("Kąt nachylenia (tg α = a)", deg(rad2deg(atan(a)).let { if (it < 0) it + 180 else it }))
                row("Prostopadła przez (0; 0)", if (zero(a)) "x = 0" else Num.lineSlope(-1 / a, 0.0))
            }
        },
        Calculator(
            "homograficzna", G_FUNCTIONS, "Funkcja homograficzna",
            "asymptoty, dziedzina, postać kanoniczna", "f(x) = @f{ax + b}{cx + d}",
            listOf(Field("a", "a", 2.0), Field("b", "b", 1.0), Field("c", "c", 1.0), Field("d", "d", -3.0)),
        ) {
            val a = this["a"]; val b = this["b"]; val c = this["c"]; val d = this["d"]
            if (zero(c)) fail("c ≠ 0 (inaczej to funkcja liniowa)")
            if (zero(a * d - b * c)) fail("ad − bc = 0 – funkcja jest stała (poza jednym punktem)")
            val p = -d / c; val q = a / c; val k = (b * c - a * d) / (c * c)
            outputs {
                row("Dziedzina", "ℝ ∖ {${short(p)}}")
                row("Asymptota pionowa", "x = ${short(p)}", strong = true)
                row("Asymptota pozioma", "y = ${short(q)}", strong = true)
                row("Zbiór wartości", "ℝ ∖ {${short(q)}}")
                row("Postać kanoniczna", "f(x) = ${short(k)}/(x ${sgnShift(p)})" + if (zero(q)) "" else " ${signed(q)}")
                row("Monotoniczność", if (k > 0) "malejąca w (−∞; ${short(p)}) i w (${short(p)}; +∞)" else "rosnąca w (−∞; ${short(p)}) i w (${short(p)}; +∞)")
                row("Miejsce zerowe", if (zero(a)) "brak" else short(-b / a))
                row("Przecięcie z OY", if (zero(d)) "brak" else point(0.0, b / d))
                row("Środek symetrii", point(p, q))
            }
        },
        Calculator(
            "cramer", G_FUNCTIONS, "Układ 2 równań (Cramer)",
            "wyznaczniki W, Wx, Wy", "@sys{a₁x + b₁y = c₁}{a₂x + b₂y = c₂}",
            listOf(
                Field("a1", "a₁", 2.0), Field("b1", "b₁", 3.0), Field("c1", "c₁", 7.0),
                Field("a2", "a₂", 1.0), Field("b2", "b₂", -1.0), Field("c2", "c₂", 1.0),
            ),
        ) {
            val a1 = this["a1"]; val b1 = this["b1"]; val c1 = this["c1"]; val a2 = this["a2"]; val b2 = this["b2"]; val c2 = this["c2"]
            val w = a1 * b2 - a2 * b1
            val wx = c1 * b2 - c2 * b1
            val wy = a1 * c2 - a2 * c1
            outputs {
                row("W = a₁b₂ − a₂b₁", short(w))
                row("Wx = c₁b₂ − c₂b₁", short(wx))
                row("Wy = a₁c₂ − a₂c₁", short(wy))
                when {
                    !zero(w) -> {
                        row("x = Wx/W", show(wx / w), strong = true)
                        row("y = Wy/W", show(wy / w), strong = true)
                        row("Typ", "oznaczony – jedno rozwiązanie")
                    }
                    zero(wx) && zero(wy) -> row("Typ", "nieoznaczony – nieskończenie wiele rozwiązań", strong = true)
                    else -> row("Typ", "sprzeczny – brak rozwiązań", strong = true)
                }
            }
        },
        Calculator(
            "logarytm", G_FUNCTIONS, "Logarytm i potęga",
            "logₐb, aᵇ, ⁿ√a", "log_{a} b,  a^{b}",
            listOf(Field("a", "a (podstawa)", 2.0), Field("b", "b", 8.0)),
        ) {
            val a = this["a"]; val b = this["b"]
            outputs {
                if (a > 0 && !zero(a - 1) && b > 0) {
                    row("logₐ b", show(kotlin.math.ln(b) / kotlin.math.ln(a)), strong = true)
                    row("log_b a", if (zero(b - 1)) "nie istnieje" else show(kotlin.math.ln(a) / kotlin.math.ln(b)))
                } else {
                    row("logₐ b", "nie istnieje (a > 0, a ≠ 1, b > 0)", strong = true)
                }
                if (b > 0) {
                    row("ln b", show(kotlin.math.ln(b)))
                    row("log b (lg)", show(kotlin.math.log10(b)))
                }
                val pw = a.pow(b)
                row("aᵇ", if (pw.isNaN()) "nie istnieje w ℝ" else show(pw))
                if (!zero(b)) {
                    val root = if (a < 0 && Num.isInt(b) && round(b).toLong() % 2L != 0L) -(-a).pow(1 / b) else a.pow(1 / b)
                    row("ᵇ√a", if (root.isNaN()) "nie istnieje w ℝ" else show(root))
                }
            }
        },
        Calculator(
            "trygonometria", G_FUNCTIONS, "Funkcje trygonometryczne",
            "sin, cos, tg, ctg kąta", "α (w stopniach)",
            listOf(Field("alpha", "α [°]", 60.0)),
        ) {
            val deg = this["alpha"]
            val r = deg * PI / 180
            val s = sin(r); val c = cos(r)
            fun clean(x: Double) = if (abs(x) < 1e-12) 0.0 else x
            outputs {
                row("α w radianach", radians(deg))
                row("sin α", show(clean(s)), strong = true)
                row("cos α", show(clean(c)), strong = true)
                row("tg α", if (abs(c) < 1e-12) "nie istnieje" else show(clean(tan(r))), strong = true)
                row("ctg α", if (abs(s) < 1e-12) "nie istnieje" else show(clean(c / s)), strong = true)
                val norm = ((deg % 360) + 360) % 360
                row(
                    "Ćwiartka",
                    when {
                        norm == 0.0 || norm == 90.0 || norm == 180.0 || norm == 270.0 -> "na osi"
                        norm < 90 -> "I (wszystkie +)"
                        norm < 180 -> "II (sin +)"
                        norm < 270 -> "III (tg, ctg +)"
                        else -> "IV (cos +)"
                    },
                )
            }
        },

        // ---------------- Trójkąty, ciągi i inne ----------------
        Calculator(
            "trojkat-boki", G_OTHER, "Trójkąt z 3 boków",
            "Heron, kąty, R, r, wysokości", "a, b, c",
            listOf(Field("a", "a", 5.0), Field("b", "b", 6.0), Field("c", "c", 7.0)),
        ) {
            val a = this["a"]; val b = this["b"]; val c = this["c"]
            if (a <= 0 || b <= 0 || c <= 0) fail("Boki muszą być dodatnie")
            if (a + b <= c || a + c <= b || b + c <= a) fail("Nie spełnia nierówności trójkąta")
            val p = (a + b + c) / 2
            val area = sqrt(p * (p - a) * (p - b) * (p - c))
            val alpha = rad2deg(acos(((b * b + c * c - a * a) / (2 * b * c)).coerceIn(-1.0, 1.0)))
            val beta = rad2deg(acos(((a * a + c * c - b * b) / (2 * a * c)).coerceIn(-1.0, 1.0)))
            val gamma = 180 - alpha - beta
            val maxA = maxOf(alpha, beta, gamma)
            outputs {
                row("Obwód", short(2 * p))
                row("Pole (wzór Herona)", show(area), strong = true)
                row("Kąty α, β, γ", "${deg(alpha)}, ${deg(beta)}, ${deg(gamma)}")
                row("Rodzaj", when {
                    abs(maxA - 90) < 1e-7 -> "prostokątny"
                    maxA > 90 -> "rozwartokątny"
                    else -> "ostrokątny"
                })
                row("Promień okręgu opisanego R", show(a * b * c / (4 * area)))
                row("Promień okręgu wpisanego r", show(area / p))
                row("Wysokości hₐ, h_b, h_c", "${dec(2 * area / a)}; ${dec(2 * area / b)}; ${dec(2 * area / c)}")
                row("Środkowa mₐ", show(0.5 * sqrt(2 * b * b + 2 * c * c - a * a)))
            }
        },
        Calculator(
            "ciag-arytm", G_OTHER, "Ciąg arytmetyczny",
            "aₙ i Sₙ", "a₁, r, n",
            listOf(Field("a1", "a₁", 2.0), Field("r", "r", 3.0), Field("n", "n", 10.0)),
        ) {
            val a1 = this["a1"]; val r = this["r"]; val n = this["n"]
            if (n < 1 || !Num.isInt(n)) fail("n musi być liczbą naturalną ≥ 1")
            val an = a1 + (n - 1) * r
            outputs {
                row("aₙ = a₁ + (n − 1)r", show(an), strong = true)
                row("Sₙ = (a₁ + aₙ)/2 · n", show((a1 + an) / 2 * n), strong = true)
                row("aₙ₊₁", show(an + r))
                row("Monotoniczność", when { r > 0 -> "rosnący"; r < 0 -> "malejący"; else -> "stały" })
            }
        },
        Calculator(
            "ciag-geom", G_OTHER, "Ciąg geometryczny",
            "aₙ, Sₙ, suma szeregu", "a₁, q, n",
            listOf(Field("a1", "a₁", 3.0), Field("q", "q", 0.5), Field("n", "n", 6.0)),
        ) {
            val a1 = this["a1"]; val q = this["q"]; val n = this["n"]
            if (n < 1 || !Num.isInt(n)) fail("n musi być liczbą naturalną ≥ 1")
            val an = a1 * q.pow(n - 1)
            val sn = if (zero(q - 1)) a1 * n else a1 * (1 - q.pow(n)) / (1 - q)
            outputs {
                row("aₙ = a₁ · qⁿ⁻¹", show(an), strong = true)
                row("Sₙ", show(sn), strong = true)
                row("S = a₁/(1 − q)", if (abs(q) < 1) show(a1 / (1 - q)) else "szereg rozbieżny (|q| ≥ 1)")
                row("aₙ₊₁", show(an * q))
            }
        },
        Calculator(
            "kombinatoryka", G_OTHER, "Kombinatoryka",
            "n!, Cₙᵏ, Vₙᵏ, z powtórzeniami", "n, k",
            listOf(Field("n", "n", 10.0), Field("k", "k", 3.0)),
        ) {
            val nd = this["n"]; val kd = this["k"]
            if (!Num.isInt(nd) || !Num.isInt(kd) || nd < 0 || kd < 0) fail("n i k – liczby naturalne")
            if (nd > 500 || kd > 500) fail("Za duże (maks. 500)")
            val n = nd.toInt(); val k = kd.toInt()
            fun fact(m: Int): BigInteger = (2..m).fold(BigInteger.ONE) { acc, i -> acc * BigInteger.valueOf(i.toLong()) }
            fun falling(m: Int, t: Int): BigInteger = (0 until t).fold(BigInteger.ONE) { acc, i -> acc * BigInteger.valueOf((m - i).toLong()) }
            outputs {
                row("n! (permutacje)", Num.big(fact(n)))
                if (k <= n) {
                    row("Kombinacje C(n, k)", Num.big(falling(n, k) / fact(k)), strong = true)
                    row("Wariacje bez powt. n!/(n−k)!", Num.big(falling(n, k)), strong = true)
                } else {
                    row("C(n, k) i V(n, k)", "0 (k > n)")
                }
                row("Wariacje z powt. nᵏ", Num.big(BigInteger.valueOf(n.toLong()).pow(k)))
                if (n + k >= 1) row("Kombinacje z powt. C(n+k−1, k)", Num.big(falling(n + k - 1, k) / fact(k)))
                row("k!", Num.big(fact(k)))
            }
        },
        Calculator(
            "procent-skladany", G_OTHER, "Procent składany",
            "lokata z kapitalizacją", "K, p %, lata, kapitalizacje/rok",
            listOf(Field("K", "Kapitał K", 1000.0), Field("p", "p [%]", 5.0), Field("n", "Lata n", 3.0), Field("m", "Kapitalizacje/rok", 1.0)),
        ) {
            val k = this["K"]; val p = this["p"]; val n = this["n"]; val m = this["m"]
            if (m < 1) fail("Co najmniej 1 kapitalizacja w roku")
            val kn = k * (1 + p / 100 / m).pow(n * m)
            outputs {
                row("Kₙ = K(1 + p/100m)ⁿᵐ", dec(kn, 2), strong = true)
                row("Odsetki", dec(kn - k, 2))
                row("Wzrost", dec((kn / k - 1) * 100, 2) + " %")
                row("Oprocentowanie efektywne", dec(((1 + p / 100 / m).pow(m) - 1) * 100, 3) + " %")
            }
        },
        Calculator(
            "srednie", G_OTHER, "Średnie dwóch liczb",
            "arytmetyczna, geometryczna, harmoniczna", "a, b > 0",
            listOf(Field("a", "a", 4.0), Field("b", "b", 9.0)),
        ) {
            val a = this["a"]; val b = this["b"]
            if (a <= 0 || b <= 0) fail("Liczby muszą być dodatnie")
            outputs {
                row("Harmoniczna 2ab/(a+b)", show(2 * a * b / (a + b)))
                row("Geometryczna √(ab)", show(sqrt(a * b)))
                row("Arytmetyczna (a+b)/2", show((a + b) / 2), strong = true)
                row("Kwadratowa √((a²+b²)/2)", show(sqrt((a * a + b * b) / 2)))
                row("NWD · NWW", if (Num.isInt(a) && Num.isInt(b)) {
                    val g = Num.gcd(round(a).toLong(), round(b).toLong())
                    "$g · ${Num.lcm(round(a).toLong(), round(b).toLong())}"
                } else "tylko dla liczb całkowitych")
            }
        },
    )

    private val byId = all.associateBy { it.id }

    fun byId(id: String) = byId[id]

    val groups: List<String> = listOf(G_ANALYTIC, G_FUNCTIONS, G_OTHER)
}

/** „− 3” / „+ 2/3” – przesunięcie w nawiasie (x − p). */
private fun sgnShift(p: Double): String = if (p > 0 || zero(p)) "$MINUS ${short(abs(p))}" else "+ ${short(abs(p))}"

private fun signed(q: Double): String = if (q < 0) "$MINUS ${short(-q)}" else "+ ${short(q)}"

private fun coefPrefix(a: Double): String = when {
    Num.isInt(a) && round(a).toLong() == 1L -> ""
    Num.isInt(a) && round(a).toLong() == -1L -> MINUS
    else -> short(a)
}

private fun den(x: Double): String = if (x < 0) "(${short(x)})" else short(x)

/** Miara łukowa jako wielokrotność π, np. 60° → „π/3 ≈ 1,0472”. */
private fun radians(deg: Double): String {
    val r = deg * PI / 180
    val (p, q) = Num.ratio(deg / 180) ?: return dec(r)
    if (p == 0L) return "0"
    val coef = when (p) {
        1L -> ""
        -1L -> MINUS
        else -> Num.int(p)
    }
    return coef + "π" + (if (q == 1L) "" else "/$q") + " ≈ " + dec(r)
}
