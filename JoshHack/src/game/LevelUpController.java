package game;

import java.util.ArrayList;
import java.util.List;

public class LevelUpController {

	private static final LevelUpOption[] options = new LevelUpOption[] {
			new LevelUpOption("Increased hit points") {
				public void invoke(CreatureInterface creature) {
					creature.modifyMaxHp(10);
					creature.modifyHp(10, "Died from increaced hp level-up bonus?");
					creature.doAction("look a lot healthier");
				}
			}, new LevelUpOption("Increased mana") {
				public void invoke(CreatureInterface creature) {
					creature.modifyMaxMana(5);
					creature.modifyMana(5);
					creature.doAction("look more magical");
				}
			}, new LevelUpOption("Increased attack value") {
				public void invoke(CreatureInterface creature) {
					creature.modifyAttackValue(2);
					creature.doAction("look stronger");
				}
			}, new LevelUpOption("Increased defense value") {
				public void invoke(CreatureInterface creature) {
					creature.modifyDefenseValue(1);
					creature.doAction("look a little tougher");
				}
			}, new LevelUpOption("Increased vision") {
				public void invoke(CreatureInterface creature) {
					creature.modifyVisionRadius(1);
					creature.doAction("look a little more aware");
				}
			}, new LevelUpOption("Increased hp regeneration") {
				public void invoke(CreatureInterface creature) {
					creature.modifyRegenHpPer1000(10);
					creature.doAction("look a little less bruised");
				}
			}, new LevelUpOption("Increased mana regeneration") {
				public void invoke(CreatureInterface creature) {
					creature.modifyRegenManaPer1000(10);
					creature.doAction("look a little less tired");
				}
			} };

	public void autoLevelUp(CreatureInterface creature) {
		options[(int) (Math.random() * options.length)].invoke(creature);
	}

	public List<String> getLevelUpOptions() {
		List<String> names = new ArrayList<String>();
		for (LevelUpOption option : options) {
			names.add(option.name());
		}
		return names;
	}

	public LevelUpOption getLevelUpOption(String name) {
		for (LevelUpOption option : options) {
			if (option.name().equals(name))
				return option;
		}
		return null;
	}
}
