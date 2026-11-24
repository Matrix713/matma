package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Trigonometry {
    val section = section("trygonometria", "Trygonometria", "sin, cos, tg, tożsamości, równania") {

        category("tryg-definicje", "Definicje i wartości", "trójkąt prostokątny, tabela, radiany") {
            f(
                "Funkcje w trójkącie prostokątnym",
                "sin α = @f{a}{c}   cos α = @f{b}{c}\ntg α = @f{a}{b}   ctg α = @f{b}{a}",
                "a – przyprostokątna naprzeciw kąta α, b – przyprostokątna przy kącie α, c – przeciwprostokątna.",
                "a = 3, b = 4, c = 5: sin α = @f{3}{5}, tg α = @f{3}{4}",
                tags = "sinus cosinus tangens cotangens definicja",
                calc = "trygonometria",
            )
            f(
                "Funkcje w układzie współrzędnych",
                "sin α = @f{y}{r}   cos α = @f{x}{r}\ntg α = @f{y}{x}   ctg α = @f{x}{y}",
                "P(x; y) na końcowym ramieniu kąta, r = @s{x² + y²}.",
            )
            f(
                "Jedynka trygonometryczna",
                "sin²α + cos²α = 1",
                "Z niej: cos α = ±@s{1 − sin²α} (znak wg ćwiartki).",
                "sin α = @f{3}{5}, α ostry ⇒ cos α = @f{4}{5}",
                tags = "jedynka",
            )
            f("Tangens i cotangens przez sin i cos", "tg α = @f{sin α}{cos α}    ctg α = @f{cos α}{sin α}", null)
            f("Iloczyn tangensa i cotangensa", "tg α · ctg α = 1", null)
            f("Tangens a cosinus", "1 + tg²α = @f{1}{cos²α}", null)
            f("Cotangens a sinus", "1 + ctg²α = @f{1}{sin²α}", null)
            f(
                "Wartości dla 0°",
                "sin 0° = 0,  cos 0° = 1\ntg 0° = 0,  ctg 0° – nie istnieje",
                null,
                calc = "trygonometria",
            )
            f(
                "Wartości dla 30°",
                "sin 30° = @f{1}{2},  cos 30° = @f{@s{3}}{2}\ntg 30° = @f{@s{3}}{3},  ctg 30° = @s{3}",
                null,
                calc = "trygonometria",
            )
            f(
                "Wartości dla 45°",
                "sin 45° = cos 45° = @f{@s{2}}{2}\ntg 45° = ctg 45° = 1",
                null,
                calc = "trygonometria",
            )
            f(
                "Wartości dla 60°",
                "sin 60° = @f{@s{3}}{2},  cos 60° = @f{1}{2}\ntg 60° = @s{3},  ctg 60° = @f{@s{3}}{3}",
                null,
                calc = "trygonometria",
            )
            f(
                "Wartości dla 90°",
                "sin 90° = 1,  cos 90° = 0\ntg 90° – nie istnieje,  ctg 90° = 0",
                null,
                calc = "trygonometria",
            )
            f(
                "Wartości dla 120°, 135°, 150°",
                "sin 120° = @f{@s{3}}{2},  cos 120° = −@f{1}{2}\nsin 135° = @f{@s{2}}{2},  cos 135° = −@f{@s{2}}{2}\nsin 150° = @f{1}{2},  cos 150° = −@f{@s{3}}{2}",
                "Z wzorów redukcyjnych: sin(180° − α) = sin α, cos(180° − α) = −cos α.",
                calc = "trygonometria",
            )
            f(
                "Znaki funkcji w ćwiartkach",
                "I: wszystkie +\nII: sin +\nIII: tg, ctg +\nIV: cos +",
                "Wierszyk: „W pierwszej wszystkie są dodatnie, w drugiej tylko sinus, w trzeciej tangens i cotangens, a w czwartej cosinus”.",
                calc = "trygonometria",
            )
            f(
                "Miara łukowa",
                "α_{rad} = @f{π}{180°}·α°    1 rad ≈ 57,3°",
                null,
                tags = "radiany stopnie",
                calc = "trygonometria",
            )
            f(
                "Kąty w radianach",
                "30° = @f{π}{6},  45° = @f{π}{4},  60° = @f{π}{3}\n90° = @f{π}{2},  180° = π,  360° = 2π",
                null,
            )
            f(
                "Kąty dopełniające",
                "sin α = cos(90° − α)\ntg α = ctg(90° − α)",
                "Dla kątów ostrych trójkąta prostokątnego: sin α = cos β.",
            )
            f(
                "Zakres wartości",
                "−1 ≤ sin α ≤ 1\n−1 ≤ cos α ≤ 1",
                "tg i ctg przyjmują wszystkie wartości rzeczywiste.",
            )
        }

        category("tryg-redukcyjne", "Wzory redukcyjne", "90° ± α, 180° ± α, parzystość") {
            f(
                "Reguła wzorów redukcyjnych",
                "90°, 270°: funkcja → kofunkcja\n180°, 360°: funkcja bez zmian\nznak – wg ćwiartki wyniku",
                "Traktuj α jak kąt ostry i sprawdź znak funkcji wyjściowej w odpowiedniej ćwiartce.",
            )
            f("Kąt 90° − α", "sin(90° − α) = cos α\ncos(90° − α) = sin α\ntg(90° − α) = ctg α", null)
            f("Kąt 90° + α", "sin(90° + α) = cos α\ncos(90° + α) = −sin α\ntg(90° + α) = −ctg α", null)
            f("Kąt 180° − α", "sin(180° − α) = sin α\ncos(180° − α) = −cos α\ntg(180° − α) = −tg α", null)
            f("Kąt 180° + α", "sin(180° + α) = −sin α\ncos(180° + α) = −cos α\ntg(180° + α) = tg α", null)
            f("Kąt 270° − α", "sin(270° − α) = −cos α\ncos(270° − α) = −sin α\ntg(270° − α) = ctg α", null)
            f("Kąt 270° + α", "sin(270° + α) = −cos α\ncos(270° + α) = sin α\ntg(270° + α) = −ctg α", null)
            f("Kąt 360° − α", "sin(360° − α) = −sin α\ncos(360° − α) = cos α\ntg(360° − α) = −tg α", null)
            f(
                "Parzystość funkcji trygonometrycznych",
                "sin(−α) = −sin α    cos(−α) = cos α\ntg(−α) = −tg α    ctg(−α) = −ctg α",
                "Cosinus jest parzysty, pozostałe – nieparzyste.",
            )
            f(
                "Okresowość",
                "sin(α + 2kπ) = sin α    cos(α + 2kπ) = cos α\ntg(α + kπ) = tg α",
                "k ∈ ℤ.",
            )
        }

        category("tryg-tozsamosci", "Tożsamości", "sumy kątów, podwojenie, połowa kąta") {
            f("Sinus sumy", "sin(α + β) = sin α cos β + cos α sin β", null)
            f("Sinus różnicy", "sin(α − β) = sin α cos β − cos α sin β", null)
            f("Cosinus sumy", "cos(α + β) = cos α cos β − sin α sin β", null)
            f("Cosinus różnicy", "cos(α − β) = cos α cos β + sin α sin β", null, "cos 15° = cos(45° − 30°) = @f{@s{6} + @s{2}}{4}")
            f("Tangens sumy", "tg(α + β) = @f{tg α + tg β}{1 − tg α·tg β}", null)
            f("Tangens różnicy", "tg(α − β) = @f{tg α − tg β}{1 + tg α·tg β}", null)
            f("Sinus podwojonego kąta", "sin 2α = 2 sin α cos α", null, tags = "podwojony kat")
            f(
                "Cosinus podwojonego kąta",
                "cos 2α = cos²α − sin²α\n= 2cos²α − 1 = 1 − 2sin²α",
                null,
                tags = "podwojony kat",
            )
            f("Tangens podwojonego kąta", "tg 2α = @f{2 tg α}{1 − tg²α}", null)
            f(
                "Obniżanie potęgi",
                "sin²α = @f{1 − cos 2α}{2}\ncos²α = @f{1 + cos 2α}{2}",
                null,
            )
            f(
                "Potrojony kąt",
                "sin 3α = 3 sin α − 4 sin³α\ncos 3α = 4 cos³α − 3 cos α",
                null,
            )
            f(
                "Połowa kąta",
                "sin@f{α}{2} = ±@s{@f{1 − cos α}{2}}\ncos@f{α}{2} = ±@s{@f{1 + cos α}{2}}",
                "Znak zależy od ćwiartki kąta α/2.",
            )
            f("Tangens połowy kąta", "tg@f{α}{2} = @f{sin α}{1 + cos α} = @f{1 − cos α}{sin α}", null)
            f("Suma sinusów", "sin α + sin β = 2 sin@f{α + β}{2} cos@f{α − β}{2}", null)
            f("Różnica sinusów", "sin α − sin β = 2 cos@f{α + β}{2} sin@f{α − β}{2}", null)
            f("Suma cosinusów", "cos α + cos β = 2 cos@f{α + β}{2} cos@f{α − β}{2}", null)
            f("Różnica cosinusów", "cos α − cos β = −2 sin@f{α + β}{2} sin@f{α − β}{2}", null)
            f("Iloczyn sinusów", "sin α sin β = @f{1}{2}(cos(α − β) − cos(α + β))", null)
            f("Iloczyn cosinusów", "cos α cos β = @f{1}{2}(cos(α − β) + cos(α + β))", null)
            f("Iloczyn sinusa i cosinusa", "sin α cos β = @f{1}{2}(sin(α + β) + sin(α − β))", null)
            f(
                "Podstawienie uniwersalne",
                "t = tg@f{α}{2}\nsin α = @f{2t}{1 + t²}    cos α = @f{1 − t²}{1 + t²}",
                null,
            )
            f(
                "Kombinacja sinusa i cosinusa",
                "a sin x + b cos x = @s{a² + b²}·sin(x + φ)",
                "cos φ = a/@s{a² + b²}, sin φ = b/@s{a² + b²}. Największa wartość: @s{a² + b²}.",
            )
        }

        category("tryg-funkcje", "Funkcje i równania tryg.", "wykresy, okresy, rozwiązania") {
            f(
                "Funkcja y = sin x",
                "D = ℝ, ZW = ⟨−1; 1⟩, T = 2π",
                "Nieparzysta. Miejsca zerowe: x = kπ. Maksimum 1 dla x = π/2 + 2kπ.",
            )
            f(
                "Funkcja y = cos x",
                "D = ℝ, ZW = ⟨−1; 1⟩, T = 2π",
                "Parzysta. Miejsca zerowe: x = π/2 + kπ. Maksimum 1 dla x = 2kπ.",
            )
            f(
                "Funkcja y = tg x",
                "D: x ≠ @f{π}{2} + kπ,  ZW = ℝ,  T = π",
                "Nieparzysta, rosnąca w każdym przedziale dziedziny. Miejsca zerowe x = kπ, asymptoty x = π/2 + kπ.",
            )
            f(
                "Funkcja y = ctg x",
                "D: x ≠ kπ,  ZW = ℝ,  T = π",
                "Malejąca w każdym przedziale dziedziny. Miejsca zerowe x = π/2 + kπ.",
            )
            f(
                "Okres i amplituda",
                "y = A·sin(Bx + C) + D\nT = @f{2π}{|B|},  amplituda |A|",
                "Dla tg i ctg: T = π/|B|. ZW sinusoidy: ⟨D − |A|; D + |A|⟩.",
            )
            f(
                "Równanie sin x = a",
                "x = x₀ + 2kπ  ∨  x = π − x₀ + 2kπ",
                "x₀ – jedno rozwiązanie (np. z tabeli), |a| ≤ 1.",
                "sin x = @f{1}{2}: x = @f{π}{6} + 2kπ ∨ x = @f{5π}{6} + 2kπ",
            )
            f(
                "Równanie cos x = a",
                "x = x₀ + 2kπ  ∨  x = −x₀ + 2kπ",
                "|a| ≤ 1.",
                "cos x = @f{1}{2}: x = ±@f{π}{3} + 2kπ",
            )
            f("Równanie tg x = a", "x = x₀ + kπ", null, "tg x = 1: x = @f{π}{4} + kπ")
            f(
                "Równania szczególne",
                "sin x = 0 ⇔ x = kπ\ncos x = 0 ⇔ x = @f{π}{2} + kπ\nsin x = 1 ⇔ x = @f{π}{2} + 2kπ\ncos x = 1 ⇔ x = 2kπ",
                null,
            )
            f(
                "Równanie sin f(x) = sin g(x)",
                "f(x) = g(x) + 2kπ  ∨  f(x) = π − g(x) + 2kπ",
                "Analogicznie cos f = cos g ⇔ f = ±g + 2kπ.",
            )
            f(
                "Funkcje cyklometryczne",
                "arcsin: ⟨−1; 1⟩ → ⟨−@f{π}{2}; @f{π}{2}⟩\narccos: ⟨−1; 1⟩ → ⟨0; π⟩\narctg: ℝ → (−@f{π}{2}; @f{π}{2})",
                "Funkcje odwrotne do obciętych funkcji trygonometrycznych.",
            )
        }

        category("tryg-trojkat", "Twierdzenia w trójkącie", "sinusów, cosinusów, pole") {
            f(
                "Twierdzenie sinusów",
                "@f{a}{sin α} = @f{b}{sin β} = @f{c}{sin γ} = 2R",
                "R – promień okręgu opisanego. Bok a leży naprzeciw kąta α.",
                tags = "sinusow",
            )
            f(
                "Twierdzenie cosinusów",
                "a² = b² + c² − 2bc·cos α",
                "Uogólnienie twierdzenia Pitagorasa (α = 90° ⇒ cos α = 0).",
                "b = 5, c = 8, α = 60°: a² = 25 + 64 − 40 = 49 ⇒ a = 7",
                tags = "cosinusow carnota",
                calc = "trojkat-boki",
            )
            f(
                "Cosinus kąta z boków",
                "cos α = @f{b² + c² − a²}{2bc}",
                null,
                calc = "trojkat-boki",
            )
            f(
                "Rodzaj trójkąta z boków",
                "c² < a² + b²: ostrokątny\nc² = a² + b²: prostokątny\nc² > a² + b²: rozwartokątny",
                "c – najdłuższy bok.",
                calc = "trojkat-boki",
            )
            f("Pole z sinusem", "P = @f{1}{2}·a·b·sin γ", "γ – kąt między bokami a i b.")
            f(
                "Twierdzenie tangensów",
                "@f{a − b}{a + b} = @f{tg@f{α − β}{2}}{tg@f{α + β}{2}}",
                null,
            )
            f(
                "Promień okręgu opisanego",
                "R = @f{a}{2 sin α}",
                null,
                calc = "trojkat-boki",
            )
        }
    }
}
