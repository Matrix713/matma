package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Planimetry {
    val section = section("planimetria", "Planimetria", "trójkąty, czworokąty, koło, wielokąty") {

        category("trojkaty", "Trójkąty – pola i własności", "Heron, R, r, środkowe, podobieństwo") {
            f("Pole trójkąta – podstawa i wysokość", "P = @f{1}{2}·a·h_{a}", null, "a = 6, h = 4: P = 12")
            f("Pole trójkąta – dwa boki i kąt", "P = @f{1}{2}·a·b·sin γ", null)
            f(
                "Wzór Herona",
                "P = @s{p(p − a)(p − b)(p − c)}\np = @f{a + b + c}{2}",
                "p – połowa obwodu.",
                "5, 6, 7: p = 9, P = @s{9·4·3·2} = 6@s{6}",
                tags = "heron",
                calc = "trojkat-boki",
            )
            f("Pole przez promień okręgu opisanego", "P = @f{a·b·c}{4R}", null, calc = "trojkat-boki")
            f("Pole przez promień okręgu wpisanego", "P = r·p", "p – połowa obwodu.", calc = "trojkat-boki")
            f("Promień okręgu wpisanego", "r = @f{2P}{a + b + c}", null, calc = "trojkat-boki")
            f("Promień okręgu opisanego", "R = @f{a·b·c}{4P} = @f{a}{2 sin α}", null, calc = "trojkat-boki")
            f("Suma kątów trójkąta", "α + β + γ = 180°", null)
            f(
                "Nierówność trójkąta",
                "a + b > c,  a + c > b,  b + c > a",
                "Równoważnie: |b − c| < a < b + c. Najdłuższy bok jest krótszy od sumy pozostałych.",
            )
            f(
                "Długość środkowej",
                "m_{a} = @f{1}{2}@s{2b² + 2c² − a²}",
                "Środkowa poprowadzona do boku a.",
                calc = "trojkat-boki",
            )
            f("Podział środkowych", "|AS| : |SA'| = 2 : 1", "Środek ciężkości S dzieli każdą środkową w stosunku 2 : 1 licząc od wierzchołka.")
            f(
                "Twierdzenie o dwusiecznej",
                "@f{|AD|}{|DB|} = @f{|AC|}{|BC|}",
                "Dwusieczna kąta C dzieli bok AB w stosunku boków przyległych.",
            )
            f("Długość dwusiecznej", "l_{c} = @f{2ab·cos@f{γ}{2}}{a + b}", null)
            f("Kąt zewnętrzny", "δ = α + β", "Kąt zewnętrzny jest równy sumie dwóch niesąsiednich kątów wewnętrznych.")
            f(
                "Odcinek łączący środki boków",
                "|MN| = @f{1}{2}|AB|,  MN ∥ AB",
                "Linia środkowa trójkąta.",
            )
            f(
                "Cechy przystawania",
                "bbb, bkb, kbk",
                "bok-bok-bok; bok-kąt-bok (kąt między bokami); kąt-bok-kąt.",
            )
            f(
                "Cechy podobieństwa",
                "bbb, bkb, kkk",
                "Boki proporcjonalne; dwa boki proporcjonalne i kąt między nimi równy; dwa kąty równe.",
            )
            f(
                "Skala podobieństwa",
                "obwody: k    pola: k²    objętości: k³",
                "k – stosunek odpowiednich długości.",
                "k = 3 ⇒ pole 9 razy większe",
            )
            f(
                "Twierdzenie Talesa",
                "@f{|OA|}{|OB|} = @f{|OA'|}{|OB'|}",
                "Ramiona kąta przecięte prostymi równoległymi AA' ∥ BB' wyznaczają odcinki proporcjonalne.",
                tags = "tales",
            )
            f(
                "Punkty szczególne trójkąta",
                "symetralne → środek okręgu opisanego\ndwusieczne → środek okręgu wpisanego\nśrodkowe → środek ciężkości\nwysokości → ortocentrum",
                null,
            )
            f("Pole z wysokością i bokami", "h_{a} = @f{2P}{a}", null)
        }

        category("prostokatny", "Trójkąt prostokątny", "Pitagoras, wysokość, R i r") {
            f(
                "Twierdzenie Pitagorasa",
                "a² + b² = c²",
                "c – przeciwprostokątna (naprzeciw kąta prostego).",
                "a = 3, b = 4 ⇒ c = 5",
                tags = "pitagoras",
            )
            f(
                "Twierdzenie odwrotne do Pitagorasa",
                "a² + b² = c² ⇒ γ = 90°",
                "Jeśli boki spełniają równość, trójkąt jest prostokątny.",
            )
            f("Pole trójkąta prostokątnego", "P = @f{1}{2}·a·b", null)
            f("Wysokość na przeciwprostokątną", "h = @f{a·b}{c}", null, "3, 4, 5: h = @f{12}{5}")
            f(
                "Wysokość a odcinki przeciwprostokątnej",
                "h² = p·q\na² = c·p,  b² = c·q",
                "p, q – odcinki, na które spodek wysokości dzieli przeciwprostokątną (p przy a).",
            )
            f("Promień okręgu opisanego (prostokątny)", "R = @f{c}{2}", "Środek okręgu opisanego to środek przeciwprostokątnej.")
            f("Promień okręgu wpisanego (prostokątny)", "r = @f{a + b − c}{2}", null, "3, 4, 5: r = 1")
            f("Środkowa na przeciwprostokątną", "m_{c} = @f{c}{2}", null)
            f("Trójkąt 30°, 60°, 90°", "a,  a@s{3},  2a", "Krótsza przyprostokątna (naprzeciw 30°) to połowa przeciwprostokątnej.")
            f("Trójkąt 45°, 45°, 90°", "a,  a,  a@s{2}", "Połowa kwadratu.")
            f(
                "Trójki pitagorejskie",
                "(3, 4, 5), (5, 12, 13), (8, 15, 17), (7, 24, 25)\nm² − n²,  2mn,  m² + n²",
                "Wielokrotności też są trójkami, np. (6, 8, 10).",
            )
        }

        category("rownoboczny", "Trójkąt równoboczny i równoramienny", "h, P, R, r") {
            f("Wysokość trójkąta równobocznego", "h = @f{a@s{3}}{2}", null, "a = 6: h = 3@s{3}")
            f("Pole trójkąta równobocznego", "P = @f{a²@s{3}}{4}", null, "a = 6: P = 9@s{3}")
            f("Promień okręgu opisanego (równoboczny)", "R = @f{a@s{3}}{3} = @f{2}{3}h", null)
            f("Promień okręgu wpisanego (równoboczny)", "r = @f{a@s{3}}{6} = @f{1}{3}h", null)
            f("Zależność R i r (równoboczny)", "R = 2r", null)
            f(
                "Wysokość trójkąta równoramiennego",
                "h = @s{b² − (@f{a}{2})²}",
                "b – ramię, a – podstawa. Wysokość dzieli podstawę na połowy.",
            )
            f("Pole trójkąta równoramiennego", "P = @f{1}{2}·b²·sin α", "α – kąt między ramionami.")
            f("Kąty trójkąta równoramiennego", "β = @f{180° − α}{2}", "Kąty przy podstawie są równe.")
        }

        category("czworokaty", "Czworokąty", "kwadrat, romb, trapez, deltoid") {
            f(
                "Kwadrat",
                "P = a² = @f{d²}{2}    d = a@s{2}\nObw = 4a,  R = @f{d}{2},  r = @f{a}{2}",
                null,
            )
            f("Prostokąt", "P = a·b    d = @s{a² + b²}\nObw = 2a + 2b", null, "a = 6, b = 8: d = 10")
            f("Równoległobok", "P = a·h_{a} = a·b·sin α", "α – kąt między bokami.")
            f("Równoległobok – przekątne", "P = @f{1}{2}d₁d₂ sin φ\nd₁² + d₂² = 2(a² + b²)", "φ – kąt między przekątnymi. Przekątne dzielą się na połowy.")
            f(
                "Romb",
                "P = a·h = a²·sin α = @f{d₁·d₂}{2}",
                "Przekątne są prostopadłe i dzielą się na połowy. Promień wpisanego okręgu r = h/2.",
            )
            f("Trapez", "P = @f{(a + b)·h}{2}", "a, b – podstawy.", "a = 10, b = 6, h = 4: P = 32")
            f("Linia środkowa trapezu", "|MN| = @f{a + b}{2}", "Odcinek łączący środki ramion jest równoległy do podstaw.")
            f(
                "Trapez równoramienny",
                "x = @f{a − b}{2}    h = @s{c² − x²}",
                "x – rzut ramienia c na dłuższą podstawę a.",
            )
            f("Trapez prostokątny", "h = ramię prostopadłe do podstaw", null)
            f("Deltoid", "P = @f{d₁·d₂}{2}", "Przekątne prostopadłe; jedna jest osią symetrii.")
            f("Dowolny czworokąt", "P = @f{1}{2}·d₁·d₂·sin φ", "φ – kąt między przekątnymi.")
            f("Suma kątów czworokąta", "α + β + γ + δ = 360°", null)
            f(
                "Czworokąt wpisany w okrąg",
                "α + γ = β + δ = 180°",
                "Sumy przeciwległych kątów są równe 180°.",
            )
            f(
                "Czworokąt opisany na okręgu",
                "a + c = b + d",
                "Sumy przeciwległych boków są równe. Pole: P = r·p.",
            )
            f(
                "Twierdzenie Ptolemeusza",
                "|AC|·|BD| = |AB|·|CD| + |AD|·|BC|",
                "Dla czworokąta wpisanego w okrąg.",
            )
        }

        category("kolo", "Koło i okrąg", "pole, łuk, wycinek, kąty, styczne") {
            f("Obwód okręgu", "L = 2πr = πd", null, "r = 5: L = 10π ≈ 31,4")
            f("Pole koła", "P = πr²", null, "r = 5: P = 25π ≈ 78,5")
            f(
                "Długość łuku",
                "l = @f{α}{360°}·2πr = α_{rad}·r",
                null,
                "r = 6, α = 60°: l = 2π",
                tags = "luk",
            )
            f(
                "Pole wycinka koła",
                "P = @f{α}{360°}·πr² = @f{1}{2}α_{rad}·r² = @f{1}{2}l·r",
                null,
                tags = "wycinek",
            )
            f(
                "Pole odcinka koła",
                "P = @f{α}{360°}·πr² − @f{1}{2}r²·sin α",
                "Wycinek minus trójkąt.",
            )
            f("Pole pierścienia", "P = π(R² − r²)", null)
            f(
                "Kąt wpisany i środkowy",
                "α = @f{1}{2}·β",
                "Kąt wpisany α jest połową kąta środkowego β opartego na tym samym łuku.",
                tags = "kat wpisany srodkowy",
            )
            f(
                "Kąty wpisane na tym samym łuku",
                "są równe\nna półokręgu: 90°",
                "Kąt wpisany oparty na średnicy jest prosty (tw. Talesa o okręgu).",
            )
            f(
                "Kąt dopisany",
                "kąt między styczną a cięciwą\n= kąt wpisany oparty na tym łuku",
                null,
            )
            f("Styczna a promień", "styczna ⟂ promień", "W punkcie styczności.")
            f("Odcinki stycznych", "|PA| = |PB|", "Odcinki stycznych poprowadzonych z jednego punktu są równe.")
            f("Twierdzenie o cięciwach", "|AP|·|PB| = |CP|·|PD|", "Cięciwy AB i CD przecinają się w punkcie P.")
            f("Twierdzenie o siecznych", "|PA|·|PB| = |PC|·|PD|", "Dwie sieczne z punktu P poza okręgiem.")
            f("Styczna i sieczna", "|PT|² = |PA|·|PB|", "PT – odcinek stycznej, PA, PB – odcinki siecznej.")
            f("Długość cięciwy", "c = 2r·sin@f{α}{2} = 2@s{r² − d²}", "α – kąt środkowy, d – odległość cięciwy od środka.")
            f("Liczba π", "π ≈ 3,14159", "Stosunek obwodu okręgu do jego średnicy.")
        }

        category("wielokaty", "Wielokąty", "kąty, przekątne, sześciokąt foremny") {
            f("Suma kątów wielokąta", "(n − 2)·180°", null, "sześciokąt: 720°")
            f("Kąt wielokąta foremnego", "α = @f{(n − 2)·180°}{n}", null, "ośmiokąt: 135°")
            f("Suma kątów zewnętrznych", "360°", "Dla każdego wielokąta wypukłego.")
            f("Liczba przekątnych", "@f{n(n − 3)}{2}", null, "dziesięciokąt: 35")
            f(
                "Sześciokąt foremny",
                "P = @f{3a²@s{3}}{2}    R = a    r = @f{a@s{3}}{2}",
                "Składa się z 6 trójkątów równobocznych. Przekątne: krótsza a@s{3}, dłuższa 2a.",
            )
            f("Ośmiokąt foremny", "P = 2a²(1 + @s{2})", null)
            f("Pole wielokąta foremnego", "P = @f{1}{2}·n·R²·sin@f{360°}{n} = @f{1}{2}·Obw·r", null)
            f("Pięciokąt foremny", "α = 108°    d = a·φ", "φ = (1 + √5)/2 – złota liczba.")
            f(
                "Wzór Picka",
                "P = W + @f{B}{2} − 1",
                "Wielokąt o wierzchołkach w punktach kratowych: W – punkty wewnątrz, B – punkty na brzegu.",
            )
        }
    }
}
