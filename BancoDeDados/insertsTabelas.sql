INSERT INTO tb_endereco (logradouro, numero, complemento, cep, fk_cidade) VALUES 
('Rua A', '123', 'Apto 101', '12345-678', 1),
('Avenida B', '456', 'Casa 203', '54321-876', 2),
('Rua C', '789', 'Sala 305', '98765-432', 3),
('Avenida D', '1011', 'Andar 2', '13579-246', 4),
('Rua E', '1213', 'Apto 405', '86420-975', 5),
('Avenida F', '1415', 'Casa 507', '36985-024', 6),
('Rua G', '1617', 'Apto 609', '25814-753', 7),
('Avenida H', '1819', 'Loja 711', '97531-486', 8),
('Rua I', '2021', 'Andar 8', '64280-159', 9),
('Avenida J', '2223', 'Casa 913', '71394-580', 10);

INSERT INTO tb_companhia_aerea (nome, cnpj, email) VALUES 
('AirCompany1', '12345678901234', 'aircompany1@example.com'),
('SkyFlights', '56789012345678', 'info@skyflights.com'),
('WingsExpress', '90123456789012', 'contact@wingsexpress.co'),
('AeroJet', '34567890123456', 'info@aerojetairlines.com'),
('FlyHigh Airlines', '78901234567890', 'flyhigh@example.com'),
('SwiftAir', '12345098765432', 'info@swiftairways.com'),
('JetStream', '65432109876543', 'contact@jetstreamairlines.co'),
('StarFlyer', '87654321098765', 'starflyer@example.com'),
('AirWaves', '21098765432109', 'info@airwavesairlines.com'),
('SkyRiders', '54321098765432', 'contact@skyriders.co');

INSERT INTO tb_aeronave (modelo, qtd_assento_economico, qtd_assento_vip, nsa, fk_companhia) VALUES 
('Boeing 737', 1, 1, 'ABC123', 1),
('Airbus A320', 1, 1, 'DEF456', 2),
('Embraer E190', 1, 1, 'GHI789', 3),
('Boeing 777', 3, 2, 'JKL012', 4),
('Airbus A380', 3, 4, 'MNO345', 5),
('Bombardier CRJ900', 1, 1, 'PQR678', 6),
('Boeing 747', 1, 1, 'STU901', 7),
('Embraer E175', 1, 1, 'VWX234', 8),
('Airbus A330', 180, 25, 'YZA567', 9),
('ATR 72', 70, 5, 'BCD890', 10);

INSERT INTO tb_aeroporto (nome, email, fk_endereco) VALUES 
('Airport1', 'info@airport1.com', 1),
('Skyport International', 'contact@skyport.com', 2),
('Wings Terminal', 'wings@terminal.co', 3),
('AeroHub', 'info@aerohub.com', 4),
('Flyers Gateway', 'gateway@flyers.com', 5),
('SwiftSky Airport', 'info@swiftsky.com', 6),
('Jetport Central', 'central@jetport.com', 7),
('StarAir Hub', 'hub@starair.com', 8),
('AirWays Terminal', 'terminal@airways.com', 9),
('SkyRiders Landing', 'landing@skyriders.com', 10);

INSERT INTO tb_passageiro (nome_completo, cpf, data_nascimento, email) VALUES 
('John Doe', '123.456.789-01', '1990-05-15', 'john.doe@example.com'),
('Jane Smith', '234.567.890-12', '1985-08-20', 'jane.smith@example.com'),
('Robert Johnson', '345.678.901-23', '1978-11-10', 'robert.j@example.com'),
('Emily Davis', '456.789.012-34', '1993-02-28', 'emily.davis@example.com'),
('Michael Brown', '567.890.123-45', '1980-07-07', 'michael.b@example.com'),
('Sophia White', '678.901.234-56', '1995-12-03', 'sophia.white@example.com'),
('Daniel Miller', '789.012.345-67', '1975-03-18', 'daniel.m@example.com'),
('Olivia Garcia', '890.123.456-78', '1987-06-22', 'olivia.g@example.com'),
('William Taylor', '901.234.567-89', '1998-09-14', 'william.t@example.com'),
('Ava Wilson', '012.345.678-90', '1982-04-26', 'ava.wilson@example.com');

INSERT INTO tb_passagem (timestamp_compra, timestamp_partida, timestamp_chegada, fk_origem, fk_destino, fk_aeronave, valor_passagem, tipo_assento, fk_passageiro) VALUES 
('2024-02-05 08:30:00', '2024-03-01 10:00:00', '2024-03-01 12:30:00', 1, 5, 1, 250.00, 'ECONOMICO', 1),
('2024-02-06 15:45:00', '2024-03-02 18:30:00', '2024-03-02 21:15:00', 2, 8, 2, 300.00, 'VIP', 2),
('2024-02-07 12:15:00', '2024-03-03 14:45:00', '2024-03-03 17:30:00', 3, 10, 3, 180.00, 'ECONOMICO', 3),
('2024-02-08 09:00:00', '2024-03-04 11:30:00', '2024-03-04 14:15:00', 4, 7, 4, 400.00, 'VIP', 4),
('2024-02-09 14:30:00', '2024-03-05 17:00:00', '2024-03-05 19:45:00', 5, 3, 5, 220.00, 'ECONOMICO', 5),
('2024-02-10 17:45:00', '2024-03-06 20:15:00', '2024-03-06 23:00:00', 6, 1, 6, 180.00, 'VIP', 6),
('2024-02-11 10:30:00', '2024-03-07 13:00:00', '2024-03-07 15:45:00', 7, 4, 7, 300.00, 'ECONOMICO', 7),
('2024-02-12 08:00:00', '2024-03-08 10:30:00', '2024-03-08 13:15:00', 8, 9, 8, 250.00, 'VIP', 8),
('2024-02-13 13:15:00', '2024-03-09 15:45:00', '2024-03-09 18:30:00', 9, 6, 9, 280.00, 'ECONOMICO', 9),
('2024-02-14 11:45:00', '2024-03-10 14:15:00', '2024-03-10 17:00:00', 10, 2, 10, 320.00, 'VIP', 10);
