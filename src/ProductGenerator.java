import javafx.beans.binding.When;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductGenerator {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        List<Product> products=new ArrayList<>();
        do{
            String id=SafeInput.getNonZeroLenString(s,"ID");
            String n=SafeInput.getNonZeroLenString(s,"Name");
            String d=SafeInput.getNonZeroLenString(s,"Description");
            double cost=SafeInput.getDouble(s,"Cost");
            products.add(new Product(id,n,d,cost));
        }while (SafeInput.getYNConfirm(s,"Add More?"));

        File workingDirectory = new File(System.getProperty("user.dir"));
        try {
            FileWriter fw = new FileWriter(workingDirectory.getPath() + "\\src", "ProductGeneratorOutput.csv");
            for (Product p : products) {
                fw.WriteLine(p.toString());
            }
            fw.Close();

        }catch (Exception e){
            System.err.println("An error occured while writing the file");
        }
    }


}
