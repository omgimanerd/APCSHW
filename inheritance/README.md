Stuyablo should consist of its classes,
Each class should have the following shared stats:
     Health (HP) - Determines a character's hitpoints, when this hits 0,
            then the character dies.
     Mana (MP) - Determines the amount of spells and abilities that the
            character can cast.
     Physical Attack (PA) - Determines the amount of physical damage that
            a character will deal in an attack.
     Magical Attack (MA) - Determines the amount of magical damage that
            a character will deal in an attack.
     Armor - Determines how much physical damage is negated when a character
            takes damage. Reduces (armor / armor + 100)% of incoming physical
            damage.
     Warding - Determines how much magical damage is negated when a character
            takes damage. Reduces (warding / warding + 100)% of incoming
            magical damage.

Each class will have its own unique characteristics and boosts to the above
stats, for example:
     Warrior: has bonus health, armor, and warding, depends on PA.
     Wizard: has high MA, but not necessarily high defensive stats.
