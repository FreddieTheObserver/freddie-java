package oop.solutions.LibraryManagement;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LibraryManagementMain {
    static final String FILE = "library.data";
    static final int RECORD_SIZE = 86;

    public static void main(String[] args) throws IOException {
        new File(FILE).delete();

        addBook(1, "Clean Code",               "Robert Martin", 350.0, 5);
        addBook(2, "The Pragmatic Programmer", "David Thomas",  420.0, 3);
        addBook(3, "Effective Java",           "Joshua Bloch",  280.0, 8);
        addBook(4, "Head First Java",          "Kathy Sierra",  190.0, 6);

        System.out.println(readBook());
        System.out.println(readBook(3));
        System.out.println(searchByAuthor("joshua bloch"));
        System.out.println(searchByPriceRange(200, 400));

        borrowBook(3);
        System.out.println(readBook(3));

        for (int i = 0; i < 6; i++) {
            borrowBook(4);
        }
    }

    public static void addBook(int id, String title, String author, double price, int quantity) throws IOException {
        try (RandomAccessFile fpt = new RandomAccessFile(FILE, "rw")) {
            fpt.seek(fpt.length());

            fpt.writeInt(id);

            byte[] titleBytes = (title + " ".repeat(40)).getBytes();
            fpt.write(titleBytes, 0, 40);

            byte[] authorBytes = (author + " ".repeat(30)).getBytes();
            fpt.write(authorBytes, 0, 30);

            fpt.writeDouble(price);
            fpt.writeInt(quantity);
        }
    }

    public static String readBook() throws IOException {
        try (RandomAccessFile fpt = new RandomAccessFile(FILE, "r")) {
            fpt.seek(0);

            int id = fpt.readInt();

            byte[] titleBytes = new byte[40];
            fpt.readFully(titleBytes);
            String title = new String(titleBytes).trim();

            byte[] authorBytes = new byte[30];
            fpt.readFully(authorBytes);
            String author = new String(authorBytes).trim();

            double price = fpt.readDouble();
            int quantity = fpt.readInt();

            return id + ", " + title + ", " + author + ", " + price + ", " + quantity;
        }
    }

    public static String readBook(int recordNumber) throws IOException {
        try (RandomAccessFile fpt = new RandomAccessFile(FILE, "r")) {
            fpt.seek((long) RECORD_SIZE * (recordNumber - 1));

            int id = fpt.readInt();

            byte[] titleBytes = new byte[40];
            fpt.readFully(titleBytes);
            String title = new String(titleBytes).trim();

            byte[] authorBytes = new byte[30];
            fpt.readFully(authorBytes);
            String author = new String(authorBytes).trim();

            double price = fpt.readDouble();
            int quantity = fpt.readInt();

            return id + ", " + title + ", " + author + ", " + price + ", " + quantity;
        }
    }

    public static String searchByAuthor(String author) throws IOException {
        StringBuffer sb = new StringBuffer();

        try (RandomAccessFile fpt = new RandomAccessFile(FILE, "r")) {
            long totalRecords = fpt.length() / RECORD_SIZE;

            for (long i = 0; i < totalRecords; i++) {
                fpt.seek(i * RECORD_SIZE);

                int id = fpt.readInt();

                byte[] titleBytes = new byte[40];
                fpt.readFully(titleBytes);
                String title = new String(titleBytes).trim();

                byte[] authorBytes = new byte[30];
                fpt.readFully(authorBytes);
                String recordAuthor = new String(authorBytes).trim();

                double price = fpt.readDouble();
                int quantity = fpt.readInt();

                if (recordAuthor.equalsIgnoreCase(author)) {
                    sb.append(id).append(", ")
                            .append(title).append(", ")
                            .append(recordAuthor).append(", ")
                            .append(price).append(", ")
                            .append(quantity).append("\n");
                }
            }
        }
        if (sb.length() == 0) {
            return "No books found for this author.";
        }
        return sb.toString().trim();
    }

    public static String searchByPriceRange(double min, double max) throws IOException {
        StringBuffer sb = new StringBuffer();

        try (RandomAccessFile fpt = new RandomAccessFile(FILE, "r")) {
            long totalRecords = fpt.length() / RECORD_SIZE;

            for (long i = 0; i < totalRecords; i++) {
                fpt.seek(i * RECORD_SIZE);

                int id = fpt.readInt();

                byte[] titleBytes = new byte[40];
                fpt.readFully(titleBytes);
                String title = new String(titleBytes).trim();

                byte[] authorBytes = new byte[30];
                fpt.readFully(authorBytes);
                String author = new String(authorBytes).trim();

                double price = fpt.readDouble();
                int quantity = fpt.readInt();

                if (price >= min && price <= max) {
                    sb.append(id).append(", ")
                            .append(title).append(", ")
                            .append(author).append(", ")
                            .append(price).append(", ")
                            .append(quantity).append("\n");
                }
            }
        }
        if (sb.length() == 0) {
            return "No books found in this price range.";
        }
        return sb.toString().trim();
    }

    public static void borrowBook(int recordNumber) throws IOException {
        try (RandomAccessFile fpt = new RandomAccessFile(FILE, "rw")) {
            long quantityOffset = (long) RECORD_SIZE * (recordNumber - 1) + 82;

            fpt.seek(quantityOffset);
            int quantity = fpt.readInt();

            if (quantity == 0) {
                System.out.println("Out of stock.");
                return;
            }

            fpt.seek(quantityOffset);
            fpt.writeInt(quantity - 1);
        }
    }
}