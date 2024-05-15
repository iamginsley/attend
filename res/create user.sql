USE `attend-db`;

INSERT INTO user (name, surname, username, password, email, studentNumber, roleId)
VALUES
    ('Hias', 'dev', 'hias', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'hias@example.com', '123456', 1),
    ('Kevin', 'dev', 'kevin', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'kevin@example.com', '234567', 1),
    ('Dominik', 'dev', 'dominik', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'dominik@example.com', '345678', 1),
    ('Seiwi', 'dev', 'seiwi', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'seiwi@example.com', '456789', 1),
    ('Michael', 'dev', 'michael', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'michael@example.com', '456789', 1),
    ('Andrea', 'Corradini', 'Andrea', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'andrea@example.com', '456789', 1);
