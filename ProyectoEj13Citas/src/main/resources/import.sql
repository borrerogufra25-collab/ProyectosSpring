-- ====================================================================
-- 1. INSERCIÓN DE DATOS: TABLA CIRUJANO (10 Especialistas)
-- ====================================================================
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (1, 'Dr. Victor Vector', 'Ciber-óptica Aplicada', 'Master');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (2, 'Dra. Alt Cunningham', 'Neuro-conectividad Cuántica', 'Master');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (3, 'Dr. Farries Cordell', 'Prótesis Biomecánicas', 'Senior');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (4, 'Dra. Lucy Kushinada', 'Cortafuegos Sinápticos', 'Junior');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (5, 'Dr. Jackie Welles', 'Anclajes Óseos Reforzados', 'Junior');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (6, 'Dra. Judy Alvarez', 'Sistemas de Calibración Neuro-sensorial', 'Senior');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (7, 'Dr. David Martinez', 'Reflejos y Estimulación Suprarrenal', 'Junior');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (8, 'Dra. Hanako Arasaka', 'Nanotecnología Molecular', 'Master');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (9, 'Dr. Kirk Sawyer', 'Modulación de Frecuencias Neuronales', 'Senior');
INSERT INTO cirujano (id, nombre, especialidad, rango) VALUES (10, 'Dra. Misty Olszewski', 'Sincronización Bio-bucle', 'Junior');

-- ====================================================================
-- 2. INSERCIÓN DE DATOS: TABLA PACIENTE
-- ====================================================================
INSERT INTO paciente (id, nombre, alias, email) VALUES (1, 'Alejandro Ramos', 'Álex', 'alex.ramos@ciber-madrid.es');
INSERT INTO paciente (id, nombre, alias, email) VALUES (2, 'María del Carmen Vega', 'Mamen', 'mvega@samurai-vlc.net');
INSERT INTO paciente (id, nombre, alias, email) VALUES (3, 'Tomás Antúnez', 'Neo', 'tantunez@lamatriz.org');
INSERT INTO paciente (id, nombre, alias, email) VALUES (4, 'Jaime Colom', 'Viajero', 'jcolom@barna-sinaptica.cat');
INSERT INTO paciente (id, nombre, alias, email) VALUES (5, 'Montserrat Kusanagi', 'Mayor', 'montse.k@seccion9.es');
INSERT INTO paciente (id, nombre, alias, email) VALUES (6, 'Diego Shaw', 'BladeRunner', 'dshaw@policia-sevilla.rep');
INSERT INTO paciente (id, nombre, alias, email) VALUES (7, 'Alfonso Murphy', 'RoboCop', 'murphy@seguridad-zgz.es');
INSERT INTO paciente (id, nombre, alias, email) VALUES (8, 'Sara Connor', 'Resistencia', 'sara.connor@antiskynet.es');
INSERT INTO paciente (id, nombre, alias, email) VALUES (9, 'Pedro Parra', 'Spidey', 'p.parra@eldiariocentral.es');
INSERT INTO paciente (id, nombre, alias, email) VALUES (10, 'Bruno Díaz', 'Batman', 'bdiaz@wayne-bilbao.com');

-- ====================================================================
-- 3. INSERCIÓN DE DATOS: TABLA CITA (10 Registros Planificados)
-- Las fechas usan formato estándar ISO 'YYYY-MM-DD HH:MM:SS'
-- ====================================================================
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (1, '2026-06-01 09:00:00', 'Módulo Delta-1', 1, 1);
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (2, '2026-06-01 12:00:00', 'Módulo Delta-1', 2, 1); -- Mismo cirujano, más tarde (vuelve a estar libre)
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (3, '2026-06-02 10:00:00', 'Quirófano Cuántico', 3, 2);
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (4, '2026-06-02 16:30:00', 'Módulo Gamma-4', 4, 3);
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (5, '2026-06-03 08:30:00', 'Laboratorio Central', 5, 5);
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (6, '2026-06-03 11:00:00', 'Laboratorio Central', 6, 4);
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (7, '2026-06-04 15:00:00', 'Sala Blindada 01', 7, 7);
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (8, '2026-06-04 18:00:00', 'Módulo Épsilon-2', 8, 6);
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (9, '2026-06-05 10:15:00', 'Quirófano Cuántico', 9, 8);
INSERT INTO cita (id, fecha_hora, sala_quirofano, paciente_id, cirujano_id) VALUES (10, '2026-06-05 13:00:00', 'Módulo Delta-2', 10, 9);

-- ====================================================================
-- 4. ACTUALIZACIÓN DE SECUENCIADORES (Alineación de ID de Hibernate)
-- Evita que el sistema falle por claves duplicadas al insertar desde la web
-- ====================================================================
ALTER TABLE cirujano ALTER COLUMN id RESTART WITH 11;
ALTER TABLE paciente ALTER COLUMN id RESTART WITH 11;
ALTER TABLE cita ALTER COLUMN id RESTART WITH 11;