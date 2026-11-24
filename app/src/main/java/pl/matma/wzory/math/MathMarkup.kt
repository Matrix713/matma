package pl.matma.wzory.math

/**
 * Prosty zapis wzorów, czytelny w kodzie i rysowany na zegarku jak w zeszycie.
 *
 *  - `@f{licznik}{mianownik}` – ułamek piętrowy
 *  - `@s{x}` – pierwiastek kwadratowy, `@r{n}{x}` – pierwiastek stopnia n
 *  - `^{…}` / `^x` – wykładnik, `_{…}` / `_x` – indeks dolny
 *  - `@v{AB}` – wektor (strzałka), `@o{x}` – kreska nad
 *  - `@lim{x→a}`, `@sum{dół}{góra}`, `@prod{dół}{góra}`, `@int{dół}{góra}` – operatory z granicami
 *  - `@b{n}{k}` – symbol Newtona, `@sys{r1}{r2}…` – układ (klamra)
 *  - `@(` i `@)` – zwykłe nawiasy klamrowe { }
 *  - spacja = miejsce, gdzie wzór może się zawinąć, `\n` = nowa linia
 */
sealed interface MNode

data class MText(val text: String) : MNode
data class MFrac(val num: List<MNode>, val den: List<MNode>) : MNode
data class MRoot(val index: List<MNode>?, val body: List<MNode>) : MNode
data class MSup(val body: List<MNode>) : MNode
data class MSub(val body: List<MNode>) : MNode
data class MVec(val body: List<MNode>) : MNode
data class MOver(val body: List<MNode>) : MNode
data class MBigOp(val symbol: String, val below: List<MNode>?, val above: List<MNode>?) : MNode
data class MBinom(val n: List<MNode>, val k: List<MNode>) : MNode
data class MSys(val rows: List<List<MNode>>) : MNode

class MathSyntaxException(message: String) : IllegalArgumentException(message)

/** Słowo = fragment bez łamania; segment = grupa słów oddzielona podwójną spacją; linia = segmenty. */
typealias MWord = List<MNode>
typealias MSegment = List<MWord>
typealias MLine = List<MSegment>

object MathMarkup {

    fun parse(src: String): List<MNode> {
        val p = Parser(src)
        val nodes = p.sequence(topLevel = true)
        if (!p.atEnd()) throw MathSyntaxException("Nadmiarowe '}' w: $src")
        return nodes
    }

    /** Znaki działań „przyklejane” do następnego słowa, żeby linia nie zaczynała się od samego „=”. */
    private val GLUE = setOf("=", "+", "−", "-", "<", ">", "≤", "≥", "·", "⇔", "⇒", "±", "∨", "∧", "≈", "≠", "∈", "→", "×", "∘", ":")

    /**
     * Wzór podzielony do rysowania z zawijaniem:
     * `\n` – nowa linia, dwie spacje – granica segmentu (osobne wzory w jednej linii),
     * pojedyncza spacja – możliwe miejsce złamania (ale nie wewnątrz nawiasów).
     */
    fun layout(src: String): List<MLine> {
        val lines = mutableListOf<MLine>()
        var segments = mutableListOf<MSegment>()
        var words = mutableListOf<MWord>()
        var word = mutableListOf<MNode>()
        val sb = StringBuilder()
        var depth = 0
        var spaces = 0

        fun flushText() {
            if (sb.isNotEmpty()) word.add(MText(sb.toString()))
            sb.clear()
        }
        fun endWord() {
            flushText()
            if (word.isNotEmpty()) words.add(word)
            word = mutableListOf()
        }
        fun endSegment() {
            endWord()
            if (words.isNotEmpty()) segments.add(glue(words))
            words = mutableListOf()
        }
        fun endLine() {
            endSegment()
            if (segments.isNotEmpty()) lines.add(segments)
            segments = mutableListOf()
        }
        fun pendingSpaces() {
            if (spaces == 0) return
            if (spaces >= 2) endSegment() else endWord()
            spaces = 0
        }

        for (node in parse(src)) {
            if (node !is MText) {
                pendingSpaces()
                flushText()
                word.add(node)
                continue
            }
            for (ch in node.text) {
                when {
                    ch == '\n' -> {
                        spaces = 0
                        depth = 0
                        endLine()
                    }
                    ch == ' ' && depth == 0 -> spaces++
                    else -> {
                        pendingSpaces()
                        when (ch) {
                            '(', '[' -> depth++
                            ')', ']' -> depth = maxOf(0, depth - 1)
                        }
                        sb.append(ch)
                    }
                }
            }
        }
        endLine()
        return lines
    }

    private fun glue(words: List<MWord>): MSegment {
        val out = mutableListOf<MWord>()
        var prefix: List<MNode> = emptyList()
        for (w in words) {
            val op = (w.singleOrNull() as? MText)?.text
            if (op != null && op in GLUE) {
                prefix = prefix + MText(op + "\u00A0")
                continue
            }
            out.add(prefix + w)
            prefix = emptyList()
        }
        if (prefix.isNotEmpty()) out.add(prefix)
        return out
    }

    /** Wzór jako zwykły tekst (podgląd na liście, wyszukiwanie). */
    fun plain(src: String): String = runCatching { plain(parse(src)) }.getOrDefault(src).replace('\n', ' ')

    fun plain(nodes: List<MNode>): String = nodes.joinToString("") { plain(it) }

