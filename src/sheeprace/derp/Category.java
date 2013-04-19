package sheeprace.derp;

public class Category {
	
	private String category;
	private int qs;
	
	public Category(String category, int qs){
		this.category = category;
		this.qs = qs;
	}
	
	public String getCategory(){
		return this.category;
	}
	
	public int getQuestionCount(){
		return this.qs;
	}
	
	@Override
	public String toString(){
		return (this.category + " with " + qs + " questions");
	}
	
}
