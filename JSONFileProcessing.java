package com.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Data;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class JSONFileProcessing {

    public static List<Invoice> invoices = new ArrayList<Invoice>();

    public static void main(String[] args) throws IOException, ParseException {
        String filepath = "json-data.json";

        System.out.println("Hola, Enter the option to perform,");
        int userInput = -1;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1, Read from file.");
            System.out.println("2, Write to new file.");
            System.out.println("3, Sort the data.");
            System.out.println("0, Exit.");
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1: {
                    JSONFileProcessing.readDataFromFile(filepath);
                    break;
                }
                case 2: {
                    if (invoices.isEmpty()) {
                        JSONFileProcessing.readDataFromFile(filepath);
                    }
                    JSONFileProcessing.writeDataToNewFile(System.currentTimeMillis() + "json-data.json");
                    break;
                }
                case 3: {
                    if (invoices.isEmpty()) {
                        JSONFileProcessing.readDataFromFile(filepath);
                    }
                    System.out.println("    Choose the option for Sort");
                    System.out.println("    1. Sort By Customer Name");
                    System.out.println("    2. Sort By Customer Mobile");
                    System.out.println("    2. Sort By Invoice Date");
                    int sortOption = scanner.nextInt();
                    JSONFileProcessing.sortInvoices(sortOption);
                    break;
                }
                case 0: {
                    System.exit(1);
                    break;
                }
                default: {
                    System.out.println("invalid input!");
                }
            }
        } while (userInput != 0);
    }

    public static void readDataFromFile(String filePath) throws IOException, ParseException {
        Gson gson = new Gson();
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(filePath);
        Object objectData = jsonParser.parse(fileReader);
        JSONArray jsonArray = (JSONArray) objectData;
        for (Object jsonObject : jsonArray) {
            JSONObject invoiceData = (JSONObject) jsonObject;
            Invoice invoice = gson.fromJson(invoiceData.toString(), Invoice.class);
            invoices.add(invoice);
            System.out.println("invoice id " + invoice.getId() + " added to list");
        }
    }

    public static void writeDataToNewFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray();
        for (Invoice invoice : invoices) {
            jsonArray.add(invoice);
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonArray);
        fileOutputStream.close();
        System.out.println("File created with data : " + file.getAbsolutePath());
    }

    public static void sortInvoices(int option) {
        System.out.println("-----------------before sort------------------");
        for (Invoice invoice : invoices) {
            System.out.println(invoice);
        }
        switch (option) {
            case 1: {
                Collections.sort(invoices, new SortInvoicesByCustomerName());
                break;
            }
            case 2: {
                Collections.sort(invoices, new SortInvoicesByMobile());
                break;
            }
            case 3: {
                Collections.sort(invoices);
                break;
            }
            default: {
                System.out.println("Invalid Sort Option!");
            }
        }
        System.out.println("-----------------after sort------------------");
        for (Invoice invoice : invoices) {
            System.out.println(invoice);
        }
    }

    public static void printSortedInvoices(String sortKey) {

    }

}

@Data
@ToString
class Invoice implements Serializable, Comparable<Invoice> {
    private Long id;
    private Users user;
    private Set<Product> purchaseItems;
    private Double billAmount;
    private String invoiceFileURL;
    private Timestamp timestamp;
    private Timestamp purchaseTime;
    private byte status = 1;
    private Long[] purchaseItemsIds;

    public int compareTo(Invoice o) {
        return this.purchaseTime.compareTo(o.purchaseTime);
    }
}

@Data
class Users implements Serializable {
    private Long id;
    private String customerName;
    private String mobile;
    private Timestamp timestamp;
    private Timestamp createTime;
    private byte status;
}

@Data
class Product implements Serializable {
    private Long id;
    private String item;
    private String batch;
    private Double price;
    private Timestamp timestamp;
    private Timestamp createTime;
    private byte status = 1;
}

class SortInvoicesByCustomerName implements Comparator<Invoice> {
    public int compare(Invoice o1, Invoice o2) {
        return o1.getUser().getCustomerName().compareTo(o2.getUser().getCustomerName());
    }
}

class SortInvoicesByMobile implements Comparator<Invoice> {
    public int compare(Invoice o1, Invoice o2) {
        return o1.getUser().getMobile().compareTo(o2.getUser().getMobile());
    }
}
