ALTER TABLE matches ALTER COLUMN team_winner TYPE INTEGER USING team_winner::integer;
ALTER TABLE matches ADD CONSTRAINT fk_matches_team_winner
    FOREIGN KEY(team_winner)
        REFERENCES team(team_id);