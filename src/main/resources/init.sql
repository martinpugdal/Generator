PRAGMA FOREIGN_KEYS = ON;
PRAGMA AUTO_VACUUM = FULL;

CREATE TABLE IF NOT EXISTS user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    xp REAL NOT NULL DEFAULT 0,
    multiplier REAL NOT NULL DEFAULT 1,
    generator_slots INT NOT NULL DEFAULT 25
);

CREATE TABLE IF NOT EXISTS generator_element (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    owner VARCHAR(36) NOT NULL,
    loc_x INT NOT NULL,
    loc_y INT NOT NULL,
    loc_z INT NOT NULL,
    world VARCHAR(255) NOT NULL DEFAULT 'plot',
    FOREIGN KEY (owner) REFERENCES user(uuid) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS generator_block (
    id INT PRIMARY KEY,
    tier INT NOT NULL,
    FOREIGN KEY (id) REFERENCES generator_element(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS generator_chest (
   id INT PRIMARY KEY,
   FOREIGN KEY (id) REFERENCES generator_element(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS generator_chest_drop (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    chest_id INT NOT NULL,
    tier INT NOT NULL,
    amount BIGINT NOT NULL,
    FOREIGN KEY (chest_id) REFERENCES generator_chest(id) ON DELETE CASCADE
);


CREATE INDEX IF NOT EXISTS idx_user ON user(uuid, xp, multiplier, generator_slots);
CREATE INDEX IF NOT EXISTS idx_generator_loc ON generator_element(loc_x, loc_y, loc_z, world);
CREATE INDEX IF NOT EXISTS idx_chest_drop ON generator_chest_drop(chest_id, tier, amount);