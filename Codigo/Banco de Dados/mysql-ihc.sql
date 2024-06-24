CREATE DATABASE  IF NOT EXISTS `gerenciadorhelenafernandes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gerenciadorhelenafernandes`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: gerenciadorhelenafernandes
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id_administrador` bigint NOT NULL AUTO_INCREMENT,
  `email_admin` varchar(100) NOT NULL,
  `senha_admin` varchar(100) NOT NULL,
  PRIMARY KEY (`id_administrador`),
  UNIQUE KEY `UK_g0330m0dd5wcxcmv1jpvti0ax` (`email_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'admin','123');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno` (
  `id_aluno` bigint NOT NULL AUTO_INCREMENT,
  `alergia` varchar(255) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `cpf_aluno` varchar(100) DEFAULT NULL,
  `cpf_responsavel` varchar(100) DEFAULT NULL,
  `data_nascimento` varchar(100) DEFAULT NULL,
  `email_aluno` varchar(100) NOT NULL,
  `mais_informacoes` varchar(100) DEFAULT NULL,
  `nome_aluno` varchar(100) NOT NULL,
  `nome_responsavel` varchar(255) DEFAULT NULL,
  `numero_casa` varchar(100) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `senha_aluno` varchar(200) NOT NULL,
  `serie` varchar(50) DEFAULT NULL,
  `status_aluno` varchar(1) DEFAULT NULL,
  `telefone_responsavel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_aluno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'Nenhuma','Bairro 1','Cidade 1','12345678901','10987654321','2000-01-01','aluno1@example.com','Informações adicionais 1','Aluno 1','Responsável 1','100','Rua 1','senhaAluno1','1º Ano','1','111111111'),(2,'Nenhuma','Bairro 2','Cidade 2','23456789012','21098765432','2001-02-02','aluno2@example.com','Informações adicionais 2','Aluno 2','Responsável 2','200','Rua 2','senhaAluno2','2º Ano','0','222222222'),(3,'Nenhuma','Bairro 3','Cidade 3','34567890123','32109876543','2002-03-03','aluno3@example.com','Informações adicionais 3','Aluno 3','Responsável 3','300','Rua 3','senhaAluno3','3º Ano','2','333333333');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disciplina` (
  `id_disciplina` bigint NOT NULL AUTO_INCREMENT,
  `carga_horaria` bigint NOT NULL,
  `nome_disciplina` varchar(100) NOT NULL,
  PRIMARY KEY (`id_disciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` VALUES (1,200,'Matemática'),(2,180,'Português'),(3,160,'Ciências');
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota_aluno`
--

