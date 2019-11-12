### Simulation football game
Gra symulacyjna przeznaczona została dla jednego użytkownika, który rywalizuję z komputerem. Zadaniem gracza jest takie ustawienie taktyki drużyny z przydzielonych zawodników aby zmaksymalizować jej szanse na osiągnięcie korzystnego rezultatu w rozgrywanym spotkaniu.

### Przebieg gry:
1)	Wylosowanie taktyki (z 7 dostępnych) oraz poziomu siły graczy (5 poziomów) dla drużyny komputera
2)	Utworzenie zawodników dla drużyny komputera
Przykład ustalania siły graczy: jeżeli poziom siły „średni” ma określoną siłę na 50 punktów w 100 stopniowej skali, to siła zawodnika z tego poziomu będzie mieściła się w przedziale 30-70 punktów (50pkt +/- 20pkt). Dzięki temu siła zawodników w drużynie jest zbalansowana wokół danego poziomu, ale dla każdego zawodnika jest indywidualna. 

3)	Wylosowanie poziomu siły graczy dla drużyny gracza, utworzenie zawodników bez przypisanej pozycji na boisku
4)	Ustawienie zawodników przez gracza w wybranej przez siebie taktyce, przypisanie do graczy wybranej pozycji.
Zadaniem gracza jest takie ustawienie swojego zespołu widząc ustawienie oraz siłę poszczególnych graczy z drużyny komputera, aby zmaksymalizować szansę na osiągnięcie dobrego rezultatu podczas symulowanego meczu

5)	Zasymulowanie meczu. Podczas meczu zostanie utworzone około 30 zdarzeń(akcji meczowych) z których każde zakończy się jakimś efektem. W grze dostępne będzie 5 rodzajów zdarzeń (strzał, rzut wolny, rzut karny, faul, spalony) oraz 7 efektów (gol, strzał niecelny, obrona bramkarza, żółta kartka, czerwona kartka, faul bez kartki, pozycja spalona)
6)	Wyświetlenie wyniku i statystyk meczu


### Technologie:
Projekt wykonany został w technologii Java 8. Interfejs użytkownika stworzony jest przy wykorzystaniu JavaFX. W celu połączenia części logicznej z tą która jest wyświetlana graczowi wykorzystany został wzorzec architektoniczny MVC (Model-View-Controller).  W projekcie nie została wykorzystana żadna baza danych, przez co dane zostają każdorazowo generowane i nie istnieje możliwość odtworzenia danych historycznych.

W aplikacji zostały wykorzystane 3 wzorce projektowe:

Builder – do tworzenia obiektu z danymi zdarzenia
Prosta Fabryka – do tworzenia zdarzeń meczowych
Strategia – do ustalania  efektu danego zdarzenia

### Poradnik użytkownika:

## a)	Rozpoczęcie gry  
Pierwszym oknem który widzi gracz jest pokazane na zdjęciu poniżej okno startowe aplikacji. Użytkownik może na nim rozpocząć nową grę klikając w jedyny dostępny przycisk.

![image](https://user-images.githubusercontent.com/25456823/68706907-088bd500-0591-11ea-8e21-b96b19caf8f8.png)

 
## b)	Ustawienie taktyki

Pierwszym oknem rozgrywki jest okno wyboru taktyki, na którym gracz widząc zawodników drużyny komputera ustawionych tak jak będą rozgrywać spotkanie (lewa strona okna) musi ustawić swój zespół. Gracz przypisuję każdemu zawodnikowi z tabeli na środku pozycję na której wystąpi on podczas spotkania. Po wyborze pozycji zawodnik zostaje ustawiony na boisko po prawej stronie w celu lepszej wizualizacji taktyki. 
Gracz może zdać się także na „ślepy los” i rozpocząć spotkanie z losowo ustawionym przez komputer składem wybierając odpowiedni przycisk pod tabelą z zawodnikami

Aby rozpocząć mecz konieczne jest przypisanie indywidualnej pozycji na boisku każdemu z przydzielonych zawodników.

![image](https://user-images.githubusercontent.com/25456823/68706840-e1350800-0590-11ea-9919-47a892337f16.png)


## c)	Symulacja meczu

Po ustawieniu składu przechodzimy do okna symulacji spotkania. Na jego górze widzimy aktualną minutę spotkania oraz wynik. Po bokach natomiast  widoczne są statystyki drużyn oraz poszczególnych zawodników (po lewej stronie drużyna komputera, po prawej gracza). W centrum okna znajduję się tabela wyświetlająca na bieżąco komentarz z symulowanych zdarzeń meczowych. W prawym dolnym rogu są dostępne przyciski wyjścia z gry oraz ponownego zagrania. Wszystkie elementy odświeżają się w czasie rzeczywisty, pokazując aktualne dane spotkania.

Czas rozegrania spotkania wynosi niecałe dwie minuty. Podczas symulacji szanse drużyn zostają poddane normalizacji dającej cień szans na zwycięstwo dużo słabszej drużynie. 
 
Poniżej zaprezentowane zostały przykładowe zdjęcia z przeprowadzonych symulacji.

![image](https://user-images.githubusercontent.com/25456823/68706949-19d4e180-0591-11ea-9758-e6831da609ce.png)

![image](https://user-images.githubusercontent.com/25456823/68706989-2c4f1b00-0591-11ea-93b2-c4b525b9cd28.png)

![image](https://user-images.githubusercontent.com/25456823/68707011-383add00-0591-11ea-92fc-173cd3055d7b.png)
