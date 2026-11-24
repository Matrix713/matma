package pl.matma.wzory.data.content

import pl.matma.wzory.data.section

object AnalyticGeometry {
    val section = section("analityczna", "Geometria analityczna", "proste, odległości, okręgi, wektory") {

        category("punkty", "Punkty i odcinki", "długość, środek, podział, pole") {
            f(
                "Długość odcinka (odległość punktów)",
                "|AB| = @s{(x₂ − x₁)² + (y₂ − y₁)²}",
                "Odległość punktów A(x₁; y₁) i B(x₂; y₂) – z twierdzenia Pitagorasa.",
                "A(1; 2), B(4; 6)\n|AB| = @s{3² + 4²} = @s{25} = 5",
                tags = "odleglosc dwoch punktow dlugosc odcinka",
                calc = "prosta-2pkt",
            )
            f(
                "Środek odcinka",
                "S = (@f{x₁ + x₂}{2}; @f{y₁ + y₂}{2})",
                "Współrzędne środka to średnie arytmetyczne współrzędnych końców.",
                "A(−2; 5), B(4; 1) ⇒ S = (1; 3)",
                calc = "prosta-2pkt",
            )
            f(
                "Drugi koniec odcinka (znany środek)",
                "x₂ = 2x_{S} − x₁\ny₂ = 2y_{S} − y₁",
                "Gdy znamy koniec A i środek S odcinka AB.",
                "A(1; 2), S(3; 5) ⇒ B(5; 8)",
            )
            f(
                "Punkt dzielący odcinek w stosunku m : n",
                "P = (@f{n·x₁ + m·x₂}{m + n}; @f{n·y₁ + m·y₂}{m + n})",
                "Punkt P na odcinku AB taki, że |AP| : |PB| = m : n.",
                "A(0; 0), B(6; 3), m : n = 1 : 2 ⇒ P(2; 1)",
                tags = "podzial odcinka stosunek",
            )
            f(
                "Punkt podziału wektorowo",
                "@v{AP} = @f{m}{m + n}·@v{AB}",
                "Ten sam podział zapisany wektorowo: P = A + k·@v{AB}, gdzie k = m/(m + n).",
            )
            f(
                "Środek ciężkości trójkąta",
                "S = (@f{x_{A} + x_{B} + x_{C}}{3}; @f{y_{A} + y_{B} + y_{C}}{3})",
                "Punkt przecięcia środkowych trójkąta ABC.",
                "A(0; 0), B(6; 0), C(0; 3) ⇒ S(2; 1)",
                tags = "barycentrum srodkowe",
                calc = "trojkat-3pkt",
            )
            f(
                "Pole trójkąta z współrzędnych",
                "P = @f{1}{2}·|(x_{B} − x_{A})(y_{C} − y_{A}) − (y_{B} − y_{A})(x_{C} − x_{A})|",
                "Połowa wartości bezwzględnej wyznacznika wektorów @v{AB} i @v{AC}.",
                "A(0; 0), B(4; 0), C(0; 3)\nP = @f{1}{2}·|4·3 − 0·0| = 6",
                tags = "wyznacznik pole trojkata wspolrzedne",
                calc = "trojkat-3pkt",
            )
            f(
                "Pole wielokąta (wzór Gaussa)",
                "P = @f{1}{2}|@sum{i=1}{n}(x_{i}y_{i+1} − x_{i+1}y_{i})|",
                "Wierzchołki kolejno (np. przeciwnie do ruchu wskazówek), x_{n+1} = x₁, y_{n+1} = y₁. Tzw. wzór „sznurowadłowy”.",
                tags = "shoelace sznurowadlowy",
            )
            f(
                "Współliniowość trzech punktów",
                "(x_{B} − x_{A})(y_{C} − y_{A}) − (y_{B} − y_{A})(x_{C} − x_{A}) = 0",
                "Punkty A, B, C leżą na jednej prostej ⇔ wyznacznik det(@v{AB}, @v{AC}) = 0 (pole „trójkąta” = 0).",
                tags = "wspolliniowe jedna prosta",
            )
            f(
                "Odległość punktu od początku układu",
                "|OP| = @s{x² + y²}",
                "O = (0; 0).",
            )
            f(
                "Odległość punktu od osi",
                "od osi OX: |y₀|\nod osi OY: |x₀|",
                "Dla punktu P(x₀; y₀).",
            )
            f(
                "Czwarty wierzchołek równoległoboku",
                "D = A + C − B\nx_{D} = x_{A} + x_{C} − x_{B},  y_{D} = y_{A} + y_{C} − y_{B}",
                "Dla równoległoboku ABCD – przekątne dzielą się na połowy (wspólny środek AC i BD).",
            )
            f(
                "Ćwiartki układu współrzędnych",
                "I: x > 0, y > 0    II: x < 0, y > 0\nIII: x < 0, y < 0    IV: x > 0, y < 0",
                "Punkty na osiach nie należą do żadnej ćwiartki.",
            )
            f(
                "Punkt na osiach",
                "na OX: (x; 0)    na OY: (0; y)",
                "Przecięcie wykresu z osią OX: y = 0, z osią OY: x = 0.",
            )
        }

        category("prosta", "Równania prostej", "ogólna, kierunkowa, przez 2 punkty") {
            f(
                "Postać ogólna prostej",
                "Ax + By + C = 0",
                "A i B nie są jednocześnie równe 0. Opisuje każdą prostą, także pionową. Wektor normalny (prostopadły) n = [A; B].",
                "2x − 3y + 6 = 0",
                calc = "postacie-prostej",
            )
            f(
                "Postać kierunkowa prostej",
                "y = ax + b",
                "a – współczynnik kierunkowy (nachylenie), b – wyraz wolny: prosta przecina OY w (0; b). Nie opisuje prostych pionowych x = c.",
                "y = 2x − 1: a = 2, b = −1",
                calc = "postacie-prostej",
            )
            f(
                "Współczynnik kierunkowy z dwóch punktów",
                "a = @f{y₂ − y₁}{x₂ − x₁}",
                "Dla x₁ ≠ x₂. Gdy x₁ = x₂, prosta jest pionowa (x = x₁).",
                "A(1; 2), B(3; 8) ⇒ a = @f{6}{2} = 3",
                tags = "nachylenie slope",
                calc = "prosta-2pkt",
            )
            f(
                "Współczynnik kierunkowy a kąt nachylenia",
                "a = tg α",
                "α – kąt między prostą a dodatnią półosią OX (0° ≤ α < 180°). a > 0 ⇒ kąt ostry, a < 0 ⇒ kąt rozwarty.",
                "y = @s{3}x + 1 ⇒ tg α = @s{3} ⇒ α = 60°",
            )
            f(
                "Prosta przez dwa punkty",
                "(y − y₁)(x₂ − x₁) − (y₂ − y₁)(x − x₁) = 0",
                "Równanie prostej AB, A(x₁; y₁), B(x₂; y₂). Działa także dla prostej pionowej.",
                "A(1; 2), B(4; 8)\n(y − 2)·3 − 6(x − 1) = 0 ⇒ y = 2x",
                tags = "rownanie prostej przechodzacej przez dwa punkty",
                calc = "prosta-2pkt",
            )
            f(
                "Prosta przez dwa punkty (kierunkowa)",
                "y − y₁ = @f{y₂ − y₁}{x₂ − x₁}(x − x₁)",
                "Gdy x₁ ≠ x₂.",
                calc = "prosta-2pkt",
            )
            f(
                "Prosta przez punkt o danym nachyleniu",
                "y − y₀ = a(x − x₀)",
                "Prosta o współczynniku kierunkowym a przechodząca przez P(x₀; y₀).",
                "P(2; 3), a = −1 ⇒ y = −x + 5",
            )
            f(
                "Postać odcinkowa prostej",
                "@f{x}{p} + @f{y}{q} = 1",
                "Prosta przecina oś OX w (p; 0), a oś OY w (0; q); p, q ≠ 0.",
                "@f{x}{3} + @f{y}{2} = 1 ⇒ (3; 0) i (0; 2)",
                calc = "postacie-prostej",
            )
            f(
                "Postać parametryczna prostej",
                "@sys{x = x₀ + t·u₁}{y = y₀ + t·u₂}   t ∈ ℝ",
                "Prosta przez P(x₀; y₀) o wektorze kierunkowym u = [u₁; u₂].",
            )
            f(
                "Prosta przez punkt prostopadła do wektora",
                "A(x − x₀) + B(y − y₀) = 0",
                "Prosta przez P(x₀; y₀) prostopadła do wektora n = [A; B] (wektor normalny).",
            )
            f(
                "Z postaci ogólnej do kierunkowej",
                "y = −@f{A}{B}x − @f{C}{B}",
                "Dla B ≠ 0. Czyli a = −A/B, b = −C/B.",
                "2x − 3y + 6 = 0 ⇒ y = @f{2}{3}x + 2",
                calc = "postacie-prostej",
            )
            f(
                "Z postaci kierunkowej do ogólnej",
                "y = ax + b ⇔ ax − y + b = 0",
                "Czyli A = a, B = −1, C = b.",
            )
            f(
                "Wektor normalny i kierunkowy prostej",
                "n = [A; B]    u = [−B; A]",
                "Dla prostej Ax + By + C = 0: n jest prostopadły, u równoległy do prostej. Dla y = ax + b: u = [1; a].",
                calc = "postacie-prostej",
            )
            f(
                "Prosta pionowa i pozioma",
                "x = c  (pionowa)\ny = c  (pozioma, a = 0)",
                "Prosta pionowa nie ma postaci kierunkowej (nie jest wykresem funkcji).",
            )
            f(
                "Punkty przecięcia prostej z osiami",
                "OX: (−@f{b}{a}; 0)    OY: (0; b)",
                "Dla y = ax + b, a ≠ 0. Dla postaci ogólnej: OX: (−C/A; 0), OY: (0; −C/B).",
            )
            f(
                "Prosta przez początek układu",
                "y = ax",
                "b = 0. Także proste x = 0 (oś OY).",
            )
            f(
                "Punkt należy do prostej",
                "Ax₀ + By₀ + C = 0",
                "P(x₀; y₀) leży na prostej ⇔ jego współrzędne spełniają równanie.",
                "Czy P(1; 4) ∈ y = 3x + 1? 3·1 + 1 = 4 ✓",
            )
            f(
                "Postać normalna prostej",
                "x·cos φ + y·sin φ − p = 0",
                "p ≥ 0 – odległość prostej od O, φ – kąt wektora normalnego z OX. Otrzymasz ją, dzieląc Ax + By + C = 0 przez ∓@s{A² + B²}.",
            )
            f(
                "Dwusieczne ćwiartek układu",
                "y = x    y = −x",
                "y = x – dwusieczna I i III ćwiartki, y = −x – II i IV.",
            )
        }

        category("polozenie", "Wzajemne położenie prostych", "∥, ⟂, przecięcie, kąt") {
            f(
                "Proste równoległe (kierunkowe)",
                "a₁ = a₂",
                "y = a₁x + b₁ ∥ y = a₂x + b₂. Gdy dodatkowo b₁ = b₂ – proste się pokrywają.",
                "y = 2x + 1 ∥ y = 2x − 7",
                tags = "rownolegle",
                calc = "prosta-kierunkowa",
            )
            f(
                "Proste prostopadłe (kierunkowe)",
                "a₁ · a₂ = −1",
                "Czyli a₂ = −1/a₁. Prosta pozioma (a = 0) jest prostopadła do pionowej.",
                "y = 2x + 1 ⟂ y = −@f{1}{2}x + 3",
                tags = "prostopadle",
                calc = "prosta-kierunkowa",
            )
            f(
                "Proste równoległe (ogólne)",
                "A₁B₂ − A₂B₁ = 0",
                "Wektory normalne są równoległe. Proste pokrywają się, gdy dodatkowo współczynniki są proporcjonalne z C.",
                calc = "dwie-proste",
            )
            f(
                "Proste prostopadłe (ogólne)",
                "A₁A₂ + B₁B₂ = 0",
                "Iloczyn skalarny wektorów normalnych równy 0.",
                calc = "dwie-proste",
            )
            f(
                "Prosta równoległa przez punkt",
                "y = a(x − x₀) + y₀\nA(x − x₀) + B(y − y₀) = 0",
                "Prosta równoległa do y = ax + b (lub Ax + By + C = 0) przechodząca przez P(x₀; y₀).",
                "∥ do y = 3x − 2 przez P(1; 5): y = 3x + 2",
                calc = "prosta-kierunkowa",
            )
            f(
                "Prosta prostopadła przez punkt",
                "y = −@f{1}{a}(x − x₀) + y₀\nB(x − x₀) − A(y − y₀) = 0",
                "Prosta prostopadła do y = ax + b (a ≠ 0) lub do Ax + By + C = 0, przez P(x₀; y₀).",
                "⟂ do y = 2x + 1 przez P(2; 3): y = −@f{1}{2}x + 4",
                calc = "prosta-kierunkowa",
            )
            f(
                "Punkt przecięcia prostych – układ",
                "@sys{A₁x + B₁y + C₁ = 0}{A₂x + B₂y + C₂ = 0}",
                "Rozwiązanie układu to punkt wspólny. W ≠ 0 – jeden punkt, W = 0 – proste równoległe lub pokrywające się.",
                calc = "dwie-proste",
            )
            f(
                "Punkt przecięcia – wzory Cramera",
                "x = @f{B₁C₂ − B₂C₁}{A₁B₂ − A₂B₁}\ny = @f{C₁A₂ − C₂A₁}{A₁B₂ − A₂B₁}",
                "Gdy W = A₁B₂ − A₂B₁ ≠ 0.",
                calc = "dwie-proste",
            )
            f(
                "Punkt przecięcia prostych kierunkowych",
                "x = @f{b₂ − b₁}{a₁ − a₂},  y = a₁x + b₁",
                "Dla a₁ ≠ a₂ – przyrównujemy a₁x + b₁ = a₂x + b₂.",
                "y = 2x + 1, y = −x + 7 ⇒ x = 2, y = 5",
            )
            f(
                "Kąt między prostymi (kierunkowe)",
                "tg φ = |@f{a₁ − a₂}{1 + a₁a₂}|",
                "φ – kąt ostry między prostymi (gdy 1 + a₁a₂ = 0, proste są prostopadłe, φ = 90°).",
                "a₁ = 3, a₂ = @f{1}{2}: tg φ = @f{2,5}{2,5} = 1 ⇒ φ = 45°",
                calc = "dwie-proste",
            )
            f(
                "Kąt między prostymi (ogólne)",
                "cos φ = @f{|A₁A₂ + B₁B₂|}{@s{A₁² + B₁²}·@s{A₂² + B₂²}}",
                "Kąt między wektorami normalnymi (ostry).",
                calc = "dwie-proste",
            )
            f(
                "Symetralna odcinka",
                "(x₂ − x₁)(x − x_{S}) + (y₂ − y₁)(y − y_{S}) = 0",
                "Prosta prostopadła do AB przez jego środek S. Każdy jej punkt jest równo oddalony od A i B: |PA| = |PB|.",
                "A(0; 0), B(4; 2): S(2; 1), 4(x − 2) + 2(y − 1) = 0 ⇒ y = −2x + 5",
                calc = "prosta-2pkt",
            )
            f(
                "Dwusieczne kątów między prostymi",
                "@f{|A₁x + B₁y + C₁|}{@s{A₁² + B₁²}} = @f{|A₂x + B₂y + C₂|}{@s{A₂² + B₂²}}",
                "Zbiór punktów równo oddalonych od obu prostych – dwie dwusieczne (rozwiąż dla „+” i „−”).",
            )
            f(
                "Pęk prostych przez punkt",
                "y = a(x − x₀) + y₀  oraz  x = x₀",
                "Wszystkie proste przechodzące przez P(x₀; y₀).",
            )
            f(
                "Liczba punktów wspólnych prostych",
                "W ≠ 0: 1 punkt\nW = 0, W_{x} = W_{y} = 0: pokrywają się\nW = 0, W_{x} ≠ 0 lub W_{y} ≠ 0: brak (∥)",
                "W, Wx, Wy – wyznaczniki układu (metoda Cramera).",
                calc = "cramer",
            )
            f(
                "Trzy proste przez jeden punkt",
                "wyznacz punkt przecięcia dwóch\ni sprawdź, czy leży na trzeciej",
                "Proste współpękowe – mają wspólny punkt.",
            )
        }

        category("odleglosci", "Odległości", "punkt–prosta, proste ∥, rzut, symetria") {
            f(
                "Odległość punktu od prostej",
                "d = @f{|Ax₀ + By₀ + C|}{@s{A² + B²}}",
                "Odległość punktu P(x₀; y₀) od prostej Ax + By + C = 0.",
                "P(2; 3), 3x + 4y − 5 = 0\nd = @f{|6 + 12 − 5|}{@s{9 + 16}} = @f{13}{5}",
                tip = "Prostą y = ax + b przepisz najpierw na ax − y + b = 0.",
                tags = "odleglosc punktu od prostej dystans",
                calc = "punkt-prosta",
            )
            f(
                "Odległość punktu od prostej (kierunkowa)",
                "d = @f{|ax₀ − y₀ + b|}{@s{a² + 1}}",
                "Odległość P(x₀; y₀) od prostej y = ax + b.",
                "P(1; 1), y = 2x + 4\nd = @f{|2 − 1 + 4|}{@s{5}} = @s{5}",
                tags = "odleglosc punktu od prostej",
                calc = "prosta-kierunkowa",
            )
            f(
                "Odległość punktu od prostej pionowej / poziomej",
                "x = c: d = |x₀ − c|\ny = c: d = |y₀ − c|",
                "Nie trzeba wzoru ogólnego.",
            )
            f(
                "Odległość początku układu od prostej",
                "d = @f{|C|}{@s{A² + B²}}",
                "Odległość O(0; 0) od prostej Ax + By + C = 0.",
                calc = "postacie-prostej",
            )
            f(
                "Odległość prostych równoległych (ogólne)",
                "d = @f{|C₁ − C₂|}{@s{A² + B²}}",
                "Dla Ax + By + C₁ = 0 i Ax + By + C₂ = 0 – z TYMI SAMYMI A i B.",
                "2x − y + 3 = 0, 2x − y − 2 = 0\nd = @f{5}{@s{5}} = @s{5}",
                tip = "Gdy A i B różnią się, najpierw pomnóż jedno równanie, np. 4x − 2y + 6 = 0 → 2x − y + 3 = 0.",
                tags = "odleglosc prostych rownoleglych",
                calc = "dwie-proste",
            )
            f(
                "Odległość prostych równoległych (kierunkowe)",
                "d = @f{|b₁ − b₂|}{@s{a² + 1}}",
                "Dla y = ax + b₁ i y = ax + b₂.",
                "y = x + 3, y = x − 1 ⇒ d = @f{4}{@s{2}} = 2@s{2}",
            )
            f(
                "Rzut prostokątny punktu na prostą",
                "P' = (x₀ − A·t; y₀ − B·t)\nt = @f{Ax₀ + By₀ + C}{A² + B²}",
                "Spodek prostopadłej opuszczonej z P(x₀; y₀) na prostą Ax + By + C = 0. Inaczej: przecięcie prostej z prostopadłą przez P.",
                tags = "spodek wysokosci rzut",
                calc = "punkt-prosta",
            )
            f(
                "Punkt symetryczny względem prostej",
                "P'' = (x₀ − 2A·t; y₀ − 2B·t)\nt = @f{Ax₀ + By₀ + C}{A² + B²}",
                "Odbicie lustrzane P względem prostej. Rzut P' jest środkiem odcinka PP''.",
                tags = "symetria osiowa odbicie",
                calc = "punkt-prosta",
            )
            f(
                "Wysokość trójkąta jako odległość",
                "h_{C} = d(C, AB) = @f{2P}{|AB|}",
                "Wysokość z wierzchołka C to odległość C od prostej AB.",
                calc = "trojkat-3pkt",
            )
            f(
                "Pole trójkąta: prosta i punkt",
                "P = @f{1}{2}·|AB|·d(C, AB)",
                "Gdy znamy odcinek AB na prostej i trzeci wierzchołek C.",
            )
            f(
                "Odległość punktu od okręgu",
                "d = | |PS| − r |",
                "Najmniejsza odległość punktu P od okręgu o środku S i promieniu r. Największa: |PS| + r.",
            )
            f(
                "Odległość prostej od okręgu",
                "d(S, l) − r",
                "Gdy prosta l jest rozłączna z okręgiem (d(S, l) > r).",
            )
        }

        category("wektory", "Wektory", "współrzędne, długość, iloczyn skalarny") {
            f(
                "Współrzędne wektora",
                "@v{AB} = [x_{B} − x_{A}; y_{B} − y_{A}]",
                "„Koniec minus początek”.",
                "A(1; 2), B(4; −2) ⇒ @v{AB} = [3; −4]",
            )
            f(
                "Długość wektora",
                "|@v{u}| = @s{u₁² + u₂²}",
                "Dla u = [u₁; u₂].",
                "|[3; −4]| = 5",
                calc = "wektory",
            )
            f(
                "Suma i różnica wektorów",
                "@v{u} ± @v{v} = [u₁ ± v₁; u₂ ± v₂]",
                "Dodawanie – reguła równoległoboku lub „trójkąta”.",
                calc = "wektory",
            )
            f(
                "Mnożenie wektora przez liczbę",
                "k·@v{u} = [k·u₁; k·u₂]",
                "|k·@v{u}| = |k|·|@v{u}|. k < 0 zmienia zwrot.",
            )
            f(
                "Wektor przeciwny",
                "−@v{u} = [−u₁; −u₂]    @v{BA} = −@v{AB}",
                null,
            )
            f(
                "Wektory równe",
                "@v{u} = @v{v} ⇔ u₁ = v₁ ∧ u₂ = v₂",
                "Ten sam kierunek, zwrot i długość.",
            )
            f(
                "Reguła łańcucha (Chasles’a)",
                "@v{AB} + @v{BC} = @v{AC}",
                null,
            )
            f(
                "Wektory równoległe",
                "u₁v₂ − u₂v₁ = 0  ⇔  @v{v} = k·@v{u}",
                "Wyznacznik det(u, v) = 0.",
                calc = "wektory",
            )
            f(
                "Wektory prostopadłe",
                "u₁v₁ + u₂v₂ = 0",
                "Iloczyn skalarny równy 0.",
                "[2; 3] ⟂ [3; −2], bo 6 − 6 = 0",
                calc = "wektory",
            )
            f(
                "Iloczyn skalarny",
                "@v{u}∘@v{v} = u₁v₁ + u₂v₂ = |@v{u}|·|@v{v}|·cos α",
                "α – kąt między wektorami. Wynik jest liczbą.",
                calc = "wektory",
            )
            f(
                "Kąt między wektorami",
                "cos α = @f{u₁v₁ + u₂v₂}{|@v{u}|·|@v{v}|}",
                "cos α > 0 – kąt ostry, = 0 – prosty, < 0 – rozwarty.",
                calc = "wektory",
            )
            f(
                "Własności iloczynu skalarnego",
                "@v{u}∘@v{u} = |@v{u}|²\n@v{u}∘@v{v} = @v{v}∘@v{u}\n(k@v{u})∘@v{v} = k(@v{u}∘@v{v})",
                null,
            )
            f(
                "Wyznacznik pary wektorów",
                "det(@v{u}, @v{v}) = u₁v₂ − u₂v₁",
                "Znak mówi o orientacji: > 0 – v jest „na lewo” od u.",
                calc = "wektory",
            )
            f(
                "Pole równoległoboku na wektorach",
                "P = |det(@v{u}, @v{v})| = |u₁v₂ − u₂v₁|",
                null,
                calc = "wektory",
            )
            f(
                "Pole trójkąta na wektorach",
                "P = @f{1}{2}|det(@v{AB}, @v{AC})|",
                null,
                calc = "trojkat-3pkt",
            )
            f(
                "Wersor (wektor jednostkowy)",
                "@v{e} = @f{@v{u}}{|@v{u}|}",
                "Wektor o długości 1 i tym samym kierunku i zwrocie.",
            )
            f(
                "Rzut wektora na wektor",
                "rzut_{@v{v}}@v{u} = @f{@v{u}∘@v{v}}{|@v{v}|²}·@v{v}",
                "Długość rzutu: |u∘v| / |v|.",
            )
            f(
                "Środek odcinka wektorowo",
                "@v{OS} = @f{1}{2}(@v{OA} + @v{OB})",
                null,
            )
            f(
                "Środek ciężkości wektorowo",
                "@v{GA} + @v{GB} + @v{GC} = @v{0}",
                "G – środek ciężkości trójkąta ABC.",
            )
        }

        category("okrag-analitycznie", "Okrąg w układzie", "równania, styczne, położenie") {
            f(
                "Równanie okręgu (kanoniczne)",
                "(x − a)² + (y − b)² = r²",
                "Okrąg o środku S(a; b) i promieniu r > 0.",
                "(x − 2)² + (y + 1)² = 9: S(2; −1), r = 3",
                tags = "rownanie okregu",
            )
            f(
                "Równanie okręgu (ogólne)",
                "x² + y² − 2ax − 2by + c = 0",
                "Środek S(a; b), promień r = @s{a² + b² − c}. Warunek: a² + b² − c > 0.",
                "x² + y² − 4x + 6y − 12 = 0\na = 2, b = −3, r = @s{4 + 9 + 12} = 5",
                calc = "okrag-ogolny",
            )
            f(
                "Okrąg z równania x² + y² + Dx + Ey + F = 0",
                "S = (−@f{D}{2}; −@f{E}{2})    r = @f{1}{2}@s{D² + E² − 4F}",
                "Ta sama postać ogólna zapisana innymi literami.",
                calc = "okrag-ogolny",
            )
            f(
                "Okrąg o środku w początku układu",
                "x² + y² = r²",
                null,
            )
            f(
                "Koło",
                "(x − a)² + (y − b)² ≤ r²",
                "Wnętrze koła bez brzegu: znak <.",
            )
            f(
                "Położenie punktu względem okręgu",
                "|PS| < r: wewnątrz\n|PS| = r: na okręgu\n|PS| > r: na zewnątrz",
                "Albo: podstaw P do (x − a)² + (y − b)² i porównaj z r².",
            )
            f(
                "Położenie prostej względem okręgu",
                "d < r: sieczna (2 punkty)\nd = r: styczna (1 punkt)\nd > r: rozłączna",
                "d – odległość środka okręgu od prostej.",
                calc = "okrag-prosta",
            )
            f(
                "Wzajemne położenie dwóch okręgów",
                "d > r₁ + r₂: rozłączne zewnętrznie\nd = r₁ + r₂: styczne zewnętrznie\n|r₁ − r₂| < d < r₁ + r₂: przecinają się\nd = |r₁ − r₂|: styczne wewnętrznie\nd < |r₁ − r₂|: rozłączne wewnętrznie",
                "d = |S₁S₂| – odległość środków. d = 0 i r₁ ≠ r₂ – okręgi współśrodkowe.",
                calc = "dwa-okregi",
            )
            f(
                "Punkty wspólne prostej i okręgu",
                "@sys{(x − a)² + (y − b)² = r²}{y = mx + k}",
                "Podstaw y do równania okręgu – powstaje równanie kwadratowe: Δ > 0 – 2 punkty, Δ = 0 – styczna, Δ < 0 – brak.",
                calc = "okrag-prosta",
            )
            f(
                "Styczna do okręgu w punkcie okręgu",
                "(x₀ − a)(x − a) + (y₀ − b)(y − b) = r²",
                "P(x₀; y₀) leży na okręgu o środku (a; b). Styczna jest prostopadła do promienia SP.",
            )
            f(
                "Styczna do x² + y² = r²",
                "x₀x + y₀y = r²",
                "W punkcie (x₀; y₀) okręgu o środku w O.",
            )
            f(
                "Warunek styczności prostej",
                "@f{|Aa + Bb + C|}{@s{A² + B²}} = r",
                "Prosta Ax + By + C = 0 jest styczna do okręgu o środku (a; b) i promieniu r.",
                calc = "okrag-prosta",
            )
            f(
                "Długość stycznej z punktu",
                "l = @s{|PS|² − r²}",
                "Długość odcinka stycznej od P do punktu styczności.",
            )
            f(
                "Długość cięciwy",
                "|AB| = 2@s{r² − d²}",
                "d – odległość środka okręgu od prostej zawierającej cięciwę.",
                calc = "okrag-prosta",
            )
            f(
                "Okrąg opisany na trójkącie",
                "|SA| = |SB| = |SC| = R",
                "Środek – przecięcie symetralnych boków. Rozwiąż układ: (x − a)² + (y − b)² = R² dla A, B, C.",
                calc = "trojkat-3pkt",
            )
            f(
                "Okrąg wpisany w trójkąt",
                "I = @f{a·A + b·B + c·C}{a + b + c}    r = @f{2P}{a + b + c}",
                "Środek – przecięcie dwusiecznych; a, b, c – długości boków naprzeciw A, B, C (współrzędne liczymy osobno dla x i y).",
                calc = "trojkat-3pkt",
            )
            f(
                "Oś potęgowa dwóch okręgów",
                "(D₁ − D₂)x + (E₁ − E₂)y + F₁ − F₂ = 0",
                "Odejmij równania ogólne okręgów – dostaniesz prostą przez ich punkty wspólne (wspólną cięciwę).",
                calc = "dwa-okregi",
            )
            f(
                "Okrąg styczny do osi",
                "do OX: r = |b|    do OY: r = |a|",
                "Dla środka S(a; b).",
            )
            f(
                "Równanie parametryczne okręgu",
                "@sys{x = a + r·cos t}{y = b + r·sin t}   t ∈ ⟨0; 2π)",
                null,
            )
        }

        category("przeksztalcenia", "Przekształcenia", "symetrie, przesunięcia, obroty, wykresy") {
            f(
                "Przesunięcie o wektor",
                "P(x; y) → P'(x + p; y + q)",
                "Translacja o wektor u = [p; q].",
            )
            f(
                "Symetria względem osi OX",
                "(x; y) → (x; −y)",
                null,
            )
            f(
                "Symetria względem osi OY",
                "(x; y) → (−x; y)",
                null,
            )
            f(
                "Symetria względem początku układu",
                "(x; y) → (−x; −y)",
                "Symetria środkowa względem O(0; 0).",
            )
            f(
                "Symetria względem punktu",
                "(x; y) → (2a − x; 2b − y)",
                "Względem S(a; b) – S jest środkiem odcinka PP'.",
            )
            f(
                "Symetria względem prostej y = x",
                "(x; y) → (y; x)",
                "Wykresy funkcji odwrotnych są symetryczne względem y = x.",
            )
            f(
                "Symetria względem prostej y = −x",
                "(x; y) → (−y; −x)",
                null,
            )
            f(
                "Symetria względem prostych x = a, y = b",
                "x = a: (x; y) → (2a − x; y)\ny = b: (x; y) → (x; 2b − y)",
                null,
            )
            f(
                "Symetria względem dowolnej prostej",
                "P' = (x₀ − 2At; y₀ − 2Bt)\nt = @f{Ax₀ + By₀ + C}{A² + B²}",
                "Względem Ax + By + C = 0.",
                calc = "punkt-prosta",
            )
            f(
                "Obrót o 90° wokół O",
                "+90°: (x; y) → (−y; x)\n−90°: (x; y) → (y; −x)\n180°: (x; y) → (−x; −y)",
                "+90° – przeciwnie do ruchu wskazówek zegara.",
            )
            f(
                "Obrót o kąt α wokół O",
                "x' = x·cos α − y·sin α\ny' = x·sin α + y·cos α",
                "Obrót wokół punktu S: przesuń S do O, obróć, przesuń z powrotem.",
            )
            f(
                "Jednokładność",
                "x' = a + k(x − a)\ny' = b + k(y − b)",
                "Środek S(a; b), skala k. Długości mnożą się przez |k|, pola przez k².",
                tags = "jednokladnosc skala",
            )
            f(
                "Wykres y = f(x − p) + q",
                "przesunięcie o wektor [p; q]",
                "p > 0 – w prawo, q > 0 – w górę.",
                "y = (x − 2)² + 3: parabola y = x² przesunięta o [2; 3]",
            )
            f(
                "Wykres y = f(x) + q",
                "przesunięcie o q w pionie",
                "q > 0 – w górę, q < 0 – w dół.",
            )
            f(
                "Wykres y = f(x − p)",
                "przesunięcie o p w poziomie",
                "Uwaga: f(x − 3) to przesunięcie w PRAWO o 3, f(x + 3) – w lewo.",
            )
            f(
                "Wykres y = −f(x)",
                "symetria względem osi OX",
                null,
            )
            f(
                "Wykres y = f(−x)",
                "symetria względem osi OY",
                null,
            )
            f(
                "Wykres y = −f(−x)",
                "symetria względem punktu O",
                null,
            )
            f(
                "Wykres y = |f(x)|",
                "część pod osią OX odbijamy w górę",
                "Część nad osią zostaje bez zmian.",
            )
            f(
                "Wykres y = f(|x|)",
                "część dla x ≥ 0 odbijamy na lewo",
                "Wykres jest symetryczny względem OY; lewą część oryginału usuwamy.",
            )
            f(
                "Wykres y = k·f(x)",
                "rozciągnięcie w pionie k razy",
                "Powinowactwo prostokątne o osi OX i skali k.",
            )
            f(
                "Wykres y = f(k·x)",
                "ściśnięcie w poziomie k razy",
                "Powinowactwo o osi OY i skali 1/k.",
            )
        }

        category("stozkowe", "Krzywe stożkowe", "elipsa, hiperbola, parabola") {
            f(
                "Elipsa",
                "@f{x²}{a²} + @f{y²}{b²} = 1",
                "Półosie a i b. Środek w O.",
            )
            f(
                "Ogniska elipsy",
                "F₁,₂ = (±c; 0)    c² = a² − b²",
                "Dla a > b (oś wielka na OX).",
            )
            f(
                "Definicja elipsy",
                "|PF₁| + |PF₂| = 2a",
                "Suma odległości od ognisk jest stała.",
            )
            f(
                "Mimośród elipsy",
                "e = @f{c}{a},  0 ≤ e < 1",
                "e = 0 – okrąg.",
            )
            f(
                "Pole elipsy",
                "P = πab",
                null,
            )
            f(
                "Obwód elipsy (Ramanujan)",
                "L ≈ π(3(a + b) − @s{(3a + b)(a + 3b)})",
                "Bardzo dokładne przybliżenie – dokładny wzór wymaga całki eliptycznej.",
            )
            f(
                "Styczna do elipsy",
                "@f{x₀x}{a²} + @f{y₀y}{b²} = 1",
                "W punkcie (x₀; y₀) elipsy.",
            )
            f(
                "Hiperbola",
                "@f{x²}{a²} − @f{y²}{b²} = 1",
                null,
            )
            f(
                "Ogniska hiperboli",
                "F₁,₂ = (±c; 0)    c² = a² + b²",
                null,
            )
            f(
                "Definicja hiperboli",
                "| |PF₁| − |PF₂| | = 2a",
                "Wartość bezwzględna różnicy odległości od ognisk jest stała.",
            )
            f(
                "Asymptoty hiperboli",
                "y = ±@f{b}{a}x",
                null,
            )
            f(
                "Mimośród hiperboli",
                "e = @f{c}{a} > 1",
                null,
            )
            f(
                "Styczna do hiperboli",
                "@f{x₀x}{a²} − @f{y₀y}{b²} = 1",
                null,
            )
            f(
                "Hiperbola y = k/x",
                "x·y = k",
                "Hiperbola równoosiowa obrócona o 45°; asymptoty to osie układu.",
            )
            f(
                "Parabola y² = 2px",
                "F = (@f{p}{2}; 0)    kierownica: x = −@f{p}{2}",
                null,
            )
            f(
                "Parabola y = ax² – ognisko",
                "F = (0; @f{1}{4a})    kierownica: y = −@f{1}{4a}",
                "Dla y = ax² + bx + c: F = (p; q + 1/(4a)), gdzie W(p; q) – wierzchołek.",
            )
            f(
                "Definicja paraboli",
                "|PF| = d(P, k)",
                "Odległość od ogniska F = odległość od kierownicy k.",
            )
            f(
                "Styczna do paraboli y² = 2px",
                "y₀y = p(x + x₀)",
                null,
            )
            f(
                "Ogólne równanie stożkowej",
                "Ax² + Bxy + Cy² + Dx + Ey + F = 0",
                "B² − 4AC < 0 – elipsa, = 0 – parabola, > 0 – hiperbola.",
            )
        }

        category("przestrzen", "Geometria analityczna 3D", "płaszczyzny, proste, wektory w przestrzeni") {
            f(
                "Odległość punktów w przestrzeni",
                "|AB| = @s{(x₂ − x₁)² + (y₂ − y₁)² + (z₂ − z₁)²}",
                null,
            )
            f(
                "Środek odcinka w przestrzeni",
                "S = (@f{x₁ + x₂}{2}; @f{y₁ + y₂}{2}; @f{z₁ + z₂}{2})",
                null,
            )
            f(
                "Długość wektora 3D",
                "|@v{u}| = @s{u₁² + u₂² + u₃²}",
                null,
            )
            f(
                "Iloczyn skalarny 3D",
                "@v{u}∘@v{v} = u₁v₁ + u₂v₂ + u₃v₃",
                "= |u|·|v|·cos α.",
            )
            f(
                "Iloczyn wektorowy",
                "@v{u} × @v{v} = [u₂v₃ − u₃v₂; u₃v₁ − u₁v₃; u₁v₂ − u₂v₁]",
                "Wektor prostopadły do u i v; długość = pole równoległoboku = |u|·|v|·sin α.",
            )
            f(
                "Pole trójkąta w przestrzeni",
                "P = @f{1}{2}|@v{AB} × @v{AC}|",
                null,
            )
            f(
                "Iloczyn mieszany i objętość",
                "V = |(@v{u} × @v{v})∘@v{w}|",
                "Objętość równoległościanu. Czworościanu: V = 1/6 tej wartości.",
            )
            f(
                "Równanie płaszczyzny (ogólne)",
                "Ax + By + Cz + D = 0",
                "n = [A; B; C] – wektor normalny.",
            )
            f(
                "Płaszczyzna przez punkt ⟂ do wektora",
                "A(x − x₀) + B(y − y₀) + C(z − z₀) = 0",
                null,
            )
            f(
                "Płaszczyzna – postać odcinkowa",
                "@f{x}{a} + @f{y}{b} + @f{z}{c} = 1",
                "Przecina osie w (a; 0; 0), (0; b; 0), (0; 0; c).",
            )
            f(
                "Odległość punktu od płaszczyzny",
                "d = @f{|Ax₀ + By₀ + Cz₀ + D|}{@s{A² + B² + C²}}",
                "Analogia do odległości punktu od prostej na płaszczyźnie.",
            )
            f(
                "Odległość płaszczyzn równoległych",
                "d = @f{|D₁ − D₂|}{@s{A² + B² + C²}}",
                "Przy tych samych A, B, C.",
            )
            f(
                "Prosta w przestrzeni (parametryczna)",
                "@sys{x = x₀ + t·u₁}{y = y₀ + t·u₂}{z = z₀ + t·u₃}",
                null,
            )
            f(
                "Prosta w przestrzeni (kanoniczna)",
                "@f{x − x₀}{u₁} = @f{y − y₀}{u₂} = @f{z − z₀}{u₃}",
                null,
            )
            f(
                "Kąt między płaszczyznami",
                "cos φ = @f{|A₁A₂ + B₁B₂ + C₁C₂|}{@s{A₁² + B₁² + C₁²}·@s{A₂² + B₂² + C₂²}}",
                null,
            )
            f(
                "Kąt prostej z płaszczyzną",
                "sin φ = @f{|@v{u}∘@v{n}|}{|@v{u}|·|@v{n}|}",
                "u – wektor kierunkowy prostej, n – normalny płaszczyzny.",
            )
            f(
                "Odległość punktu od prostej w 3D",
                "d = @f{|@v{AP} × @v{u}|}{|@v{u}|}",
                "A – punkt prostej, u – jej wektor kierunkowy.",
            )
            f(
                "Odległość prostych skośnych",
                "d = @f{|(@v{u} × @v{v})∘@v{AB}|}{|@v{u} × @v{v}|}",
                "A, B – punkty prostych, u, v – ich wektory kierunkowe.",
            )
            f(
                "Sfera",
                "(x − a)² + (y − b)² + (z − c)² = r²",
                null,
            )
        }
    }
}
