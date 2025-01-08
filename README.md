# Projekt "Biuro podóży" JDBC

Celem projektu było przećwiczenie wykorzystania JDBC w praktyce oraz nauka mechanizmów internacjonalizacji aplikacji. 

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


Klasa Database obsługuje wszystkie operacje związane z przetwarzaniem danych oraz zapisywaniem i odczytywaniem ich z bazy danych. 

Klasa TravelData ma za zadanie zinternacjonalizować wyświetlane w GUI dane. 

Dodatkwe pliki .txt w folderze Data zawierają dane do przetstowania działania aplikacji.
W folderze Rosources znajdują się pliki .properties, które są dodatkowym źródłem wykorzystywanym przez mechanizm Resource Boundle. Przed uruchomieniem aplikacji należy się umewnić, że folder ten został odpowiednio dodany na classpath i jest używany jako źródło dancyh przez program. 

UWAGA! Przed próbą uruchomienia aplikacji trzeba otworzyć bazę danych (PostrgreSQL), nawiązać połączenie z serwerem, mieć utworzoną bazę danych travel_ofers, a w niej tabelę z kolumnami o odpowiednich typach danych.
