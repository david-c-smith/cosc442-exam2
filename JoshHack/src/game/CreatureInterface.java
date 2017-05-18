package game;

import java.awt.Color;
import java.util.List;

public interface CreatureInterface {

	char glyph();

	Color color();

	void setCreatureAi(CreatureAi ai);

	int maxHp();

	void modifyMaxHp(int amount);

	int hp();

	void modifyAttackValue(int value);

	int attackValue();

	void modifyDefenseValue(int value);

	int defenseValue();

	void modifyVisionRadius(int value);

	int visionRadius();

	String name();

	Inventory inventory();

	int maxFood();

	int food();

	Item weapon();

	Item armor();

	int xp();

	void modifyXp(int amount);

	int level();

	void modifyRegenHpPer1000(int amount);

	List<Effect> effects();

	int maxMana();

	void modifyMaxMana(int amount);

	int mana();

	void modifyMana(int amount);

	void modifyRegenManaPer1000(int amount);

	String causeOfDeath();

	void moveBy(int mx, int my, int mz);

	void meleeAttack(Creature other);

	void rangedWeaponAttack(Creature other);

	void gainXp(Creature other);

	void modifyHp(int amount, String causeOfDeath);

	void dig(int wx, int wy, int wz);

	void update();

	boolean canEnter(int wx, int wy, int wz);

	void notify(String message, Object... params);

	void doAction(String message, Object... params);

	void doAction(Item item, String message, Object... params);

	boolean canSee(int wx, int wy, int wz);

	Tile realTile(int wx, int wy, int wz);

	Tile tile(int wx, int wy, int wz);

	CreatureInterface creature(int wx, int wy, int wz);

	void pickup();

	void drop(Item item);

	void modifyFood(int amount);

	boolean isPlayer();

	void eat(Item item);

	void quaff(Item item);

	void unequip(Item item);

	void equip(Item item);

	Item item(int wx, int wy, int wz);

	String details();

	void throwItem(Item item, int wx, int wy, int wz);

	void summon(Creature other);

	void modifyDetectCreatures(int amount);

	void castSpell(Spell spell, int x2, int y2);

	String nameOf(Item item);

	void learnName(Item item);

}