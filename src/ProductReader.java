import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductReader {
    public static void main(String[] args) {
        OpenFileDialog();
    }
    public static void OpenFileDialog(){
        FileOpener.ShowFileDialog(f -> {
            if(f==null)
                OpenFileDialog();
            else {
                try {
                    PrintData(f);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || ( f.getName().endsWith(".csv") || f.getName().endsWith(".txt"));
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
    }

    private static void PrintData(File f) throws IOException{
        InputStream in = new BufferedInputStream(Files.newInputStream(f.toPath()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));


        String l=reader.readLine();
        List<Product> people= new ArrayList<>();
        while(l!=null) {
            if(Product.isProduct(l))
                people.add(new Product(l));
            else
                System.err.println("Line is not a product: \t"+l);
            l = reader.readLine();
        }
        ConsoleTable.PrintTable("PEOPLE",
                Arrays.asList("ID#","NAME","DESCRIPTION","COST"),
                people.stream().map(Product::toList).collect(Collectors.toList())
        );
    }
}
