package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class Creature implements CreatureInterface {
	private World world;
	
	public int x;
	public int y;
	public int z;
	
	private char glyph;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#glyph()
	 */
	@Override
	public char glyph() { return glyph; }
	
	private Color color;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#color()
	 */
	@Override
	public Color color() { return color; }

	private CreatureAi ai;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#setCreatureAi(game.CreatureAi)
	 */
	@Override
	public void setCreatureAi(CreatureAi ai) { this.ai = ai; }
	
	private int maxHp;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#maxHp()
	 */
	@Override
	public int maxHp() { return maxHp; }
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyMaxHp(int)
	 */
	@Override
	public void modifyMaxHp(int amount) { maxHp += amount; }
	
	private int hp;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#hp()
	 */
	@Override
	public int hp() { return hp; }
	
	private int attackValue;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyAttackValue(int)
	 */
	@Override
	public void modifyAttackValue(int value) { attackValue += value; }
	/* (non-Javadoc)
	 * @see game.CreatureInterface#attackValue()
	 */
	@Override
	public int attackValue() { 
		return attackValue
			+ (weapon == null ? 0 : weapon.attackValue())
			+ (armor == null ? 0 : armor.attackValue());
	}

	private int defenseValue;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyDefenseValue(int)
	 */
	@Override
	public void modifyDefenseValue(int value) { defenseValue += value; }
	/* (non-Javadoc)
	 * @see game.CreatureInterface#defenseValue()
	 */
	@Override
	public int defenseValue() { 
		return defenseValue
			+ (weapon == null ? 0 : weapon.defenseValue())
			+ (armor == null ? 0 : armor.defenseValue());
	}

	private int visionRadius;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyVisionRadius(int)
	 */
	@Override
	public void modifyVisionRadius(int value) { visionRadius += value; }
	/* (non-Javadoc)
	 * @see game.CreatureInterface#visionRadius()
	 */
	@Override
	public int visionRadius() { return visionRadius; }

	private String name;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#name()
	 */
	@Override
	public String name() { return name; }

	private Inventory inventory;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#inventory()
	 */
	@Override
	public Inventory inventory() { return inventory; }

	private int maxFood;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#maxFood()
	 */
	@Override
	public int maxFood() { return maxFood; }
	
	private int food;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#food()
	 */
	@Override
	public int food() { return food; }
	
	private Item weapon;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#weapon()
	 */
	@Override
	public Item weapon() { return weapon; }
	
	private Item armor;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#armor()
	 */
	@Override
	public Item armor() { return armor; }
	
	private int xp;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#xp()
	 */
	@Override
	public int xp() { return xp; }
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyXp(int)
	 */
	@Override
	public void modifyXp(int amount) { 
		xp += amount;
		
		notify("You %s %d xp.", amount < 0 ? "lose" : "gain", amount);
		
		while (xp > (int)(Math.pow(level, 1.75) * 25)) {
			level++;
			doAction("advance to level %d", level);
			ai.onGainLevel();
			modifyHp(level * 2, "Died from having a negative level?");
		}
	}
	
	private int level;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#level()
	 */
	@Override
	public int level() { return level; }
	
	private int regenHpCooldown;
	private int regenHpPer1000;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyRegenHpPer1000(int)
	 */
	@Override
	public void modifyRegenHpPer1000(int amount) { regenHpPer1000 += amount; }
	
	private List<Effect> effects;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#effects()
	 */
	@Override
	public List<Effect> effects(){ return effects; }
	
	private int maxMana;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#maxMana()
	 */
	@Override
	public int maxMana() { return maxMana; }
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyMaxMana(int)
	 */
	@Override
	public void modifyMaxMana(int amount) { maxMana += amount; }
	
	private int mana;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#mana()
	 */
	@Override
	public int mana() { return mana; }
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyMana(int)
	 */
	@Override
	public void modifyMana(int amount) { mana = Math.max(0, Math.min(mana+amount, maxMana)); }
	
	private int regenManaCooldown;
	private int regenManaPer1000;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyRegenManaPer1000(int)
	 */
	@Override
	public void modifyRegenManaPer1000(int amount) { regenManaPer1000 += amount; }
	
	private String causeOfDeath;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#causeOfDeath()
	 */
	@Override
	public String causeOfDeath() { return causeOfDeath; }
	
	public Creature(World world, char glyph, Color color, String name, int maxHp, int attack, int defense){
		this.world = world;
		this.glyph = glyph;
		this.color = color;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackValue = attack;
		this.defenseValue = defense;
		this.visionRadius = 9;
		this.name = name;
		this.inventory = new Inventory(20);
		this.maxFood = 1000;
		this.food = maxFood / 3 * 2;
		this.level = 1;
		this.regenHpPer1000 = 10;
		this.effects = new ArrayList<>();
		this.maxMana = 5;
		this.mana = maxMana;
		this.regenManaPer1000 = 20;
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#moveBy(int, int, int)
	 */
	@Override
	public void moveBy(int mx, int my, int mz){
		if (mx==0 && my==0 && mz==0)
			return;
		
		Tile tile = world.tile(x+mx, y+my, z+mz);
		
		if (mz == -1){
			if (tile == Tile.STAIRS_DOWN) {
				doAction("walk up the stairs to level %d", z+mz+1);
			} else {
				doAction("try to go up but are stopped by the cave ceiling");
				return;
			}
		} else if (mz == 1){
			if (tile == Tile.STAIRS_UP) {
				doAction("walk down the stairs to level %d", z+mz+1);
			} else {
				doAction("try to go down but are stopped by the cave floor");
				return;
			}
		}
		
		Creature other = world.creature(x+mx, y+my, z+mz);
		
		modifyFood(-1);
		
		if (other == null)
			ai.onEnter(x+mx, y+my, z+mz, tile);
		else
			meleeAttack(other);
	}

	/* (non-Javadoc)
	 * @see game.CreatureInterface#meleeAttack(game.Creature)
	 */
	@Override
	public void meleeAttack(Creature other){
		commonAttack(other, attackValue(), "attack the %s for %d damage", other.name);
	}

	private void throwAttack(Item item, Creature other) {
		commonAttack(other, attackValue / 2 + item.thrownAttackValue(), "throw a %s at the %s for %d damage", nameOf(item), other.name);
		other.addEffect(item.quaffEffect());
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#rangedWeaponAttack(game.Creature)
	 */
	@Override
	public void rangedWeaponAttack(Creature other){
		commonAttack(other, attackValue / 2 + weapon.rangedAttackValue(), "fire a %s at the %s for %d damage", nameOf(weapon), other.name);
	}
	
	private void commonAttack(Creature other, int attack, String action, Object ... params) {
		modifyFood(-2);
		
		int amount = Math.max(0, attack - other.defenseValue());
		
		amount = (int)(Math.random() * amount) + 1;
		
		Object[] params2 = new Object[params.length+1];
		for (int i = 0; i < params.length; i++){
			params2[i] = params[i];
		}
		params2[params2.length - 1] = amount;
		
		doAction(action, params2);
		
		other.modifyHp(-amount, "Killed by a " + name);
		
		if (other.hp < 1)
			gainXp(other);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#gainXp(game.Creature)
	 */
	@Override
	public void gainXp(Creature other){
		int amount = other.maxHp 
			+ other.attackValue() 
			+ other.defenseValue()
			- level;
		
		if (amount > 0)
			modifyXp(amount);
	}

	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyHp(int, java.lang.String)
	 */
	@Override
	public void modifyHp(int amount, String causeOfDeath) { 
		hp += amount;
		this.causeOfDeath = causeOfDeath;
		
		if (hp > maxHp) {
			hp = maxHp;
		} else if (hp < 1) {
			doAction("die");
			leaveCorpse();
			world.remove(this);
		}
	}
	
	private void leaveCorpse(){
		Item corpse = new Item('%', color, name + " corpse", null);
		corpse.modifyFoodValue(maxHp * 5);
		world.addAtEmptySpace(corpse, x, y, z);
		for (Item item : inventory.getItems()){
			if (item != null)
				drop(item);
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#dig(int, int, int)
	 */
	@Override
	public void dig(int wx, int wy, int wz) {
		modifyFood(-10);
		world.dig(wx, wy, wz);
		doAction("dig");
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#update()
	 */
	@Override
	public void update(){
		modifyFood(-1);
		regenerateHealth();
		regenerateMana();
		updateEffects();
		ai.onUpdate();
	}
	
	private void updateEffects(){
		List<Effect> done = new ArrayList<>();
		
		for (Effect effect : effects){
			effect.update(this);
			if (effect.isDone()) {
				effect.end(this);
				done.add(effect);
			}
		}
		
		effects.removeAll(done);
	}
	
	private void regenerateHealth(){
		regenHpCooldown -= regenHpPer1000;
		if (regenHpCooldown < 0){
			if (hp < maxHp){
				modifyHp(1, "Died from regenerating health?");
				modifyFood(-1);
			}
			regenHpCooldown += 1000;
		}
	}

	private void regenerateMana(){
		regenManaCooldown -= regenManaPer1000;
		if (regenManaCooldown < 0){
			if (mana < maxMana) {
				modifyMana(1);
				modifyFood(-1);
			}
			regenManaCooldown += 1000;
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#canEnter(int, int, int)
	 */
	@Override
	public boolean canEnter(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz).isGround() && world.creature(wx, wy, wz) == null;
	}

	/* (non-Javadoc)
	 * @see game.CreatureInterface#notify(java.lang.String, java.lang.Object)
	 */
	@Override
	public void notify(String message, Object ... params){
		ai.onNotify(String.format(message, params));
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#doAction(java.lang.String, java.lang.Object)
	 */
	@Override
	public void doAction(String message, Object ... params){
		for (CreatureInterface other : getCreaturesWhoSeeMe()){
			if (other == this){
				other.notify("You " + message + ".", params);
			} else {
				other.notify(String.format("The %s %s.", name, makeSecondPerson(message)), params);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#doAction(game.Item, java.lang.String, java.lang.Object)
	 */
	@Override
	public void doAction(Item item, String message, Object ... params){
		if (hp < 1)
			return;
		
		for (CreatureInterface other : getCreaturesWhoSeeMe()){
			if (other == this){
				other.notify("You " + message + ".", params);
			} else {
				other.notify(String.format("The %s %s.", name, makeSecondPerson(message)), params);
			}
			other.learnName(item);
		}
	}
	
	private List<Creature> getCreaturesWhoSeeMe(){
		List<Creature> others = new ArrayList<>();
		int r = 9;
		for (int ox = -r; ox < r+1; ox++){
			for (int oy = -r; oy < r+1; oy++){
				if (ox*ox + oy*oy > r*r)
					continue;
				
				Creature other = world.creature(x+ox, y+oy, z);
				
				if (other == null)
					continue;
				
				others.add(other);
			}
		}
		return others;
	}
	
	private String makeSecondPerson(String text){
		String[] words = text.split(" ");
		words[0] = words[0] + "s";
		
		StringBuilder builder = new StringBuilder();
		for (String word : words){
			builder.append(" ");
			builder.append(word);
		}
		
		return builder.toString().trim();
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#canSee(int, int, int)
	 */
	@Override
	public boolean canSee(int wx, int wy, int wz){
		return (detectCreatures > 0 && world.creature(wx, wy, wz) != null
				|| ai.canSee(wx, wy, wz));
	}

	/* (non-Javadoc)
	 * @see game.CreatureInterface#realTile(int, int, int)
	 */
	@Override
	public Tile realTile(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#tile(int, int, int)
	 */
	@Override
	public Tile tile(int wx, int wy, int wz) {
		if (canSee(wx, wy, wz))
			return world.tile(wx, wy, wz);
		else
			return ai.rememberedTile(wx, wy, wz);
	}

	/* (non-Javadoc)
	 * @see game.CreatureInterface#creature(int, int, int)
	 */
	@Override
	public Creature creature(int wx, int wy, int wz) {
		if (canSee(wx, wy, wz))
			return world.creature(wx, wy, wz);
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#pickup()
	 */
	@Override
	public void pickup(){
		Item item = world.item(x, y, z);
		
		if (inventory.isFull() || item == null){
			doAction("grab at the ground");
		} else {
			doAction("pickup a %s", nameOf(item));
			world.remove(x, y, z);
			inventory.add(item);
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#drop(game.Item)
	 */
	@Override
	public void drop(Item item){
		if (world.addAtEmptySpace(item, x, y, z)){
			doAction("drop a " + nameOf(item));
			inventory.remove(item);
			unequip(item);
		} else {
			notify("There's nowhere to drop the %s.", nameOf(item));
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyFood(int)
	 */
	@Override
	public void modifyFood(int amount) { 
		food += amount;
		
		if (food > maxFood) {
			maxFood = (maxFood + food) / 2;
			food = maxFood;
			notify("You can't belive your stomach can hold that much!");
			modifyHp(-1, "Killed by overeating.");
		} else if (food < 1 && isPlayer()) {
			modifyHp(-1000, "Starved to death.");
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#isPlayer()
	 */
	@Override
	public boolean isPlayer(){
		return glyph == '@';
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#eat(game.Item)
	 */
	@Override
	public void eat(Item item){
		doAction("eat a " + nameOf(item));
		consume(item);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#quaff(game.Item)
	 */
	@Override
	public void quaff(Item item){
		doAction("quaff a " + nameOf(item));
		consume(item);
	}
	
	private void consume(Item item){
		if (item.foodValue() < 0)
			notify("Gross!");
		
		addEffect(item.quaffEffect());
		
		modifyFood(item.foodValue());
		getRidOf(item);
	}
	
	private void addEffect(Effect effect){
		if (effect == null)
			return;
		
		effect.start(this);
		effects.add(effect);
	}
	
	private void getRidOf(Item item){
		inventory.remove(item);
		unequip(item);
	}
	
	private void putAt(Item item, int wx, int wy, int wz){
		inventory.remove(item);
		unequip(item);
		world.addAtEmptySpace(item, wx, wy, wz);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#unequip(game.Item)
	 */
	@Override
	public void unequip(Item item){
		if (item == null)
			return;
		
		if (item == armor){
			if (hp > 0)
				doAction("remove a " + nameOf(item));
			armor = null;
		} else if (item == weapon) {
			if (hp > 0) 
				doAction("put away a " + nameOf(item));
			weapon = null;
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#equip(game.Item)
	 */
	@Override
	public void equip(Item item){
		if (!inventory.contains(item)) {
			if (inventory.isFull()) {
				notify("Can't equip %s since you're holding too much stuff.", nameOf(item));
				return;
			} else {
				world.remove(item);
				inventory.add(item);
			}
		}
		
		if (item.attackValue() == 0 && item.rangedAttackValue() == 0 && item.defenseValue() == 0)
			return;
		
		if (item.attackValue() + item.rangedAttackValue() >= item.defenseValue()){
			unequip(weapon);
			doAction("wield a " + nameOf(item));
			weapon = item;
		} else {
			unequip(armor);
			doAction("put on a " + nameOf(item));
			armor = item;
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#item(int, int, int)
	 */
	@Override
	public Item item(int wx, int wy, int wz) {
		if (canSee(wx, wy, wz))
			return world.item(wx, wy, wz);
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#details()
	 */
	@Override
	public String details() {
		return String.format("  level:%d  attack:%d  defense:%d  hp:%d", level, attackValue(), defenseValue(), hp);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#throwItem(game.Item, int, int, int)
	 */
	@Override
	public void throwItem(Item item, int wx1, int wy1, int wz) {
		Point end = new Point(x, y, 0);
		
		for (Point p : new Line(x, y, wx1, wy1)){
			if (!realTile(p.x, p.y, z).isGround())
				break;
			end = p;
		}
		
		wx1 = end.x;
		wy1 = end.y;
		
		Creature c = creature(wx1, wy1, wz);
		
		if (c != null)
			throwAttack(item, c);				
		else
			doAction("throw a %s", nameOf(item));
		
		if (item.quaffEffect() != null && c != null)
			getRidOf(item);
		else
			putAt(item, wx1, wy1, wz);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#summon(game.Creature)
	 */
	@Override
	public void summon(Creature other) {
		world.add(other);
	}
	
	private int detectCreatures;
	/* (non-Javadoc)
	 * @see game.CreatureInterface#modifyDetectCreatures(int)
	 */
	@Override
	public void modifyDetectCreatures(int amount) { detectCreatures += amount; }
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#castSpell(game.Spell, int, int)
	 */
	@Override
	public void castSpell(Spell spell, int x2, int y2) {
		Creature other = creature(x2, y2, z);
		
		if (spell.manaCost() > mana){
			doAction("point and mumble but nothing happens");
			return;
		} else if (other == null) {
			doAction("point and mumble at nothing");
			return;
		}
		
		other.addEffect(spell.effect());
		modifyMana(-spell.manaCost());
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#nameOf(game.Item)
	 */
	@Override
	public String nameOf(Item item){
		return ai.getName(item);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureInterface#learnName(game.Item)
	 */
	@Override
	public void learnName(Item item){
		notify("The " + item.appearance() + " is a " + item.name() + "!");
		ai.setName(item, item.name());
	}
}
