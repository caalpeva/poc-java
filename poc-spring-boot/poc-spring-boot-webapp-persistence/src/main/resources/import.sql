--
-- Base de datos: `POC_JOBS`
--

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `CATEGORIES`
--

INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (1, 'Habilidades para dise?ar, dirigir y construir proyectos arquitect?nicos, que pueden ir desde dise?os en peque?a escala (como casas), hasta gran escala (como el planeamiento de una ciudad).', 'Arquitectura');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (2, 'Actividades para mantener oportuna y correcta la aplicaci?n del sistema contable y presupuestal, asi como mantener en forma eficiente la programaci?n y pago de los egresos, as? como la respectiva creaci?n de pasivos.', 'Contablidad/Finanzas');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (3, 'Trabajos de capturistas, manejo de paqueteria de software, dise?o de redes, telecomunicaciones, etc.', 'Computacion/TI');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (4, 'Trabajos con aplicaci?n en la industria, telecomunicaciones, en el dise?o y an?lisis de instrumentaci?n electr?nica, microcontroladores y microprocesadores. ', 'Ingenieria electronica');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (5, 'Administraci?n de n?minas, pagas extra de los empleados, supervisar el trabajo de los empleados, determinar las necesidades del personal.', 'Recursos Humanos');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (6, 'Actividades relacionadas con ofrecer servicios y atencion a los clientes de forma efectiva.', 'Servicio y atencion al cliente');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (7, 'Trabajos relacionados con capacidad de observaci?n, buena memoria, habilidad num?rica y verbal, razonamiento concreto y abstracto, imaginaci?n e inventiva, habilidad para el manejo de instrumentos y material de laboratorio, capacidad de adaptaci?n social ', 'Logistica/transportes');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (8, 'Profesionistas capaces de analizar, dise?ar y mejorar estrat?gicamente proyectos de sistemas de software mediante la aplicaci?n de procesos, modelos, herramientas y est?ndares de calidad en su desarrollo.', 'Desarrollo de software');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (9, 'Crear conceptos visuales para publicidad, reunirse con clientes para conocer el presupuesto del proyecto, asesorar a los clientes para crear estrategias de publicidad visual, liderar equipos de trabajo, dise?ar logotipos.', 'Dise');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (10, 'Profesionales que se encargan de escribir las noticias que usualmente vemos en las revistas, peri?dicos e incluso en la televisi?n, muchos incluso, se dedican a escribir historias en Blogs y en los diferentes medios de comunicaci?n escrita.', 'Comunicaciones');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (11, 'Investigaci?n de mercados, programaci?n y desarrollo del producto, fijaci?n de precios, canales de distribuci?n y log?stica. Comunicaci?n integral: publicidad, comunicaci?n e imagen, relaciones p?blicas, marketing directo, promoci?n, etc.', 'Mercadotecnia');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (12, 'Habilidades para supervisar y dirigir las actividades de una oficina o de un departamento de Ventas. Coordinar y monitorear el trabajo de los empleados a su cargo.', 'Ventas');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (13, 'Habilidadeds para planificar, dirigir y coordinar las actividades de publicidad y relaciones p?blicas de la empresa u organizaci?n. Dise?ar y planificar campa?as publicitarias. Dirigir y gestionar las actividades del personal de publicidad y relaciones p?', 'Publicidad');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (14, 'Profesionistas capaces para programar, organizar, dirigir, controlar y supervisar las actividades de personal, tesorer?a, contabilidad y costos, log?stica y servicios internos y de mantenimiento.', 'Gerencia/Administracion');
INSERT INTO `CATEGORIES` (`id`, `description`, `name`) VALUES (15, 'Funciones de docencia de car?cter profesional que implica la realizaci?n directa de los procesos sistem?ticos de ense?anza - aprendizaje, lo cual incluye el diagn?stico, la planificaci?n, la ejecuci?n y la evaluaci?n de los mismos procesos y sus resultado', 'Educacion');

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `JOBS`
--

INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (1, 'Importante Empresa dedicada a ofrecer servicios de Consultoria en Recursos Humanos, Jur?dico y Finanzas te invita a formar parte de su equipo como Analista Contable.', '<p><strong>Requisitos:</strong><br/>Edad: 24 a 35 a&ntilde;os<br/>Escolaridad: Licenciatura en Contabilidad o af&iacute;n</p>\r\n<p><strong>Experiencia m&iacute;nima de 2 a&ntilde;os en:</strong><br/>1. Descarga de facturas del sistema de CXP y/o SAT', 0, 'logo1.png', 'Analista Contable.', '2019-05-05 00:00:00', 8500, 'ACTIVE', 2);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (2, '?nete al Socio Log?stico con mayor presencia en M?xico, Soft Technologies te invita a formar parte de su gran equipo de trabajo como Administrador de datos en la nube.', '<p><span style=\"color: #0000ff;\"><strong>REQUISITOS</strong></span></p>\r\n<p>Escolaridad: Lic. trunca o pasante en Ing en Sistemas Computacionales o afin.<br/>Experiencia de 2 a&ntilde;os en:</p><ul><li>2 a&ntilde;os como ingeniero de infraestructura', 0, 'logo2.png', 'Administrador de datos en la nube ', '2019-05-05 00:00:00', 14200, 'ACTIVE', 3);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (3, 'TE INVITAMOS A UNIRTE A MARKETING TEC2000 COMO COORDINADOR O COORDINADORA DE MARKETING. SOMOR UNA EMPRESA DE INNOVACION EN EL SECTOR METALMECANICO E IMPORTACIONES, EXCELENTE OPORTUNIDAD DE DESARROLLO Y APLICACION DE CONOCIMIENTOS.', '<p><strong><span style=\"background-color: #ccffcc;\">BUSCAMOS LIDER QUE GUSTE DE RETOS PARA:</span></strong></p><ul><li>DISE&Ntilde;AR, PLANIFICAR ELABORAR E INSTAURAR LOS PLANES DE MARKETING DE LA EMPRESA.</li><li>', 0, 'logo3.png', 'Coordinador de Marketing', '2019-05-06 00:00:00', 7900, 'ACTIVE', 11);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (4, 'Importante empresa l?der en su giro solicita por expansi?n Gerente de Recursos Humanos para llevar el control de personal, supervisi?n del reclutamiento y los programas de capacitaci', '<p><strong>IMPORTANTE EMPRESA L&Iacute;DER EN SU GIRO SOLICITA POR EXPANSI&Oacute;N</strong><br/>EJECUTIVO DE RECLUTAMIENTO BILING&Uuml;E<br/><span style=\"color: #ff0000;\"><strong>REQUISITOS:</strong></span></p><ol><li>LIC. EN PSICOLOGIA', 0, 'logo4.png', 'Gerente de Recursos Humanos', '2019-05-06 00:00:00', 17000, 'ACTIVE', 5);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (5, 'Se requiere Jefe de Almac?n y log?stica en empresa l?der a nivel nacional dedicada a la distribuci?n de medicamentos de alta especialidad en el sector gobierno, organismos descentralizados y hospitales privados.', '<p><span style=\"background-color: #00ccff;\"><em>Requisitos:</em></span></p><ol><li>Titulado en Ing. Industrial, Ing. Qu&iacute;mico, Q.F.B., Q.F.I., Lic. En Farmacia y/o carrera af&iacute;n.</li>', 0, 'logo5.png', 'Jefe de Almac', '2019-05-07 00:00:00', 12400, 'INACTIVE', 7);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (6, 'Importante Empresa solicita Analista de cr?dito para actualizar y depurar las cuentas: integraci?n documental y actualizaci?n en SAP.', '<p><span style=\"color: #ff0000;\"><strong>Analista de Cr&eacute;dito y Cobranza</strong></span><br /><br/><strong>Requisitos:</strong></p><ol><li>Titulado. Licenciatura en Contabilidad, Administraci&oacute;n o af&iacute;n.</li>', 1, 'logo2.png', 'Analista de Cr?dito y Cobranza', '2019-05-07 00:00:00', 21000, 'INACTIVE', 2);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (7, 'Estamos solicitando personal, \"dise?ador\" para sus puntos de venta localizados en la zona, debe tener conocimientos en alguno de los diferentes softwares de dise?o, Corel, Photoshop o Illustrator.', '<p><strong><span style=\"color: #0000ff;\">DESCRIPCION</span></strong></p><p><span style=\"color: #ff0000;\"><em><strong>Consultor&iacute;a de Imagen est&aacute; en b&uacute;squeda del talento de un Dise&ntilde;ador Gr&aacute;fico</strong></em></span>', 1, 'logo3.png', 'Dise?ador grafico - T?cnico o superior', '2019-05-07 00:00:00', 7200, 'ACTIVE', 9);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (8, 'Reclutamos para una empresa l?der de transporte especializado un Gerente de Recursos Humanos con buen liderazgo, din?mico, negociador, pro activo, enfocado a resultados.', '<p><span style=\"color: #ff0000;\"><strong>Funciones:</strong></span></p><ul><li>Responsable de las &aacute;reas de Reclutamiento, Capcitaci&oacute;n, N&oacute;mina, Laboral, Compensaciones, Seguridad Patrimonial.</li><li>Promover la cultura organizac', 1, 'logo1.png', 'Gerente de Recursos Humanos', '2019-05-08 00:00:00', 22000, 'INACTIVE', 5);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (9, 'Estamos solicitando Arquitecto / Ing Civil con experiencia en desarrollos habitacionales, en los procesos de venta, comercializaci?n, publicidad y administradores de obras.', '<p><strong>Descripci&oacute;n</strong><br/>Empresa constructora solicita personal para puesto de Residente de Obra.<br/><strong>Escolaridad:</strong> Arquitecto y/o Ingeniero Civil<br/><strong>Funci&oacute;n principal:</strong><br/>', 1, 'logo12.png', 'Residente de obra Arquitecto o Ing Civil', '2019-05-08 00:00:00', 16500, 'ACTIVE', 1);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (10, 'Empresa importante del sector financiero solicita Ing. de Sistemas para monitorear y administrar el desempe?o de los servidores Linux.', '<p style=\"text-align: center;\"><span style=\"color: #0000ff;\">&iexcl;&iexcl;INT&Eacute;GRATE A NUESTRO GRAN EQUIPO</span><br/><span style=\"color: #0000ff;\">EMPRESA IMPORTANTE SOLICITA ADMINISTRADOR DE SO RED HAT ENTERPRISE LINUX</span></p>', 1, 'logo14.png', 'Administrador de redes y Sistema Operativo Red Hat', '2019-05-09 00:00:00', 25000, 'ACTIVE', 3);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (11, 'Empresa con presencia a nivel nacional, te invita a formar parte de su equipo de trabajo para dise?ar, crear y realizar mantenimiento a p?ginas y aplicaciones web.', '<p><span style=\"color: #ff0000;\"><strong>PROGRAMADOR DE SOFTWARE</strong></span><br /><br /><strong>OBJETIVO:</strong> DISE&Ntilde;AR, CREAR Y REALIZAR MANTENIMIENTO A P&Aacute;GINAS Y APLICACIONES WEB<br /><br/><strong><span style=\"color: #0000ff;\">', 1, 'logo15.png', 'Programador de Software', '2019-05-09 00:00:00', 19700, 'ACTIVE', 8);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (12, 'Empresa internacional solicita Contador P?blico para realizar las siguientes actividades: conciliaciones y movimientos bancarios, emisi?n de estados financieros, c?lculo de impuestos y presentaci?n de declaraciones, estrategias fiscales, entre otros.', '<p><strong>EMPRESA L&Iacute;DER EN LA ADMINISTRACI&Oacute;N DE CAPITAL HUMANO EST&Aacute; EN B&Uacute;SQUEDA DE:</strong><br/><br/><strong>EJECUTIVO DE CONTABLE</strong><br /><br /><strong><span style=\"color: #ff0000;\">REQUISITOS:</span></strong><br/>', 1, 'logo10.png', 'Ejecutivo Contable', '2019-05-09 00:00:00', 16900, 'ACTIVE', 2);
INSERT INTO `JOBS` (`id`, `description`, `details`, `featured`, `image`, `name`, `publicationDate`, `salary`, `status`, `categoryId`) VALUES (13, 'Estamos reclutando Ing. Electr?nico para dise?ar equipos y sistemas electr?nicos (circuitos), tanto anal?gicos como digitales y para dirigir la operaci?n de equipos y sistemas electr?nicos en las ?reas de comunicaci?n, control, instrumentaci?n y potencia.', '<p>&iexcl;&Uacute;nete a nuestro equipo de trabajo!<br/><br/>Nuestra visi&oacute;n es ser la inspiraci&oacute;n de la industria de la entrega inmediata, a trav&eacute;s del desarrollo del talento humano y la excelencia operacional.</p>\r\n<p>', 1, 'logo13.png', 'Ingeniero electr?nico', '2019-05-09 00:00:00', 23600, 'ACTIVE', 4);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `PROFILES`
--

