CREATE TABLE IF NOT EXISTS tickets_by_hand
(
    Id int PRIMARY KEY NOT NULL,
    Train_id int NOT NULL,
    Passenger_id int NOT NULL,
    Start_St_id int NOT NULL,
    End_St_id int NOT NULL
);
CREATE UNIQUE INDEX tickets_by_hand_Train_id_Passenger_id_uindex ON tickets_by_hand (Train_id, Passenger_id)