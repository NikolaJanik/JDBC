package zad1;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.text.*;


public class TravelData {
	
	private final File dataDir;

    public TravelData(File dataDir) {
        this.dataDir = dataDir;
    }

    public List<String> getOffersDescriptionsList(String loc, String dateFormat) {
        List<String> descriptions = new ArrayList<>();
        Locale locale = Locale.forLanguageTag(loc.replace('_', '-'));

        // Załadowanie odpowiedniego pliku ResourceBundle
        ResourceBundle bundle = ResourceBundle.getBundle("translations", locale);

        // Pobranie formatów daty i walut
        String datePattern = bundle.getString("date.format");
        DateFormat df = new SimpleDateFormat(datePattern, locale);

        try (DirectoryStream<Path> files = Files.newDirectoryStream(dataDir.toPath())) {
            for (Path file : files) {
                try (BufferedReader br = Files.newBufferedReader(file)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split("\t");
                        if (parts.length != 7) {
                            System.err.println("Nieprawidłowy format danych: " + line);
                            continue;
                        }

                        // Lokalizacja na podstawie pierwszego elementu
                        String localeStr = parts[0];
                        Locale rowLocale = Locale.forLanguageTag(localeStr.replace('_', '-'));
                        ResourceBundle rowBundle = ResourceBundle.getBundle("translations", rowLocale);

                        // Odczyt i formatowanie danych
                        String country = parts[1];
                        Date departureDate = parseDate(parts[2], rowBundle.getString("date.format"));
                        Date returnDate = parseDate(parts[3], rowBundle.getString("date.format"));
                        String place = parts[4];
                        double price = parsePrice(parts[5], rowLocale);
                        String currency = parts[6];

                        // Formatowanie opisu oferty
                        descriptions.add(String.format(rowLocale, "%s\t%s\t%s\t%s\t%.2f\t%s",
                                country, df.format(departureDate), df.format(returnDate), place, price, currency));
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return descriptions;
    }

    private String translateCountry(String country, Locale locale) {
        Map<String, String> translations = new HashMap<>();
        translations.put("Japonia", locale.getLanguage().equals("en") ? "Japan" : "Japonia");
        translations.put("Włochy", locale.getLanguage().equals("en") ? "Italy" : "Włochy");
        translations.put("United States", locale.getLanguage().equals("pl") ? "Stany Zjednoczone Ameryki" : "United States");
        return translations.getOrDefault(country, country);
    }

    private String translatePlace(String place, Locale locale) {
        switch (place.toLowerCase()) {
            case "morze": return locale.getLanguage().equals("en") ? "sea" : "morze";
            case "jezioro": return locale.getLanguage().equals("en") ? "lake" : "jezioro";
            case "góry": return locale.getLanguage().equals("en") ? "mountains" : "góry";
            default: return place;
        }
    }
    
    private java.util.Date parseDate(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    private double parsePrice(String priceStr, Locale locale) {
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        try {
            return nf.parse(priceStr).doubleValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Nieprawidłowy format ceny: " + priceStr, e);
        }
    }

}
