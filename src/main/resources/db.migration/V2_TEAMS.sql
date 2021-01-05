CREATE TABLE team(
    team_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    acron VARCHAR(10),
    wins INTEGER,
    losses INTEGER,
    goals_maden INTEGER,
    goals_taken INTEGER,
    goals_balance INTEGER
);