package pl.matma.wzory.math

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.matma.wzory.ui.NumberEditor

class CalcTest {
    private fun run(id: String, vararg v: Pair<String, Double>): Map<String, String> {
        val c = Calculators.byId(id)!!
        val rows = c.run(c.defaults() + v.toMap()).getOrThrow()
        return rows.associate { it.label to it.value }
    }

    @Test fun exactForms() {
        assertEquals("5", Num.show(5.0))
        assertEquals("2/3 ≈ 0,6667", Num.show(2.0 / 3))
        assertEquals("7√13/13 ≈ 1,9415", Num.show(7 / Math.sqrt(13.0)))
        assertEquals("−√2/2 ≈ −0,7071", Num.show(-Math.sqrt(2.0) / 2))
        assertEquals("≈ 3,1416", Num.show(Math.PI))
        assertEquals("3x − 4y + 5 = 0", Num.lineGeneral(-1.5, 2.0, -2.5))
        assertEquals("y = (2/3)x − 1", Num.lineSlope(2.0 / 3, -1.0))
    }

    @Test fun allCalculatorsRunWithDefaults() {
        Calculators.all.forEach { c ->
            val r = c.run(c.defaults())
            assertTrue("${c.id}: ${r.exceptionOrNull()}", r.isSuccess && r.getOrThrow().isNotEmpty())
        }
    }

    @Test fun pointLineDistance() {
        val r = run("punkt-prosta")
        assertEquals("13/5 ≈ 2,6", r["Odległość d"])
        val r2 = run("punkt-prosta", "A" to 2.0, "B" to -3.0, "C" to 1.0, "x0" to 1.0, "y0" to 4.0)
        // |2 − 12 + 1| / √13 = 9/√13
        assertEquals("9√13/13 ≈ 2,4962", r2["Odległość d"])
        assertEquals("(1; 4)", run("punkt-prosta", "x0" to 1.0, "y0" to 4.0, "A" to 0.0, "B" to 1.0, "C" to -4.0)["Rzut P na prostą (spodek)"])
    }

    @Test fun twoPointsLine() {
        val r = run("prosta-2pkt")
        assertEquals("y = 2x", r["Postać kierunkowa"])
        assertEquals("2x − y = 0", r["Postać ogólna"])
        assertEquals("(5/2; 5)", r["Środek odcinka"])
    }

    @Test fun twoLines() {
        val r = run("dwie-proste")
        assertEquals("(1; 2)", r["Punkt przecięcia"])
        val p = run("dwie-proste", "A2" to 2.0, "B2" to -2.0, "C2" to 6.0)
        assertTrue(p["Położenie"]!!.startsWith("równoległe"))
        assertEquals("√2 ≈ 1,4142", p["Odległość prostych"])
    }

    @Test fun quadratic() {
        val r = run("kwadratowa")
        assertEquals("16", r["Δ = b² − 4ac"])
        assertEquals("−1", r["x₁ = (−b − √Δ)/2a"])
        assertEquals("3", r["x₂ = (−b + √Δ)/2a"])
        assertEquals("(1; −4)", r["Wierzchołek W(p; q)"])
        val irr = run("kwadratowa", "a" to 1.0, "b" to -2.0, "c" to -1.0)
        assertEquals("1 − √2 ≈ −0,4142", irr["x₁ = (−b − √Δ)/2a"])
        val irr2 = run("kwadratowa", "a" to 2.0, "b" to 3.0, "c" to -1.0)
        assertEquals("(−3 + √17)/4 ≈ 0,2808", irr2["x₂ = (−b + √Δ)/2a"])
    }

    @Test fun triangle() {
        val r = run("trojkat-3pkt")
        assertEquals("6", r["Pole P = ½|det(AB, AC)|"])
        assertEquals("(2; 3/2)", r["Środek okręgu opisanego"])
        assertEquals("1", r["Promień r"])
        assertTrue(r["Rodzaj"]!!.startsWith("prostokątny"))
    }

    @Test fun circles() {
        val r = run("okrag-ogolny")
        assertEquals("(2; −3)", r["Środek S = (−D/2; −E/2)"])
        assertEquals("5", r["Promień r"])
        val c = run("dwa-okregi")
        assertEquals("(3; 4)", c["Punkt wspólny 1"])
        val l = run("okrag-prosta", "a" to 0.0, "b" to 0.0, "r" to 5.0, "A" to 0.0, "B" to 1.0, "C" to -3.0)
        assertEquals("8", l["Długość cięciwy 2√(r² − d²)"])
    }

    @Test fun trigExact() {
        val r = run("trygonometria", "alpha" to 30.0)
        assertEquals("1/2 ≈ 0,5", r["sin α"])
        assertEquals("√3/3 ≈ 0,5774", r["tg α"])
        assertEquals("π/6 ≈ 0,5236", r["α w radianach"])
        assertEquals("nie istnieje", run("trygonometria", "alpha" to 90.0)["tg α"])
    }

    @Test fun combinatorics() {
        val r = run("kombinatoryka", "n" to 49.0, "k" to 6.0)
        assertEquals("13 983 816", r["Kombinacje C(n, k)"])
    }

    @Test fun numberEditor() {
        assertEquals("-2,5", "2,5".let { NumberEditor.append(it, '±') })
        assertEquals(2.0 / 3, NumberEditor.parse("2/3")!!, 1e-12)
        assertEquals(-0.5, NumberEditor.parse("-0,5")!!, 1e-12)
        assertEquals("0,", NumberEditor.append("", ','))
        assertEquals("3", NumberEditor.format(3.0))
        assertEquals("-0,25", NumberEditor.format(-0.25))
    }
}
