import java.util.Arrays;
import java.util.List;

public class Product {
    String id,name,description;
    double cost;

    public Product(String id, String name, String description, double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
    public Product(String line){
        String[] s=line.split(",");
        id=s[0];
        name=s[1];
        description=s[2];
        cost=Double.parseDouble(s[3].replace(" ",""));
    }
    public static boolean isProduct(String line){
        String[] s=line.split(",");
        if(s.length==4){
            try{
                Double.parseDouble(s[3].replace(" ",""));
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    public List<String> toList(){
        return Arrays.asList(id,name,description,cost+"");
    }
    @Override
    public String toString() {
     return String.join(",",id,name,description,cost+"");
    }
}
