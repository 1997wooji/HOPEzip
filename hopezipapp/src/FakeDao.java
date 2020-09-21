import java.util.ArrayList;
import java.util.List;

public class FakeDao {
	static{
		fakeDao=new FakeDao();
	}
	
	public static FakeDao fakeDao;
	public List<FakeGoal> list;
	
	public FakeDao()
	{
		list=new ArrayList<FakeGoal>();
	}
	


}

class FakeGoal{
	
	public String goalName;
	public String goalCategory;
	public String examCategory;
	public String startDate;
	public String endDate;
	public String startTime;
	public String endTime;
	public String goalQty;
	public String goalUnit;
	public String location;
	public String memo;
	
	public FakeGoal(String goalName, String goalCategory, String examCategory, String startDate, String endDate, String startTime, String endTime, String goalQty, String goalUnit, String location, String memo) {
		super();
		this.goalName = goalName;
		this.goalCategory = goalCategory;
		this.examCategory = examCategory;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.goalQty = goalQty;
		this.goalUnit = goalUnit;
		this.location = location;
		this.memo = memo;
	}
	

	
}