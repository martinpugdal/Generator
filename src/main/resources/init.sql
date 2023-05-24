PRAGMA FOREIGN_KEYS = ON;
PRAGMA AUTO_VACUUM = FULL;

CREATE TABLE IF NOT EXISTS user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    uuid VARCHAR(36) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS generator_element (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    owner VARCHAR(36) NOT NULL,
    loc_x INT NOT NULL,
    loc_y INT NOT NULL,
    loc_z INT NOT NULL,
    world VARCHAR(255) NOT NULL DEFAULT 'world',
    FOREIGN KEY (owner) REFERENCES user(uuid) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS generator_block (
    id INT PRIMARY KEY,
    tier INT NOT NULL,
    FOREIGN KEY (id) REFERENCES generator_element(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS generator_chest (
   id INT PRIMARY KEY,
   tier INT NOT NULL,
   FOREIGN KEY (id) REFERENCES generator_element(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS generator_chest_drop (
    id INT PRIMARY KEY,
    tier INT NOT NULL,
    amount INT NOT NULL,
    FOREIGN KEY (id) REFERENCES generator_chest(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_generator_loc ON generator_element(loc_x, loc_y, loc_z, world);