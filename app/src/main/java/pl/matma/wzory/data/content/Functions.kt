package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Functions {
    val section = section("funkcje", "Funkcje", "liniowa, kwadratowa, wielomiany, log") {

        category("funkcja-pojecia", "Własności funkcji", "dziedzina, monotoniczność, parzystość") {
            f(
                "Dziedzina – typowe warunki",
                "@f{1}{g(x)}: g(x) ≠ 0\n@s{g(x)}: g(x) ≥ 0\nlog_{a} g(x): g(x) > 0, a > 0, a ≠ 1\ntg x: x ≠ @f{π}{2} + kπ",
                "Wyznaczając dziedzinę, wypisz wszystkie warunki i weź ich część wspólną.",
                tags = "dziedzina funkcji zalozenia",
            )
            f(
                "Miejsce zerowe",
                "f(x₀) = 0",
                "Argument, dla którego wartość funkcji jest równa 0 – punkt przecięcia wykresu z osią OX.",
            )
            f(
                "Punkt przecięcia z osią OY",
                "(0; f(0))",
                "Istnieje, gdy 0 należy do dziedziny.",
            )
            f(
                "Funkcja rosnąca",
                "x₁ < x₂ ⇒ f(x₁) < f(x₂)",
                "Dla dowolnych x₁, x₂ z przedziału. Niemalejąca: f(x₁) ≤ f(x₂).",
            )
            f(
                "Funkcja malejąca",
                "x₁ < x₂ ⇒ f(x₁) > f(x₂)",
                "Nierosnąca: f(x₁) ≥ f(x₂).",
            )
            f(
                "Funkcja parzysta",
                "f(−x) = f(x)",
                "Wykres symetryczny względem osi OY. Np. x², cos x, |x|.",
            )
            f(
                "Funkcja nieparzysta",
                "f(−x) = −f(x)",
                "Wykres symetryczny względem punktu O. Np. x³, sin x, 1/x.",
            )
            f(
                "Funkcja okresowa",
                "f(x + T) = f(x)",
                "T > 0 – okres. Najmniejszy dodatni okres to okres podstawowy.",
            )
            f(
                "Funkcja różnowartościowa",
                "x₁ ≠ x₂ ⇒ f(x₁) ≠ f(x₂)",
                "Każda wartość przyjmowana co najwyżej raz. Tylko taka funkcja ma funkcję odwrotną.",
            )
            f(
                "Złożenie funkcji",
                "(f∘g)(x) = f(g(x))",
                "Najpierw działa g, potem f. Zwykle f∘g ≠ g∘f.",
                "f(x) = x², g(x) = x + 1\n(f∘g)(x) = (x + 1)²",
            )
            f(
                "Funkcja odwrotna",
                "y = f(x) ⇔ x = f^{−1}(y)\nf(f^{−1}(x)) = x",
                "Wykres f^{−1} jest symetryczny do wykresu f względem prostej y = x. Wyznacz x z równania y = f(x) i zamień litery.",
                "f(x) = 2x + 4 ⇒ f^{−1}(x) = @f{x − 4}{2}",
            )
            f(
                "Iloraz różnicowy (średnie tempo zmian)",
                "@f{f(x₂) − f(x₁)}{x₂ − x₁}",
                "Współczynnik kierunkowy siecznej przez punkty wykresu.",
            )
            f(
                "Wartość największa i najmniejsza w przedziale",
                "sprawdź f na końcach przedziału\ni w punktach krytycznych",
                "Funkcja ciągła na przedziale domkniętym ⟨a; b⟩ osiąga tam swoje wartości największą i najmniejszą.",
            )
            f(
                "Funkcja ograniczona",
                "m ≤ f(x) ≤ M",
                "Dla każdego x z dziedziny.",
            )
            f(
                "Funkcja przedziałami",
                "f(x) = @sys{g(x), x < a}{h(x), x ≥ a}",
                "Wykres rysujemy osobno na każdym przedziale.",
            )
        }

        category("liniowa", "Funkcja liniowa", "y = ax + b, miejsce zerowe, układy") {
            f(
                "Wzór funkcji liniowej",
                "f(x) = ax + b",
                "Wykresem jest prosta. a – współczynnik kierunkowy, b – wyraz wolny.",
                calc = "liniowa",
            )
            f(
                "Monotoniczność funkcji liniowej",
                "a > 0: rosnąca\na < 0: malejąca\na = 0: stała",
                null,
                calc = "liniowa",
            )
            f(
                "Miejsce zerowe funkcji liniowej",
                "x₀ = −@f{b}{a}",
                "Dla a ≠ 0.",
                "f(x) = 3x − 6 ⇒ x₀ = 2",
                calc = "liniowa",
            )
            f(
                "Współczynnik a z dwóch punktów",
                "a = @f{f(x₂) − f(x₁)}{x₂ − x₁}",
                null,
                calc = "prosta-2pkt",
            )
            f(
                "Kąt nachylenia wykresu",
                "a = tg α",
                null,
            )
            f(
                "Funkcja liniowa przez dwa punkty",
                "y = @f{y₂ − y₁}{x₂ − x₁}(x − x₁) + y₁",
                null,
                calc = "prosta-2pkt",
            )
            f(
                "Proporcjonalność prosta",
                "y = ax  ⇔  @f{y}{x} = a",
                "a ≠ 0 – współczynnik proporcjonalności. Wykres przechodzi przez (0; 0).",
            )
            f(
                "Nierówność liniowa",
                "ax + b > 0\na > 0: x > −@f{b}{a}\na < 0: x < −@f{b}{a}",
                "Dzieląc przez liczbę ujemną, zmieniamy zwrot nierówności!",
            )
            f(
                "Liczba rozwiązań ax + b = 0",
                "a ≠ 0: jedno\na = 0, b = 0: nieskończenie wiele\na = 0, b ≠ 0: brak",
                null,
            )
            f(
                "Układ równań – wzory Cramera",
                "W = a₁b₂ − a₂b₁\nW_{x} = c₁b₂ − c₂b₁,  W_{y} = a₁c₂ − a₂c₁\nx = @f{W_{x}}{W},  y = @f{W_{y}}{W}",
                "Dla układu a₁x + b₁y = c₁, a₂x + b₂y = c₂ przy W ≠ 0.",
                calc = "cramer",
            )
            f(
                "Rodzaje układów równań liniowych",
                "oznaczony: W ≠ 0 (1 rozwiązanie)\nnieoznaczony: W = W_{x} = W_{y} = 0 (∞)\nsprzeczny: W = 0, W_{x} ≠ 0 lub W_{y} ≠ 0",
                "Geometrycznie: proste przecinają się / pokrywają się / są równoległe.",
                calc = "cramer",
            )
        }

        category("kwadratowa", "Funkcja kwadratowa", "Δ, pierwiastki, wierzchołek, Viète") {
            f(
                "Postać ogólna",
                "f(x) = ax² + bx + c,  a ≠ 0",
                "Wykres – parabola. a > 0: ramiona w górę, a < 0: w dół.",
                calc = "kwadratowa",
            )
            f(
                "Wyróżnik (delta)",
                "Δ = b² − 4ac",
                "Δ > 0 – dwa pierwiastki, Δ = 0 – jeden (podwójny), Δ < 0 – brak pierwiastków.",
                "x² − 2x − 3: Δ = 4 + 12 = 16",
                tags = "delta wyroznik",
                calc = "kwadratowa",
            )
            f(
                "Pierwiastki równania kwadratowego",
                "x₁ = @f{−b − @s{Δ}}{2a}    x₂ = @f{−b + @s{Δ}}{2a}",
                "Dla Δ > 0.",
                "x² − 2x − 3 = 0: x₁ = @f{2 − 4}{2} = −1, x₂ = 3",
                tags = "miejsca zerowe rozwiazania delta",
                calc = "kwadratowa",
            )
            f(
                "Pierwiastek podwójny",
                "x₀ = −@f{b}{2a}",
                "Dla Δ = 0.",
                calc = "kwadratowa",
            )
            f(
                "Delta „ćwiartkowa” (b parzyste)",
                "Δ' = (@f{b}{2})² − ac\nx₁,₂ = @f{−@f{b}{2} ± @s{Δ'}}{a}",
                "Mniejsze liczby, gdy b jest parzyste.",
            )
            f(
                "Wierzchołek paraboli",
                "W = (p; q)\np = −@f{b}{2a},  q = −@f{Δ}{4a}",
                "Również q = f(p).",
                "f(x) = x² − 2x − 3: p = 1, q = f(1) = −4",
                tags = "wierzcholek",
                calc = "kwadratowa",
            )
            f(
                "Postać kanoniczna",
                "f(x) = a(x − p)² + q",
                "(p; q) – wierzchołek paraboli.",
                "x² − 2x − 3 = (x − 1)² − 4",
                calc = "kwadratowa",
            )
            f(
                "Postać iloczynowa",
                "f(x) = a(x − x₁)(x − x₂)",
                "Gdy Δ ≥ 0 (dla Δ = 0: a(x − x₀)²). Dla Δ < 0 nie istnieje.",
                "x² − 2x − 3 = (x + 1)(x − 3)",
                calc = "kwadratowa",
            )
            f(
                "Wzory Viète’a",
                "x₁ + x₂ = −@f{b}{a}\nx₁ · x₂ = @f{c}{a}",
                "Gdy Δ ≥ 0.",
                tags = "viete wzory vieta",
                calc = "kwadratowa",
            )
            f(
                "Suma kwadratów pierwiastków",
                "x₁² + x₂² = (x₁ + x₂)² − 2x₁x₂",
                "Wyrażamy przez wzory Viète’a.",
            )
            f(
                "Suma odwrotności pierwiastków",
                "@f{1}{x₁} + @f{1}{x₂} = @f{x₁ + x₂}{x₁x₂} = −@f{b}{c}",
                null,
            )
            f(
                "Różnica pierwiastków",
                "|x₁ − x₂| = @f{@s{Δ}}{|a|}",
                null,
            )
            f(
                "Suma sześcianów pierwiastków",
                "x₁³ + x₂³ = (x₁ + x₂)³ − 3x₁x₂(x₁ + x₂)",
                null,
            )
            f(
                "Oś symetrii paraboli",
                "x = p = @f{x₁ + x₂}{2}",
                "Pierwsza współrzędna wierzchołka to średnia pierwiastków.",
            )
            f(
                "Zbiór wartości",
                "a > 0: ⟨q; +∞)\na < 0: (−∞; q⟩",
                null,
                calc = "kwadratowa",
            )
            f(
                "Monotoniczność",
                "a > 0: maleje w (−∞; p⟩, rośnie w ⟨p; +∞)\na < 0: rośnie w (−∞; p⟩, maleje w ⟨p; +∞)",
                null,
                calc = "kwadratowa",
            )
            f(
                "Wartość najmniejsza/największa w ⟨m; n⟩",
                "p ∈ ⟨m; n⟩: porównaj f(p), f(m), f(n)\np ∉ ⟨m; n⟩: porównaj f(m), f(n)",
                null,
            )
            f(
                "Znaki pierwiastków",
                "dwa dodatnie: Δ > 0, x₁x₂ > 0, x₁ + x₂ > 0\ndwa ujemne: Δ > 0, x₁x₂ > 0, x₁ + x₂ < 0\nróżnych znaków: x₁x₂ < 0 (a·c < 0)",
                "Typowe warunki w zadaniach z parametrem.",
                tags = "parametr",
            )
            f(
                "Nierówność kwadratowa",
                "a > 0, Δ > 0:\nax² + bx + c > 0 ⇔ x ∈ (−∞; x₁) ∪ (x₂; +∞)\nax² + bx + c < 0 ⇔ x ∈ (x₁; x₂)",
                "Zawsze naszkicuj parabolę i odczytaj znak. Dla a < 0 – odwrotnie.",
            )
            f(
                "Równania niezupełne",
                "ax² + bx = 0 ⇔ x(ax + b) = 0\nax² + c = 0 ⇔ x² = −@f{c}{a}",
                "Bez delty – szybciej.",
            )
            f(
                "Równanie dwukwadratowe",
                "ax⁴ + bx² + c = 0,  t = x², t ≥ 0",
                "Rozwiąż równanie kwadratowe z t, odrzuć ujemne t, potem x = ±@s{t}.",
            )
            f(
                "Parabola z wierzchołka i punktu",
                "a = @f{y₀ − q}{(x₀ − p)²}",
                "Gdy znamy W(p; q) i punkt (x₀; y₀) paraboli.",
            )
        }

        category("wielomiany", "Wielomiany", "Bézout, Horner, pierwiastki wymierne") {
            f(
                "Postać wielomianu",
                "W(x) = a_{n}x^{n} + a_{n−1}x^{n−1} + … + a₁x + a₀",
                "a_{n} ≠ 0 – stopień n. a₀ – wyraz wolny = W(0).",
            )
            f(
                "Twierdzenie Bézouta",
                "W(a) = 0 ⇔ (x − a) | W(x)",
                "Liczba a jest pierwiastkiem ⇔ wielomian dzieli się przez (x − a).",
                tags = "bezout",
            )
            f(
                "Reszta z dzielenia przez (x − a)",
                "R = W(a)",
                null,
                "W(x) = x³ − 2x + 5, dzielnik (x − 2): R = W(2) = 9",
            )
            f(
                "Reszta z dzielenia przez (x − a)(x − b)",
                "R(x) = px + q\n@sys{W(a) = pa + q}{W(b) = pb + q}",
                "Reszta ma stopień mniejszy niż dzielnik.",
            )
            f(
                "Dzielenie z resztą",
                "W(x) = P(x)·Q(x) + R(x)",
                "st R < st P.",
            )
            f(
                "Pierwiastki wymierne",
                "x = @f{p}{q},  p | a₀,  q | a_{n}",
                "Kandydaci na pierwiastki wymierne wielomianu o współczynnikach całkowitych.",
            )
            f(
                "Pierwiastki całkowite",
                "x | a₀",
                "Całkowity pierwiastek dzieli wyraz wolny.",
                "x³ − 6x² + 11x − 6: kandydaci ±1, ±2, ±3, ±6",
            )
            f(
                "Pierwiastek k-krotny",
                "(x − a)^{k} | W(x),  (x − a)^{k+1} ∤ W(x)",
                "W „wężyku” przy pierwiastku parzystej krotności wykres odbija się od osi.",
            )
            f(
                "Schemat Hornera",
                "b_{n−1} = a_{n}\nb_{k−1} = a_{k} + c·b_{k}\nR = a₀ + c·b₀",
                "Dzielenie W(x) przez (x − c): kolejne b to współczynniki ilorazu.",
                tags = "horner",
            )
            f(
                "Wzory Viète’a – stopień 3",
                "x₁ + x₂ + x₃ = −@f{b}{a}\nx₁x₂ + x₁x₃ + x₂x₃ = @f{c}{a}\nx₁x₂x₃ = −@f{d}{a}",
                "Dla ax³ + bx² + cx + d = 0.",
            )
            f(
                "Suma współczynników",
                "W(1) = a_{n} + … + a₁ + a₀",
                "W(−1) = różnica sum współczynników przy potęgach parzystych i nieparzystych.",
            )
            f(
                "Liczba pierwiastków",
                "stopień n ⇒ co najwyżej n pierwiastków",
                "Wielomian stopnia nieparzystego ma co najmniej jeden pierwiastek rzeczywisty.",
            )
            f(
                "Równość wielomianów",
                "W = P ⇔ te same stopnie\ni równe współczynniki",
                "Przyrównuj współczynniki przy tych samych potęgach.",
            )
            f(
                "Stopień iloczynu i sumy",
                "st(P·Q) = st P + st Q\nst(P + Q) ≤ max(st P, st Q)",
                null,
            )
            f(
                "Nierówności wielomianowe (wężyk)",
                "rozłóż na czynniki → zaznacz pierwiastki\n→ rysuj od prawej, z góry gdy a_{n} > 0",
                "Przy pierwiastkach parzystej krotności wykres „odbija się”, przy nieparzystej przechodzi przez oś.",
            )
            f(
                "Rozkład przez grupowanie",
                "x³ + x² − 4x − 4 = x²(x + 1) − 4(x + 1)\n= (x + 1)(x − 2)(x + 2)",
                "Wyłącz wspólny czynnik z par wyrazów.",
            )
        }

        category("wymierna", "Funkcje wymierne", "a/x, homograficzna, asymptoty") {
            f(
                "Proporcjonalność odwrotna",
                "f(x) = @f{a}{x},  a ≠ 0",
                "Wykres – hiperbola. x·y = a. a > 0: ćwiartki I i III, a < 0: II i IV.",
            )
            f(
                "Funkcja homograficzna",
                "f(x) = @f{ax + b}{cx + d}",
                "c ≠ 0 i ad − bc ≠ 0.",
                calc = "homograficzna",
            )
            f(
                "Asymptoty funkcji homograficznej",
                "pionowa: x = −@f{d}{c}\npozioma: y = @f{a}{c}",
                null,
                calc = "homograficzna",
            )
            f(
                "Postać kanoniczna homograficznej",
                "f(x) = @f{k}{x − p} + q",
                "Wykres y = k/x przesunięty o wektor [p; q]. k = (bc − ad)/c², p = −d/c, q = a/c.",
                "f(x) = @f{2x + 1}{x − 3} = @f{7}{x − 3} + 2",
                calc = "homograficzna",
            )
            f(
                "Monotoniczność homograficznej",
                "k > 0: malejąca w (−∞; p) i (p; +∞)\nk < 0: rosnąca w (−∞; p) i (p; +∞)",
                "Nie jest monotoniczna w całej dziedzinie!",
                calc = "homograficzna",
            )
            f(
                "Równanie wymierne",
                "@f{W(x)}{P(x)} = 0 ⇔ W(x) = 0 ∧ P(x) ≠ 0",
                "Zawsze zacznij od dziedziny.",
            )
            f(
                "Nierówność wymierna",
                "@f{W(x)}{P(x)} > 0 ⇔ W(x)·P(x) > 0",
                "Nie mnóż przez mianownik o nieznanym znaku – przenieś wszystko na jedną stronę.",
            )
            f(
                "Dodawanie ułamków algebraicznych",
                "@f{a}{b} + @f{c}{d} = @f{ad + bc}{bd}",
                null,
            )
            f(
                "Mnożenie i dzielenie ułamków",
                "@f{a}{b}·@f{c}{d} = @f{ac}{bd}    @f{a}{b} : @f{c}{d} = @f{ad}{bc}",
                null,
            )
        }

        category("wykladnicza", "Funkcja wykładnicza", "aˣ, równania, wzrost i rozpad") {
            f(
                "Funkcja wykładnicza",
                "f(x) = a^{x},  a > 0, a ≠ 1",
                "D = ℝ, ZW = (0; +∞), przechodzi przez (0; 1), asymptota y = 0.",
            )
            f(
                "Monotoniczność aˣ",
                "a > 1: rosnąca\n0 < a < 1: malejąca",
                null,
            )
            f(
                "Równanie wykładnicze",
                "a^{f(x)} = a^{g(x)} ⇔ f(x) = g(x)",
                "Sprowadź obie strony do tej samej podstawy.",
                "2^{x+1} = 8 ⇔ x + 1 = 3 ⇔ x = 2",
            )
            f(
                "Nierówność wykładnicza",
                "a > 1: a^{f(x)} > a^{g(x)} ⇔ f(x) > g(x)\n0 < a < 1: a^{f(x)} > a^{g(x)} ⇔ f(x) < g(x)",
                "Przy podstawie mniejszej od 1 zmieniamy zwrot.",
            )
            f(
                "Liczba e",
                "e = @lim{n→∞}(1 + @f{1}{n})^{n} ≈ 2,71828",
                "Podstawa logarytmu naturalnego.",
            )
            f(
                "Wzrost/zanik wykładniczy",
                "N(t) = N₀·e^{kt}",
                "k > 0 – wzrost, k < 0 – zanik.",
            )
            f(
                "Rozpad połowiczny",
                "N(t) = N₀·(@f{1}{2})^{t/T}",
                "T – czas połowicznego rozpadu.",
            )
            f(
                "Równanie z podstawieniem",
                "4^{x} − 3·2^{x} + 2 = 0\nt = 2^{x} > 0: t² − 3t + 2 = 0",
                "Pamiętaj, że t = aˣ musi być dodatnie.",
            )
        }

        category("logarytmy", "Logarytmy", "definicja, działania, zmiana podstawy") {
            f(
                "Definicja logarytmu",
                "log_{a} b = c ⇔ a^{c} = b",
                "a > 0, a ≠ 1, b > 0.",
                "log₂ 8 = 3, bo 2³ = 8",
                tags = "logarytm",
                calc = "logarytm",
            )
            f(
                "Logarytmy szczególne",
                "log_{a} 1 = 0    log_{a} a = 1",
                null,
            )
            f(
                "Podstawowa tożsamość",
                "a^{log_{a} b} = b",
                null,
            )
            f(
                "Logarytm iloczynu",
                "log_{a}(x·y) = log_{a} x + log_{a} y",
                null,
            )
            f(
                "Logarytm ilorazu",
                "log_{a} @f{x}{y} = log_{a} x − log_{a} y",
                null,
            )
            f(
                "Logarytm potęgi",
                "log_{a} x^{n} = n·log_{a} x",
                "Także log_a @r{n}{x} = (1/n)·log_a x.",
                "log₂ 32 = log₂ 2⁵ = 5",
            )
            f(
                "Potęga w podstawie",
                "log_{a^{n}} x = @f{1}{n}·log_{a} x",
                null,
            )
            f(
                "Zmiana podstawy logarytmu",
                "log_{a} b = @f{log_{c} b}{log_{c} a}",
                "Kalkulator liczy log i ln – np. log₃ 7 = ln 7 / ln 3.",
                calc = "logarytm",
            )
            f(
                "Odwrócenie logarytmu",
                "log_{a} b = @f{1}{log_{b} a}",
                null,
            )
            f(
                "Łańcuch logarytmów",
                "log_{a} b · log_{b} c = log_{a} c",
                null,
            )
            f(
                "Zamiana podstawy z wykładnikiem",
                "a^{log_{b} c} = c^{log_{b} a}",
                null,
            )
            f(
                "Logarytm dziesiętny i naturalny",
                "log x = log₁₀ x    ln x = log_{e} x",
                null,
                calc = "logarytm",
            )
            f(
                "Funkcja logarytmiczna",
                "f(x) = log_{a} x",
                "D = (0; +∞), ZW = ℝ, przez (1; 0), asymptota x = 0. a > 1 – rosnąca, 0 < a < 1 – malejąca.",
            )
            f(
                "Równanie logarytmiczne",
                "log_{a} f(x) = log_{a} g(x) ⇔ f(x) = g(x) > 0",
                "Najpierw dziedzina: f(x) > 0 i g(x) > 0.",
            )
            f(
                "Nierówność logarytmiczna",
                "a > 1: log_{a} f > log_{a} g ⇔ f > g > 0\n0 < a < 1: log_{a} f > log_{a} g ⇔ 0 < f < g",
                null,
            )
            f(
                "Liczba cyfr liczby",
                "⌊log N⌋ + 1",
                "Liczba cyfr liczby naturalnej N w zapisie dziesiętnym.",
                "2¹⁰⁰: ⌊100·log 2⌋ + 1 = 31 cyfr",
            )
        }

        category("modul", "Wartość bezwzględna", "|x|, równania i nierówności") {
            f(
                "Definicja wartości bezwzględnej",
                "|x| = @sys{x,  gdy x ≥ 0}{−x,  gdy x < 0}",
                null,
                tags = "modul",
            )
            f(
                "Moduł jako pierwiastek",
                "|x| = @s{x²}",
                null,
            )
            f(
                "Interpretacja geometryczna",
                "|x − a| = odległość x od a",
                "Na osi liczbowej.",
            )
            f(
                "Równanie |x| = a",
                "|x| = a ⇔ x = a ∨ x = −a",
                "Dla a ≥ 0; dla a < 0 brak rozwiązań.",
            )
            f(
                "Nierówność |x| < a",
                "|x| < a ⇔ −a < x < a",
                "Dla a > 0.",
                "|x − 3| < 2 ⇔ x ∈ (1; 5)",
            )
            f(
                "Nierówność |x| > a",
                "|x| > a ⇔ x < −a ∨ x > a",
                null,
                "|x + 1| > 4 ⇔ x ∈ (−∞; −5) ∪ (3; +∞)",
            )
            f(
                "Przedział jako moduł",
                "|x − a| ≤ r ⇔ x ∈ ⟨a − r; a + r⟩",
                null,
            )
            f(
                "Własności modułu",
                "|a·b| = |a|·|b|    |@f{a}{b}| = @f{|a|}{|b|}\n|−a| = |a|    |a|² = a²",
                null,
            )
            f(
                "Nierówność trójkąta",
                "|a + b| ≤ |a| + |b|\n| |a| − |b| | ≤ |a − b|",
                null,
            )
            f(
                "Równanie z kilkoma modułami",
                "wyznacz miejsca zerowe wyrażeń\npod modułami i rozpatrz przedziały",
                "W każdym przedziale opuść moduły z odpowiednim znakiem i sprawdź, czy rozwiązanie należy do przedziału.",
            )
        }
    }
}
