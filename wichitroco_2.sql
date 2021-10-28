-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-10-2021 a las 00:27:32
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `wichitroco`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tcliente`
--

CREATE TABLE `tcliente` (
  `nRutc` int(10) NOT NULL,
  `nIdCliente` int(10) NOT NULL,
  `cNombre` varchar(20) NOT NULL,
  `cDireccion` varchar(20) NOT NULL,
  `nTelefono` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tproducto`
--

CREATE TABLE `tproducto` (
  `nIdProducto` int(11) NOT NULL,
  `cNombreProducto` varchar(20) NOT NULL,
  `nPrecioActual` int(10) NOT NULL,
  `cNombreProveedor` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tproveedores`
--

CREATE TABLE `tproveedores` (
  `nIdProveedor` int(10) NOT NULL,
  `nRutp` int(10) NOT NULL,
  `cNombreProveedor` varchar(20) NOT NULL,
  `cDireccion` varchar(20) NOT NULL,
  `nTelefono` int(10) NOT NULL,
  `cPaginaWeb` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tusuario`
--

CREATE TABLE `tusuario` (
  `nIdUsuario` int(11) NOT NULL,
  `cNombreUsuario` varchar(20) NOT NULL,
  `nIdentificacion` int(15) NOT NULL,
  `cDireccion` varchar(20) NOT NULL,
  `cCorreo` varchar(100) NOT NULL,
  `cUsuario` varchar(30) NOT NULL,
  `cPass` varchar(30) NOT NULL,
  `cTipoUsuario` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tventa`
--

CREATE TABLE `tventa` (
  `nIdVenta` int(11) NOT NULL,
  `dFecha` date NOT NULL,
  `nIdCliente` int(11) NOT NULL,
  `cDescuento` varchar(80) NOT NULL,
  `nMontoFinal` int(10) NOT NULL,
  `nIdProducto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tcliente`
--
ALTER TABLE `tcliente`
  ADD PRIMARY KEY (`nIdCliente`);

--
-- Indices de la tabla `tproducto`
--
ALTER TABLE `tproducto`
  ADD PRIMARY KEY (`nIdProducto`),
  ADD KEY `cNombreProveedor` (`cNombreProveedor`);

--
-- Indices de la tabla `tproveedores`
--
ALTER TABLE `tproveedores`
  ADD PRIMARY KEY (`nIdProveedor`),
  ADD UNIQUE KEY `cNombreProveedor` (`cNombreProveedor`);

--
-- Indices de la tabla `tusuario`
--
ALTER TABLE `tusuario`
  ADD PRIMARY KEY (`nIdUsuario`);

--
-- Indices de la tabla `tventa`
--
ALTER TABLE `tventa`
  ADD PRIMARY KEY (`nIdVenta`),
  ADD UNIQUE KEY `nIdProducto` (`nIdProducto`),
  ADD KEY `nIdCliente` (`nIdCliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tcliente`
--
ALTER TABLE `tcliente`
  MODIFY `nIdCliente` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tproducto`
--
ALTER TABLE `tproducto`
  MODIFY `nIdProducto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tproveedores`
--
ALTER TABLE `tproveedores`
  MODIFY `nIdProveedor` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tusuario`
--
ALTER TABLE `tusuario`
  MODIFY `nIdUsuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tventa`
--
ALTER TABLE `tventa`
  MODIFY `nIdVenta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tproducto`
--
ALTER TABLE `tproducto`
  ADD CONSTRAINT `tproducto_ibfk_1` FOREIGN KEY (`cNombreProveedor`) REFERENCES `tproveedores` (`cNombreProveedor`),
  ADD CONSTRAINT `tproducto_ibfk_2` FOREIGN KEY (`nIdProducto`) REFERENCES `tventa` (`nIdProducto`);

--
-- Filtros para la tabla `tventa`
--
ALTER TABLE `tventa`
  ADD CONSTRAINT `tventa_ibfk_1` FOREIGN KEY (`nIdCliente`) REFERENCES `tcliente` (`nIdCliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
