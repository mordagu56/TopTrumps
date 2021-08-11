package Quartett;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VehicleCard  implements Comparable<VehicleCard>{

    public enum Category {

        PRICE_EUR("Preis", 1) ,
        CYLINDER_CAPACITY_CM3("Hubraum", 5),
        ENGINE_POWER_HP("Leistung", 4),
        ACCELERATION_SEC("Beschleunigung", 3),
        VELOCITY_KMH("Geschwindigkeit", 2),
        CONSUMPTION_L("Verbrauch", 0) {
            @Override
            public int bonus(Double value) {
                return (int) (value + getFactor());
            }
        };
        final private String categoryName;
        final private int factor;
        private Category(String categoryName, int factor) {
            if (categoryName == null || categoryName.isEmpty() || factor < 0) throw new IllegalArgumentException();
            this.categoryName = categoryName;
            this.factor = factor;
        }
        public int bonus(Double value) {
            return (int) (this.factor * value);
        }
        public int getFactor() {
            return this.factor;
        }
        @Override
        public String toString() {
            return this.categoryName;
        }
    }

    private String name;
    private Map<Category, Double> categories = null;

    public VehicleCard(String name, Map<Category, Double> categories) {
        if (name.isEmpty() || name == null || categories == null || categories.size() != 6) throw new IllegalArgumentException();
   
        for (Double v : categories.values()) {
            if (v == null || v < 0) throw new IllegalArgumentException();
        }
        this.categories = categories;
        this.name = name;
    }
  
    @Override
    public int compareTo(VehicleCard other) {
        int tb = totalBonus();
        int tbO = other.totalBonus();
        if (other.totalBonus() == totalBonus()) return 0;
        else if (tb > tbO) return 1;
        else return -1;
    }

    public static Comparator<VehicleCard> compareByName() {
		return new Comparator<VehicleCard>() {
			@Override
			public int compare(VehicleCard lop, VehicleCard rop) {
				return (rop.getName().compareTo(lop.getName()));
			}
		};
    }
    
    protected int getBonus(Category category) {
         return category.bonus(categories.get(category));
    }
   
    public int totalBonus() {
        int sum = 0;
        for (Category c : categories.keySet()) {
            sum += getBonus(c);
        }
        return sum;
    }
    public static Map<Category, Double> newCategoriesMap(double price, double capa, double pwr, double acc,
                                                         double velo, double cons) {
        Map<Category, Double> map = new HashMap<Category, Double>();
        map.put(Category.PRICE_EUR, price);
        map.put(Category.CYLINDER_CAPACITY_CM3, capa);
        map.put(Category.ENGINE_POWER_HP, pwr);
        map.put(Category.ACCELERATION_SEC, acc);
        map.put(Category.VELOCITY_KMH, velo);
        map.put(Category.CONSUMPTION_L, cons);
        return map;
    }
    protected String categoryToString(Category category) {
        return category.toString();
    }
    @Override
    public String toString() {
    	
        String r ="";
        r += "- " + this.name + "(" + totalBonus() + ")";
        r += " -> {";
        boolean first = true;
        for (Entry<Category, Double> c : categories.entrySet()) {
        	if(first) first = false;
        	else r += ", ";
        	r += categoryToString(c.getKey()) + '=' + c.getValue();
        }
        r += "}";
 
        return r;
    }


    public String getName() {
        return name;
    }

    public Map<Category, Double> getCategories() {
        return categories;
    }
  
}