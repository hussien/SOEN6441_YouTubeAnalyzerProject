package models;

public class TitleAndCount
{

	private String title;
	private String count;
	public TitleAndCount()
	{}
	
	public TitleAndCount(String t,String c)
	{
		this.title=t;
		this.count=c;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getCount()
	{
		return count;
	}

	public void setCount(String count)
	{
		this.count = count;
	}
}