    private fun plain(node: MNode): String = when (node) {
        is MText -> node.text
        is MFrac -> group(node.num) + "/" + group(node.den)
        is MRoot -> (node.index?.let { sup(plain(it)) } ?: "") + "√" + group(node.body)
        is MSup -> sup(plain(node.body))
        is MSub -> sub(plain(node.body))
        is MVec -> plain(node.body) + "⃗"
        is MOver -> plain(node.body).map { "$it̅" }.joinToString("")
        is MBigOp -> buildString {
            append(node.symbol)
            val lo = node.below?.let { plain(it) }
            val hi = node.above?.let { plain(it) }
            when {
                lo != null && hi != null -> append("[$lo..$hi]")
                lo != null -> append("($lo)")
            }
            append(' ')
        }
        is MBinom -> "C(" + plain(node.n) + "," + plain(node.k) + ")"
        is MSys -> "{ " + node.rows.joinToString("; ") { plain(it) }
    }

    private fun group(nodes: List<MNode>): String {
        val s = plain(nodes)
        val bars = s.length > 2 && s.first() == '|' && s.last() == '|' && s.count { it == '|' } == 2
        val simple = bars || (nodes.size == 1 && (nodes[0] !is MText || s.none { it in " +-−±·=<>≤≥" }))
        return if (simple) s else "($s)"
    }

    private const val SUP_FROM = "0123456789+-−=()niakxmr"
    private const val SUP_TO = "⁰¹²³⁴⁵⁶⁷⁸⁹⁺⁻⁻⁼⁽⁾ⁿⁱᵃᵏˣᵐʳ"
    private const val SUB_FROM = "0123456789+-−=()aeoxhklmnpstij"
    private const val SUB_TO = "₀₁₂₃₄₅₆₇₈₉₊₋₋₌₍₎ₐₑₒₓₕₖₗₘₙₚₛₜᵢⱼ"

    private fun mapAll(s: String, from: String, to: String): String? {
        val sb = StringBuilder()
        for (c in s) {
            val i = from.indexOf(c)
            if (i < 0) return null
            sb.append(to[i])
        }
        return sb.toString()
    }

    private fun sup(s: String) = mapAll(s, SUP_FROM, SUP_TO) ?: if (s.length == 1) "^$s" else "^($s)"
    private fun sub(s: String) = mapAll(s, SUB_FROM, SUB_TO) ?: if (s.length == 1) "_$s" else "_($s)"

    private class Parser(val src: String) {
        var pos = 0

        fun atEnd() = pos >= src.length

        fun sequence(topLevel: Boolean): List<MNode> {
            val out = mutableListOf<MNode>()
            val text = StringBuilder()
            fun flush() {
                if (text.isNotEmpty()) out.add(MText(text.toString()))
                text.clear()
            }
            while (pos < src.length) {
                val c = src[pos]
                when (c) {
                    '}' -> {
                        if (topLevel) throw MathSyntaxException("Nadmiarowe '}' w: $src")
                        break
                    }
                    '{' -> throw MathSyntaxException("Nieoczekiwane '{' na pozycji $pos w: $src")
                    '^', '_' -> {
                        pos++
                        flush()
                        val body = argOrChar()
                        out.add(if (c == '^') MSup(body) else MSub(body))
                    }
                    '@' -> {
                        pos++
                        if (pos < src.length && (src[pos] == '(' || src[pos] == ')')) {
                            text.append(if (src[pos] == '(') '{' else '}')
                            pos++
                        } else {
                            flush()
                            out.add(command())
                        }
                    }
                    else -> {
                        text.append(c)
                        pos++
                    }
                }
            }
            flush()
            return out
        }

        private fun arg(): List<MNode> {
            if (pos >= src.length || src[pos] != '{') throw MathSyntaxException("Oczekiwano '{' na pozycji $pos w: $src")
            pos++
            val nodes = sequence(topLevel = false)
            if (pos >= src.length) throw MathSyntaxException("Brak '}' w: $src")
            pos++
            return nodes
        }

        private fun optArg(): List<MNode>? = arg().ifEmpty { null }

        private fun argOrChar(): List<MNode> {
            if (pos >= src.length) throw MathSyntaxException("Pusty wykładnik/indeks w: $src")
            if (src[pos] == '{') return arg()
            val ch = src[pos]
            pos++
            return listOf(MText(ch.toString()))
        }

        private fun command(): MNode {
            val start = pos
            while (pos < src.length && src[pos].isLetter()) pos++
            return when (val name = src.substring(start, pos)) {
                "f" -> MFrac(arg(), arg())
                "s" -> MRoot(null, arg())
                "r" -> MRoot(arg(), arg())
                "v" -> MVec(arg())
                "o" -> MOver(arg())
                "b" -> MBinom(arg(), arg())
                "lim" -> MBigOp("lim", optArg(), null)
                "sum" -> MBigOp("Σ", optArg(), optArg())
                "prod" -> MBigOp("Π", optArg(), optArg())
                "int" -> MBigOp("∫", optArg(), optArg())
                "sys" -> {
                    val rows = mutableListOf<List<MNode>>()
                    while (pos < src.length && src[pos] == '{') rows.add(arg())
                    if (rows.isEmpty()) throw MathSyntaxException("@sys bez wierszy w: $src")
                    MSys(rows)
                }
                else -> throw MathSyntaxException("Nieznane polecenie @$name w: $src")
            }
        }
    }
}
