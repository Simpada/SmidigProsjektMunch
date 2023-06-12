
INSERT INTO Categories (event_id, category)
VALUES (1, 'PARTY'),
       (1, 'EXHIBITION');

INSERT INTO Categories (event_id, category)
VALUES (2, 'KIDS');

INSERT INTO Categories (event_id, category)
VALUES (3, 'PARTY'),
       (3, 'FAMILY'),
       (3, 'NEW');

INSERT INTO Categories (event_id, category)
VALUES (4, 'GAMES');

INSERT INTO Categories (event_id, category)
VALUES (5, 'PARTY'),
       (5, 'EXHIBITION');



INSERT INTO Event_Reviews (user_id, event_id, review_text, num_stars)
VALUES (1, 1, 'Great event!', 5),
       (2, 1, 'Enjoyed it!', 4),
       (3, 2, 'Could be better.', 3),
       (4, 3, 'Amazing experience!', 5),
       (5, 3, 'Not impressed.', 2),
       (1, 4, 'Highly recommended!', 5),
       (2, 4, 'Average event.', 3),
       (3, 5, 'Loved every moment.', 5),
       (4, 5, 'Disappointing.', 2),
       (5, 5, 'Well organized.', 4);

INSERT INTO App_Reviews (user_id, review_text, num_stars)
VALUES (1, 'Great app!', 5),
       (2, 'Easy to use and helpful.', 4),
       (3, 'Could use some improvements.', 3),
       (4, 'Excellent features!', 5),
       (5, 'Needs better performance.', 2);


INSERT INTO Preferences (user_id, preference)
VALUES (1, 'PARTY'), (1, 'EXHIBITION'), (1, 'KIDS'), (1, 'FAMILY'), (1, 'NEW'), (1, 'GAMES');

INSERT INTO Preferences (user_id, preference)
VALUES (2, 'PARTY'), (2, 'EXHIBITION');

INSERT INTO Preferences (user_id, preference)
VALUES (3, 'KIDS'), (3, 'FAMILY'), (3, 'GAMES');

INSERT INTO Preferences (user_id, preference)
VALUES (4, 'GAMES'), (4, 'NEW');


INSERT INTO Points (user_id, current_points, weekly_points, monthly_points, alltime_points)
VALUES (1, 150, 50, 200, 1000);

INSERT INTO Points (user_id, current_points, weekly_points, monthly_points, alltime_points)
VALUES (2, 300, 100, 400, 2500);

INSERT INTO Points (user_id, current_points, weekly_points, monthly_points, alltime_points)
VALUES (3, 200, 75, 350, 1800);

INSERT INTO Points (user_id, current_points, weekly_points, monthly_points, alltime_points)
VALUES (4, 400, 120, 500, 3000);

INSERT INTO Points (user_id, current_points, weekly_points, monthly_points, alltime_points)
VALUES (5, 250, 80, 300, 1500);


INSERT INTO Paintings_Collected (user_id, painting_id)
VALUES (1, 1), (1, 3), (1, 5);

INSERT INTO Paintings_Collected (user_id, painting_id)
VALUES (2, 2), (2, 4), (2, 5);

INSERT INTO Paintings_Collected (user_id, painting_id)
VALUES (3, 1), (3, 2), (3, 3), (3, 4), (3, 5);

INSERT INTO Paintings_Collected (user_id, painting_id)
VALUES (4, 2);

INSERT INTO Paintings_Collected (user_id, painting_id)
VALUES (5, 1), (5, 2), (5, 3), (5, 4);