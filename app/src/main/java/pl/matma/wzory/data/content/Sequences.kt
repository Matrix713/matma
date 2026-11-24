package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Sequences {
    val section = section("ciagi", "Ciągi", "arytmetyczny, geometryczny, granice") {

        category("ciagi-ogolne", "Ciągi – podstawy", "wzór ogólny, sumy, monotoniczność") {
            f("Wzór ogólny i rekurencyjny", "a_{n} = f(n)    a_{n+1} = g(a_{n})", "Ciąg rekurencyjny wymaga podania pierwszego wyrazu.")
            f("Ciąg rosnący / malejący", "a_{n+1} − a_{n} > 0: rosnący\na_{n+1} − a_{n} < 0: malejący", "Zbadaj znak różnicy dla każdego n.")
            f("Suma n wyrazów", "S_{n} = a₁ + a₂ + … + a_{n}", null)
            f("Wyraz z sum", "a_{n} = S_{n} − S_{n−1}", "Dla n ≥ 2; a₁ = S₁.")
            f("Ciąg Fibonacciego", "a_{n+2} = a_{n+1} + a_{n}", "a₁ = a₂ = 1: 1, 1, 2, 3, 5, 8, 13…")
            f("Suma kolejnych liczb", "1 + 2 + … + n = @f{n(n + 1)}{2}", null, "1 + … + 100 = 5050")
            f("Suma kwadratów", "1² + 2² + … + n² = @f{n(n + 1)(2n + 1)}{6}", null)
            f("Suma sześcianów", "1³ + 2³ + … + n³ = (@f{n(n + 1)}{2})²", null)
            f("Suma liczb nieparzystych", "1 + 3 + 5 + … + (2n − 1) = n²", null)
            f("Liczba wyrazów ciągu", "od a do b z krokiem r: @f{b − a}{r} + 1", null)
        }

        category("arytmetyczny", "Ciąg arytmetyczny", "aₙ, r, Sₙ") {
            f(
                "Wyraz ogólny ciągu arytmetycznego",
                "a_{n} = a₁ + (n − 1)·r",
                "r – różnica ciągu.",
                "a₁ = 2, r = 3: a₁₀ = 2 + 27 = 29",
                tags = "arytmetyczny",
                calc = "ciag-arytm",
            )
            f("Różnica ciągu", "r = a_{n+1} − a_{n}", null)
            f(
                "Suma ciągu arytmetycznego",
                "S_{n} = @f{a₁ + a_{n}}{2}·n",
                null,
                "a₁ = 2, a₁₀ = 29: S₁₀ = @f{31}{2}·10 = 155",
                calc = "ciag-arytm",
            )
            f("Suma (przez r)", "S_{n} = @f{2a₁ + (n − 1)r}{2}·n", null, calc = "ciag-arytm")
            f(
                "Warunek na ciąg arytmetyczny",
                "2b = a + c",
                "Liczby a, b, c (w tej kolejności) tworzą ciąg arytmetyczny ⇔ środkowa jest średnią arytmetyczną sąsiednich.",
            )
            f("Wyraz środkowy", "a_{n} = @f{a_{n−1} + a_{n+1}}{2}", null)
            f("Wyraz przez dowolny wyraz", "a_{n} = a_{k} + (n − k)·r", null)
            f("Własność indeksów", "m + n = p + q ⇒ a_{m} + a_{n} = a_{p} + a_{q}", null)
            f("Monotoniczność ciągu arytmetycznego", "r > 0: rosnący\nr < 0: malejący\nr = 0: stały", null)
        }

        category("geometryczny", "Ciąg geometryczny", "aₙ, q, Sₙ, szereg") {
            f(
                "Wyraz ogólny ciągu geometrycznego",
                "a_{n} = a₁·q^{n−1}",
                "q – iloraz ciągu.",
                "a₁ = 3, q = 2: a₅ = 3·16 = 48",
                tags = "geometryczny",
                calc = "ciag-geom",
            )
            f("Iloraz ciągu", "q = @f{a_{n+1}}{a_{n}}", null)
            f(
                "Suma ciągu geometrycznego",
                "S_{n} = a₁·@f{1 − q^{n}}{1 − q}   (q ≠ 1)\nS_{n} = n·a₁   (q = 1)",
                null,
                "a₁ = 1, q = 2: S₁₀ = 2¹⁰ − 1 = 1023",
                calc = "ciag-geom",
            )
            f(
                "Warunek na ciąg geometryczny",
                "b² = a·c",
                "Dla wyrazów różnych od zera: a, b, c tworzą ciąg geometryczny.",
            )
            f("Wyraz środkowy (geometryczny)", "|a_{n}| = @s{a_{n−1}·a_{n+1}}", null)
            f("Wyraz przez dowolny wyraz (geom.)", "a_{n} = a_{k}·q^{n−k}", null)
            f(
                "Monotoniczność ciągu geometrycznego",
                "a₁ > 0, q > 1 lub a₁ < 0, 0 < q < 1: rosnący\na₁ > 0, 0 < q < 1 lub a₁ < 0, q > 1: malejący\nq < 0: niemonotoniczny",
                null,
            )
            f(
                "Szereg geometryczny",
                "S = @f{a₁}{1 − q},  |q| < 1",
                "Suma nieskończenie wielu wyrazów. Dla |q| ≥ 1 szereg jest rozbieżny.",
                "1 + @f{1}{2} + @f{1}{4} + … = 2",
                calc = "ciag-geom",
            )
        }

        category("granice-ciagow", "Granice ciągów", "e, symbole nieoznaczone, twierdzenia") {
            f("Granica 1/n", "@lim{n→∞} @f{1}{n} = 0", "Także 1/nᵏ → 0 dla k > 0.")
            f("Granica qⁿ", "@lim{n→∞} q^{n} = 0,  |q| < 1", "q > 1: qⁿ → ∞; q = 1: 1; q ≤ −1: brak granicy.")
            f("Liczba e", "@lim{n→∞}(1 + @f{1}{n})^{n} = e ≈ 2,71828", null)
            f("Uogólnienie liczby e", "@lim{n→∞}(1 + @f{a}{n})^{n} = e^{a}", null)
            f("Pierwiastki n-tego stopnia", "@lim{n→∞}@r{n}{a} = 1 (a > 0)    @lim{n→∞}@r{n}{n} = 1", null)
            f(
                "Granica ilorazu wielomianów",
                "st licznika = st mianownika: @f{a_{k}}{b_{k}}\nst licznika < st mianownika: 0\nst licznika > st mianownika: ±∞",
                "a_k, b_k – współczynniki przy najwyższych potęgach.",
                "@lim{n→∞}@f{3n² + 1}{2n² − n} = @f{3}{2}",
            )
            f(
                "Działania na granicach",
                "lim(a_{n} ± b_{n}) = a ± b\nlim(a_{n}·b_{n}) = a·b\nlim @f{a_{n}}{b_{n}} = @f{a}{b}  (b ≠ 0)",
                "Gdy oba ciągi są zbieżne.",
            )
            f(
                "Symbole nieoznaczone",
                "∞ − ∞,  @f{0}{0},  @f{∞}{∞},  0·∞,  1^{∞},  ∞⁰,  0⁰",
                "Wymagają przekształcenia wyrażenia.",
            )
            f(
                "Różnica pierwiastków",
                "@s{a} − @s{b} = @f{a − b}{@s{a} + @s{b}}",
                "Mnożenie przez sprzężenie – sposób na ∞ − ∞.",
                "@lim{n→∞}(@s{n² + n} − n) = @f{1}{2}",
            )
            f("Twierdzenie o trzech ciągach", "a_{n} ≤ b_{n} ≤ c_{n},  a_{n}, c_{n} → g ⇒ b_{n} → g", null)
            f("Ciąg monotoniczny i ograniczony", "jest zbieżny", "Twierdzenie Weierstrassa o ciągach.")
        }
    }
}
