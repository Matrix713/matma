package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Probability {
    val section = section("prawdopodobienstwo", "Kombinatoryka i statystyka", "permutacje, P(A), średnia, wariancja") {

        category("kombinatoryka", "Kombinatoryka", "silnia, wariacje, kombinacje, Newton") {
            f("Silnia", "n! = 1·2·3·…·n    0! = 1", null, "5! = 120", calc = "kombinatoryka")
            f("Permutacje", "P_{n} = n!", "Liczba ustawień n różnych elementów w ciąg.", "4 osoby w kolejce: 24", calc = "kombinatoryka")
            f(
                "Permutacje z powtórzeniami",
                "@f{n!}{n₁!·n₂!·…·n_{k}!}",
                "Ustawienia n elementów, wśród których są grupy jednakowych.",
                "Słowo MAMA: @f{4!}{2!·2!} = 6",
            )
            f("Permutacje cykliczne", "(n − 1)!", "Sadzanie n osób przy okrągłym stole.")
            f(
                "Wariacje bez powtórzeń",
                "V_{n}^{k} = @f{n!}{(n − k)!}",
                "Ciągi k różnych elementów z n (kolejność ważna).",
                "podium z 8 zawodników: 8·7·6 = 336",
                calc = "kombinatoryka",
            )
            f(
                "Wariacje z powtórzeniami",
                "W_{n}^{k} = n^{k}",
                "Ciągi długości k o wyrazach z n-elementowego zbioru.",
                "PIN 4-cyfrowy: 10⁴",
                calc = "kombinatoryka",
            )
            f(
                "Kombinacje",
                "C_{n}^{k} = @b{n}{k} = @f{n!}{k!·(n − k)!}",
                "Podzbiory k-elementowe zbioru n-elementowego (kolejność nieważna).",
                "@b{49}{6} = 13 983 816",
                tags = "symbol newtona",
                calc = "kombinatoryka",
            )
            f("Kombinacje z powtórzeniami", "@b{n + k − 1}{k}", null, calc = "kombinatoryka")
            f(
                "Własności symbolu Newtona",
                "@b{n}{0} = @b{n}{n} = 1    @b{n}{1} = n\n@b{n}{k} = @b{n}{n − k}",
                null,
            )
            f("Liczba par / odcinków", "@b{n}{2} = @f{n(n − 1)}{2}", "Np. liczba uścisków dłoni lub meczów „każdy z każdym”.")
            f("Trójkąt Pascala", "@b{n}{k} + @b{n}{k + 1} = @b{n + 1}{k + 1}", null)
            f("Suma symboli Newtona", "@sum{k=0}{n}@b{n}{k} = 2^{n}", null)
            f("Liczba podzbiorów", "2^{n}", "Wszystkie podzbiory zbioru n-elementowego (z pustym).")
            f("Reguła mnożenia", "n₁·n₂·…·n_{k}", "Gdy wybór składa się z k etapów.")
            f("Reguła dodawania", "|A ∪ B| = |A| + |B| − |A ∩ B|", null)
            f("Rozmieszczenia (gwiazdki i kreski)", "@b{n + k − 1}{k − 1}", "Liczba rozwiązań x₁ + … + x_k = n w liczbach naturalnych (z zerem).")
        }

        category("prawdopodobienstwo", "Prawdopodobieństwo", "klasyczne, warunkowe, Bernoulli") {
            f(
                "Prawdopodobieństwo klasyczne",
                "P(A) = @f{|A|}{|Ω|}",
                "Gdy wszystkie zdarzenia elementarne są jednakowo prawdopodobne.",
                "suma oczek = 7 przy 2 kostkach: @f{6}{36} = @f{1}{6}",
            )
            f("Własności prawdopodobieństwa", "0 ≤ P(A) ≤ 1    P(Ω) = 1    P(∅) = 0", null)
            f("Zdarzenie przeciwne", "P(A') = 1 − P(A)", "Przydatne przy „co najmniej jeden”.")
            f("Suma zdarzeń", "P(A ∪ B) = P(A) + P(B) − P(A ∩ B)", null)
            f("Zdarzenia rozłączne", "P(A ∪ B) = P(A) + P(B)", "Gdy A ∩ B = ∅.")
            f("Różnica zdarzeń", "P(A ∖ B) = P(A) − P(A ∩ B)", null)
            f("Monotoniczność", "A ⊂ B ⇒ P(A) ≤ P(B)", null)
            f(
                "Prawdopodobieństwo warunkowe",
                "P(A|B) = @f{P(A ∩ B)}{P(B)}",
                "P(B) > 0. Prawdopodobieństwo A pod warunkiem, że zaszło B.",
            )
            f("Zdarzenia niezależne", "P(A ∩ B) = P(A)·P(B)", null)
            f(
                "Prawdopodobieństwo całkowite",
                "P(A) = P(A|B₁)·P(B₁) + … + P(A|B_{n})·P(B_{n})",
                "B₁, …, Bₙ – rozbicie Ω (zdarzenia rozłączne, w sumie Ω).",
            )
            f("Wzór Bayesa", "P(B_{k}|A) = @f{P(A|B_{k})·P(B_{k})}{P(A)}", null)
            f(
                "Schemat Bernoulliego",
                "P(S_{n} = k) = @b{n}{k}·p^{k}·(1 − p)^{n−k}",
                "k sukcesów w n niezależnych próbach, p – prawdopodobieństwo sukcesu.",
                "3 orły w 5 rzutach: @b{5}{3}·(@f{1}{2})⁵ = @f{10}{32}",
                tags = "bernoulli",
            )
            f("Co najmniej jeden sukces", "1 − (1 − p)^{n}", null)
            f("Najbardziej prawdopodobna liczba sukcesów", "k = ⌊(n + 1)·p⌋", "Gdy (n + 1)p jest całkowite – dwie wartości: k i k − 1.")
            f("Prawdopodobieństwo geometryczne", "P(A) = @f{|A|}{|Ω|}", "|·| – długość, pole lub objętość.")
            f("Drzewko stochastyczne", "mnożymy wzdłuż gałęzi\ndodajemy różne gałęzie", null)
        }

        category("zmienne-losowe", "Zmienna losowa", "E(X), wariancja, rozkłady") {
            f("Wartość oczekiwana", "E(X) = x₁p₁ + x₂p₂ + … + x_{n}p_{n}", null)
            f("Wariancja zmiennej losowej", "D²(X) = E(X²) − (E(X))²", null)
            f("Odchylenie standardowe", "σ = @s{D²(X)}", null)
            f("Własności E i D²", "E(aX + b) = a·E(X) + b\nD²(aX + b) = a²·D²(X)", null)
            f("Rozkład dwumianowy", "E(X) = np    D²(X) = np(1 − p)", null)
            f(
                "Rozkład normalny",
                "f(x) = @f{1}{σ@s{2π}}·e^{−@f{(x − μ)²}{2σ²}}",
                "μ – średnia, σ – odchylenie standardowe. Krzywa Gaussa.",
            )
            f(
                "Reguła trzech sigm",
                "μ ± σ: ≈ 68%\nμ ± 2σ: ≈ 95%\nμ ± 3σ: ≈ 99,7%",
                "Dla rozkładu normalnego.",
            )
            f("Standaryzacja", "Z = @f{X − μ}{σ}", null)
            f("Rozkład Poissona", "P(X = k) = @f{λ^{k}·e^{−λ}}{k!}", "E(X) = D²(X) = λ.")
            f("Rozkład jednostajny", "E = @f{a + b}{2}    D² = @f{(b − a)²}{12}", "Na przedziale ⟨a; b⟩.")
            f("Rozkład geometryczny", "P(X = k) = (1 − p)^{k−1}·p    E = @f{1}{p}", "Numer próby z pierwszym sukcesem.")
        }

        category("statystyka", "Statystyka", "średnia, mediana, wariancja, korelacja") {
            f("Średnia arytmetyczna", "@o{x} = @f{x₁ + x₂ + … + x_{n}}{n}", null, "2, 4, 9: @o{x} = 5")
            f("Średnia ważona", "@f{w₁x₁ + w₂x₂ + … + w_{n}x_{n}}{w₁ + w₂ + … + w_{n}}", "w – wagi.")
            f(
                "Mediana",
                "n nieparzyste: x_{@f{n+1}{2}}\nn parzyste: @f{1}{2}(x_{@f{n}{2}} + x_{@f{n}{2}+1})",
                "Dane muszą być uporządkowane rosnąco!",
                "1, 3, 7, 8: mediana = 5",
            )
            f("Dominanta (moda)", "najczęstsza wartość", "Może być kilka lub żadna.")
            f("Rozstęp", "R = x_{max} − x_{min}", null)
            f(
                "Wariancja",
                "σ² = @f{(x₁ − @o{x})² + … + (x_{n} − @o{x})²}{n}",
                null,
                "2, 4, 9: σ² = @f{9 + 1 + 16}{3} = @f{26}{3}",
            )
            f("Wariancja – wzór skrócony", "σ² = @f{x₁² + … + x_{n}²}{n} − @o{x}²", "Średnia kwadratów minus kwadrat średniej.")
            f("Odchylenie standardowe danych", "σ = @s{σ²}", null)
            f("Odchylenie przeciętne", "d = @f{|x₁ − @o{x}| + … + |x_{n} − @o{x}|}{n}", null)
            f("Wariancja z próby", "s² = @f{1}{n − 1}@sum{i=1}{n}(x_{i} − @o{x})²", "Estymator nieobciążony.")
            f("Współczynnik zmienności", "V = @f{σ}{@o{x}}·100%", null)
            f("Kwartyle", "Q₁, Q₂ = mediana, Q₃\nIQR = Q₃ − Q₁", "Q₁ – mediana dolnej połowy danych, Q₃ – górnej.")
            f("Średnia z szeregu rozdzielczego", "@o{x} = @f{x₁n₁ + x₂n₂ + … + x_{k}n_{k}}{n}", "n_i – liczebności.")
            f(
                "Współczynnik korelacji Pearsona",
                "r = @f{@sum{}{}(x_{i} − @o{x})(y_{i} − @o{y})}{@s{@sum{}{}(x_{i} − @o{x})²·@sum{}{}(y_{i} − @o{y})²}}",
                "−1 ≤ r ≤ 1. |r| bliskie 1 – silna zależność liniowa.",
            )
            f(
                "Regresja liniowa",
                "y = ax + b\na = @f{@sum{}{}(x_{i} − @o{x})(y_{i} − @o{y})}{@sum{}{}(x_{i} − @o{x})²},  b = @o{y} − a@o{x}",
                "Metoda najmniejszych kwadratów.",
            )
        }
    }
}
