--insert Paises

INSERT INTO tb_pais (id, nome, sigla) VALUES
(1, 'Brasil', 'BR'),
(2, 'Estados Unidos', 'EUA'),
(3, 'Japão', 'JP');

-- insert estados

INSERT INTO tb_estado (id, nome, uf, fk_pais) VALUES

--Brasil
(1, 'Acre', 'AC',1),
(2, 'Alagoas', 'AL',1),
(3, 'Amazonas', 'AM',1),
(4, 'Amapá', 'AP',1),
(5, 'Bahia', 'BA',1),
(6, 'Ceará', 'CE',1),
(7, 'Distrito Federal', 'DF',1),
(8, 'Espírito Santo', 'ES',1),
(9, 'Goiás', 'GO',1),
(10, 'Maranhão', 'MA',1),
(11, 'Minas Gerais', 'MG',1),
(12, 'Mato Grosso do Sul', 'MS',1),
(13, 'Mato Grosso', 'MT',1),
(14, 'Pará', 'PA',1),
(15, 'Paraíba', 'PB',1),
(16, 'Pernambuco', 'PE',1),
(17, 'Piauí', 'PI',1),
(18, 'Paraná', 'PR',1),
(19, 'Rio de Janeiro', 'RJ',1),
(20, 'Rio Grande do Norte', 'RN',1),
(21, 'Rondônia', 'RO',1),
(22, 'Roraima', 'RR',1),
(23, 'Rio Grande do Sul', 'RS',1),
(24, 'Santa Catarina', 'SC',1),
(25, 'Sergipe', 'SE',1),
(26, 'São Paulo', 'SP',1),
(27, 'Tocantins', 'TO',1),

-- Estados Unidos
(28, 'Alabama', 'AL', 2),
(29, 'Alaska', 'AK', 2),
(30, 'Arizona', 'AZ', 2),
(31, 'Arkansas', 'AR', 2),
(32, 'California', 'CA', 2),
(33, 'Colorado', 'CO', 2),
(34, 'Connecticut', 'CT', 2),
(35, 'Delaware', 'DE', 2),
(36, 'Florida', 'FL', 2),
(37, 'Georgia', 'GA', 2),
(38, 'Hawaii', 'HI', 2),
(39, 'Idaho', 'ID', 2),
(40, 'Illinois', 'IL', 2),
(41, 'Indiana', 'IN', 2),
(42, 'Iowa', 'IA', 2),
(43, 'Kansas', 'KS', 2),
(44, 'Kentucky', 'KY', 2),
(45, 'Louisiana', 'LA', 2),
(46, 'Maine', 'ME', 2),
(47, 'Maryland', 'MD', 2),
(48, 'Massachusetts', 'MA', 2),
(49, 'Michigan', 'MI', 2),
(50, 'Minnesota', 'MN', 2),
(51, 'Mississippi', 'MS', 2),
(52, 'Missouri', 'MO', 2),
(53, 'Montana', 'MT', 2),
(54, 'Nebraska', 'NE', 2),
(55, 'Nevada', 'NV', 2),
(56, 'New Hampshire', 'NH', 2),
(57, 'New Jersey', 'NJ', 2),
(58, 'New Mexico', 'NM', 2),
(59, 'New York', 'NY', 2),
(60, 'North Carolina', 'NC', 2),
(61, 'North Dakota', 'ND', 2),
(62, 'Ohio', 'OH', 2),
(63, 'Oklahoma', 'OK', 2),
(64, 'Oregon', 'OR', 2),
(65, 'Pennsylvania', 'PA', 2),
(66, 'Rhode Island', 'RI', 2),
(67, 'South Carolina', 'SC', 2),
(68, 'South Dakota', 'SD', 2),
(69, 'Tennessee', 'TN', 2),
(70, 'Texas', 'TX', 2),
(71, 'Utah', 'UT', 2),
(72, 'Vermont', 'VT', 2),
(73, 'Virginia', 'VA', 2),
(74, 'Washington', 'WA', 2),
(75, 'West Virginia', 'WV', 2),
(76, 'Wisconsin', 'WI', 2),
(77, 'Wyoming', 'WY', 2),

