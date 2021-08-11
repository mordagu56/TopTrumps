package Quartett;

import java.util.List;
import java.util.Map;

public class FoilVehicleCard extends VehicleCard{
    private List<Category> specials;
    public FoilVehicleCard(String name, Map<Category, Double> categories, List<Category> specials) {
        super(name, categories);
        if (specials == null || specials.size() > 3) throw new IllegalArgumentException();
        this.specials = specials;
    }
    @Override
    
    protected int getBonus(Category category) {
    	boolean match = false;
		for(Category key: specials) {
			if(category.equals(key)) match = true;
		}
		if(match) return 2*super.getBonus(category);
		return super.getBonus(category);
    }
    
    @Override
    protected String categoryToString(Category category) {
    	boolean match = false;
		for(Category key: specials) {
			if(category.equals(key)) match = true;
		}
		if(match) return '*' + super.categoryToString(category) + '*';
		return super.categoryToString(category);
		
    } 
}