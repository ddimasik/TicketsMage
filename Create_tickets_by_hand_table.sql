CREATE TABLE IF NOT EXISTS tickets
(
    Id int PRIMARY KEY NOT NULL,
    Train_id int NOT NULL,
    Passenger_id int NOT NULL,
    Start_St_id int NOT NULL,
    End_St_id int NOT NULL,
    DateTime datetime NOT NULL
);
CREATE UNIQUE INDEX tickets_Train_Passenger_Start_St_End_St_uindex ON tickets (Train_id, Passenger_id, Start_St_id, End_St_id)



