package lab4;
import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer>{

	@Override
	public int compare(Beer o1, Beer o2) {
		int ret = 0;
		double delta = o1.getStrength()-o2.getStrength();
		if (delta == 0)
			ret = 0;
		else if(delta > 0)
			ret = 1;
		else
			ret = -1;
		return ret;
	}

	
}
