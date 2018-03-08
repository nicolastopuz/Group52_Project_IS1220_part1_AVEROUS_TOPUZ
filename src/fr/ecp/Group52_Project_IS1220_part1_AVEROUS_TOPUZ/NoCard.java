package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public class NoCard extends Card {

	public NoCard(User user) {
		super(user);
		this.type=CardTypes.NoCard;
	}
	
	@Override
	public String toString() {
		return (this.user.getName()+" doesn't have a card.\n");
	}

}
