import java.util.ArrayList;

public class WoodStorage {
	private ArrayList<Wood> storage = new ArrayList<Wood>();
	
	public void BuyWood(String state, int amount) {
		//create wood for requested amount
		for (int i = 0; i < amount; i++) {
			Wood wood = new Wood(state);
			storage.add(wood);
		}
	}
	
	public int GetStock() {
		return storage.size();
	}
	
	public void ClearStorage() {
		storage.clear();
	}
}
