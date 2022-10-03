package lab4;
import java.util.Comparator;

public class StyleComparator implements Comparator<Beer> {

	@Override
	public int compare(Beer o1, Beer o2) {
		return o1.getStyle().compareToIgnoreCase(o2.getStyle());
		
	}
	
}