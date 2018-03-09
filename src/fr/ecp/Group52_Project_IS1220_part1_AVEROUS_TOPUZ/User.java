package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public class User {
	protected String name;
	protected static double counter=0;
	protected double numericalId;
	protected GPScoordinates position;
	protected Card card;
	protected MovingBehavior behavior;
	
	
	public User(String name) {
		this.name=name;
		User.counter+=1;
		this.numericalId=User.counter;
	}


	public String getName() {
		return name;
	}


	public static double getCounter() {
		return counter;
	}


	public double getNumericalId() {
		return numericalId;
	}


	public GPScoordinates getPosition() {
		return position;
	}


	public Card getCard() {
		return card;
	}


	public MovingBehavior getBehavior() {
		return behavior;
	}


	public void setName(String name) {
		this.name = name;
	}
	

	public void setPosition(GPScoordinates position) {
		this.position = position;
	}


	public void setCard(Card card) {
		this.card = card;
	}


	public void setBehavior(MovingBehavior behavior) {
		this.behavior = behavior;
	}
	
	@Override
	public String toString() {
		if (this.card.getType()==CardTypes.NoCard) {
			return (this.name+" is user number "+this.numericalId+"and hasn't got a card .\n");
		}
		else {
			return (this.name+" is user number "+this.numericalId+"and has a "+this.card.getType()+" card.\n");
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User that = (User) obj;
			return (this.numericalId==that.getNumericalId() && this.name==that.getName() && this.card==that.getCard() && this.behavior==that.getBehavior());
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (41*(41+this.name.hashCode())+this.numericalId);
	}


}
