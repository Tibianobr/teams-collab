CREATE TABLE matches(
    match_id SERIAL PRIMARY KEY,
    team_a INTEGER,
    team_b INTEGER,
	score_a INTEGER,
	score_b INTEGER,
	team_winner VARCHAR(255),
	CONSTRAINT fk_matches_team_a
      FOREIGN KEY(team_a)
	  REFERENCES team(team_id),
	CONSTRAINT fk_matches_team_b
      FOREIGN KEY(team_b)
	  REFERENCES team(team_id)
);