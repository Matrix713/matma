package pl.matma.wzory.math

import java.math.BigInteger
import java.util.Locale
import kotlin.math.abs
import kotlin.math.round
import kotlin.math.sqrt

/**
 * Formatowanie wyników po polsku (przecinek dziesiętny) z rozpoznawaniem postaci dokładnej:
 * liczby całkowite, ułamki zwykłe (2/3) i niewymierności kwadratowe (7√13/13).
 */
object Num {
    private const val MAX_DEN = 1000L
    const val MINUS = "−"

    fun isInt(x: Double) = x.isFinite() && abs(x - round(x)) < 1e-9 * maxOf(1.0, abs(x)) && abs(x) < 1e15

    fun gcd(a: Long, b: Long): Long {
        var x = abs(a)
        var y = abs(b)
        while (y != 0L) {
            val t = x % y
            x = y
            y = t
        }
        return x
    }

    fun lcm(a: Long, b: Long): Long = if (a == 0L || b == 0L) 0 else abs(a / gcd(a, b) * b)

    /** Ułamek p/q (q > 0, skrócony) bliski x albo null. */
    fun ratio(x: Double): Pair<Long, Long>? {
        if (!x.isFinite() || abs(x) > 1e12) return null
        val tol = 1e-9 * maxOf(1.0, abs(x))
        for (q in 1..MAX_DEN) {
            val p = round(x * q)
            if (abs(p / q - x) < tol) {
                val g = gcd(p.toLong(), q)
                return (p.toLong() / g) to (q / g)
            }
        }
        return null
    }

    /** √n = k·√m (m bez czynników kwadratowych). */
    fun sqrtSplit(n: Long): Pair<Long, Long> {
        require(n >= 0)
        var k = 1L
        var m = n
        var f = 2L
        while (f * f <= m) {
            while (m % (f * f) == 0L) {
                m /= f * f
                k *= f
            }
            f++
        }
        return k to m
    }

    /** Dokładna postać liczby (bez przybliżenia) albo null. */
    fun exact(x: Double): String? {
        if (!x.isFinite()) return null
        if (isInt(x)) return int(round(x).toLong())
        ratio(x)?.let { (p, q) -> return frac(p, q) }
        // x = ±√(p/q) = ±√(p·q)/q = ±k√m/q
        val sq = ratio(x * x) ?: return null
        val (p, q) = sq
        if (p <= 0 || p * q > 10_000_000L) return null
        val (k0, m) = sqrtSplit(p * q)
        if (m == 1L) return null
        val g = gcd(k0, q)
        val k = k0 / g
        val d = q / g
        val sign = if (x < 0) MINUS else ""
        val num = (if (k == 1L) "" else "$k") + "√$m"
        return sign + if (d == 1L) num else "$num/$d"
    }

    fun int(n: Long) = if (n < 0) MINUS + (-n) else n.toString()

    fun frac(p: Long, q: Long): String = if (q == 1L) int(p) else int(p) + "/" + q

    /** Liczba dziesiętna z max. [digits] miejscami, bez zbędnych zer, z przecinkiem. */
    fun dec(x: Double, digits: Int = 4): String {
        if (x.isNaN()) return "—"
        if (x.isInfinite()) return if (x > 0) "∞" else "$MINUS∞"
        if (abs(x) >= 1e12 || (abs(x) < 1e-4 && x != 0.0 && abs(x) > 1e-12)) {
            return String.format(Locale.ROOT, "%.4e", x).replace('.', ',').replace("-", MINUS)
        }
        var s = String.format(Locale.ROOT, "%.${digits}f", x)
        if (s.contains('.')) s = s.trimEnd('0').trimEnd('.')
        if (s == "-0") s = "0"
        return s.replace('.', ',').replace("-", MINUS)
    }

    /** Wynik do wyświetlenia: „5”, „2/3 ≈ 0,6667”, „7√13/13 ≈ 1,9415”, „≈ 3,1416”. */
    fun show(x: Double): String {
        val e = exact(x)
        val d = dec(x)
        return when {
            e == null -> if (isInt(x * 10000)) d else "≈ $d"
            e == d -> e
            else -> "$e ≈ $d"
        }
    }

