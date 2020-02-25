 INSERT INTO 
    unit (ID, Nameunit, Total1, UID2, Total2 ) 
VALUES
    (1, 'grams',       1, 2, 0.035274),
    (2, 'ounce(oz)',   1, 1, 28.35),
    (3, 'liter',       1, 4, 4.23),
    (4, 'Cups',        1, 3, 0.2366),
    (5, 'Bread Slice', 1, 1, 10);

INSERT INTO 
    ingredient (ID, Nameingredient, Description ) 
VALUES
    (1, 'Sea Salt','Salt from Sea'),
    (2, 'Pepper','Pepper'),
    (3, 'Bread','Bread'),
    (4, 'Cheese','Cheese'),
    (5, 'Cabbage','Cabbage'),
    (6, 'Carrot','Carrot'),
    (7, 'Tomato','Tomato'),
    (8, 'Dressing','Dressing'),
    (9, 'Mayones','Mayones'),
    (10, 'Onion','Onion');


INSERT INTO 
    recipe (ID, Namerecipe, Description ) 
VALUES
    (1, 'sandwich','sandwich bread with egg and veggy'),
    (2, 'egg salad','veggy with egg and dressing');

INSERT INTO 
    recipemenu (ID, RID, INID, Total, UID ) 
VALUES
    ( 1, 1, 1,  3, 1),
    ( 2, 1, 2,  3, 1),
    ( 3, 1, 3,  2, 5),
    ( 4, 1, 4, 10, 1),
    ( 5, 1, 7, 15, 1),
    ( 6, 2, 5, 60, 1),
    ( 7, 2, 6, 80, 1),
    ( 8, 2, 7, 20, 1),
    ( 9, 2, 8, 30, 1),
    (10, 1, 9, 30, 1);

INSERT INTO 
    predictcost (ID, RID, Total ) 
VALUES
    (1, 1, 30),
    (2, 2, 45);

INSERT INTO 
    `ingredientcost` ( INID, Nameingredient, Total, IUID, Total2, UID, Nameunit, Cost, Allcost, Countitem )
VALUES
    (1, 'Sea Salt',   90.0, 1,  200.0, 1, 'grams', 10.0, 0.0, 0.0),
    (2, 'Pepper',     90.0, 1,  200.0, 1, 'grams', 30.0, 0.0, 0.0),
    (3, 'Bread',      60.0, 5,  220.0, 1, 'grams', 20.0, 0.0, 0.0),
    (4, 'Cheese',    300.0, 1,  100.0, 1, 'grams', 85.0, 0.0, 0.0),
    (5, 'Cabbage',  2700.0, 1, 1000.0, 1, 'grams', 35.0, 0.0, 0.0),
    (6, 'Carrot',   3600.0, 1, 1000.0, 1, 'grams', 65.0, 0.0, 0.0),
    (7, 'Tomato',   1350.0, 1,  500.0, 1, 'grams', 35.0, 0.0, 0.0),
    (8, 'Dressing', 1350.0, 1,  200.0, 1, 'grams', 80.0, 0.0, 0.0),
    (9, 'Mayones',   900.0, 1,  200.0, 1, 'grams', 50.0, 0.0, 0.0);


