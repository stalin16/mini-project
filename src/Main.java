package src;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.lang.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Product> productList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int curPage=1;
    static int rowPage=2;
    private static Table getTable() {
        Table table = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        table.addCell("   *)Display");
        table.addCell("   | W)rite");
        table.addCell("   | R)ead");
        table.addCell("   | U)pdate");
        table.addCell("   | D)elete");
        table.addCell("   | F)irst");
        table.addCell("   | P)revious");
        table.addCell("   | N)ext");
        table.addCell("   | L)ast   ");
        table.addCell("   S)earch");
        table.addCell("   | G)oto");
        table.addCell("   | Se)t row");
        table.addCell("   | H)elp");
        table.addCell("   | E)xit");
        return table;
    }
    public static void display(ArrayList<Product> productList, int curPage, int rowPage) {
        int i;
        int startIdx = (curPage - 1) * rowPage;
        int endIdx = Math.min(startIdx + rowPage, productList.size());
        Table tableDisplay = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        tableDisplay.addCell(" ".repeat(2) + "ID"          + " ".repeat(2));
        tableDisplay.addCell(" ".repeat(2) + "Name"        + " ".repeat(2));
        tableDisplay.addCell(" ".repeat(2) + "Unit Price"  + " ".repeat(2));
        tableDisplay.addCell(" ".repeat(2) + "QTY"         + " ".repeat(2));
        tableDisplay.addCell(" ".repeat(2) + "Import Date" + " ".repeat(2));

        for (i = startIdx; i < endIdx; i++) {
            Product p = productList.get(i);
            tableDisplay.addCell(" ".repeat(2) + p.getId().toString());
            tableDisplay.addCell(" ".repeat(2) + p.getName());
            tableDisplay.addCell(" ".repeat(2) + p.getUnitPrice().toString());
            tableDisplay.addCell(" ".repeat(2) + p.getQty().toString());
            tableDisplay.addCell(" ".repeat(2) + LocalDate.now());
        }
        int totalPages = (int) Math.ceil((double) productList.size() / rowPage);
        if(productList.isEmpty()){
            System.out.println("<>".repeat(80));
            System.out.println("No any products have found!!!");
        } else {
            System.out.println("<>".repeat(80));
            System.out.println(tableDisplay.render());
            System.out.println("    Page: " + curPage + " of " + totalPages + " ".repeat(30) + "Total records : " + productList.size());
        }
        System.out.println("<>".repeat(80));

    }
    public static void write(){

        System.out.print("ID: ");
        Integer id = scanner.nextInt();

        System.out.print("Name: ");
        String name = scanner.next();
        scanner.nextLine();

        System.out.print("Unit Price: ");
        Double unitPrice = scanner.nextDouble();

        System.out.print("QTY: ");
        Integer qty = scanner.nextInt();
        scanner.nextLine();

        Product p = new Product( id, name, unitPrice, qty, LocalDate.now());

        System.out.println("Do you wanna add this? [Y/y] or [N/n]: ");    String yn = scanner.nextLine().toLowerCase();
        if(yn.equals("y")){
            productList.add(p);
            System.out.println("Record added successfully.");
        } else if (yn.equals("n")) {
            System.out.println("This record is not stored.");
        } else {
            System.out.println("Please try again!!!");
        }
    }
    public static void delete(ArrayList<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Delete by iD ");
        Integer proID = Integer.parseInt(scanner.nextLine());
        for (Product p : productList) {
            if (p.getId().equals(proID)) {
                Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                table.addCell(" ID            : "+p.getId()        +" ".repeat(10));
                table.addCell(" Name          : "+p.getName()      +" ".repeat(10));
                table.addCell(" Unit price    : "+p.getUnitPrice() +" ".repeat(10));
                table.addCell(" QTY           : "+p.getQty()       +" ".repeat(10));
                table.addCell(" Import Date   : "+LocalDate.now()  +" ".repeat(10));
                System.out.println(table.render());
                System.out.print("Do you wanna delete this? [Y/y] or [N/n]: ");
                String yn = scanner.nextLine().toLowerCase();
                if(yn.equals("y")){
                    productList.remove(p);
                    System.out.println("Product ID is " + proID + " deleted successfully.");
                    return;
                } else if (yn.equals("n")) {
                    System.out.println("Product is still in stock");
                    return;
                } else {
                    System.out.println("Please, choose the correct options!!!");
                }
            }
        }
    }

    public static void search(ArrayList<Product> productList, int curPage, int rowPage) {
        ArrayList<Product> match = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search by keyword: ");
        String sName = scanner.nextLine().toLowerCase();
        for (Product pro : productList) {
            String proName = pro.getName().toLowerCase();
            if (proName.contains(sName)) {
                match.add(pro);
            }
        }
        int totalPages = (int) Math.ceil((double) match.size() / rowPage);
        if (match.isEmpty()) {
            System.out.println("<>".repeat(80));
            System.out.println("No products found!!!");
            System.out.println("<>".repeat(80));
        } else {
            if (curPage < 1) {
                curPage = 1;
            } else if (curPage > totalPages) {
                curPage = totalPages;
            }
            int startIdx = (curPage - 1) * rowPage;
            int endIdx = Math.min(startIdx + rowPage, match.size());
            Table tableDisplay = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
            tableDisplay.addCell(" ".repeat(2) + "ID"          + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Name"        + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Unit Price"  + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "QTY"         + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Import Date" + " ".repeat(2));
            for (int i = startIdx; i < endIdx; i++) {
                Product p = match.get(i);
                tableDisplay.addCell(" ".repeat(2) + p.getId().toString());
                tableDisplay.addCell(" ".repeat(2) + p.getName());
                tableDisplay.addCell(" ".repeat(2) + p.getUnitPrice().toString());
                tableDisplay.addCell(" ".repeat(2) + p.getQty().toString());
                tableDisplay.addCell(" ".repeat(2) + p.getImportDate().toString());
            }
            System.out.println(tableDisplay.render());
            System.out.println("<>".repeat(60));
            System.out.println("    Page: " + curPage + " of " + totalPages + " ".repeat(40) + "Total: " + match.size());
            System.out.println("<>".repeat(60));
        }
    }




    public static void read(ArrayList<Product> productList){
        Scanner scanner = new Scanner(System.in);
        boolean isFound = false;
        System.out.print("Read by ID: ");   Integer proID = Integer.parseInt(scanner.nextLine());
        for (Product p : productList) {
            if (p.getId().equals(proID)) {
                Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                table.addCell(" ID            : " + p.getId()        +" ".repeat(10));
                table.addCell(" Name          : " + p.getName()      +" ".repeat(10));
                table.addCell(" Unit price    : " + p.getUnitPrice() +" ".repeat(10));
                table.addCell(" QTY           : " + p.getQty()       +" ".repeat(10));
                table.addCell(" Import Date   : " + LocalDate.now()  +" ".repeat(10));
                System.out.println(table.render());
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("Product ID : "+ proID +" is not found");
        }
    }

    public static void update(ArrayList<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        boolean isFound = false;

        System.out.println("Enter ID to update : ");
        Integer idUpdate = Integer.parseInt(scanner.nextLine());
        for (Product p : productList) {
            if (p.getId().equals(idUpdate)) {
                Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                table.addCell(" ID            : " + p.getId()        + " ".repeat(10));
                table.addCell(" Name          : " + p.getName()      + " ".repeat(10));
                table.addCell(" Unit price    : " + p.getUnitPrice() + " ".repeat(10));
                table.addCell(" QTY           : " + p.getQty()       + " ".repeat(10));
                table.addCell(" Import Date   : " + LocalDate.now()  + " ".repeat(10));
                System.out.println(table.render());
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("Product ID : " + idUpdate + " is not found");
        }
        Product proUpdate = null;
        for (Product p : productList) {
            if (p.getId().equals(idUpdate)) {
                proUpdate = p;
                isFound = true;
                break;
            }
        }
        Product p = proUpdate;
        System.out.println("Choose option to update:  ");
        Table tableToUpdate = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.SURROUND);
        tableToUpdate.addCell(" ".repeat(2) + " 1. All Details:       " + " ".repeat(2));
        tableToUpdate.addCell(" ".repeat(2) + " 2. Name               " + " ".repeat(2));
        tableToUpdate.addCell(" ".repeat(2) + " 3. QTY                " + " ".repeat(2));
        tableToUpdate.addCell(" ".repeat(2) + " 4. Price              " + " ".repeat(2));
        tableToUpdate.addCell(" ".repeat(2) + " 5. Exit               " + " ".repeat(2));
        System.out.println(tableToUpdate.render());
        System.out.print("Choose option from 1 to 5: ");    int op = Integer.parseInt(scanner.nextLine());
        switch (op) {
            case 1 -> {
                System.out.print("New Name: ");
                String nName = scanner.nextLine();
                System.out.print("New QTY: ");
                Integer nQty = Integer.parseInt(scanner.nextLine());
                System.out.print("New Price: ");
                Double nPrice = Double.parseDouble(scanner.nextLine());

                p.setName(nName);
                p.setQty(nQty);
                p.setUnitPrice(nPrice);

                Table updTab = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                updTab.addCell(" ID            : " + p.getId()       + " ".repeat(10));
                updTab.addCell(" Name          : " + nName           + " ".repeat(10));
                updTab.addCell(" Unit Price    : " + nPrice          + " ".repeat(10));
                updTab.addCell(" QTY           : " + nQty            + " ".repeat(10));
                updTab.addCell(" Import Date   : " + LocalDate.now() + " ".repeat(10));
                System.out.println(updTab.render());

                System.out.print("Do you wanna update this? [Y/y] or [N/n] : ");    String yn = scanner.nextLine().toLowerCase();
                if(yn.equals("y")){
                    productList.add(p);
                    System.out.println("Update successfully.");

                } else if (yn.equals("n")) {
                    System.out.println("Update not successfully.");
                } else {
                    System.out.println("Please try again!!!");
                }

            }

            case 2 -> {
                System.out.println("New Name : ");
                String nName = scanner.nextLine();
                p.setName(nName);
                Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                updatedTable.addCell(" ID            : " + p.getId()        + " ".repeat(10));
                updatedTable.addCell(" Name          : " + nName            + " ".repeat(10));
                updatedTable.addCell(" Unit Price    : " + p.getUnitPrice() + " ".repeat(10));
                updatedTable.addCell(" QTY           : " + p.getQty()       + " ".repeat(10));
                updatedTable.addCell(" Import Date   : " + LocalDate.now()  + " ".repeat(10));
                System.out.println(updatedTable.render());
                System.out.print("Do you wanna update this? [Y/y] or [N/n] : ");    String yn = scanner.nextLine().toLowerCase();
                if(yn.equals("y")){
                    productList.add(p);
                    System.out.println("Update successfully.");
                } else if (yn.equals("n")) {
                    System.out.println("Update not successfully.");
                } else {
                    System.out.println("Please try again!!!");
                }
            }
            case 3 -> {
                System.out.println("New Price : ");
                Double nPrice = Double.parseDouble(scanner.nextLine());
                p.setUnitPrice(nPrice);
                Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                updatedTable.addCell(" ID            : " + p.getId() + " ".repeat(10));
                updatedTable.addCell(" Name          : " + p.getProName() + " ".repeat(10));
                updatedTable.addCell(" Unit Price    : " + nPrice + " ".repeat(10));
                updatedTable.addCell(" QTY           : " + p.getQty() + " ".repeat(10));
                updatedTable.addCell(" Import Date   : " + LocalDate.now() + " ".repeat(10));
                System.out.println(updatedTable.render());
                System.out.print("Do you wanna update this? [Y/y] or [N/n] : ");     String yn = scanner.nextLine().toLowerCase();
                if(yn.equals("y")){
                    productList.add(p);
                    System.out.println("Update successfully.");
                } else if (yn.equals("n")) {
                    System.out.println("Update not successfully.");
                } else {
                    System.out.println("Please try again!!!");
                }
            }
            case 4 -> {
                System.out.println("New Price : ");
                Integer nQty = Integer.parseInt(scanner.nextLine());
                p.setQty(nQty);
                Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                updatedTable.addCell(" ID            : " + p.getId()        + " ".repeat(10));
                updatedTable.addCell(" Name          : " + p.getName()      + " ".repeat(10));
                updatedTable.addCell(" Unit Price    : " + p.getUnitPrice() + " ".repeat(10));
                updatedTable.addCell(" QTY           : " + nQty             + " ".repeat(10));
                updatedTable.addCell(" Import Date   : " + LocalDate.now()  + " ".repeat(10));
                System.out.println(updatedTable.render());
                System.out.print("Do you wanna update this? [Y/y] or [N/n] : ");     String yn = scanner.nextLine().toLowerCase();
                if(yn.equals("y")){
                    productList.add(p);
                    System.out.println("Update successfully.");

                } else if (yn.equals("n")) {
                    System.out.println("Update not successfully.");
                } else {
                    System.out.println("Please try again!!!");
                }

            }
            case 5 -> {
                System.out.println("Exit ");
                System.exit(0);
            }
        }
    }

    public static void help() {
        System.out.println("! 1.     Press  * : Display all record of products                                  !");
        System.out.println("! 2.     Press  w : All new Product                                                 !");
        System.out.println("!        Press  w ->#proname-unitprice-qty : shortcut for add new product           !");
        System.out.println("! 3.     Press  r : read Content any content                                        !");
        System.out.println("!        Press  r#porId : shortcut for read product by Id                           !");
        System.out.println("! 4.     Press  u : Update Data                                                     !");
        System.out.println("! 5.     Press  d : Delete Data                                                     !");
        System.out.println("!        Press  d#proId : shortcut for delete product by Id                         !");
        System.out.println("! 6.     Press  f : Display First Page                                              !");
        System.out.println("! 7.     Press  p : Display previous Page                                           !");
        System.out.println("! 8.     Press  n : Display Next Page                                               !");
        System.out.println("! 9.     Press  l : Display Last Page                                               !");
        System.out.println("! 10.    Press  s : Search product by name                                          !");
        System.out.println("! 11.    Press  h : Help                                                            !");
    }

    public static int first(ArrayList<Product> productList,int curPage, int rowPage) {
        if (curPage == 1) {
            System.out.println("You're on 1st page.");
            display(productList, curPage, rowPage);

        }
        else {
            curPage= 1;
            display(productList, curPage, rowPage);
        }
        return curPage;
    }

    public static int previous(ArrayList<Product> productList,int curPage, int rowPage) {
        if (curPage > 1) {
            curPage--;
            display(productList, curPage, rowPage);
        } else {
            System.out.println("You're on 1st page.");
            display(productList, curPage, rowPage);
        }
        return curPage;
    }

    public static int next(ArrayList<Product> productList,int curPage, int rowPage) {
        int totalPages = (int) Math.ceil((double) productList.size() / rowPage);
        if (curPage < totalPages) {
            curPage++;
            display(productList, curPage, rowPage);
        } else {
            System.out.println("You're on last page.");
            display(productList, curPage, rowPage);
        }
        return curPage;
    }

    public static int last(ArrayList<Product> productList,int curPage, int rowPage) {
        int totalPages = (int) Math.ceil((double) productList.size() / rowPage);
        if (curPage == totalPages) {
            System.out.println("You're on last page.");
            display(productList, curPage, rowPage);
        } else {
            curPage = totalPages;
            display(productList, curPage, rowPage);
        }
        return curPage;
    }
    public static int goTo(ArrayList<Product> productList,int curPage, int rowPage) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("The number of page that you wanna goto: ");   int gPage = scanner.nextInt();
        int tPages = (int) Math.ceil((double) productList.size() / rowPage);
        if (gPage >= 1 && gPage <= tPages) {
            curPage = gPage;
            display(productList, curPage, rowPage);
        } else System.out.println("Please try again!!! The number of page from 1 to " + tPages);
        return curPage;
    }
    public static int setrow(ArrayList<Product> productList, int rowPage) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set number of rows: ");int nRow = Integer.parseInt(scanner.nextLine());
        if (nRow >0 && nRow <= productList.size()) return nRow;
        return rowPage;
    }
    public static void main(String[] args) {
        System.out.println(" ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗     \n" +
                "█c█╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗    \n" +
                "██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║    \n" +
                "██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    \n" +
                "╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    \n" +
                " ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    \n" +
                "                                                                                  ");
        System.out.println("STOCK MANAGEMENT SYSTEM");
        do {
            Table table = getTable();
            System.out.println(table.render());
            System.out.print("Command -> ");
            String option=scanner.next();
            switch (option){
                case "*" -> display(productList,curPage,rowPage);

                case "W","w" -> write();

                case "R","r" -> read(productList);
                
                case "U","u" -> update(productList);

                case "D","d" -> delete(productList);

                case "F","f" -> first(productList,curPage,rowPage);

                case "P","p" ->previous(productList,curPage,rowPage);

                case "N","n" ->next(productList,curPage,rowPage);

                case "L","l" ->last(productList,curPage,rowPage);

                case "S","s" -> search(productList,curPage,rowPage);

                case "G","g" -> goTo(productList,curPage,rowPage);

                case "Se","se" ->setrow(productList,rowPage);

                case "H","h" ->help();

                case "E","e" ->{
                        System.exit(0);
                        System.out.println("Exit");
                }
            }
        } while (true);
    }
}