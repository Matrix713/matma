package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object Stereometry {
    val section = section("stereometria", "Stereometria", "graniastosłupy, ostrosłupy, bryły obrotowe") {

        category("graniastoslupy", "Graniastosłupy", "sześcian, prostopadłościan, prawidłowe") {
            f(
                "Sześcian",
                "V = a³    P_{c} = 6a²\nd = a@s{3},  przekątna ściany a@s{2}",
                null,
                "a = 2: V = 8, P = 24, d = 2@s{3}",
            )
            f(
                "Prostopadłościan",
                "V = a·b·c    P_{c} = 2(ab + bc + ac)\nd = @s{a² + b² + c²}",
                null,
            )
            f("Graniastosłup – objętość", "V = P_{p}·H", "P_p – pole podstawy, H – wysokość.")
            f("Graniastosłup – pole", "P_{c} = 2P_{p} + P_{b}", "P_b – pole powierzchni bocznej.")
            f("Pole boczne graniastosłupa prostego", "P_{b} = Obw_{p}·H", null)
            f(
                "Graniastosłup prawidłowy trójkątny",
                "V = @f{a²@s{3}}{4}·H    P_{b} = 3aH",
                null,
            )
            f(
                "Graniastosłup prawidłowy czworokątny",
                "V = a²H    P_{c} = 2a² + 4aH\nd = @s{2a² + H²}",
                null,
            )
            f(
                "Graniastosłup prawidłowy sześciokątny",
                "V = @f{3a²@s{3}}{2}·H    P_{b} = 6aH",
                "Przekątne: dłuższa @s{4a² + H²}, krótsza @s{3a² + H²}.",
            )
            f(
                "Kąt przekątnej z podstawą",
                "tg α = @f{H}{d_{p}}",
                "d_p – przekątna podstawy (rzut przekątnej bryły).",
            )
        }

        category("ostroslupy", "Ostrosłupy", "objętość, prawidłowe, czworościan") {
            f("Ostrosłup – objętość", "V = @f{1}{3}·P_{p}·H", null)
            f("Ostrosłup – pole", "P_{c} = P_{p} + P_{b}", null)
            f(
                "Ostrosłup prawidłowy czworokątny",
                "V = @f{1}{3}a²H\nh_{b}² = H² + (@f{a}{2})²\nb² = H² + (@f{a@s{2}}{2})²",
                "h_b – wysokość ściany bocznej, b – krawędź boczna.",
            )
            f(
                "Ostrosłup prawidłowy trójkątny",
                "V = @f{a²@s{3}}{12}·H",
                "Spodek wysokości w środku trójkąta: do wierzchołka R = a@s{3}/3, do boku r = a@s{3}/6.",
            )
            f("Ostrosłup prawidłowy sześciokątny", "V = @f{a²@s{3}}{2}·H", "Spodek wysokości: do wierzchołka R = a.")
            f(
                "Czworościan foremny",
                "V = @f{a³@s{2}}{12}    P_{c} = a²@s{3}\nH = @f{a@s{6}}{3}",
                null,
            )
            f("Ośmiościan foremny", "V = @f{a³@s{2}}{3}    P_{c} = 2a²@s{3}", null)
            f(
                "Ostrosłup ścięty",
                "V = @f{1}{3}H(P₁ + @s{P₁P₂} + P₂)",
                "P₁, P₂ – pola podstaw.",
            )
            f(
                "Kąty nachylenia w ostrosłupie",
                "krawędzi bocznej: tg α = @f{H}{R}\nściany bocznej: tg β = @f{H}{r}",
                "Ostrosłup prawidłowy: R – promień okręgu opisanego na podstawie, r – wpisanego.",
            )
            f(
                "Równe krawędzie boczne",
                "spodek wysokości = środek okręgu\nopisanego na podstawie",
                "Gdy ściany boczne są jednakowo nachylone – środek okręgu wpisanego.",
            )
        }

        category("obrotowe", "Bryły obrotowe", "walec, stożek, kula") {
            f(
                "Walec",
                "V = πr²H    P_{b} = 2πrH\nP_{c} = 2πr(r + H)",
                null,
                "r = 3, H = 5: V = 45π",
            )
            f(
                "Stożek",
                "V = @f{1}{3}πr²H    P_{b} = πrl\nP_{c} = πr(r + l)",
                "l – tworząca: l² = r² + H².",
                "r = 3, H = 4: l = 5, V = 12π",
            )
            f("Kąt wycinka – siatka stożka", "α = @f{r}{l}·360°", "Powierzchnia boczna stożka to wycinek koła o promieniu l.")
            f("Kula", "V = @f{4}{3}πr³    P = 4πr²", null, "r = 3: V = 36π, P = 36π")
            f(
                "Stożek ścięty",
                "V = @f{1}{3}πH(R² + Rr + r²)\nP_{b} = π(R + r)l",
                null,
            )
            f("Czasza kulista", "P = 2πRh", "h – wysokość czaszy.")
            f("Odcinek kuli", "V = πh²(R − @f{h}{3})", null)
            f("Wycinek kuli", "V = @f{2}{3}πR²h", null)
            f("Torus", "V = 2π²Rr²    P = 4π²Rr", "R – odległość środka koła od osi, r – promień koła.")
        }

        category("bryly-wlasnosci", "Bryły – własności", "kule wpisane, Euler, podobieństwo") {
            f(
                "Kule a sześcian",
                "wpisana: r = @f{a}{2}\nopisana: R = @f{a@s{3}}{2}\nstyczna do krawędzi: @f{a@s{2}}{2}",
                null,
            )
            f("Kula opisana na prostopadłościanie", "R = @f{d}{2} = @f{1}{2}@s{a² + b² + c²}", null)
            f("Kule a czworościan foremny", "R = @f{a@s{6}}{4}    r = @f{a@s{6}}{12}", null)
            f("Kula wpisana w stożek", "r = @f{R·H}{R + l}", "R – promień podstawy stożka, l – tworząca.")
            f("Walec opisany na kuli", "V_{kuli} : V_{walca} = 2 : 3", "Twierdzenie Archimedesa.")
            f("Wzór Eulera dla wielościanów", "W − K + S = 2", "Wierzchołki, krawędzie, ściany.", "sześcian: 8 − 12 + 6 = 2")
            f(
                "Bryły podobne",
                "pola: k²    objętości: k³",
                "k – skala podobieństwa.",
            )
            f("Objętość ze wzoru Cavalieriego", "równe przekroje na każdej\nwysokości ⇒ równe objętości", null)
        }
    }
}
