# Project "Travel Agent" with JDBC

**[PL]**
**Cel projektu:**

Projekt jest realizacją zadania z przedmiotu Uniwersalne Techniki Programowania. Celem projektu było wykorzysteniem JDBC w praktyce oraz zastosowanie mechanizmów internacjonalizacji aplikacji. 

**Założenia projektu:**

Program symulowania biura podróży został stworzony tak, aby przychodzące od klientów pliki tekstowe zawierające opisy oferty były wpisywane do bazy danych (w tym przypadku PostgreSQL), a następnie były wyświetlane przy pomocy prostego GUI (JTable). Wyświetlana tabela z ofertami jest zinternacjonalizowana, czyli dostosowuje język oraz formatowanie daty i waluty według lokalizacji klienta. 
Zakładam, że przesyłane pliki z ofertami (każda oferta jest w jednym wierszu pliku i zawiera, rozdzielone znakami tabulacji informacje) mają format:

lokalizacje_kontrahenta kraj  date_wyjazdu  date_powrotu miejsce cene symbol_waluty

gdzie:
lokalizacja - napis,  oznaczający język_kraj (np. pl_PL, en_US; tak jak zwraca to metoda toString() z klasy Locale)
kraj - nazwa kraju w języku kontrahenta,
daty - (wyjazdu, powrotu) daty w formacie RRRR-MM-DD (np. 2015-12-31),
miejsce - jedno z: [morze, jezioro, góry] - w języku kontrahenta,
cena - liczba w formacie liczb, używanym w kraji kontrahenta,
symbol_waluty = PLN, USD itp.

**Opis zawartości pakietu:**

1. Klasa Database obsługuje wszystkie operacje związane z przetwarzaniem danych oraz zapisywaniem i odczytywaniem ich z bazy danych. W tej klasie równiez znajduje się fragment kodu tworzący prosty interfejs użytkownika, na którym prezentowane są oferty wicieczek.

2. Klasa TravelData ma za zadanie zinternacjonalizować wyświetlane w GUI dane.

3. W klasie Main należy uzupełnić linię kodu, gdzie nawiązywane jest połączenie z bazą danych. 

4. Dodatkwe pliki .txt w folderze Data zawierają dane do przetstowania działania aplikacji.

5. W folderze Rosources znajdują się pliki .properties, które są dodatkowym źródłem wykorzystywanym przez mechanizm Resource Boundle. Przed uruchomieniem aplikacji należy się upewnić, że folder ten został odpowiednio dodany do classpath i jest używany jako źródło danych przez program. 

**UWAGA!** Przed uruchomieniem aplikacji należy:
- otworzyć bazę danych (PostrgreSQL) i nawiązać połączenie z serwerem
- mieć utworzoną bazę danych travel_ofers,
- w bazie danych powinna się znajdować tabela z kolumnami: id (PrimaryKey, integer), country (text), departure (date), return (date), place (text), price (real), currency (text).


**[EN]**
