USE ligafutbol;

INSERT INTO Jugadores(nombre, apellido, dorsal, equipo_id) VALUE 
	('Lionel', 'Messi', 10, 1),
	('Cristiano', 'Ronaldo', 7, 2),
	('Neymar', 'Junior', 11, 3),
	('Kylian', 'Mbappé', 7, 4),
	('Kevin', 'De Bruyne', 17, 5),
	('Robert', 'Lewandowski', 9, 6),
	('Sergio', 'Ramos', 4, 7),
	('Virgil', 'van Dijk', 4, 8),
	('Karim', 'Benzema', 9, 9),
	('Erling', 'Haaland', 9, 10),
	('Mohamed', 'Salah', 11, 1),
	('Harry', 'Kane', 10, 2),
	('Luka', 'Modric', 10, 3),
	('Paul', 'Pogba', 6, 4),
	('Manuel', 'Neuer', 1, 5),
	('Alisson', 'Becker', 1, 6);

INSERT INTO Goles(minuto_anotacion, descripcion, jugador_id)VALUES
	('23', 'Golazo de tiro libre', 1),
	('45', 'Gol de cabeza en el área', 2),
	('78', 'Gol de penal', 3),
	('89', 'Contraataque rápido y gol', 4),
	('15', 'Gol de volea', 5),
	('5', 'Gol de rebote', 6),
	('60', 'Gol desde fuera del área', 7),
	('12', 'Gol en jugada combinada', 8),
	('88', 'Gol de taco', 9),
	('90', 'Gol en tiempo extra', 10);

INSERT INTO Patrocinadores (nombre, logo)VALUES
	('Nike', 'logo_nike.png'),
	('Adidas', 'logo_adidas.png'),
	('Puma', 'logo_puma.png'),
	('Coca Cola', 'logo_cocacola.png'),
	('Emirates', 'logo_emirates.png'),
	('Pepsi', 'logo_pepsi.png'),
	('Red Bull', 'logo_redbull.png'),
	('Samsung', 'logo_samsung.png'),
	('Honda', 'logo_honda.png'),
	('Toyota', 'logo_toyota.png');

INSERT INTO Equipos(nombre, estadio, ciudad, aforo) VALUE 
	('Barcelona', 'Camp Nou', 'Barcelona', '105000'),
	('Real Madrid', 'Santiago Bernabeu', 'Madrid', '95000'),
	('Bayern München', 'Allianz Arena', 'München', 75000),
	('Milan', 'San Siro', 'Milan', '75800'),
	('Liverpool', 'Andfield', 'Liverpool', '61000'),
	('Juventus', 'Juventus Stadium', 'Turin', '41000'),
	('PSG', 'Parc Des Princes', 'Paris', '48000'),
	('Manchester United', 'Old Trafford', 'Manchester', '74000'),
	('Arsenal', 'Emirates Stadium', 'Londres', '60700'),
	('Borussia Dortmund', 'Signal Iduna Park', 'Dortmund', '81000');

INSERT INTO Equipamientos(color, escudo, patrocinador_id, equipo_id) VALUE 
	('Rojo y Azul', 'https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/640px-FC_Barcelona_%28crest%29.svg.png', 1, 1),
	('Blanco y Negro', 'https://upload.wikimedia.org/wikipedia/en/5/56/Real_Madrid_CF.svg', 2, 2),
	('Rojo y Blanco', 'https://a.espncdn.com/i/teamlogos/soccer/500-dark/132.png', 3, 3),
	('Rojo y Negro', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png', 4, 4),
	('Rojo Y Blanco', 'https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/640px-Liverpool_FC.svg.png', 5, 5),
	('Blanco y Negro', 'https://assets.coingecko.com/coins/images/10060/large/Juve-10.png', 6, 6),
	('Azul y Blanco', 'https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/160.png', 7, 7),
	('Rojo y Amarillo', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQp1MKKS7V36N57DgZ9ZFDFEBiRiw5WVvOwQ&s', 8, 8),
	('Rojo y Blanco', 'https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png', 9, 9),
	('Amarillo y Negro', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/800px-Borussia_Dortmund_logo.svg.png', 10, 10);

INSERT INTO Partidos(golLocal, golVisitante, fecha, equipo_id) VALUE 
	('7', '0', '2024/08/31', 1),
	('4', '2', '2024/07/03', 2),
	('9', '1', '2024/09/15', 3),
	('5', '0', '2024/07/15', 4),
	('4', '0', '2024/05/07', 5),
	('2', '1', '2024/06/01', 6),
	('2', '4', '2024/04/16', 7),
	('3', '1', '2024/10/18', 8),
	('6', '0', '2024/01/17', 9),
	('7', '2', '2024/02/14', 10);