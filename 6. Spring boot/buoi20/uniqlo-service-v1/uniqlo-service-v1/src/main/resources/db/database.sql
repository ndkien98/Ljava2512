-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database:
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!50606 SET @OLD_INNODB_STATS_AUTO_RECALC=@@INNODB_STATS_AUTO_RECALC */;
/*!50606 SET GLOBAL INNODB_STATS_AUTO_RECALC=OFF */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `mysql`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `mysql` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mysql`;

--
-- Table structure for table `columns_priv`
--

DROP TABLE IF EXISTS `columns_priv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `columns_priv` (
                                `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                                `Db` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                `User` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                `Table_name` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                `Column_name` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                `Column_priv` set('Select','Insert','Update','References') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
                                PRIMARY KEY (`Host`,`User`,`Db`,`Table_name`,`Column_name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Column privileges';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component` (
                             `component_id` int unsigned NOT NULL AUTO_INCREMENT,
                             `component_group_id` int unsigned NOT NULL,
                             `component_urn` text NOT NULL,
                             PRIMARY KEY (`component_id`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='Components';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `db`
--

DROP TABLE IF EXISTS `db`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `db` (
                      `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                      `Db` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                      `User` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                      `Select_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Insert_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Update_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Delete_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Create_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Drop_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Grant_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `References_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Index_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Alter_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Create_tmp_table_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Lock_tables_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Create_view_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Show_view_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Create_routine_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Alter_routine_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Execute_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Event_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      `Trigger_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                      PRIMARY KEY (`Host`,`User`,`Db`),
                      KEY `User` (`User`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Database privileges';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `default_roles`
--

DROP TABLE IF EXISTS `default_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `default_roles` (
                                 `HOST` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                                 `USER` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                 `DEFAULT_ROLE_HOST` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '%',
                                 `DEFAULT_ROLE_USER` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                 PRIMARY KEY (`HOST`,`USER`,`DEFAULT_ROLE_HOST`,`DEFAULT_ROLE_USER`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Default roles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `engine_cost`
--

DROP TABLE IF EXISTS `engine_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `engine_cost` (
                               `engine_name` varchar(64) NOT NULL,
                               `device_type` int NOT NULL,
                               `cost_name` varchar(64) NOT NULL,
                               `cost_value` float DEFAULT NULL,
                               `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               `comment` varchar(1024) DEFAULT NULL,
                               `default_value` float GENERATED ALWAYS AS ((case `cost_name` when _utf8mb3'io_block_read_cost' then 1.0 when _utf8mb3'memory_block_read_cost' then 0.25 else NULL end)) VIRTUAL,
                               PRIMARY KEY (`cost_name`,`engine_name`,`device_type`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `func`
--

DROP TABLE IF EXISTS `func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `func` (
                        `name` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                        `ret` tinyint NOT NULL DEFAULT '0',
                        `dl` char(128) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                        `type` enum('function','aggregate') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        PRIMARY KEY (`name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='User defined functions';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `global_grants`
--

DROP TABLE IF EXISTS `global_grants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_grants` (
                                 `USER` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                 `HOST` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                                 `PRIV` char(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
                                 `WITH_GRANT_OPTION` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                                 PRIMARY KEY (`USER`,`HOST`,`PRIV`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Extended global grants';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gtid_executed`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `gtid_executed` (
                                               `source_uuid` char(36) NOT NULL COMMENT 'uuid of the source where the transaction was originally executed.',
    `interval_start` bigint NOT NULL COMMENT 'First number of interval.',
    `interval_end` bigint NOT NULL COMMENT 'Last number of interval.',
    PRIMARY KEY (`source_uuid`,`interval_start`)
    ) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `help_category`
--

DROP TABLE IF EXISTS `help_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `help_category` (
                                 `help_category_id` smallint unsigned NOT NULL,
                                 `name` char(64) NOT NULL,
                                 `parent_category_id` smallint unsigned DEFAULT NULL,
                                 `url` text NOT NULL,
                                 PRIMARY KEY (`help_category_id`),
                                 UNIQUE KEY `name` (`name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='help categories';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `help_keyword`
--

DROP TABLE IF EXISTS `help_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `help_keyword` (
                                `help_keyword_id` int unsigned NOT NULL,
                                `name` char(64) NOT NULL,
                                PRIMARY KEY (`help_keyword_id`),
                                UNIQUE KEY `name` (`name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='help keywords';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `help_relation`
--

DROP TABLE IF EXISTS `help_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `help_relation` (
                                 `help_topic_id` int unsigned NOT NULL,
                                 `help_keyword_id` int unsigned NOT NULL,
                                 PRIMARY KEY (`help_keyword_id`,`help_topic_id`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='keyword-topic relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `help_topic`
--

DROP TABLE IF EXISTS `help_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `help_topic` (
                              `help_topic_id` int unsigned NOT NULL,
                              `name` char(64) NOT NULL,
                              `help_category_id` smallint unsigned NOT NULL,
                              `description` text NOT NULL,
                              `example` text NOT NULL,
                              `url` text NOT NULL,
                              PRIMARY KEY (`help_topic_id`),
                              UNIQUE KEY `name` (`name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='help topics';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ndb_binlog_index`
--

DROP TABLE IF EXISTS `ndb_binlog_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ndb_binlog_index` (
                                    `Position` bigint unsigned NOT NULL,
                                    `File` varchar(255) NOT NULL,
                                    `epoch` bigint unsigned NOT NULL,
                                    `inserts` int unsigned NOT NULL,
                                    `updates` int unsigned NOT NULL,
                                    `deletes` int unsigned NOT NULL,
                                    `schemaops` int unsigned NOT NULL,
                                    `orig_server_id` int unsigned NOT NULL,
                                    `orig_epoch` bigint unsigned NOT NULL,
                                    `gci` int unsigned NOT NULL,
                                    `next_position` bigint unsigned NOT NULL,
                                    `next_file` varchar(255) NOT NULL,
                                    PRIMARY KEY (`epoch`,`orig_server_id`,`orig_epoch`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=latin1 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `password_history`
--

DROP TABLE IF EXISTS `password_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_history` (
                                    `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                                    `User` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                    `Password_timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                    `Password` text COLLATE utf8mb3_bin,
                                    PRIMARY KEY (`Host`,`User`,`Password_timestamp` DESC)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Password history for user accounts';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plugin`
--

DROP TABLE IF EXISTS `plugin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plugin` (
                          `name` varchar(64) NOT NULL DEFAULT '',
                          `dl` varchar(128) NOT NULL DEFAULT '',
                          PRIMARY KEY (`name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='MySQL plugins';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `procs_priv`
--

DROP TABLE IF EXISTS `procs_priv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procs_priv` (
                              `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                              `Db` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                              `User` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                              `Routine_name` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
                              `Routine_type` enum('FUNCTION','PROCEDURE') COLLATE utf8mb3_bin NOT NULL,
                              `Grantor` varchar(288) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                              `Proc_priv` set('Execute','Alter Routine','Grant') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
                              `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`Host`,`User`,`Db`,`Routine_name`,`Routine_type`),
                              KEY `Grantor` (`Grantor`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Procedure privileges';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proxies_priv`
--

DROP TABLE IF EXISTS `proxies_priv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proxies_priv` (
                                `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                                `User` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                `Proxied_host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                                `Proxied_user` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                `With_grant` tinyint(1) NOT NULL DEFAULT '0',
                                `Grantor` varchar(288) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                                `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`Host`,`User`,`Proxied_host`,`Proxied_user`),
                                KEY `Grantor` (`Grantor`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='User proxy privileges';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `replication_asynchronous_connection_failover`
--

DROP TABLE IF EXISTS `replication_asynchronous_connection_failover`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `replication_asynchronous_connection_failover` (
                                                                `Channel_name` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'The replication channel name that connects source and replica.',
                                                                `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL COMMENT 'The source hostname that the replica will attempt to switch over the replication connection to in case of a failure.',
                                                                `Port` int unsigned NOT NULL COMMENT 'The source port that the replica will attempt to switch over the replication connection to in case of a failure.',
                                                                `Network_namespace` char(64) NOT NULL COMMENT 'The source network namespace that the replica will attempt to switch over the replication connection to in case of a failure. If its value is empty, connections use the default (global) namespace.',
                                                                `Weight` tinyint unsigned NOT NULL COMMENT 'The order in which the replica shall try to switch the connection over to when there are failures. Weight can be set to a number between 1 and 100, where 100 is the highest weight and 1 the lowest.',
                                                                `Managed_name` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT 'The name of the group which this server belongs to.',
                                                                PRIMARY KEY (`Channel_name`,`Host`,`Port`,`Network_namespace`,`Managed_name`),
                                                                KEY `Channel_name` (`Channel_name`,`Managed_name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='The source configuration details';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `replication_asynchronous_connection_failover_managed`
--

DROP TABLE IF EXISTS `replication_asynchronous_connection_failover_managed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `replication_asynchronous_connection_failover_managed` (
                                                                        `Channel_name` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'The replication channel name that connects source and replica.',
                                                                        `Managed_name` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT 'The name of the source which needs to be managed.',
                                                                        `Managed_type` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT 'Determines the managed type.',
                                                                        `Configuration` json DEFAULT NULL COMMENT 'The data to help manage group. For Managed_type = GroupReplication, Configuration value should contain {"Primary_weight": 80, "Secondary_weight": 60}, so that it assigns weight=80 to PRIMARY of the group, and weight=60 for rest of the members in mysql.replication_asynchronous_connection_failover table.',
                                                                        PRIMARY KEY (`Channel_name`,`Managed_name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='The managed source configuration details';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `replication_group_configuration_version`
--

DROP TABLE IF EXISTS `replication_group_configuration_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `replication_group_configuration_version` (
                                                           `name` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL COMMENT 'The configuration name.',
                                                           `version` bigint unsigned NOT NULL COMMENT 'The version of the configuration name.',
                                                           PRIMARY KEY (`name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='The group configuration version.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `replication_group_member_actions`
--

DROP TABLE IF EXISTS `replication_group_member_actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `replication_group_member_actions` (
                                                    `name` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL COMMENT 'The action name.',
                                                    `event` char(64) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL COMMENT 'The event that will trigger the action.',
                                                    `enabled` tinyint(1) NOT NULL COMMENT 'Whether the action is enabled.',
                                                    `type` char(64) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL COMMENT 'The action type.',
                                                    `priority` tinyint unsigned NOT NULL COMMENT 'The order on which the action will be run, value between 1 and 100, lower values first.',
                                                    `error_handling` char(64) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL COMMENT 'On errors during the action will be handled: IGNORE, CRITICAL.',
                                                    PRIMARY KEY (`name`,`event`),
                                                    KEY `event` (`event`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='The member actions configuration.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_edges`
--

DROP TABLE IF EXISTS `role_edges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_edges` (
                              `FROM_HOST` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                              `FROM_USER` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                              `TO_HOST` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                              `TO_USER` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                              `WITH_ADMIN_OPTION` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                              PRIMARY KEY (`FROM_HOST`,`FROM_USER`,`TO_HOST`,`TO_USER`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Role hierarchy and role grants';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `server_cost`
--

DROP TABLE IF EXISTS `server_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_cost` (
                               `cost_name` varchar(64) NOT NULL,
                               `cost_value` float DEFAULT NULL,
                               `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               `comment` varchar(1024) DEFAULT NULL,
                               `default_value` float GENERATED ALWAYS AS ((case `cost_name` when _utf8mb3'disk_temptable_create_cost' then 20.0 when _utf8mb3'disk_temptable_row_cost' then 0.5 when _utf8mb3'key_compare_cost' then 0.05 when _utf8mb3'memory_temptable_create_cost' then 1.0 when _utf8mb3'memory_temptable_row_cost' then 0.1 when _utf8mb3'row_evaluate_cost' then 0.1 else NULL end)) VIRTUAL,
                               PRIMARY KEY (`cost_name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers`
--

DROP TABLE IF EXISTS `servers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servers` (
                           `Server_name` char(64) NOT NULL DEFAULT '',
                           `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                           `Db` char(64) NOT NULL DEFAULT '',
                           `Username` char(64) NOT NULL DEFAULT '',
                           `Password` char(64) NOT NULL DEFAULT '',
                           `Port` int NOT NULL DEFAULT '0',
                           `Socket` char(64) NOT NULL DEFAULT '',
                           `Wrapper` char(64) NOT NULL DEFAULT '',
                           `Owner` char(64) NOT NULL DEFAULT '',
                           PRIMARY KEY (`Server_name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='MySQL Foreign Servers table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `slave_master_info`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `slave_master_info` (
                                                   `Number_of_lines` int unsigned NOT NULL COMMENT 'Number of lines in the file.',
                                                   `Master_log_name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'The name of the master binary log currently being read from the master.',
                                                   `Master_log_pos` bigint unsigned NOT NULL COMMENT 'The master log position of the last read event.',
                                                   `Host` varchar(255) CHARACTER SET ascii COLLATE ascii_general_ci DEFAULT NULL COMMENT 'The host name of the source.',
    `User_name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The user name used to connect to the master.',
    `User_password` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The password used to connect to the master.',
    `Port` int unsigned NOT NULL COMMENT 'The network port used to connect to the master.',
    `Connect_retry` int unsigned NOT NULL COMMENT 'The period (in seconds) that the slave will wait before trying to reconnect to the master.',
    `Enabled_ssl` tinyint(1) NOT NULL COMMENT 'Indicates whether the server supports SSL connections.',
    `Ssl_ca` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The file used for the Certificate Authority (CA) certificate.',
    `Ssl_capath` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The path to the Certificate Authority (CA) certificates.',
    `Ssl_cert` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The name of the SSL certificate file.',
    `Ssl_cipher` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The name of the cipher in use for the SSL connection.',
    `Ssl_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The name of the SSL key file.',
    `Ssl_verify_server_cert` tinyint(1) NOT NULL COMMENT 'Whether to verify the server certificate.',
    `Heartbeat` float NOT NULL,
    `Bind` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'Displays which interface is employed when connecting to the MySQL server',
    `Ignored_server_ids` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The number of server IDs to be ignored, followed by the actual server IDs',
    `Uuid` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The master server uuid.',
    `Retry_count` bigint unsigned NOT NULL COMMENT 'Number of reconnect attempts, to the master, before giving up.',
    `Ssl_crl` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The file used for the Certificate Revocation List (CRL)',
    `Ssl_crlpath` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The path used for Certificate Revocation List (CRL) files',
    `Enabled_auto_position` tinyint(1) NOT NULL COMMENT 'Indicates whether GTIDs will be used to retrieve events from the master.',
    `Channel_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'The channel on which the replica is connected to a source. Used in Multisource Replication',
    `Tls_version` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'Tls version',
    `Public_key_path` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The file containing public key of master server.',
    `Get_public_key` tinyint(1) NOT NULL COMMENT 'Preference to get public key from master.',
    `Network_namespace` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'Network namespace used for communication with the master server.',
    `Master_compression_algorithm` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'Compression algorithm supported for data transfer between source and replica.',
    `Master_zstd_compression_level` int unsigned NOT NULL COMMENT 'Compression level associated with zstd compression algorithm.',
    `Tls_ciphersuites` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'Ciphersuites used for TLS 1.3 communication with the master server.',
    `Source_connection_auto_failover` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Indicates whether the channel connection failover is enabled.',
    `Gtid_only` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Indicates if this channel only uses GTIDs and does not persist positions.',
    PRIMARY KEY (`Channel_name`)
    ) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Master Information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `slave_relay_log_info`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `slave_relay_log_info` (
                                                      `Number_of_lines` int unsigned NOT NULL COMMENT 'Number of lines in the file or rows in the table. Used to version table definitions.',
                                                      `Relay_log_name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The name of the current relay log file.',
                                                      `Relay_log_pos` bigint unsigned DEFAULT NULL COMMENT 'The relay log position of the last executed event.',
                                                      `Master_log_name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'The name of the master binary log file from which the events in the relay log file were read.',
                                                      `Master_log_pos` bigint unsigned DEFAULT NULL COMMENT 'The master log position of the last executed event.',
                                                      `Sql_delay` int DEFAULT NULL COMMENT 'The number of seconds that the slave must lag behind the master.',
                                                      `Number_of_workers` int unsigned DEFAULT NULL,
                                                      `Id` int unsigned DEFAULT NULL COMMENT 'Internal Id that uniquely identifies this record.',
                                                      `Channel_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'The channel on which the replica is connected to a source. Used in Multisource Replication',
    `Privilege_checks_username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'Username part of PRIVILEGE_CHECKS_USER.',
    `Privilege_checks_hostname` varchar(255) CHARACTER SET ascii COLLATE ascii_general_ci DEFAULT NULL COMMENT 'Hostname part of PRIVILEGE_CHECKS_USER.',
    `Require_row_format` tinyint(1) NOT NULL COMMENT 'Indicates whether the channel shall only accept row based events.',
    `Require_table_primary_key_check` enum('STREAM','ON','OFF','GENERATE') NOT NULL DEFAULT 'STREAM' COMMENT 'Indicates what is the channel policy regarding tables without primary keys on create and alter table queries',
    `Assign_gtids_to_anonymous_transactions_type` enum('OFF','LOCAL','UUID') NOT NULL DEFAULT 'OFF' COMMENT 'Indicates whether the channel will generate a new GTID for anonymous transactions. OFF means that anonymous transactions will remain anonymous. LOCAL means that anonymous transactions will be assigned a newly generated GTID based on server_uuid. UUID indicates that anonymous transactions will be assigned a newly generated GTID based on Assign_gtids_to_anonymous_transactions_value',
    `Assign_gtids_to_anonymous_transactions_value` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'Indicates the UUID used while generating GTIDs for anonymous transactions',
    PRIMARY KEY (`Channel_name`)
    ) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Relay Log Information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `slave_worker_info`
--

DROP TABLE IF EXISTS `slave_worker_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slave_worker_info` (
                                     `Id` int unsigned NOT NULL,
                                     `Relay_log_name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
                                     `Relay_log_pos` bigint unsigned NOT NULL,
                                     `Master_log_name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
                                     `Master_log_pos` bigint unsigned NOT NULL,
                                     `Checkpoint_relay_log_name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
                                     `Checkpoint_relay_log_pos` bigint unsigned NOT NULL,
                                     `Checkpoint_master_log_name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
                                     `Checkpoint_master_log_pos` bigint unsigned NOT NULL,
                                     `Checkpoint_seqno` int unsigned NOT NULL,
                                     `Checkpoint_group_size` int unsigned NOT NULL,
                                     `Checkpoint_group_bitmap` blob NOT NULL,
                                     `Channel_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'The channel on which the replica is connected to a source. Used in Multisource Replication',
                                     PRIMARY KEY (`Channel_name`,`Id`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Worker Information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tables_priv`
--

DROP TABLE IF EXISTS `tables_priv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tables_priv` (
                               `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                               `Db` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                               `User` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                               `Table_name` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                               `Grantor` varchar(288) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                               `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               `Table_priv` set('Select','Insert','Update','Delete','Create','Drop','Grant','References','Index','Alter','Create View','Show view','Trigger') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
                               `Column_priv` set('Select','Insert','Update','References') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
                               PRIMARY KEY (`Host`,`User`,`Db`,`Table_name`),
                               KEY `Grantor` (`Grantor`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Table privileges';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `time_zone`
--

DROP TABLE IF EXISTS `time_zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_zone` (
                             `Time_zone_id` int unsigned NOT NULL AUTO_INCREMENT,
                             `Use_leap_seconds` enum('Y','N') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                             PRIMARY KEY (`Time_zone_id`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Time zones';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `time_zone_leap_second`
--

DROP TABLE IF EXISTS `time_zone_leap_second`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_zone_leap_second` (
                                         `Transition_time` bigint NOT NULL,
                                         `Correction` int NOT NULL,
                                         PRIMARY KEY (`Transition_time`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Leap seconds information for time zones';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `time_zone_name`
--

DROP TABLE IF EXISTS `time_zone_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_zone_name` (
                                  `Name` char(64) NOT NULL,
                                  `Time_zone_id` int unsigned NOT NULL,
                                  PRIMARY KEY (`Name`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Time zone names';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `time_zone_transition`
--

DROP TABLE IF EXISTS `time_zone_transition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_zone_transition` (
                                        `Time_zone_id` int unsigned NOT NULL,
                                        `Transition_time` bigint NOT NULL,
                                        `Transition_type_id` int unsigned NOT NULL,
                                        PRIMARY KEY (`Time_zone_id`,`Transition_time`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Time zone transitions';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `time_zone_transition_type`
--

DROP TABLE IF EXISTS `time_zone_transition_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_zone_transition_type` (
                                             `Time_zone_id` int unsigned NOT NULL,
                                             `Transition_type_id` int unsigned NOT NULL,
                                             `Offset` int NOT NULL DEFAULT '0',
                                             `Is_DST` tinyint unsigned NOT NULL DEFAULT '0',
                                             `Abbreviation` char(8) NOT NULL DEFAULT '',
                                             PRIMARY KEY (`Time_zone_id`,`Transition_type_id`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Time zone transition types';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `Host` char(255) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '',
                        `User` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
                        `Select_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Insert_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Update_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Delete_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Create_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Drop_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Reload_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Shutdown_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Process_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `File_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Grant_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `References_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Index_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Alter_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Show_db_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Super_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Create_tmp_table_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Lock_tables_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Execute_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Repl_slave_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Repl_client_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Create_view_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Show_view_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Create_routine_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Alter_routine_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Create_user_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Event_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Trigger_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Create_tablespace_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `ssl_type` enum('','ANY','X509','SPECIFIED') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
                        `ssl_cipher` blob NOT NULL,
                        `x509_issuer` blob NOT NULL,
                        `x509_subject` blob NOT NULL,
                        `max_questions` int unsigned NOT NULL DEFAULT '0',
                        `max_updates` int unsigned NOT NULL DEFAULT '0',
                        `max_connections` int unsigned NOT NULL DEFAULT '0',
                        `max_user_connections` int unsigned NOT NULL DEFAULT '0',
                        `plugin` char(64) COLLATE utf8mb3_bin NOT NULL DEFAULT 'caching_sha2_password',
                        `authentication_string` text COLLATE utf8mb3_bin,
                        `password_expired` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `password_last_changed` timestamp NULL DEFAULT NULL,
                        `password_lifetime` smallint unsigned DEFAULT NULL,
                        `account_locked` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Create_role_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Drop_role_priv` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'N',
                        `Password_reuse_history` smallint unsigned DEFAULT NULL,
                        `Password_reuse_time` smallint unsigned DEFAULT NULL,
                        `Password_require_current` enum('N','Y') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                        `User_attributes` json DEFAULT NULL,
                        PRIMARY KEY (`Host`,`User`)
) /*!50100 TABLESPACE `mysql` */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin STATS_PERSISTENT=0 ROW_FORMAT=DYNAMIC COMMENT='Users and global privileges';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'mysql'
--

--
-- Table structure for table `general_log`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `general_log` (
                                             `event_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `user_host` mediumtext NOT NULL,
    `thread_id` bigint unsigned NOT NULL,
    `server_id` int unsigned NOT NULL,
    `command_type` varchar(64) NOT NULL,
    `argument` mediumblob NOT NULL
    ) ENGINE=CSV DEFAULT CHARSET=utf8mb3 COMMENT='General log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `slow_log`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `slow_log` (
                                          `start_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `user_host` mediumtext NOT NULL,
    `query_time` time(6) NOT NULL,
    `lock_time` time(6) NOT NULL,
    `rows_sent` int NOT NULL,
    `rows_examined` int NOT NULL,
    `db` varchar(512) NOT NULL,
    `last_insert_id` int NOT NULL,
    `insert_id` int NOT NULL,
    `server_id` int unsigned NOT NULL,
    `sql_text` mediumblob NOT NULL,
    `thread_id` bigint unsigned NOT NULL
    ) ENGINE=CSV DEFAULT CHARSET=utf8mb3 COMMENT='Slow log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Current Database: `antino`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `antino` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `antino`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                        `full_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                        `age` int DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'antino'
--

--
-- Current Database: `db_exo`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_exo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_exo`;

--
-- Table structure for table `activity_logs`
--

DROP TABLE IF EXISTS `activity_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_logs` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `created_by` varchar(255) DEFAULT NULL,
                                 `created_date` datetime(6) DEFAULT NULL,
                                 `deleted` tinyint(1) DEFAULT '0',
                                 `modified_by` varchar(255) DEFAULT NULL,
                                 `modified_date` datetime(6) DEFAULT NULL,
                                 `action` varchar(255) DEFAULT NULL,
                                 `code` varchar(50) NOT NULL,
                                 `description` text,
                                 `ip_address` varchar(50) DEFAULT NULL,
                                 `user_action` varchar(50) DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `UKcy1knxkwavnc1ha5sn5po9vr0` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `blog_categories`
--

DROP TABLE IF EXISTS `blog_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_categories` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `created_by` varchar(255) DEFAULT NULL,
                                   `created_date` datetime(6) DEFAULT NULL,
                                   `deleted` tinyint(1) DEFAULT '0',
                                   `modified_by` varchar(255) DEFAULT NULL,
                                   `modified_date` datetime(6) DEFAULT NULL,
                                   `code` varchar(50) NOT NULL,
                                   `description` text,
                                   `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                   `slug` varchar(100) DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `UKcdkmg5hqwtcoah8sw17c8plqa` (`code`),
                                   UNIQUE KEY `UK90f22w2fqsbqkn1fbhqrysqb7` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `blogs`
--

DROP TABLE IF EXISTS `blogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogs` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` tinyint(1) DEFAULT '0',
                         `modified_by` varchar(255) DEFAULT NULL,
                         `modified_date` datetime(6) DEFAULT NULL,
                         `author` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                         `code` varchar(50) NOT NULL,
                         `description` longtext,
                         `image_path` varchar(255) DEFAULT NULL,
                         `short_description` text,
                         `slug` varchar(255) DEFAULT NULL,
                         `status` varchar(20) DEFAULT NULL,
                         `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                         `category_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UKtpmipxqa6jv3s7c5f9l38n3k0` (`code`),
                         UNIQUE KEY `UKpl5w1yw2c5lligoeb9a393fr3` (`slug`),
                         KEY `FKf2ci0ovwtuw6nsmcbvl20ucxv` (`category_id`),
                         CONSTRAINT `FKf2ci0ovwtuw6nsmcbvl20ucxv` FOREIGN KEY (`category_id`) REFERENCES `blog_categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_info`
--

DROP TABLE IF EXISTS `company_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_info` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `created_by` varchar(255) DEFAULT NULL,
                                `created_date` datetime(6) DEFAULT NULL,
                                `deleted` tinyint(1) DEFAULT '0',
                                `modified_by` varchar(255) DEFAULT NULL,
                                `modified_date` datetime(6) DEFAULT NULL,
                                `address` varchar(255) DEFAULT NULL,
                                `code` varchar(50) NOT NULL,
                                `contact_email` varchar(255) DEFAULT NULL,
                                `contact_phone` varchar(50) DEFAULT NULL,
                                `core_values` text,
                                `description` text,
                                `established_year` int DEFAULT NULL,
                                `mission` text,
                                `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                `vision` text,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `UKdjxojg852dl0k7p7m6ltt7t2q` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `config_content`
--

DROP TABLE IF EXISTS `config_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_content` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `created_by` varchar(255) DEFAULT NULL,
                                  `created_date` datetime(6) DEFAULT NULL,
                                  `deleted` tinyint(1) DEFAULT '0',
                                  `modified_by` varchar(255) DEFAULT NULL,
                                  `modified_date` datetime(6) DEFAULT NULL,
                                  `code` varchar(50) NOT NULL,
                                  `description` text,
                                  `title` longtext,
                                  `type` varchar(50) DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `UKfpcxn729jjy9umqlr2yvem2iy` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_by` varchar(255) DEFAULT NULL,
                            `created_date` datetime(6) DEFAULT NULL,
                            `deleted` tinyint(1) DEFAULT '0',
                            `modified_by` varchar(255) DEFAULT NULL,
                            `modified_date` datetime(6) DEFAULT NULL,
                            `code` varchar(50) NOT NULL,
                            `company` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `message` text,
                            `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `phone` varchar(50) DEFAULT NULL,
                            `status` varchar(20) DEFAULT NULL,
                            `subject` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `file_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UKb42wa7jxj2x5sb773fkrq7rsh` (`code`),
                            KEY `FKkamkosx5juqm5fuplkp6kqxcc` (`file_id`),
                            CONSTRAINT `FKkamkosx5juqm5fuplkp6kqxcc` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `content_blocks`
--

DROP TABLE IF EXISTS `content_blocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `content_blocks` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `created_by` varchar(255) DEFAULT NULL,
                                  `created_date` datetime(6) DEFAULT NULL,
                                  `deleted` tinyint(1) DEFAULT '0',
                                  `modified_by` varchar(255) DEFAULT NULL,
                                  `modified_date` datetime(6) DEFAULT NULL,
                                  `block_key` varchar(100) DEFAULT NULL,
                                  `code` varchar(50) NOT NULL,
                                  `content` text,
                                  `media_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `UKharfl72i3ykaxqvs0icds2ty2` (`code`),
                                  UNIQUE KEY `UK9tayvo5tnixk3metjf5dxf2q` (`block_key`),
                                  KEY `FKdhcdav6ltxp9ywsodo857kqfd` (`media_id`),
                                  CONSTRAINT `FKdhcdav6ltxp9ywsodo857kqfd` FOREIGN KEY (`media_id`) REFERENCES `file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `experiences`
--

DROP TABLE IF EXISTS `experiences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experiences` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `created_by` varchar(255) DEFAULT NULL,
                               `created_date` datetime(6) DEFAULT NULL,
                               `deleted` tinyint(1) DEFAULT '0',
                               `modified_by` varchar(255) DEFAULT NULL,
                               `modified_date` datetime(6) DEFAULT NULL,
                               `client` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                               `code` varchar(50) NOT NULL,
                               `description` text,
                               `image_path` varchar(255) DEFAULT NULL,
                               `order_position` int DEFAULT NULL,
                               `project_date` date DEFAULT NULL,
                               `project_url` varchar(255) DEFAULT NULL,
                               `pricing_plan_id` bigint DEFAULT NULL,
                               `project_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UK69vx9tml2gem16tm9v9kqnl1n` (`code`),
                               KEY `FKmmemmtywegsrt8tecgovcmw2v` (`pricing_plan_id`),
                               KEY `FKi5i8l3c11v0q9f03rkgaguok3` (`project_id`),
                               CONSTRAINT `FKi5i8l3c11v0q9f03rkgaguok3` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
                               CONSTRAINT `FKmmemmtywegsrt8tecgovcmw2v` FOREIGN KEY (`pricing_plan_id`) REFERENCES `pricing_plans` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `faqs`
--

DROP TABLE IF EXISTS `faqs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faqs` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `created_by` varchar(255) DEFAULT NULL,
                        `created_date` datetime(6) DEFAULT NULL,
                        `deleted` tinyint(1) DEFAULT '0',
                        `modified_by` varchar(255) DEFAULT NULL,
                        `modified_date` datetime(6) DEFAULT NULL,
                        `answer` longtext,
                        `code` varchar(50) NOT NULL,
                        `question` longtext,
                        `status` varchar(20) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UKi3db1kdnt14hbtc9uuvxmj43` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `created_by` varchar(255) DEFAULT NULL,
                        `created_date` datetime(6) DEFAULT NULL,
                        `deleted` tinyint(1) DEFAULT '0',
                        `modified_by` varchar(255) DEFAULT NULL,
                        `modified_date` datetime(6) DEFAULT NULL,
                        `alt_text` varchar(255) DEFAULT NULL,
                        `code` varchar(50) NOT NULL,
                        `description` text,
                        `name` varchar(255) DEFAULT NULL,
                        `path` varchar(255) DEFAULT NULL,
                        `type` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UKn6lljb31ruvbkxhn8kbhl3yth` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inquiry_about`
--

DROP TABLE IF EXISTS `inquiry_about`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_about` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `created_by` varchar(255) DEFAULT NULL,
                                 `created_date` datetime(6) DEFAULT NULL,
                                 `deleted` tinyint(1) DEFAULT '0',
                                 `modified_by` varchar(255) DEFAULT NULL,
                                 `modified_date` datetime(6) DEFAULT NULL,
                                 `code` varchar(50) NOT NULL,
                                 `inquiry_details` text,
                                 `contact_id` bigint DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `UKsrhwuucmttx2yacvxqyevsvud` (`code`),
                                 KEY `FKkg5tf69semt3ld0k7hrthbvct` (`contact_id`),
                                 CONSTRAINT `FKkg5tf69semt3ld0k7hrthbvct` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `leadership_team`
--

DROP TABLE IF EXISTS `leadership_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leadership_team` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `created_by` varchar(255) DEFAULT NULL,
                                   `created_date` datetime(6) DEFAULT NULL,
                                   `deleted` tinyint(1) DEFAULT '0',
                                   `modified_by` varchar(255) DEFAULT NULL,
                                   `modified_date` datetime(6) DEFAULT NULL,
                                   `biography` text,
                                   `code` varchar(50) NOT NULL,
                                   `email` varchar(255) DEFAULT NULL,
                                   `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                   `order_position` int DEFAULT NULL,
                                   `phone` varchar(50) DEFAULT NULL,
                                   `photo_path` varchar(255) DEFAULT NULL,
                                   `position` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `UK10s65sudctpb2oiig8malsp83` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `navigation_menus`
--

DROP TABLE IF EXISTS `navigation_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `navigation_menus` (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `created_by` varchar(255) DEFAULT NULL,
                                    `created_date` datetime(6) DEFAULT NULL,
                                    `deleted` tinyint(1) DEFAULT '0',
                                    `modified_by` varchar(255) DEFAULT NULL,
                                    `modified_date` datetime(6) DEFAULT NULL,
                                    `code` varchar(50) NOT NULL,
                                    `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                    `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                    `url` varchar(255) DEFAULT NULL,
                                    `parent_id` bigint DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `UKmusptv0p96aedd3t79m6iv2nw` (`code`),
                                    KEY `FKgx8yt8mhb6qcuj1qu0awe5xaw` (`parent_id`),
                                    CONSTRAINT `FKgx8yt8mhb6qcuj1qu0awe5xaw` FOREIGN KEY (`parent_id`) REFERENCES `navigation_menus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pages` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` tinyint(1) DEFAULT '0',
                         `modified_by` varchar(255) DEFAULT NULL,
                         `modified_date` datetime(6) DEFAULT NULL,
                         `code` varchar(50) NOT NULL,
                         `description` text,
                         `name` text,
                         `status` varchar(20) DEFAULT NULL,
                         `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK9gwvntu0bn7ayftfarjbx7v36` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pricing_plans`
--

DROP TABLE IF EXISTS `pricing_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricing_plans` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `created_by` varchar(255) DEFAULT NULL,
                                 `created_date` datetime(6) DEFAULT NULL,
                                 `deleted` tinyint(1) DEFAULT '0',
                                 `modified_by` varchar(255) DEFAULT NULL,
                                 `modified_date` datetime(6) DEFAULT NULL,
                                 `code` varchar(50) NOT NULL,
                                 `currency` varchar(10) DEFAULT NULL,
                                 `description` text,
                                 `duration` varchar(100) DEFAULT NULL,
                                 `features` text,
                                 `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                 `price` float DEFAULT NULL,
                                 `status` varchar(20) DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `UK2qkegvovgfaconwr2e1yyelnu` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project_image`
--

DROP TABLE IF EXISTS `project_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_image` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `created_by` varchar(255) DEFAULT NULL,
                                 `created_date` datetime(6) DEFAULT NULL,
                                 `deleted` tinyint(1) DEFAULT '0',
                                 `modified_by` varchar(255) DEFAULT NULL,
                                 `modified_date` datetime(6) DEFAULT NULL,
                                 `code` varchar(50) NOT NULL,
                                 `type` varchar(50) DEFAULT NULL,
                                 `file_id` bigint DEFAULT NULL,
                                 `project_id` bigint DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `UKnw67961jg9m7uo1nx3wt3pcs8` (`code`),
                                 KEY `FK8u59v9jd1f7714wjmhduk33ft` (`file_id`),
                                 KEY `FK35wkm2g8cshlquepbi5p273qe` (`project_id`),
                                 CONSTRAINT `FK35wkm2g8cshlquepbi5p273qe` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
                                 CONSTRAINT `FK8u59v9jd1f7714wjmhduk33ft` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_by` varchar(255) DEFAULT NULL,
                            `created_date` datetime(6) DEFAULT NULL,
                            `deleted` tinyint(1) DEFAULT '0',
                            `modified_by` varchar(255) DEFAULT NULL,
                            `modified_date` datetime(6) DEFAULT NULL,
                            `challenges` text,
                            `client` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `code` varchar(50) NOT NULL,
                            `description` text,
                            `icon_client_path` varchar(255) DEFAULT NULL,
                            `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `order_position` int DEFAULT NULL,
                            `outcome` text,
                            `project_date` date DEFAULT NULL,
                            `slug` varchar(255) DEFAULT NULL,
                            `solution` text,
                            `status` varchar(20) DEFAULT NULL,
                            `summary` text,
                            `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `website_url` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UKclujw4wu21d33ssgde022aymk` (`code`),
                            UNIQUE KEY `UKcxqk67qijm09gpgig8a997mb0` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recruitment`
--

DROP TABLE IF EXISTS `recruitment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruitment` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `created_by` varchar(255) DEFAULT NULL,
                               `created_date` datetime(6) DEFAULT NULL,
                               `deleted` tinyint(1) DEFAULT '0',
                               `modified_by` varchar(255) DEFAULT NULL,
                               `modified_date` datetime(6) DEFAULT NULL,
                               `block` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                               `code` varchar(50) NOT NULL,
                               `contact` text,
                               `deadline` date DEFAULT NULL,
                               `description` text,
                               `job_type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                               `level` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                               `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                               `name` varchar(255) DEFAULT NULL,
                               `quantity` int DEFAULT NULL,
                               `remuneration` text,
                               `requirements` text,
                               `salary` float DEFAULT NULL,
                               `status` varchar(50) DEFAULT NULL,
                               `title` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UKlerpq9b7jjowbvyvwbjs0cl6l` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_categories`
--

DROP TABLE IF EXISTS `service_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_categories` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `created_by` varchar(255) DEFAULT NULL,
                                      `created_date` datetime(6) DEFAULT NULL,
                                      `deleted` tinyint(1) DEFAULT '0',
                                      `modified_by` varchar(255) DEFAULT NULL,
                                      `modified_date` datetime(6) DEFAULT NULL,
                                      `code` varchar(50) NOT NULL,
                                      `description` text,
                                      `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                      `slug` varchar(255) DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `UKof410cn7nuk3pa4bfy0c4c16i` (`code`),
                                      UNIQUE KEY `UKnafvghrttkaaxv4o0gaw5hgg9` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_faq`
--

DROP TABLE IF EXISTS `service_faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_faq` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `created_by` varchar(255) DEFAULT NULL,
                               `created_date` datetime(6) DEFAULT NULL,
                               `deleted` tinyint(1) DEFAULT '0',
                               `modified_by` varchar(255) DEFAULT NULL,
                               `modified_date` datetime(6) DEFAULT NULL,
                               `faq_id` bigint DEFAULT NULL,
                               `service_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FKf2yast3up0aslr74c4gifqt1h` (`faq_id`),
                               KEY `FKr0ijwfps13rgbt9ypfvj9y4w2` (`service_id`),
                               CONSTRAINT `FKf2yast3up0aslr74c4gifqt1h` FOREIGN KEY (`faq_id`) REFERENCES `faqs` (`id`),
                               CONSTRAINT `FKr0ijwfps13rgbt9ypfvj9y4w2` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_project`
--

DROP TABLE IF EXISTS `service_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_project` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `created_by` varchar(255) DEFAULT NULL,
                                   `created_date` datetime(6) DEFAULT NULL,
                                   `deleted` tinyint(1) DEFAULT '0',
                                   `modified_by` varchar(255) DEFAULT NULL,
                                   `modified_date` datetime(6) DEFAULT NULL,
                                   `project_id` bigint DEFAULT NULL,
                                   `service_id` bigint DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `FKn71rjorr36ni27gm6w09w9rom` (`project_id`),
                                   KEY `FKcgdy650rx2qjps0ivs70vbwca` (`service_id`),
                                   CONSTRAINT `FKcgdy650rx2qjps0ivs70vbwca` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`),
                                   CONSTRAINT `FKn71rjorr36ni27gm6w09w9rom` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_tech`
--

DROP TABLE IF EXISTS `service_tech`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_tech` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `created_by` varchar(255) DEFAULT NULL,
                                `created_date` datetime(6) DEFAULT NULL,
                                `deleted` tinyint(1) DEFAULT '0',
                                `modified_by` varchar(255) DEFAULT NULL,
                                `modified_date` datetime(6) DEFAULT NULL,
                                `code` varchar(50) NOT NULL,
                                `file_id` bigint DEFAULT NULL,
                                `service_id` bigint DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `UK9hab71tuk9gyk9p07m8hd7jc7` (`code`),
                                KEY `FKbvv0ya84p2hkhp54nnoyiwu4t` (`file_id`),
                                KEY `FKg8jt5qt44wd9didcchm6ibji8` (`service_id`),
                                CONSTRAINT `FKbvv0ya84p2hkhp54nnoyiwu4t` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`),
                                CONSTRAINT `FKg8jt5qt44wd9didcchm6ibji8` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_by` varchar(255) DEFAULT NULL,
                            `created_date` datetime(6) DEFAULT NULL,
                            `deleted` tinyint(1) DEFAULT '0',
                            `modified_by` varchar(255) DEFAULT NULL,
                            `modified_date` datetime(6) DEFAULT NULL,
                            `avatar_path` varchar(255) DEFAULT NULL,
                            `code` varchar(50) NOT NULL,
                            `description` longtext,
                            `icon_path` varchar(255) DEFAULT NULL,
                            `slug` varchar(255) DEFAULT NULL,
                            `status` varchar(20) DEFAULT NULL,
                            `title` varchar(255) DEFAULT NULL,
                            `category_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK3ff0vue74scb6a5dbic1yj2ip` (`code`),
                            UNIQUE KEY `UKgnenm2itqjotnod9yfan1e9in` (`slug`),
                            KEY `FKfffr4emayc2n4uq3yv618d9j0` (`category_id`),
                            CONSTRAINT `FKfffr4emayc2n4uq3yv618d9j0` FOREIGN KEY (`category_id`) REFERENCES `service_categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `services_we_provide`
--

DROP TABLE IF EXISTS `services_we_provide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services_we_provide` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `created_by` varchar(255) DEFAULT NULL,
                                       `created_date` datetime(6) DEFAULT NULL,
                                       `deleted` tinyint(1) DEFAULT '0',
                                       `modified_by` varchar(255) DEFAULT NULL,
                                       `modified_date` datetime(6) DEFAULT NULL,
                                       `value` varchar(255) DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `what_you_get`
--

DROP TABLE IF EXISTS `what_you_get`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `what_you_get` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `created_by` varchar(255) DEFAULT NULL,
                                `created_date` datetime(6) DEFAULT NULL,
                                `deleted` tinyint(1) DEFAULT '0',
                                `modified_by` varchar(255) DEFAULT NULL,
                                `modified_date` datetime(6) DEFAULT NULL,
                                `name` varchar(255) DEFAULT NULL,
                                `value` text,
                                `file_id` bigint DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `FKj21f4h0fhdyqutnbxosr7d1lq` (`file_id`),
                                CONSTRAINT `FKj21f4h0fhdyqutnbxosr7d1lq` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'db_exo'
--

--
-- Current Database: `insurance_claim`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `insurance_claim` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `insurance_claim`;

--
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `amount` double DEFAULT NULL,
                         `claim_date` date DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `status_id` bigint DEFAULT NULL,
                         `customer_id` bigint DEFAULT NULL,
                         `product_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKfkypqix42u6j9uj5a1kq2428n` (`status_id`),
                         KEY `FKlmon26is6b9p2wipdl88yn68y` (`customer_id`),
                         KEY `FK6k25jaynajdvvcmst08cphrwl` (`product_id`),
                         CONSTRAINT `FK6k25jaynajdvvcmst08cphrwl` FOREIGN KEY (`product_id`) REFERENCES `insurance_product` (`id`),
                         CONSTRAINT `FKfkypqix42u6j9uj5a1kq2428n` FOREIGN KEY (`status_id`) REFERENCES `claim_status` (`id`),
                         CONSTRAINT `FKlmon26is6b9p2wipdl88yn68y` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `claim_document`
--

DROP TABLE IF EXISTS `claim_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim_document` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `created_by` varchar(255) DEFAULT NULL,
                                  `created_date` datetime(6) DEFAULT NULL,
                                  `deleted` bit(1) DEFAULT NULL,
                                  `last_modified_by` varchar(255) DEFAULT NULL,
                                  `last_modified_date` datetime(6) DEFAULT NULL,
                                  `document_name` varchar(255) DEFAULT NULL,
                                  `document_type` varchar(255) DEFAULT NULL,
                                  `file_path` varchar(255) DEFAULT NULL,
                                  `upload_date` date DEFAULT NULL,
                                  `claim_id` bigint DEFAULT NULL,
                                  `update_date` date DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FK482n7wmh1ry39ewmqmailkpb8` (`claim_id`),
                                  CONSTRAINT `FK482n7wmh1ry39ewmqmailkpb8` FOREIGN KEY (`claim_id`) REFERENCES `claim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `claim_status`
--

DROP TABLE IF EXISTS `claim_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim_status` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `created_by` varchar(255) DEFAULT NULL,
                                `created_date` datetime(6) DEFAULT NULL,
                                `deleted` bit(1) DEFAULT NULL,
                                `last_modified_by` varchar(255) DEFAULT NULL,
                                `last_modified_date` datetime(6) DEFAULT NULL,
                                `code` varchar(255) DEFAULT NULL,
                                `description` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_by` varchar(255) DEFAULT NULL,
                            `created_date` datetime(6) DEFAULT NULL,
                            `deleted` bit(1) DEFAULT NULL,
                            `last_modified_by` varchar(255) DEFAULT NULL,
                            `last_modified_date` datetime(6) DEFAULT NULL,
                            `address` varchar(255) DEFAULT NULL,
                            `bank_name` varchar(255) DEFAULT NULL,
                            `bank_number` varchar(255) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            `phone_number` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `insurance_product`
--

DROP TABLE IF EXISTS `insurance_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_product` (
                                     `id` bigint NOT NULL AUTO_INCREMENT,
                                     `created_by` varchar(255) DEFAULT NULL,
                                     `created_date` datetime(6) DEFAULT NULL,
                                     `deleted` bit(1) DEFAULT NULL,
                                     `last_modified_by` varchar(255) DEFAULT NULL,
                                     `last_modified_date` datetime(6) DEFAULT NULL,
                                     `coverage` varchar(255) DEFAULT NULL,
                                     `description` varchar(255) DEFAULT NULL,
                                     `name` varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` bigint NOT NULL,
                              `role_id` bigint NOT NULL,
                              PRIMARY KEY (`user_id`,`role_id`),
                              KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
                              CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                              CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `address` varchar(255) DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `first_name` varchar(255) DEFAULT NULL,
                         `last_name` varchar(255) DEFAULT NULL,
                         `mime_type` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `path_avatar` varchar(255) DEFAULT NULL,
                         `phone` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL,
                         `age` int DEFAULT NULL,
                         `full_name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'insurance_claim'
--

--
-- Current Database: `insuranceclaim`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `insuranceclaim` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `insuranceclaim`;

--
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `amount` double DEFAULT NULL,
                         `claim_date` date DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `status_id` bigint DEFAULT NULL,
                         `customer_id` bigint DEFAULT NULL,
                         `product_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKfkypqix42u6j9uj5a1kq2428n` (`status_id`),
                         KEY `FKlmon26is6b9p2wipdl88yn68y` (`customer_id`),
                         KEY `FK6k25jaynajdvvcmst08cphrwl` (`product_id`),
                         CONSTRAINT `FK6k25jaynajdvvcmst08cphrwl` FOREIGN KEY (`product_id`) REFERENCES `insurance_product` (`id`),
                         CONSTRAINT `FKfkypqix42u6j9uj5a1kq2428n` FOREIGN KEY (`status_id`) REFERENCES `claim_status` (`id`),
                         CONSTRAINT `FKlmon26is6b9p2wipdl88yn68y` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `claim_document`
--

DROP TABLE IF EXISTS `claim_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim_document` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `created_by` varchar(255) DEFAULT NULL,
                                  `created_date` datetime(6) DEFAULT NULL,
                                  `deleted` bit(1) DEFAULT NULL,
                                  `last_modified_by` varchar(255) DEFAULT NULL,
                                  `last_modified_date` datetime(6) DEFAULT NULL,
                                  `document_name` varchar(255) DEFAULT NULL,
                                  `document_type` varchar(255) DEFAULT NULL,
                                  `file_path` varchar(255) DEFAULT NULL,
                                  `upload_date` date DEFAULT NULL,
                                  `claim_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FK482n7wmh1ry39ewmqmailkpb8` (`claim_id`),
                                  CONSTRAINT `FK482n7wmh1ry39ewmqmailkpb8` FOREIGN KEY (`claim_id`) REFERENCES `claim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `claim_status`
--

DROP TABLE IF EXISTS `claim_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim_status` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `created_by` varchar(255) DEFAULT NULL,
                                `created_date` datetime(6) DEFAULT NULL,
                                `deleted` bit(1) DEFAULT NULL,
                                `last_modified_by` varchar(255) DEFAULT NULL,
                                `last_modified_date` datetime(6) DEFAULT NULL,
                                `code` varchar(255) DEFAULT NULL,
                                `description` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_by` varchar(255) DEFAULT NULL,
                            `created_date` datetime(6) DEFAULT NULL,
                            `deleted` bit(1) DEFAULT NULL,
                            `last_modified_by` varchar(255) DEFAULT NULL,
                            `last_modified_date` datetime(6) DEFAULT NULL,
                            `address` varchar(255) DEFAULT NULL,
                            `bank_name` varchar(255) DEFAULT NULL,
                            `bank_number` varchar(255) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            `phone_number` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `insurance_product`
--

DROP TABLE IF EXISTS `insurance_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_product` (
                                     `id` bigint NOT NULL AUTO_INCREMENT,
                                     `created_by` varchar(255) DEFAULT NULL,
                                     `created_date` datetime(6) DEFAULT NULL,
                                     `deleted` bit(1) DEFAULT NULL,
                                     `last_modified_by` varchar(255) DEFAULT NULL,
                                     `last_modified_date` datetime(6) DEFAULT NULL,
                                     `coverage` varchar(255) DEFAULT NULL,
                                     `description` varchar(255) DEFAULT NULL,
                                     `name` varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` bigint NOT NULL,
                              `role_id` bigint NOT NULL,
                              PRIMARY KEY (`user_id`,`role_id`),
                              KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
                              CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                              CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `address` varchar(255) DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `first_name` varchar(255) DEFAULT NULL,
                         `last_name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `phone` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL,
                         `mime_type` varchar(255) DEFAULT NULL,
                         `path_avatar` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'insuranceclaim'
--

--
-- Current Database: `insuranceclaim2`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `insuranceclaim2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `insuranceclaim2`;

--
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `amount` double DEFAULT NULL,
                         `claim_date` date DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `status_id` bigint DEFAULT NULL,
                         `customer_id` bigint DEFAULT NULL,
                         `product_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKfkypqix42u6j9uj5a1kq2428n` (`status_id`),
                         KEY `FKlmon26is6b9p2wipdl88yn68y` (`customer_id`),
                         KEY `FK6k25jaynajdvvcmst08cphrwl` (`product_id`),
                         CONSTRAINT `FK6k25jaynajdvvcmst08cphrwl` FOREIGN KEY (`product_id`) REFERENCES `insurance_product` (`id`),
                         CONSTRAINT `FKfkypqix42u6j9uj5a1kq2428n` FOREIGN KEY (`status_id`) REFERENCES `claim_status` (`id`),
                         CONSTRAINT `FKlmon26is6b9p2wipdl88yn68y` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `claim_document`
--

DROP TABLE IF EXISTS `claim_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim_document` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `created_by` varchar(255) DEFAULT NULL,
                                  `created_date` datetime(6) DEFAULT NULL,
                                  `deleted` bit(1) DEFAULT NULL,
                                  `last_modified_by` varchar(255) DEFAULT NULL,
                                  `last_modified_date` datetime(6) DEFAULT NULL,
                                  `document_name` varchar(255) DEFAULT NULL,
                                  `document_type` varchar(255) DEFAULT NULL,
                                  `file_path` varchar(255) DEFAULT NULL,
                                  `upload_date` date DEFAULT NULL,
                                  `claim_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FK482n7wmh1ry39ewmqmailkpb8` (`claim_id`),
                                  CONSTRAINT `FK482n7wmh1ry39ewmqmailkpb8` FOREIGN KEY (`claim_id`) REFERENCES `claim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `claim_status`
--

DROP TABLE IF EXISTS `claim_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim_status` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `created_by` varchar(255) DEFAULT NULL,
                                `created_date` datetime(6) DEFAULT NULL,
                                `deleted` bit(1) DEFAULT NULL,
                                `last_modified_by` varchar(255) DEFAULT NULL,
                                `last_modified_date` datetime(6) DEFAULT NULL,
                                `code` varchar(255) DEFAULT NULL,
                                `description` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_by` varchar(255) DEFAULT NULL,
                            `created_date` datetime(6) DEFAULT NULL,
                            `deleted` bit(1) DEFAULT NULL,
                            `last_modified_by` varchar(255) DEFAULT NULL,
                            `last_modified_date` datetime(6) DEFAULT NULL,
                            `address` varchar(255) DEFAULT NULL,
                            `bank_name` varchar(255) DEFAULT NULL,
                            `bank_number` varchar(255) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            `phone_number` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `insurance_product`
--

DROP TABLE IF EXISTS `insurance_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_product` (
                                     `id` bigint NOT NULL AUTO_INCREMENT,
                                     `created_by` varchar(255) DEFAULT NULL,
                                     `created_date` datetime(6) DEFAULT NULL,
                                     `deleted` bit(1) DEFAULT NULL,
                                     `last_modified_by` varchar(255) DEFAULT NULL,
                                     `last_modified_date` datetime(6) DEFAULT NULL,
                                     `coverage` varchar(255) DEFAULT NULL,
                                     `description` text,
                                     `name` varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` bigint NOT NULL,
                              `role_id` bigint NOT NULL,
                              PRIMARY KEY (`user_id`,`role_id`),
                              KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
                              CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                              CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_by` varchar(255) DEFAULT NULL,
                         `created_date` datetime(6) DEFAULT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `address` varchar(255) DEFAULT NULL,
                         `code` varchar(255) DEFAULT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `first_name` varchar(255) DEFAULT NULL,
                         `last_name` varchar(255) DEFAULT NULL,
                         `mime_type` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `path_avatar` varchar(255) DEFAULT NULL,
                         `phone` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'insuranceclaim2'
--

--
-- Current Database: `quanlynhansu`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `quanlynhansu` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `quanlynhansu`;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
                               `department_id` int NOT NULL,
                               `department_name` varchar(100) NOT NULL,
                               `location` varchar(100) NOT NULL,
                               PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
                             `employee_id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(100) NOT NULL,
                             `position` varchar(50) NOT NULL,
                             `salary` decimal(10,2) NOT NULL,
                             `department_id` int DEFAULT NULL,
                             `hire_date` date NOT NULL,
                             PRIMARY KEY (`employee_id`),
                             KEY `department_id` (`department_id`),
                             CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `code` varchar(50) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `full_name` varchar(255) DEFAULT NULL,
                        `deleted` tinyint(1) DEFAULT '0',
                        `role_id` int DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `username` (`username`),
                        KEY `role_id` (`role_id`),
                        CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'quanlynhansu'
--

--
-- Current Database: `quanlysinhvien`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `quanlysinhvien` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `quanlysinhvien`;

--
-- Table structure for table `bang_diem`
--

DROP TABLE IF EXISTS `bang_diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bang_diem` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `ma_sv` int DEFAULT NULL,
                             `ma_mon_hoc` int DEFAULT NULL,
                             `diem` decimal(5,2) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `ma_sv` (`ma_sv`),
                             KEY `ma_mon_hoc` (`ma_mon_hoc`),
                             CONSTRAINT `bang_diem_ibfk_1` FOREIGN KEY (`ma_sv`) REFERENCES `sinh_vien` (`ma_sv`),
                             CONSTRAINT `bang_diem_ibfk_2` FOREIGN KEY (`ma_mon_hoc`) REFERENCES `danh_muc_mon_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `danh_muc_mon_hoc`
--

DROP TABLE IF EXISTS `danh_muc_mon_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danh_muc_mon_hoc` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `ten_danh_muc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                    `parent_id` int DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `parent_id` (`parent_id`),
                                    CONSTRAINT `danh_muc_mon_hoc_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `danh_muc_mon_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lop_hoc`
--

DROP TABLE IF EXISTS `lop_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop_hoc` (
                           `ma_lop` int NOT NULL AUTO_INCREMENT,
                           `ten_lop` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                           PRIMARY KEY (`ma_lop`),
                           UNIQUE KEY `ten_lop` (`ten_lop`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sinh_vien`
--

DROP TABLE IF EXISTS `sinh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinh_vien` (
                             `ma_sv` int NOT NULL AUTO_INCREMENT,
                             `ho_ten` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                             `ngay_sinh` date NOT NULL,
                             `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                             `hoc_phi` decimal(10,2) NOT NULL,
                             `ma_lop` int DEFAULT NULL,
                             PRIMARY KEY (`ma_sv`),
                             UNIQUE KEY `email` (`email`),
                             KEY `sinh_vien_ibfk_1` (`ma_lop`),
                             CONSTRAINT `sinh_vien_ibfk_1` FOREIGN KEY (`ma_lop`) REFERENCES `lop_hoc` (`ma_lop`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'quanlysinhvien'
--

--
-- Current Database: `shop_book`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `shop_book` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `shop_book`;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `categoryName` varchar(255) DEFAULT NULL,
                              `description` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
                                      `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `identity_cards`
--

DROP TABLE IF EXISTS `identity_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `identity_cards` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `address` varchar(255) NOT NULL,
                                  `date_of_birth` varchar(255) NOT NULL,
                                  `full_name` varchar(255) NOT NULL,
                                  `identity_number` varchar(255) NOT NULL,
                                  `user_id` bigint NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `UK_d508hfeloc2s30m0ytqkae9wr` (`identity_number`),
                                  UNIQUE KEY `UK_gx8gapcl0vj5dkrm3shh76sc` (`user_id`),
                                  CONSTRAINT `FKnns21x4u3fbach7spaopg6vwx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `production`
--

DROP TABLE IF EXISTS `production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `title` varchar(255) DEFAULT NULL,
                              `author` varchar(255) DEFAULT NULL,
                              `description` text,
                              `genre` varchar(100) DEFAULT NULL,
                              `publisher` varchar(100) DEFAULT NULL,
                              `publication_year` int DEFAULT NULL,
                              `page_count` int DEFAULT NULL,
                              `price` decimal(10,2) DEFAULT NULL,
                              `discount_percent` decimal(5,2) DEFAULT NULL,
                              `stock_quantity` int DEFAULT NULL,
                              `category` varchar(100) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `book_title` varchar(255) DEFAULT NULL,
                            `author` varchar(255) DEFAULT NULL,
                            `page_count` int DEFAULT NULL,
                            `publisher` varchar(255) DEFAULT NULL,
                            `publication_year` int DEFAULT NULL,
                            `genre` varchar(100) DEFAULT NULL,
                            `price` double DEFAULT NULL,
                            `discount` double DEFAULT NULL,
                            `stock_quantity` int DEFAULT NULL,
                            `description` text,
                            `bookTitle` varchar(255) DEFAULT NULL,
                            `image` varchar(255) DEFAULT NULL,
                            `pageCount` int DEFAULT NULL,
                            `publicationYear` int DEFAULT NULL,
                            `stockQuantity` int DEFAULT NULL,
                            `category_id` int DEFAULT NULL,
                            `discountPercent` double DEFAULT NULL,
                            `title` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
                            CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `role_name` varchar(255) NOT NULL,
                         `deleted` bit(1) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_716hgxp60ym1lifrdgp67xt5k` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `user_id` int NOT NULL,
                             `role_id` int NOT NULL,
                             PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` bigint NOT NULL,
                              `role_id` bigint NOT NULL,
                              PRIMARY KEY (`user_id`,`role_id`),
                              KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
                              CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                              CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `password` varchar(255) NOT NULL,
                         `username` varchar(255) NOT NULL,
                         `active` bit(1) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'shop_book'
--

--
-- Current Database: `shop_book2`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `shop_book2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `shop_book2`;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `categoryName` varchar(255) DEFAULT NULL,
                              `description` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `author` varchar(255) DEFAULT NULL,
                            `description` varchar(255) DEFAULT NULL,
                            `discountPercent` double DEFAULT NULL,
                            `genre` varchar(255) DEFAULT NULL,
                            `pageCount` int DEFAULT NULL,
                            `price` double DEFAULT NULL,
                            `publicationYear` int DEFAULT NULL,
                            `publisher` varchar(255) DEFAULT NULL,
                            `stockQuantity` int DEFAULT NULL,
                            `title` varchar(255) DEFAULT NULL,
                            `category_id` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
                            CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `deleted` bit(1) DEFAULT NULL,
                         `role_name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `user_id` int NOT NULL,
                             `role_id` int NOT NULL,
                             PRIMARY KEY (`user_id`,`role_id`),
                             KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
                             CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                             CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `active` bit(1) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL,
                         `age` varchar(255) DEFAULT NULL,
                         `created` date DEFAULT NULL,
                         `fullName` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'shop_book2'
--

--
-- Current Database: `test_security2`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `test_security2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `test_security2`;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `birthday` datetime(6) DEFAULT NULL,
                        `cart_id` int DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `full_name` varchar(255) DEFAULT NULL,
                        `gender` int DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `user_code` varchar(255) DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `user_id` int NOT NULL,
                             `role_id` int NOT NULL,
                             PRIMARY KEY (`user_id`,`role_id`),
                             KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
                             CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                             CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'test_security2'
--

--
-- Current Database: `uniqlo_education`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `uniqlo_education` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `uniqlo_education`;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `user_id` int NOT NULL,
                              `sku_id` int NOT NULL,
                              `quantity` int NOT NULL DEFAULT '1',
                              `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `created_by` varchar(255) DEFAULT NULL,
                              `deleted` tinyint NOT NULL,
                              `updated_by` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `user_id` (`user_id`),
                              KEY `sku_id` (`sku_id`),
                              CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                              CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`sku_id`) REFERENCES `product_skus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) NOT NULL,
                              `parent_id` int DEFAULT NULL,
                              `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `updated_by` varchar(255) DEFAULT NULL,
                              `is_deleted` tinyint(1) DEFAULT NULL,
                              `deleted` tinyint NOT NULL,
                              `created_by` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `parent_id` (`parent_id`),
                              CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `color_code` varchar(50) NOT NULL,
                          `hex_code` varchar(10) DEFAULT NULL,
                          `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                          `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `updated_by` varchar(255) DEFAULT NULL,
                          `deleted` tinyint NOT NULL,
                          `created_by` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `product_id` int NOT NULL,
                                  `color_id` int NOT NULL,
                                  `image_url` varchar(255) NOT NULL,
                                  `is_main` tinyint(1) DEFAULT '0',
                                  `sort_order` int DEFAULT '0',
                                  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  KEY `product_id` (`product_id`),
                                  KEY `color_id` (`color_id`),
                                  CONSTRAINT `product_images_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
                                  CONSTRAINT `product_images_ibfk_2` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_skus`
--

DROP TABLE IF EXISTS `product_skus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_skus` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `product_id` int NOT NULL,
                                `color_id` int NOT NULL,
                                `size_id` int NOT NULL,
                                `sku_code` varchar(100) DEFAULT NULL,
                                `original_price` decimal(15,2) NOT NULL,
                                `sale_price` decimal(15,2) DEFAULT NULL,
                                `stock_quantity` int DEFAULT '0',
                                `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                `updated_by` int DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `sku_code` (`sku_code`),
                                KEY `product_id` (`product_id`),
                                KEY `color_id` (`color_id`),
                                KEY `size_id` (`size_id`),
                                CONSTRAINT `product_skus_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
                                CONSTRAINT `product_skus_ibfk_2` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
                                CONSTRAINT `product_skus_ibfk_3` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
                            `id` int NOT NULL,
                            `category_id` int NOT NULL,
                            `name` varchar(255) NOT NULL,
                            `description` text,
                            `material_info` text,
                            `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `updated_by` varchar(255) DEFAULT NULL,
                            `avatar` text,
                            `deleted` tinyint NOT NULL,
                            `created_by` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `category_id` (`category_id`),
                            CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `product_id` int NOT NULL,
                           `user_id` int NOT NULL,
                           `sku_id` int DEFAULT NULL,
                           `rating` tinyint NOT NULL,
                           `comment` text,
                           `user_height` varchar(50) DEFAULT NULL,
                           `user_weight` varchar(50) DEFAULT NULL,
                           `fit_status` varchar(50) DEFAULT NULL,
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `product_id` (`product_id`),
                           KEY `user_id` (`user_id`),
                           KEY `sku_id` (`sku_id`),
                           CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
                           CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                           CONSTRAINT `reviews_ibfk_3` FOREIGN KEY (`sku_id`) REFERENCES `product_skus` (`id`),
                           CONSTRAINT `reviews_chk_1` CHECK ((`rating` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `created_at` datetime(6) DEFAULT NULL,
                         `created_by` varchar(255) DEFAULT NULL,
                         `deleted` tinyint NOT NULL,
                         `updated_at` datetime(6) DEFAULT NULL,
                         `updated_by` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sizes`
--

DROP TABLE IF EXISTS `sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sizes` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `size_code` varchar(20) NOT NULL,
                         `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         `created_by` int DEFAULT NULL,
                         `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `updated_by` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `created_by` (`created_by`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` int NOT NULL,
                              `role_id` int NOT NULL,
                              PRIMARY KEY (`user_id`,`role_id`),
                              KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
                              CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                              CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `full_name` varchar(100) DEFAULT NULL,
                         `email` varchar(255) NOT NULL,
                         `password_hash` varchar(255) NOT NULL,
                         `birthday` date DEFAULT NULL,
                         `gender` enum('Male','Female','Decline to state') DEFAULT NULL,
                         `role` varchar(20) DEFAULT 'USER',
                         `remember_token` varchar(255) DEFAULT NULL,
                         `avatar` varchar(500) DEFAULT NULL,
                         `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `created_by` varchar(255) DEFAULT NULL,
                         `deleted` tinyint NOT NULL,
                         `updated_by` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `visit_stats`
--

DROP TABLE IF EXISTS `visit_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visit_stats` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `visit_count` bigint NOT NULL DEFAULT '0',
                               `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'uniqlo_education'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!50606 SET GLOBAL INNODB_STATS_AUTO_RECALC=@OLD_INNODB_STATS_AUTO_RECALC */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-20 22:10:31