    /** Krótko: dokładnie, gdy to liczba wymierna, inaczej dziesiętnie (współrzędne, współczynniki). */
    fun short(x: Double): String {
        if (isInt(x)) return int(round(x).toLong())
        ratio(x)?.let { (p, q) -> if (q <= 100) return frac(p, q) }
        return dec(x)
    }

    fun point(x: Double, y: Double) = "(${short(x)}; ${short(y)})"
    fun point(x: Double, y: Double, z: Double) = "(${short(x)}; ${short(y)}; ${short(z)})"

    fun deg(x: Double) = dec(x, 2) + "°"

    /** Duża liczba całkowita: do 18 cyfr w całości (ze spacjami), dalej w notacji naukowej. */
    fun big(n: BigInteger): String {
        val s = n.abs().toString()
        val sign = if (n.signum() < 0) MINUS else ""
        if (s.length <= 18) return sign + group(s)
        val mant = s[0] + "," + s.substring(1, 5)
        return "$sign$mant·10^${s.length - 1}"
    }

    fun group(digits: String): String {
        if (digits.length <= 4) return digits
        val sb = StringBuilder()
        digits.forEachIndexed { i, c ->
            if (i > 0 && (digits.length - i) % 3 == 0) sb.append(' ')
            sb.append(c)
        }
        return sb.toString()
    }

    /** Współczynnik przed zmienną: 1·x → „x”, −1·x → „−x”, 2/3·x → „(2/3)x”. */
    private fun coef(c: Double, v: String): String {
        if (isInt(c)) {
            val n = round(c).toLong()
            return when (n) {
                1L -> v
                -1L -> MINUS + v
                else -> int(n) + v
            }
        }
        val r = ratio(c)
        if (r != null && r.second <= 100) {
            val (p, q) = r
            return (if (p < 0) MINUS else "") + "(${abs(p)}/$q)" + v
        }
        return dec(c) + v
    }

    /** Wyrażenie liniowe, np. terms(2.0 to "x", -3.0 to "y", const = 5.0) = „2x − 3y + 5”. */
    fun terms(vararg t: Pair<Double, String>, const: Double = 0.0): String {
        val parts = mutableListOf<String>()
        for ((c, v) in t) if (abs(c) > 1e-12) parts.add(coef(c, v))
        if (abs(const) > 1e-12 || parts.isEmpty()) parts.add(short(const))
        val sb = StringBuilder()
        parts.forEachIndexed { i, p ->
            val neg = p.startsWith(MINUS)
            val body = if (neg) p.substring(1) else p
            when {
                i == 0 -> sb.append(p)
                neg -> sb.append(" $MINUS ").append(body)
                else -> sb.append(" + ").append(body)
            }
        }
        return sb.toString()
    }

    /** Skaluje współczynniki do najmniejszych liczb całkowitych (gdy są wymierne). */
    fun integerize(vararg c: Double): DoubleArray {
        val rs = c.map { ratio(it) }
        if (rs.any { it == null }) return c
        var l = 1L
        rs.forEach { l = lcm(l, it!!.second) }
        val ints = rs.map { it!!.first * (l / it.second) }
        var g = 0L
        ints.forEach { g = gcd(g, it) }
        if (g == 0L) return c
        return ints.map { (it / g).toDouble() }.toDoubleArray()
    }

    /** Prosta Ax + By + C = 0 w czytelnej postaci (całkowite współczynniki, A > 0). */
    fun lineGeneral(a: Double, b: Double, c: Double): String {
        var (aa, bb, cc) = integerize(a, b, c).toList()
        if (aa < 0 || (aa == 0.0 && bb < 0)) {
            aa = -aa; bb = -bb; cc = -cc
        }
        return terms(aa to "x", bb to "y", const = cc) + " = 0"
    }

    /** Prosta y = ax + b. */
    fun lineSlope(a: Double, b: Double): String = "y = " + terms(a to "x", const = b)

    fun sqrtSafe(x: Double) = sqrt(maxOf(0.0, x))
}
