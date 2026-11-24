package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Algebra {
    val section = section("algebra", "Algebra", "skrócone mnożenie, potęgi, procenty") {

        category("skrocone", "Wzory skróconego mnożenia", "(a ± b)², a² − b², sześciany") {
            f("Kwadrat sumy", "(a + b)² = a² + 2ab + b²", null, "(x + 3)² = x² + 6x + 9")
            f("Kwadrat różnicy", "(a − b)² = a² − 2ab + b²", null, "(2x − 1)² = 4x² − 4x + 1")
            f("Różnica kwadratów", "a² − b² = (a − b)(a + b)", null, "x² − 9 = (x − 3)(x + 3)")
            f("Sześcian sumy", "(a + b)³ = a³ + 3a²b + 3ab² + b³", null)
            f("Sześcian różnicy", "(a − b)³ = a³ − 3a²b + 3ab² − b³", null)
            f("Suma sześcianów", "a³ + b³ = (a + b)(a² − ab + b²)", null, "x³ + 8 = (x + 2)(x² − 2x + 4)")
            f("Różnica sześcianów", "a³ − b³ = (a − b)(a² + ab + b²)", null, "x³ − 27 = (x − 3)(x² + 3x + 9)")
            f("Kwadrat trzech składników", "(a + b + c)² = a² + b² + c² + 2ab + 2ac + 2bc", null)
            f("Czwarta potęga sumy", "(a + b)⁴ = a⁴ + 4a³b + 6a²b² + 4ab³ + b⁴", "Współczynniki z trójkąta Pascala: 1 4 6 4 1.")
            f("Różnica czwartych potęg", "a⁴ − b⁴ = (a − b)(a + b)(a² + b²)", null)
            f(
                "Różnica n-tych potęg",
                "a^{n} − b^{n} = (a − b)(a^{n−1} + a^{n−2}b + … + ab^{n−2} + b^{n−1})",
                null,
            )
            f("Suma kwadratów przez sumę", "a² + b² = (a + b)² − 2ab", "Przydatne np. z wzorami Viète’a.")
            f("Iloczyn przez kwadraty", "ab = @f{(a + b)² − (a − b)²}{4}", null)
            f(
                "Dwumian Newtona",
                "(a + b)^{n} = @sum{k=0}{n} @b{n}{k} a^{n−k} b^{k}",
                "Współczynniki to symbole Newtona (trójkąt Pascala).",
                tags = "newton dwumian",
            )
            f(
                "Usuwanie niewymierności z mianownika",
                "@f{1}{@s{a}} = @f{@s{a}}{a}\n@f{1}{@s{a} − @s{b}} = @f{@s{a} + @s{b}}{a − b}",
                "Mnożymy licznik i mianownik przez sprzężenie.",
                "@f{2}{@s{3} − 1} = @f{2(@s{3} + 1)}{2} = @s{3} + 1",
                tags = "niewymiernosc mianownik sprzezenie",
            )
            f("Kwadrat różnicy – symetria", "(a − b)² = (b − a)²", null)
        }

        category("potegi", "Potęgi i pierwiastki", "działania, wykładniki wymierne") {
            f("Potęga o wykładniku naturalnym", "a^{n} = a·a·…·a  (n razy)", null)
            f("Potęga zerowa", "a⁰ = 1,  a ≠ 0", "0⁰ jest symbolem nieoznaczonym.")
            f("Potęga ujemna", "a^{−n} = @f{1}{a^{n}}", null, "2^{−3} = @f{1}{8}")
            f("Iloczyn potęg", "a^{m}·a^{n} = a^{m+n}", null)
            f("Iloraz potęg", "@f{a^{m}}{a^{n}} = a^{m−n}", null)
            f("Potęga potęgi", "(a^{m})^{n} = a^{m·n}", null)
            f("Potęga iloczynu", "(a·b)^{n} = a^{n}·b^{n}", null)
            f("Potęga ilorazu", "(@f{a}{b})^{n} = @f{a^{n}}{b^{n}}", null)
            f("Odwrócenie ułamka", "(@f{a}{b})^{−n} = (@f{b}{a})^{n}", null, "(@f{2}{3})^{−2} = @f{9}{4}")
            f("Wykładnik wymierny", "a^{@f{m}{n}} = @r{n}{a^{m}}", "a ≥ 0.", "8^{@f{2}{3}} = @r{3}{64} = 4")
            f("Pierwiastek jako potęga", "@r{n}{a} = a^{@f{1}{n}}", null)
            f("Pierwiastek iloczynu", "@s{a·b} = @s{a}·@s{b}", "a, b ≥ 0.", "@s{50} = @s{25·2} = 5@s{2}")
            f("Pierwiastek ilorazu", "@s{@f{a}{b}} = @f{@s{a}}{@s{b}}", "a ≥ 0, b > 0.")
            f("Pierwiastek z pierwiastka", "@r{n}{@r{m}{a}} = @r{n·m}{a}", null)
            f("Pierwiastek z kwadratu", "@s{a²} = |a|", "Częsty błąd: @s{a²} ≠ a, gdy a < 0.")
            f("Pierwiastek nieparzystego stopnia", "@r{3}{−a} = −@r{3}{a}", "Pierwiastki nieparzystego stopnia istnieją z liczb ujemnych.")
            f("Wyłączanie czynnika przed pierwiastek", "@s{a²b} = |a|@s{b}", null, "@s{72} = 6@s{2}")
            f("Notacja wykładnicza", "x = a·10^{k},  1 ≤ a < 10", null, "0,00034 = 3,4·10^{−4}")
            f("Potęgi liczby −1", "(−1)^{n} = @sys{1,  n parzyste}{−1,  n nieparzyste}", null)
        }

        category("procenty", "Procenty i liczby", "procenty, średnie, podzielność, błędy") {
            f("p procent liczby", "@f{p}{100}·a", null, "15% z 80 = 0,15·80 = 12")
            f("Jakim procentem a jest b", "@f{b}{a}·100%", null)
            f("Zmiana procentowa", "@f{nowa − stara}{stara}·100%", null, "z 80 do 100: @f{20}{80} = 25%")
            f(
                "Podwyżka i obniżka o p%",
                "podwyżka: a·(1 + @f{p}{100})\nobniżka: a·(1 − @f{p}{100})",
                "Podwyżka o 20% i obniżka o 20% NIE daje ceny wyjściowej (1,2·0,8 = 0,96).",
            )
            f("Punkty procentowe", "z 20% do 25% = wzrost o 5 p.p.", "To różnica dwóch wartości procentowych (o 25% względnie).")
            f(
                "Procent składany",
                "K_{n} = K·(1 + @f{p}{100})^{n}",
                "Kapitał K po n okresach przy oprocentowaniu p% w okresie.",
                "1000 zł, 5%, 3 lata: 1000·1,05³ = 1157,63 zł",
                calc = "procent-skladany",
            )
            f(
                "Kapitalizacja m razy w roku",
                "K_{n} = K·(1 + @f{p}{100·m})^{n·m}",
                null,
                calc = "procent-skladany",
            )
            f("Błąd bezwzględny", "Δ = |x − x₀|", "x – wartość dokładna, x₀ – przybliżenie.")
            f("Błąd względny", "δ = @f{|x − x₀|}{|x|}·100%", null)
            f("Średnia arytmetyczna", "@f{a₁ + a₂ + … + a_{n}}{n}", null, calc = "srednie")
            f("Średnia geometryczna", "@r{n}{a₁·a₂·…·a_{n}}", "Dla liczb nieujemnych.", calc = "srednie")
            f("Średnia harmoniczna", "@f{n}{@f{1}{a₁} + @f{1}{a₂} + … + @f{1}{a_{n}}}", "Np. średnia prędkość na tej samej trasie.", calc = "srednie")
            f("Średnia kwadratowa", "@s{@f{a₁² + a₂² + … + a_{n}²}{n}}", null, calc = "srednie")
            f(
                "Nierówność między średnimi",
                "H ≤ G ≤ A ≤ K",
                "Harmoniczna ≤ geometryczna ≤ arytmetyczna ≤ kwadratowa; równość ⇔ wszystkie liczby równe.",
                "a + b ≥ 2@s{ab}",
                calc = "srednie",
            )
            f("NWD i NWW", "NWD(a, b)·NWW(a, b) = a·b", null, "NWD(12, 18) = 6, NWW = @f{216}{6} = 36", calc = "srednie")
            f("Algorytm Euklidesa", "NWD(a, b) = NWD(b, a mod b)", "Powtarzaj, aż reszta będzie równa 0.", "NWD(48, 18) = NWD(18, 12) = NWD(12, 6) = 6")
            f("Dzielenie z resztą", "a = b·q + r,  0 ≤ r < b", null, "17 = 5·3 + 2")
            f(
                "Cechy podzielności",
                "2: ostatnia cyfra parzysta\n3: suma cyfr podzielna przez 3\n4: dwie ostatnie cyfry tworzą liczbę ⋮ 4\n5: ostatnia cyfra 0 lub 5\n9: suma cyfr podzielna przez 9\n10: ostatnia cyfra 0\n11: naprzemienna suma cyfr ⋮ 11",
                null,
            )
            f("Liczby parzyste i nieparzyste", "parzysta: 2k    nieparzysta: 2k + 1", "k ∈ ℤ. Kolejne liczby: n, n + 1, n + 2.")
            f("Ułamek okresowy na zwykły", "0,(a) = @f{a}{9}    0,(ab) = @f{ab}{99}", null, "0,(36) = @f{36}{99} = @f{4}{11}")
            f("Część całkowita", "x = ⌊x⌋ + @(x@)", "⌊x⌋ – największa liczba całkowita ≤ x, @(x@) ∈ ⟨0; 1) – część ułamkowa.")
            f("Proporcja", "@f{a}{b} = @f{c}{d} ⇔ a·d = b·c", "Mnożenie „na krzyż”.")
            f("Skala i podobieństwo map", "1 : n → 1 cm = n cm w terenie", "Pola w skali 1 : n²!")
        }

        category("rownania", "Równania i nierówności", "metody, parametry, przedziały") {
            f("Równanie iloczynowe", "a·b = 0 ⇔ a = 0 ∨ b = 0", null)
            f("Równanie x² = a", "x² = a ⇔ x = @s{a} ∨ x = −@s{a}", "Dla a ≥ 0. Częsty błąd: pominięcie rozwiązania ujemnego.")
            f(
                "Równanie z pierwiastkiem",
                "@s{f(x)} = g(x) ⇔ f(x) = g(x)² ∧ g(x) ≥ 0",
                "Po podniesieniu do kwadratu sprawdź rozwiązania!",
            )
            f("Mnożenie nierówności przez liczbę ujemną", "a < b ∧ c < 0 ⇒ ac > bc", "Zmiana zwrotu nierówności.")
            f(
                "Dwa różne pierwiastki (parametr)",
                "a ≠ 0 ∧ Δ > 0",
                "Dla równania ax² + bx + c = 0 z parametrem.",
                tags = "parametr",
            )
            f(
                "Dokładnie jedno rozwiązanie (parametr)",
                "a = 0 ∧ b ≠ 0  lub  a ≠ 0 ∧ Δ = 0",
                "Nie zapomnij o przypadku, gdy równanie staje się liniowe!",
                tags = "parametr",
            )
            f(
                "Pierwiastki różnych znaków",
                "a·c < 0",
                "Wtedy automatycznie Δ > 0.",
                tags = "parametr",
            )
            f(
                "Metoda podstawiania",
                "z jednego równania wyznacz zmienną\ni podstaw do drugiego",
                null,
            )
            f(
                "Metoda przeciwnych współczynników",
                "pomnóż równania tak, by przy jednej\nzmiennej były liczby przeciwne, i dodaj",
                null,
            )
            f(
                "Przedziały liczbowe",
                "(a; b): a < x < b\n⟨a; b⟩: a ≤ x ≤ b\n⟨a; b): a ≤ x < b\n(a; +∞): x > a",
                "Nawias okrągły – koniec nie należy, kwadratowy (ostry) – należy.",
            )
            f(
                "Nierówność kwadratowa – schemat",
                "1. Δ i pierwiastki\n2. szkic paraboli\n3. odczyt znaku",
                null,
            )
            f(
                "Nierówność typu x² < a",
                "x² < a ⇔ |x| < @s{a}",
                "Dla a > 0. Nie pisz „x < ±√a”!",
            )
        }

        category("zespolone", "Liczby zespolone", "a + bi, moduł, de Moivre") {
            f("Postać algebraiczna", "z = a + bi,  i² = −1", "a = Re z – część rzeczywista, b = Im z – urojona.")
            f("Potęgi jednostki urojonej", "i¹ = i,  i² = −1,  i³ = −i,  i⁴ = 1", "Potęgi i powtarzają się co 4.")
            f("Sprzężenie", "@o{z} = a − bi", null)
            f("Moduł", "|z| = @s{a² + b²}", null)
            f("Iloczyn przez sprzężenie", "z·@o{z} = |z|²", null)
            f("Mnożenie", "(a + bi)(c + di) = (ac − bd) + (ad + bc)i", null)
            f("Dzielenie", "@f{z₁}{z₂} = @f{z₁·@o{z₂}}{|z₂|²}", "Pomnóż licznik i mianownik przez sprzężenie mianownika.")
            f("Postać trygonometryczna", "z = |z|(cos φ + i·sin φ)", "cos φ = a/|z|, sin φ = b/|z|.")
            f("Wzór Eulera", "e^{iφ} = cos φ + i·sin φ", "Postać wykładnicza: z = |z|·e^{iφ}.")
            f("Wzór de Moivre’a", "z^{n} = |z|^{n}(cos nφ + i·sin nφ)", null)
            f(
                "Pierwiastki z liczby zespolonej",
                "w_{k} = @r{n}{|z|}(cos@f{φ + 2kπ}{n} + i·sin@f{φ + 2kπ}{n})",
                "k = 0, 1, …, n − 1 – n różnych pierwiastków.",
            )
            f("Równanie kwadratowe przy Δ < 0", "x₁,₂ = @f{−b ± i@s{−Δ}}{2a}", null)
        }

        category("macierze", "Macierze i wyznaczniki", "2×2, Sarrus, Cramer") {
            f("Wyznacznik 2×2", "det[a  b; c  d] = ad − bc", null)
            f(
                "Wyznacznik 3×3 (Sarrus)",
                "det[a b c; d e f; g h i] =\naei + bfg + cdh − ceg − afh − bdi",
                null,
            )
            f(
                "Wzory Cramera (3 niewiadome)",
                "x = @f{W_{x}}{W},  y = @f{W_{y}}{W},  z = @f{W_{z}}{W}",
                "W_x – wyznacznik z kolumną x zastąpioną wyrazami wolnymi. W ≠ 0.",
            )
            f("Macierz odwrotna 2×2", "A^{−1} = @f{1}{ad − bc}[d  −b; −c  a]", "Istnieje, gdy det A ≠ 0.")
            f("Mnożenie macierzy", "(AB)_{ij} = @sum{k}{} a_{ik}·b_{kj}", "Wiersz razy kolumna. Zwykle AB ≠ BA.")
            f("Własności wyznacznika", "det(AB) = det A·det B\ndet(A^{T}) = det A\ndet(kA) = k^{n}·det A", "n – stopień macierzy.")
        }
    }
}
