/**
 *
 *  @author Janik Nikola 
 *
 */

package travel_offers;


import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    File dataDir = new File("data");
    TravelData travelData = new TravelData(dataDir);
    String dateFormat = "yyyy-MM-dd";
    for (String locale : Arrays.asList("pl_PL", "en_GB")) {
      List<String> odlist = travelData.getOffersDescriptionsList(locale, dateFormat);
      for (String od : odlist) System.out.println(od);
    }
    // --- część bazodanowa
    String url = "jdbc:postgresql://localhost:5432/travel_offers?user=postgres&password=TWOJE_HASLO"; /*<-- tu należy wpisać URL bazy danych i uzupełnić TWOJE_HASLO */
    Database db = new Database(url, travelData);
    db.create();
    db.showGui();
  }

}
