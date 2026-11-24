package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Calculus {
    val section = section("analiza", "Analiza", "granice, pochodne, całki") {

        category("granice-funkcji", "Granice i asymptoty", "granice specjalne, de l’Hospital") {
            f("Granica sin x / x", "@lim{x→0}@f{sin x}{x} = 1", null)
            f("Granica tg x / x", "@lim{x→0}@f{tg x}{x} = 1", null)
            f("Granica (1 − cos x)/x²", "@lim{x→0}@f{1 − cos x}{x²} = @f{1}{2}", null)
            f("Liczba e – granica funkcji", "@lim{x→±∞}(1 + @f{1}{x})^{x} = e    @lim{x→0}(1 + x)^{@f{1}{x}} = e", null)
            f("Granica wykładnicza", "@lim{x→0}@f{e^{x} − 1}{x} = 1    @lim{x→0}@f{a^{x} − 1}{x} = ln a", null)
            f("Granica logarytmiczna", "@lim{x→0}@f{ln(1 + x)}{x} = 1", null)
            f("Szybkość wzrostu", "@lim{x→∞}@f{x^{n}}{e^{x}} = 0    @lim{x→∞}@f{ln x}{x} = 0", "Wykładnicza rośnie szybciej niż potęgowa, a ta szybciej niż logarytm.")
            f(
                "Reguła de l’Hospitala",
                "@lim{x→a}@f{f(x)}{g(x)} = @lim{x→a}@f{f'(x)}{g'(x)}",
                "Dla symboli 0/0 lub ∞/∞, gdy prawa granica istnieje.",
                tags = "hospital",
            )
            f(
                "Istnienie granicy",
                "@lim{x→a⁻}f(x) = @lim{x→a⁺}f(x) = g",
                "Granica w punkcie istnieje ⇔ granice jednostronne istnieją i są równe.",
            )
            f("Asymptota pionowa", "x = a,  gdy  @lim{x→a^{±}}f(x) = ±∞", "Szukaj w punktach wyłączonych z dziedziny.")
            f("Asymptota pozioma", "y = b,  b = @lim{x→±∞}f(x)", null)
            f(
                "Asymptota ukośna",
                "y = mx + k\nm = @lim{x→±∞}@f{f(x)}{x},  k = @lim{x→±∞}(f(x) − mx)",
                "Gdy m = 0 – asymptota pozioma.",
            )
            f("Ciągłość funkcji", "@lim{x→x₀}f(x) = f(x₀)", "Funkcja ciągła w punkcie – granica równa wartości.")
            f(
                "Własność Darboux",
                "f ciągła na ⟨a; b⟩, f(a)·f(b) < 0\n⇒ ∃ c ∈ (a; b): f(c) = 0",
                "Służy do wykazywania istnienia pierwiastka.",
            )
        }

        category("pochodne-reguly", "Pochodne – reguły", "definicja, iloczyn, iloraz, złożenie") {
            f(
                "Definicja pochodnej",
                "f'(x₀) = @lim{h→0}@f{f(x₀ + h) − f(x₀)}{h}",
                "Granica ilorazu różnicowego.",
            )
            f("Pochodna sumy", "(f ± g)' = f' ± g'", null)
            f("Stała przed funkcją", "(c·f)' = c·f'", null)
            f("Pochodna iloczynu", "(f·g)' = f'·g + f·g'", null, "(x²·sin x)' = 2x sin x + x² cos x")
            f("Pochodna ilorazu", "(@f{f}{g})' = @f{f'·g − f·g'}{g²}", null, "(@f{x}{x + 1})' = @f{1}{(x + 1)²}")
            f(
                "Pochodna funkcji złożonej",
                "(f(g(x)))' = f'(g(x))·g'(x)",
                "Pochodna zewnętrznej razy pochodna wewnętrznej.",
                "((3x + 1)⁵)' = 5(3x + 1)⁴·3",
            )
            f("Pochodna funkcji odwrotnej", "(f^{−1})'(y) = @f{1}{f'(x)},  y = f(x)", null)
            f("Druga pochodna", "f''(x) = (f'(x))'", null)
            f(
                "Pochodna potęgowo-wykładnicza",
                "(f^{g})' = f^{g}·(g'·ln f + g·@f{f'}{f})",
                "Np. (xˣ)' = xˣ(ln x + 1).",
            )
        }

        category("pochodne-tabela", "Pochodne – tabela", "funkcje elementarne") {
            f("Pochodna stałej", "(c)' = 0", null)
            f("Pochodna potęgi", "(x^{n})' = n·x^{n−1}", null, "(x⁵)' = 5x⁴")
            f("Pochodna pierwiastka", "(@s{x})' = @f{1}{2@s{x}}", null)
            f("Pochodna 1/x", "(@f{1}{x})' = −@f{1}{x²}", null)
            f("Pochodna pierwiastka n-tego stopnia", "(@r{n}{x})' = @f{1}{n·@r{n}{x^{n−1}}}", null)
            f("Pochodna sinusa", "(sin x)' = cos x", null)
            f("Pochodna cosinusa", "(cos x)' = −sin x", null)
            f("Pochodna tangensa", "(tg x)' = @f{1}{cos²x}", null)
            f("Pochodna cotangensa", "(ctg x)' = −@f{1}{sin²x}", null)
            f("Pochodna eˣ", "(e^{x})' = e^{x}", null)
            f("Pochodna aˣ", "(a^{x})' = a^{x}·ln a", null)
            f("Pochodna ln x", "(ln x)' = @f{1}{x}", null)
            f("Pochodna logₐ x", "(log_{a} x)' = @f{1}{x·ln a}", null)
            f("Pochodna arcsin", "(arcsin x)' = @f{1}{@s{1 − x²}}", null)
            f("Pochodna arccos", "(arccos x)' = −@f{1}{@s{1 − x²}}", null)
            f("Pochodna arctg", "(arctg x)' = @f{1}{1 + x²}", null)
            f("Pochodna arcctg", "(arcctg x)' = −@f{1}{1 + x²}", null)
        }

        category("pochodne-zastosowania", "Zastosowania pochodnej", "styczna, ekstrema, monotoniczność") {
            f(
                "Styczna do wykresu funkcji",
                "y = f'(x₀)(x − x₀) + f(x₀)",
                "Styczna w punkcie (x₀; f(x₀)).",
                "f(x) = x², x₀ = 1: y = 2(x − 1) + 1 = 2x − 1",
                tags = "styczna rownanie stycznej",
            )
            f("Współczynnik kierunkowy stycznej", "a = f'(x₀) = tg α", "Interpretacja geometryczna pochodnej.")
            f("Normalna do wykresu", "y = −@f{1}{f'(x₀)}(x − x₀) + f(x₀)", "Prosta prostopadła do stycznej w punkcie styczności.")
            f(
                "Monotoniczność a pochodna",
                "f'(x) > 0 ⇒ f rosnąca\nf'(x) < 0 ⇒ f malejąca",
                "W przedziale.",
            )
            f(
                "Warunek konieczny ekstremum",
                "f'(x₀) = 0",
                "Dla funkcji różniczkowalnej. Nie jest wystarczający (np. x³ w 0).",
            )
            f(
                "Warunek wystarczający ekstremum",
                "f' zmienia znak + → −: maksimum\nf' zmienia znak − → +: minimum",
                null,
            )
            f(
                "Ekstremum a druga pochodna",
                "f'(x₀) = 0, f''(x₀) > 0: minimum\nf'(x₀) = 0, f''(x₀) < 0: maksimum",
                null,
            )
            f(
                "Wypukłość",
                "f''(x) > 0: wypukła (∪)\nf''(x) < 0: wklęsła (∩)",
                null,
            )
            f("Punkt przegięcia", "f''(x₀) = 0 i f'' zmienia znak", null)
            f(
                "Zadanie optymalizacyjne – schemat",
                "1. funkcja jednej zmiennej + dziedzina\n2. f'(x) = 0\n3. znak f' → ekstremum\n4. odpowiedź",
                "Sprawdź także końce przedziału, jeśli jest domknięty.",
            )
            f("Prędkość i przyspieszenie", "v(t) = s'(t)    a(t) = v'(t) = s''(t)", null)
            f(
                "Twierdzenie Lagrange’a",
                "f'(c) = @f{f(b) − f(a)}{b − a}",
                "Dla f ciągłej na ⟨a; b⟩ i różniczkowalnej w (a; b) istnieje takie c ∈ (a; b).",
            )
            f("Przybliżenie liniowe", "f(x₀ + Δx) ≈ f(x₀) + f'(x₀)·Δx", null)
            f(
                "Wzór Taylora",
                "f(x) ≈ f(x₀) + f'(x₀)(x − x₀) + @f{f''(x₀)}{2!}(x − x₀)² + …",
                null,
            )
        }

        category("calki-tabela", "Całki – tabela", "całki nieoznaczone elementarne") {
            f("Całka ze stałej", "@int{}{} k dx = kx + C", null)
            f("Całka z potęgi", "@int{}{} x^{n} dx = @f{x^{n+1}}{n + 1} + C", "n ≠ −1.", "@int{}{} x² dx = @f{x³}{3} + C")
            f("Całka z 1/x", "@int{}{} @f{1}{x} dx = ln|x| + C", null)
            f("Całka z eˣ", "@int{}{} e^{x} dx = e^{x} + C", null)
            f("Całka z aˣ", "@int{}{} a^{x} dx = @f{a^{x}}{ln a} + C", null)
            f("Całka z sin x", "@int{}{} sin x dx = −cos x + C", null)
            f("Całka z cos x", "@int{}{} cos x dx = sin x + C", null)
            f("Całka z 1/cos²x", "@int{}{} @f{1}{cos²x} dx = tg x + C", null)
            f("Całka z 1/sin²x", "@int{}{} @f{1}{sin²x} dx = −ctg x + C", null)
            f("Całka z tg x", "@int{}{} tg x dx = −ln|cos x| + C", null)
            f("Całka z 1/(1 + x²)", "@int{}{} @f{1}{1 + x²} dx = arctg x + C", null)
            f("Całka z 1/(x² + a²)", "@int{}{} @f{1}{x² + a²} dx = @f{1}{a}arctg@f{x}{a} + C", null)
            f("Całka z 1/√(1 − x²)", "@int{}{} @f{1}{@s{1 − x²}} dx = arcsin x + C", null)
            f("Całka z 1/(x² − a²)", "@int{}{} @f{1}{x² − a²} dx = @f{1}{2a}ln|@f{x − a}{x + a}| + C", null)
            f("Całka z 1/√(x² + k)", "@int{}{} @f{1}{@s{x² + k}} dx = ln|x + @s{x² + k}| + C", null)
            f("Całka z ln x", "@int{}{} ln x dx = x·ln x − x + C", null)
            f("Całka z f'/f", "@int{}{} @f{f'(x)}{f(x)} dx = ln|f(x)| + C", null)
        }

        category("calki-metody", "Całki – metody i zastosowania", "przez części, pole, objętość") {
            f("Liniowość całki", "@int{}{}(a·f + b·g) dx = a@int{}{}f dx + b@int{}{}g dx", null)
            f(
                "Całkowanie przez części",
                "@int{}{} u·v' dx = u·v − @int{}{} u'·v dx",
                null,
                "@int{}{} x·e^{x} dx = x·e^{x} − e^{x} + C",
            )
            f(
                "Całkowanie przez podstawienie",
                "@int{}{} f(g(x))·g'(x) dx = @int{}{} f(t) dt,  t = g(x)",
                null,
            )
            f(
                "Wzór Newtona–Leibniza",
                "@int{a}{b} f(x) dx = F(b) − F(a)",
                "F – funkcja pierwotna f.",
                "@int{0}{2} x² dx = @f{8}{3}",
            )
            f("Pole pod wykresem", "P = @int{a}{b} |f(x)| dx", null)
            f("Pole między wykresami", "P = @int{a}{b}(f(x) − g(x)) dx", "Gdy f(x) ≥ g(x) na ⟨a; b⟩.")
            f("Objętość bryły obrotowej", "V = π@int{a}{b} f²(x) dx", "Obrót wykresu wokół osi OX.")
            f("Długość łuku krzywej", "L = @int{a}{b}@s{1 + (f'(x))²} dx", null)
            f("Pole powierzchni obrotowej", "P = 2π@int{a}{b} f(x)@s{1 + (f'(x))²} dx", null)
            f("Wartość średnia funkcji", "@f{1}{b − a}@int{a}{b} f(x) dx", null)
            f(
                "Własności całki oznaczonej",
                "@int{a}{a} f dx = 0    @int{a}{b} f dx = −@int{b}{a} f dx\n@int{a}{b} f dx + @int{b}{c} f dx = @int{a}{c} f dx",
                null,
            )
            f(
                "Całka z funkcji parzystej/nieparzystej",
                "nieparzysta: @int{−a}{a} f(x) dx = 0\nparzysta: @int{−a}{a} f(x) dx = 2@int{0}{a} f(x) dx",
                null,
            )
        }
    }
}
