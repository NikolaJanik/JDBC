/**
 *
 *  @author Janik Nikola 
 *
 */

package travel_offers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;


public class Database {

	private final String url;
    private final TravelData travelData;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }

    public void create() {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS offers");
            stmt.executeUpdate("CREATE TABLE offers (id SERIAL PRIMARY KEY, country TEXT, departure DATE, return DATE, place TEXT, price REAL, currency TEXT)");

            List<String> offers = travelData.getOffersDescriptionsList("pl_PL", "yyyy-MM-dd");
            for (String offer : offers) {
                String[] parts = offer.split("\t");

                if (parts.length != 6) {
                    System.err.println("Nieprawidłowy format danych: " + Arrays.toString(parts));
                    continue;
                }

                String country = parts[0];
                java.sql.Date departure = parseDate(parts[1]);
                java.sql.Date returnDate = parseDate(parts[2]);
                String place = parts[3];
                double price = parsePrice(parts[4], Locale.forLanguageTag("pl-PL"));
                String currency = parts[5];

                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO offers (country, departure, return, place, price, currency) VALUES (?, ?, ?, ?, ?, ?)")) {
                    ps.setString(1, country);
                    ps.setDate(2, departure);
                    ps.setDate(3, returnDate);
                    ps.setString(4, place);
                    ps.setDouble(5, price);
                    ps.setString(6, currency);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    private java.sql.Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        java.util.Date utilDate = sdf.parse(dateStr);
        return new java.sql.Date(utilDate.getTime()); 
    }
    
    private double parsePrice(String priceStr, Locale locale) {
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        try {
            return nf.parse(priceStr).doubleValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Nieprawidłowy format ceny: " + priceStr, e);
        }
    }

    public void showGui() {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM offers");

            JTable table = new JTable();
            DefaultTableModel model = new DefaultTableModel(new String[]{"Country", "Departure", "Return", "Place", "Price", "Currency"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("country"),
                        rs.getDate("departure"),
                        rs.getDate("return"),
                        rs.getString("place"),
                        rs.getDouble("price"),
                        rs.getString("currency")
                });
            }
            table.setModel(model);

            JFrame frame = new JFrame("Travel Offers");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JScrollPane(table));
            frame.pack();
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
