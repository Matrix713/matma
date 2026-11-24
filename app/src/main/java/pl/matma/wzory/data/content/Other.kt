package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Other {
    val section = section("inne", "Logika, zbiory, stałe", "De Morgan, działania, jednostki") {

        category("logika", "Logika", "prawa logiczne, kwantyfikatory") {
            f("Prawa De Morgana", "¬(p ∧ q) ⇔ ¬p ∨ ¬q\n¬(p ∨ q) ⇔ ¬p ∧ ¬q", "Negacja koniunkcji i alternatywy.", tags = "de morgan")
            f("Implikacja przez alternatywę", "(p ⇒ q) ⇔ (¬p ∨ q)", null)
            f("Prawo kontrapozycji", "(p ⇒ q) ⇔ (¬q ⇒ ¬p)", "Podstawa dowodu „nie wprost”.")
            f("Negacja implikacji", "¬(p ⇒ q) ⇔ p ∧ ¬q", null)
            f("Równoważność", "(p ⇔ q) ⇔ (p ⇒ q) ∧ (q ⇒ p)", null)
            f("Prawo wyłączonego środka", "p ∨ ¬p", "Zawsze prawdziwe (tautologia).")
            f("Prawo podwójnego przeczenia", "¬(¬p) ⇔ p", null)
            f(
                "Wartości implikacji",
                "1 ⇒ 1: 1    1 ⇒ 0: 0\n0 ⇒ 1: 1    0 ⇒ 0: 1",
                "Implikacja jest fałszywa tylko, gdy z prawdy wynika fałsz.",
            )
            f(
                "Negacja kwantyfikatorów",
                "¬∀x p(x) ⇔ ∃x ¬p(x)\n¬∃x p(x) ⇔ ∀x ¬p(x)",
                null,
            )
            f(
                "Indukcja matematyczna",
                "1. T(n₀) prawdziwe\n2. T(k) ⇒ T(k + 1)\n⇒ T(n) dla n ≥ n₀",
                null,
            )
        }

        category("zbiory", "Zbiory", "działania, De Morgan, liczba elementów") {
            f(
                "Działania na zbiorach",
                "A ∪ B – suma    A ∩ B – iloczyn\nA ∖ B – różnica    A' – dopełnienie",
                null,
            )
            f("Prawa De Morgana dla zbiorów", "(A ∪ B)' = A' ∩ B'\n(A ∩ B)' = A' ∪ B'", null)
            f("Różnica przez dopełnienie", "A ∖ B = A ∩ B'", null)
            f("Liczba elementów sumy", "|A ∪ B| = |A| + |B| − |A ∩ B|", null)
            f(
                "Zasada włączeń i wyłączeń",
                "|A ∪ B ∪ C| = |A| + |B| + |C|\n− |A ∩ B| − |A ∩ C| − |B ∩ C| + |A ∩ B ∩ C|",
                null,
            )
            f("Liczba podzbiorów", "2^{|A|}", null)
            f("Iloczyn kartezjański", "|A × B| = |A|·|B|", "Zbiór par uporządkowanych (a; b).")
            f("Zbiory liczbowe", "ℕ ⊂ ℤ ⊂ ℚ ⊂ ℝ ⊂ ℂ", "Naturalne, całkowite, wymierne, rzeczywiste, zespolone. ℝ ∖ ℚ – niewymierne.")
            f("Zapis przedziału jako zbioru", "(a; b) = @(x ∈ ℝ: a < x < b@)", null)
        }

        category("stale", "Stałe i tablice", "π, e, √2, potęgi, liczby pierwsze") {
            f("Liczba π", "π ≈ 3,14159 26535 89793", null)
            f("Liczba e", "e ≈ 2,71828 18284 59045", null)
            f("Pierwiastki", "@s{2} ≈ 1,41421    @s{3} ≈ 1,73205\n@s{5} ≈ 2,23607    @s{6} ≈ 2,44949", null)
            f("Złota liczba", "φ = @f{1 + @s{5}}{2} ≈ 1,61803", "φ² = φ + 1.")
            f("Logarytmy", "log 2 ≈ 0,30103    log 3 ≈ 0,47712\nln 2 ≈ 0,69315    ln 10 ≈ 2,30259", null)
            f("Kwadraty 11–20", "11² = 121, 12² = 144, 13² = 169\n14² = 196, 15² = 225, 16² = 256\n17² = 289, 18² = 324, 19² = 361, 20² = 400", null)
            f("Sześciany 1–10", "1, 8, 27, 64, 125\n216, 343, 512, 729, 1000", null)
            f("Potęgi dwójki", "2⁵ = 32, 2⁶ = 64, 2⁷ = 128, 2⁸ = 256\n2⁹ = 512, 2¹⁰ = 1024, 2¹¹ = 2048, 2¹² = 4096", null)
            f(
                "Liczby pierwsze do 100",
                "2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97",
                "25 liczb pierwszych. 1 nie jest ani pierwsza, ani złożona.",
            )
            f("Jednostki pola", "1 a = 100 m²    1 ha = 10 000 m²\n1 km² = 100 ha", null)
            f("Jednostki objętości", "1 l = 1 dm³    1 ml = 1 cm³\n1 m³ = 1000 l", null)
            f("Prędkość", "1 @f{m}{s} = 3,6 @f{km}{h}", null)
            f("Liczby rzymskie", "I = 1, V = 5, X = 10, L = 50\nC = 100, D = 500, M = 1000", "Mniejsza przed większą – odejmujemy: IV = 4, XC = 90.")
        }
    }
}
