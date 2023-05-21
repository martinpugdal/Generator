PRAGMA FOREIGN_KEYS = ON;
PRAGMA AUTO_VACUUM = FULL;

CREATE TABLE IF NOT EXISTS generator (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    owner VARCHAR(36) NOT NULL,
    tier INT NOT NULL,
    loc_x INT NOT NULL,
    loc_y INT NOT NULL,
    loc_z INT NOT NULL,
    world VARCHAR(255) NOT NULL DEFAULT 'world'
);

CREATE INDEX IF NOT EXISTS idx_generator_loc ON generator(loc_x, loc_y, loc_z, world);