-- Japão
(78, 'Hokkaido', NULL, 3),
(79, 'Aomori', NULL, 3),
(80, 'Iwate', NULL, 3),
(81, 'Miyagi', NULL, 3),
(82, 'Akita', NULL, 3),
(83, 'Yamagata', NULL, 3),
(84, 'Fukushima', NULL, 3),
(85, 'Ibaraki', NULL, 3),
(86, 'Tochigi', NULL, 3),
(87, 'Gunma', NULL, 3),
(88, 'Saitama', NULL, 3),
(89, 'Chiba', NULL, 3),
(90, 'Tokyo', NULL, 3),
(91, 'Kanagawa', NULL, 3),
(92, 'Niigata', NULL, 3),
(93, 'Toyama', NULL, 3),
(94, 'Ishikawa', NULL, 3),
(95, 'Fukui', NULL, 3),
(96, 'Yamanashi', NULL, 3),
(97, 'Nagano', NULL, 3),
(98, 'Gifu', NULL, 3),
(99, 'Shizuoka', NULL, 3),
(100, 'Aichi', NULL, 3),
(101, 'Mie', NULL, 3),
(102, 'Shiga', NULL, 3),
(103, 'Kyoto', NULL, 3),
(104, 'Osaka', NULL, 3),
(105, 'Hyogo', NULL, 3),
(106, 'Nara', NULL, 3),
(107, 'Wakayama', NULL, 3),
(108, 'Tottori', NULL, 3),
(109, 'Shimane', NULL, 3),
(110, 'Okayama', NULL, 3),
(111, 'Hiroshima', NULL, 3),
(112, 'Yamaguchi', NULL, 3),
(113, 'Tokushima', NULL, 3),
(114, 'Kagawa', NULL, 3),
(115, 'Ehime', NULL, 3),
(116, 'Kochi', NULL, 3),
(117, 'Fukuoka', NULL, 3),
(118, 'Saga', NULL, 3),
(119, 'Nagasaki', NULL, 3),
(120, 'Kumamoto', NULL, 3),
(121, 'Oita', NULL, 3),
(122, 'Miyazaki', NULL, 3),
(123, 'Kagoshima', NULL, 3),
(124, 'Okinawa', NULL, 3);

-- insert cidades

-- Cidades com aeroportos no Brasil
INSERT INTO tb_cidade (id, nome, fk_estado) VALUES
(1, 'Rio de Janeiro', 19), -- Rio de Janeiro, RJ
(2, 'São Paulo', 26),      -- São Paulo, SP
(3, 'Brasília', 7),         -- Distrito Federal, DF
(4, 'Salvador', 5),         -- Bahia, BA
(5, 'Fortaleza', 6),        -- Ceará, CE
(6, 'Recife', 16),          -- Pernambuco, PE
(7, 'Manaus', 3),           -- Amazonas, AM
(8, 'Porto Alegre', 23),    -- Rio Grande do Sul, RS
(9, 'Curitiba', 18),        -- Paraná, PR
(10, 'Belém', 14);          -- Pará, PA

-- Cidades com aeroportos nos Estados Unidos
INSERT INTO tb_cidade (id, nome, fk_estado) VALUES
(11, 'New York', 59),       -- New York, NY
(12, 'Los Angeles', 32),    -- California, CA
(13, 'Chicago', 40),        -- Illinois, IL
(14, 'Miami', 36),          -- Florida, FL
(15, 'Dallas', 70),         -- Texas, TX
(16, 'Atlanta', 37),        -- Georgia, GA
(17, 'Denver', 33),         -- Colorado, CO
(18, 'Seattle', 74),        -- Washington, WA
(19, 'Boston', 48),         -- Massachusetts, MA
(20, 'Las Vegas', 55);      -- Nevada, NV

-- Cidades com aeroportos no Japão
INSERT INTO tb_cidade (id, nome, fk_estado) VALUES
(21, 'Tokyo', 90),          -- Tokyo
(22, 'Osaka', 104),         -- Osaka
(23, 'Nagoya', 100),        -- Aichi
(24, 'Sapporo', 78),        -- Hokkaido
(25, 'Fukuoka', 117),       -- Fukuoka
(26, 'Kobe', 105),          -- Hyogo
(27, 'Kyoto', 103),         -- Kyoto
(28, 'Yokohama', 91),       -- Kanagawa
(29, 'Saitama', 88),        -- Saitama
(30, 'Chiba', 89);          -- Chiba