DROP TABLE IF EXISTS `nota_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota_aluno` (
  `id_nota` bigint NOT NULL,
  `id_aluno` bigint NOT NULL,
  KEY `FKhtaw9ep89t9ijo41vacg8bm8t` (`id_aluno`),
  KEY `FKsqo0kc8m8tm1rm6ku55ccxm3s` (`id_nota`),
  CONSTRAINT `FKhtaw9ep89t9ijo41vacg8bm8t` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `FKsqo0kc8m8tm1rm6ku55ccxm3s` FOREIGN KEY (`id_nota`) REFERENCES `notas` (`id_notas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota_aluno`
--

LOCK TABLES `nota_aluno` WRITE;
/*!40000 ALTER TABLE `nota_aluno` DISABLE KEYS */;
INSERT INTO `nota_aluno` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `nota_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota_disciplina`
--

DROP TABLE IF EXISTS `nota_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota_disciplina` (
  `id_nota` bigint NOT NULL,
  `id_disciplina` bigint NOT NULL,
  KEY `FKscvq7acp5q0o7tw6yo30ewqpe` (`id_disciplina`),
  KEY `FKm7agh6pnq48kss1haq5hmigel` (`id_nota`),
  CONSTRAINT `FKm7agh6pnq48kss1haq5hmigel` FOREIGN KEY (`id_nota`) REFERENCES `notas` (`id_notas`),
  CONSTRAINT `FKscvq7acp5q0o7tw6yo30ewqpe` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota_disciplina`
--

LOCK TABLES `nota_disciplina` WRITE;
/*!40000 ALTER TABLE `nota_disciplina` DISABLE KEYS */;
INSERT INTO `nota_disciplina` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `nota_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota_professor`
--

DROP TABLE IF EXISTS `nota_professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota_professor` (
  `id_nota` bigint NOT NULL,
  `id_professor` bigint NOT NULL,
  KEY `FKp9quit267vpdxpek49d16l96a` (`id_professor`),
  KEY `FKmv2461oavvv37g1iww2njnchu` (`id_nota`),
  CONSTRAINT `FKmv2461oavvv37g1iww2njnchu` FOREIGN KEY (`id_nota`) REFERENCES `notas` (`id_notas`),
  CONSTRAINT `FKp9quit267vpdxpek49d16l96a` FOREIGN KEY (`id_professor`) REFERENCES `professor` (`id_professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota_professor`
--

LOCK TABLES `nota_professor` WRITE;
/*!40000 ALTER TABLE `nota_professor` DISABLE KEYS */;
INSERT INTO `nota_professor` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `nota_professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota_turma`
--

DROP TABLE IF EXISTS `nota_turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota_turma` (
  `id_nota` bigint NOT NULL,
  `id_turma` bigint NOT NULL,
  KEY `FKt5gmjy7g7jeo5f1u78eeeha5t` (`id_turma`),
  KEY `FK4wrm82bbbtf7gcykuor7ti6fd` (`id_nota`),
  CONSTRAINT `FK4wrm82bbbtf7gcykuor7ti6fd` FOREIGN KEY (`id_nota`) REFERENCES `notas` (`id_notas`),
  CONSTRAINT `FKt5gmjy7g7jeo5f1u78eeeha5t` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota_turma`
--

LOCK TABLES `nota_turma` WRITE;
/*!40000 ALTER TABLE `nota_turma` DISABLE KEYS */;
INSERT INTO `nota_turma` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `nota_turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notas`
--

DROP TABLE IF EXISTS `notas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notas` (
  `id_notas` bigint NOT NULL AUTO_INCREMENT,
  `prova1` double DEFAULT NULL,
  `prova2` double DEFAULT NULL,
  `prova3` double DEFAULT NULL,
  `trabalho1` double DEFAULT NULL,
  `trabalho2` double DEFAULT NULL,
  `trabalho3` double DEFAULT NULL,
  PRIMARY KEY (`id_notas`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notas`
--

LOCK TABLES `notas` WRITE;
/*!40000 ALTER TABLE `notas` DISABLE KEYS */;
INSERT INTO `notas` VALUES (1,8.5,7.2,9.1,8,7.5,8.8),(2,6.4,7.8,6.5,7,6.2,7.4),(3,20,20,20,20,10,10);
/*!40000 ALTER TABLE `notas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `id_professor` bigint NOT NULL AUTO_INCREMENT,
  `cpf_professor` varchar(100) DEFAULT NULL,
  `data_nascimento` varchar(100) DEFAULT NULL,
  `email_professor` varchar(100) NOT NULL,
  `formacao_professor` varchar(100) DEFAULT NULL,
  `nome_professor` varchar(100) NOT NULL,
  `senha_professor` varchar(100) NOT NULL,
  `id_disciplina` bigint DEFAULT NULL,
  PRIMARY KEY (`id_professor`),
  KEY `FKf5ikcokyk24ma01pgae9ej80v` (`id_disciplina`),
  CONSTRAINT `FKf5ikcokyk24ma01pgae9ej80v` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'45678901234','1980-01-01','professor1@example.com','Matemática','Professor 1','senhaProfessor1',1),(2,'56789012345','1981-02-02','professor2@example.com','Português','Professor 2','senhaProfessor2',2),(3,'67890123456','1982-03-03','professor3@example.com','Ciências','Professor 3','senhaProfessor3',3);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma` (
  `id_turma` bigint NOT NULL AUTO_INCREMENT,
  `nome_turma` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_turma`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` VALUES (1,'Turma A'),(2,'Turma B'),(3,'Turma C');
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma_aluno`
--

DROP TABLE IF EXISTS `turma_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma_aluno` (
  `id_turma` bigint NOT NULL,
  `id_aluno` bigint NOT NULL,
  KEY `FKikbeigkk2qyfi1r5ppvn4h97e` (`id_aluno`),
  KEY `FK2yu37q0lxvkncx9wvl5cw4smm` (`id_turma`),
  CONSTRAINT `FK2yu37q0lxvkncx9wvl5cw4smm` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`),
  CONSTRAINT `FKikbeigkk2qyfi1r5ppvn4h97e` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma_aluno`
--

LOCK TABLES `turma_aluno` WRITE;
/*!40000 ALTER TABLE `turma_aluno` DISABLE KEYS */;
INSERT INTO `turma_aluno` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `turma_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma_disciplina`
--

DROP TABLE IF EXISTS `turma_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma_disciplina` (
  `id_turma` bigint NOT NULL,
  `id_disciplina` bigint NOT NULL,
  KEY `FK62cjp3haj8p3ipeutlroj08yb` (`id_disciplina`),
  KEY `FK4bd3jj429u7gonvpswn6n146b` (`id_turma`),
  CONSTRAINT `FK4bd3jj429u7gonvpswn6n146b` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`),
  CONSTRAINT `FK62cjp3haj8p3ipeutlroj08yb` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma_disciplina`
--

LOCK TABLES `turma_disciplina` WRITE;
/*!40000 ALTER TABLE `turma_disciplina` DISABLE KEYS */;
INSERT INTO `turma_disciplina` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `turma_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma_professor`
--

DROP TABLE IF EXISTS `turma_professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma_professor` (
  `id_turma` bigint NOT NULL,
  `id_professor` bigint NOT NULL,
  KEY `FK7ouq545xvb9aggfpk949sf95s` (`id_professor`),
  KEY `FK17sa4l2tjh7rvyi8d6comlugt` (`id_turma`),
  CONSTRAINT `FK17sa4l2tjh7rvyi8d6comlugt` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`),
  CONSTRAINT `FK7ouq545xvb9aggfpk949sf95s` FOREIGN KEY (`id_professor`) REFERENCES `professor` (`id_professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma_professor`
--

LOCK TABLES `turma_professor` WRITE;
/*!40000 ALTER TABLE `turma_professor` DISABLE KEYS */;
INSERT INTO `turma_professor` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `turma_professor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-24  9:01:58
