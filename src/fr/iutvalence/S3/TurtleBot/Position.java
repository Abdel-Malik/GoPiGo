package fr.iutvalence.S3.TurtleBot;

public class Position 
{
	
	private int xPosition;

	private int yPosition;

	private int zPosition;
	
	public Position(int xPosition, int yPosition, int zPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.zPosition = zPosition;
	}
	
	public int getX()
	{
		return xPosition;
	}
	
	public int getY()
	{
		return yPosition;
	}
	
	public int getZ()
	{
		return zPosition;
	}
	
	public void setxPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}
	
	public void setyPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}

	public void setzPosition(int zPosition)
	{
		this.zPosition = zPosition;
	}
	
}

