package game;

@SuppressWarnings("ucd")
public class BatAi extends CreatureAi {

	@SuppressWarnings("ucd")
	public BatAi(Creature creature) {
		super(creature);
	}

	public void onUpdate(){
		wander();
		wander();
	}
}
