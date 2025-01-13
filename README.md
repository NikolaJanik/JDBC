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

**Project Objective:**

The project is a task for the course "Universal Programming Techniques." Its aim was to apply JDBC in practice and implement application internationalization mechanisms.

**Project Assumptions:**

A travel agency simulation program was designed so that incoming text files from clients containing offer descriptions are written to a database (PostgreSQL in this case) and then displayed using a simple GUI (JTable). The displayed table of offers is internationalized, adjusting the language, date format, and currency formatting according to the client's locale.
It is assumed that the transmitted offer files (each offer in one row of the file, with information separated by tab characters) have the following format:

location_partner country departure_date return_date place price currency_symbol

Where:

location - a string indicating the language and country (e.g., pl_PL, en_US, as returned by the toString() method of the Locale class),
country - the name of the country in the partner's language,
dates (departure, return) - dates in the format YYYY-MM-DD (e.g., 2015-12-31),
place - one of [sea, lake, mountains] in the partner's language,
price - a number formatted according to the partner's locale,
currency_symbol - e.g., PLN, USD, etc.

**Package Contents Description:**

1. Class Database handles all operations related to data processing, saving, and retrieving from the database. This class also contains a section of code for creating a simple user interface to display travel offers.
2. Class TravelData is responsible for internationalizing the data displayed in the GUI.
3. In Class Main, the line of code establishing a connection with the database needs to be completed.
4. Additional .txt files in the Data folder contain data for testing the application.
5. The Resources folder contains .properties files, which serve as an additional source utilized by the ResourceBundle mechanism. Before running the application, ensure that this folder is correctly added to the classpath and is used as a data source by the program.

**IMPORTANT!** Before running the application:

- Open the database (PostgreSQL) and connect to the server.
- Ensure that a database named travel_offers is created.
- The database should contain a table with the following columns: id (PrimaryKey, integer), country (text), departure (date), return (date), place (text), price (real), currency (text).
