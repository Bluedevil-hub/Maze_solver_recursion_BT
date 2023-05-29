

//My name is Harshil Mehta

public class PairInt {

    //class variables
    private int x;
	private int y;
	
    //constructer
	public PairInt(int x, int y){
		this.x=x;
		this.y=y;
	}

    //getx
	public int getX(){
		return x;
	}
    //gety
	public int getY(){
		return y;
	}
    //sety
	public void setX(int x){
		this.x=x;
	}
    //sety
	public void setY(int y){
		this.y=y;
	}

    //boolean equal method
	public boolean equals(PairInt p){
		if(p.getX() == this.x && p.getY()==this.y) 
			return true;
		else 
			return false;
	}

    //to string method
	public String toString(){
		return "("+x+","+y+")";
	}

    //copy method
	public PairInt copy(){
		return new PairInt(x,y);
	}

    
}