INSERT INTO `PROFILES` (`id`, `name`) VALUES (1, 'MANAGER');
INSERT INTO `PROFILES` (`id`, `name`) VALUES (2, 'ADMINISTRATOR');
INSERT INTO `PROFILES` (`id`, `name`) VALUES (3, 'USER');

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `USERS`
--

--//INSERT INTO `USERS` (`id`, `email`, `name`, `password`, `registrationDate`, `status`, `username`) VALUES (1, 'egon@ghostbusters.com', 'Egon Spengler', '{noop}egon', '2022-05-04 12:47:07', 'ACTIVE', 'egon');
--//INSERT INTO `USERS` (`id`, `email`, `name`, `password`, `registrationDate`, `status`, `username`) VALUES (2, 'ray@ghostbusters.com', 'Ray Stantz', '{noop}ray', '2022-05-04 12:47:07', 'INACTIVE', 'ray');
--//INSERT INTO `USERS` (`id`, `email`, `name`, `password`, `registrationDate`, `status`, `username`) VALUES (3, 'venkman@ghostbusters.com', 'Peter Venkman', '{noop}venkman', '2022-05-04 12:47:07', 'ACTIVE', 'venkman');
INSERT INTO `USERS` (`id`, `email`, `name`, `password`, `registrationDate`, `status`, `username`) VALUES (1, 'egon@ghostbusters.com', 'Egon Spengler', '$2a$10$4v2B3UNYZuV.b5/FrlA88O1yV8dNPuZw39gdWwvCkrLHsx7MgWu0y', '2022-05-04 12:47:07', 'ACTIVE', 'egon');
INSERT INTO `USERS` (`id`, `email`, `name`, `password`, `registrationDate`, `status`, `username`) VALUES (2, 'ray@ghostbusters.com', 'Ray Stantz', '$2a$10$l.qb63rpGHHVEmmrrFO6LuvkqTUMoF4vwSiKfwYXmeZdLdyxjUVLe', '2022-05-04 12:47:07', 'INACTIVE', 'ray');
INSERT INTO `USERS` (`id`, `email`, `name`, `password`, `registrationDate`, `status`, `username`) VALUES (3, 'venkman@ghostbusters.com', 'Peter Venkman', '$2a$10$FZg7OWEJcts1ky16iQ./TObfSfDLQx9mlxefzeo7IPMQ7sNMKYG5u', '2022-05-04 12:47:07', 'ACTIVE', 'venkman');


-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `USER_PROFILES`
--

INSERT INTO `USER_PROFILES` (`userId`, `profileId`) VALUES (1, 1);
INSERT INTO `USER_PROFILES` (`userId`, `profileId`) VALUES (1, 2);
INSERT INTO `USER_PROFILES` (`userId`, `profileId`) VALUES (2, 2);
INSERT INTO `USER_PROFILES` (`userId`, `profileId`) VALUES (3, 1);