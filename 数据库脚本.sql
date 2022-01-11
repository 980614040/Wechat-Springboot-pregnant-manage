/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.28-log : Database - pregnant manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pregnant manage` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `pregnant manage`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `adminid` int(11) NOT NULL,
  `adminname` varchar(16) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` varchar(2) DEFAULT NULL,
  `pwd` varchar(16) NOT NULL,
  `describes` varchar(300) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `admin` */

insert  into `admin`(`adminid`,`adminname`,`sex`,`age`,`pwd`,`describes`,`tel`) values (10001,'杨钧升','男','23','123456','本程序开发人员','19857047005');

/*Table structure for table `doctor` */

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `doctorid` int(11) NOT NULL,
  `doctorname` varchar(16) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(2) DEFAULT NULL,
  `pwd` varchar(16) NOT NULL,
  `describes` varchar(300) DEFAULT NULL,
  `src` varchar(300) DEFAULT NULL,
  `Workyear` varchar(10) DEFAULT NULL,
  `post` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`doctorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `doctor` */

insert  into `doctor`(`doctorid`,`doctorname`,`sex`,`age`,`pwd`,`describes`,`src`,`Workyear`,`post`) values (20001,'金巧智','男',35,'123456','精通眼鼻喉科，多年荣获中国医师奖，副业是院长','/icon/用户.png','3年','院长'),(20002,'周金玲','女',28,'123456','妇产科 早期妊娠，避孕，人工流产，阴道炎，月经不调，HPV感染，1111','/imgs/doctors/20001.png','7年','主治医生'),(20003,'李顺华','女',43,'123456','副主任医生 早期妊娠，激素避孕，人工流产，月经不调，阴道炎，月经不调，HPV感染','/imgs/doctors/20002.png','6年','副主任医生'),(20004,'申沛','女',35,'123456','副主任医生 子宫肌瘤，白带异常，月经不调，阴道炎，月经不调，HPV','/imgs/doctors/20003.png','7年','主任医生');

/*Table structure for table `doctortime` */

DROP TABLE IF EXISTS `doctortime`;

CREATE TABLE `doctortime` (
  `timeid` varchar(20) NOT NULL,
  `doctorid` int(11) DEFAULT NULL,
  `startTime` varchar(50) DEFAULT NULL,
  `endTime` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`timeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `doctortime` */

insert  into `doctortime`(`timeid`,`doctorid`,`startTime`,`endTime`,`status`,`userid`) values ('20211030',20001,'2021-10-30 8:00:00','2021-10-30 15:00:00',102,30002),('20211031',20001,'2021-10-31 8:00:00','2021-10-31 9:00:00',102,30002),('2021103108',20002,'2021-10-31 8:00:00','2021-10-31 9:00:00',102,30002),('2021103113',20002,'2021-10-31 13:00:00','2021-10-31 15:00:00',102,30002),('20211101',20002,'2021-10-30 8:00:00','2021-10-30 15:00:00',102,30002),('20211101150000',20002,'2021-11-01 15:00:00','2021-11-01 16:00:00',102,30001),('20211101200000',20002,'2021-11-01 20:00:00','2021-11-01 21:00:00',102,NULL),('20211102',20002,'2021-10-30 8:00:00','2021-10-30 15:00:00',102,30002),('20211112170000',20002,'2021-11-12 17:00:00','2021-11-12 18:00:00',102,30001),('20211206160000',20002,'2021-12-06 16:00:00','2021-12-06 17:00:00',102,30001);

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `examid` int(11) NOT NULL,
  `examtime` varchar(50) DEFAULT NULL,
  `islimosis` tinyint(1) DEFAULT NULL,
  `isurinalysis` tinyint(1) DEFAULT NULL,
  `examproject` varchar(100) DEFAULT NULL,
  `exampurpose` varchar(100) DEFAULT NULL,
  `attentionmsg` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`examid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `exam` */

insert  into `exam`(`examid`,`examtime`,`islimosis`,`isurinalysis`,`examproject`,`exampurpose`,`attentionmsg`) values (1,'12周',1,1,'血常规、尿常规、宫高腹围、胎心、血压、NT','排查常见疾病及各类型流产','由于第1次产检比较费时，产检时最好有人陪伴,同时穿易于穿脱的衣物。'),(2,'16周',1,0,'唐氏综合征筛查','排除唐氏综合征查看第一次报告','检查前一天晚上12点以后禁食物和水,空腹检查。'),(3,'16周',0,0,'四维彩超','胎儿畸形检查','照彩超的时候,妈妈要放松心态，过于紧张会影响宝宝活动。'),(4,'20周',1,0,'妊娠糖尿病筛查','筛查准妈妈是否有妊娠糖尿病','如有妊娠糖尿病,要采取饮食调整'),(5,'24周',1,0,'乙肝抗原复查、胎位检测','排除乙肝或胎儿畸形','若携带乙肝病毒,则宝宝生下后24小时内要注射之肝疫苗'),(6,'28周',0,0,'浮肿检查、排查子痫前症','排除子痫前症','准妈妈应该每天自数胎动,有异常就就医'),(7,'30周',0,0,'胎心监护','排除胎儿异常','准妈妈在这期间应该多走动，吃点东西'),(8,'32周',0,0,'评估胎儿体重','预估胎儿出生体重','若胎儿预估体重未达标，准妈妈要多补膳'),(9,'34周',0,0,'胎位检测','防止胎位不正难产','胎位在最后几周会有所变化，谨遵医嘱!'),(10,'36周',0,0,'胎心监护，骨盆内诊','帮助确定生产方式','骨盆内诊会有些疼痛感，应有心理准备'),(11,'每周一次',0,0,'胎心监护','确认母子健康','确保妈妈和胎儿的健康，听从医嘱');

/*Table structure for table `exam_paper` */

DROP TABLE IF EXISTS `exam_paper`;

CREATE TABLE `exam_paper` (
  `paperid` varchar(50) NOT NULL,
  `examid` int(11) NOT NULL,
  `describes` varchar(500) DEFAULT NULL,
  `papaeratttion` varchar(100) DEFAULT NULL,
  `doctorid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `papaertime` varchar(50) DEFAULT NULL,
  `src` varchar(300) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `fetal_heart` int(11) DEFAULT NULL,
  `blood_pressure` int(11) DEFAULT NULL,
  `uterine_height` int(11) DEFAULT NULL,
  `abdominal_girth` int(11) DEFAULT NULL,
  PRIMARY KEY (`paperid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `exam_paper` */

insert  into `exam_paper`(`paperid`,`examid`,`describes`,`papaeratttion`,`doctorid`,`userid`,`papaertime`,`src`,`weight`,`fetal_heart`,`blood_pressure`,`uterine_height`,`abdominal_girth`) values ('2000203000101',1,'排查常见疾病及各类型流产','无明显症状',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',70,270,340,344,300),('2000203000102',2,'唐氏综合征筛查','无症状',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',300,270,340,344,300),('2000203000103',3,'胎儿畸形检查','无症状，胎儿正常',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',200,270,340,344,300),('2000203000104',4,'唐氏综合征筛查','无症状',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',100,270,340,344,300),('2000203000105',5,'唐氏综合征筛查','无症状',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',55,24,340,344,300),('2000203000106',6,'唐氏综合征筛查','无症状',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',30,50,340,344,300),('2000203000107',7,'唐氏综合征筛查','无症状',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',120,135,340,344,300),('2000203000108',8,'唐氏综合征筛查','无症状',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',150,120,340,344,300),('2000203000109',9,'唐氏综合征筛查','无症状',20002,30001,'2021-10-31 8:00:00','/imgs/users/30002.jpg',200,140,340,344,300),('2000203000201',1,'排查常见疾病及各类型流产','无明显症状',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',123,124,150,222,300),('2000203000202',2,'唐氏综合征筛查','无症状',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',150,126,200,223,100),('2000203000203',3,'胎儿畸形检查','无症状，胎儿正常',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',170,127,222,240,120),('2000203000204',4,'唐氏综合征筛查','无症状',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',180,128,234,250,130),('2000203000205',5,'唐氏综合征筛查','无症状',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',200,270,215,270,140),('2000203000206',6,'唐氏综合征筛查','无症状',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',300,270,130,230,150),('2000203000207',7,'唐氏综合征筛查','无症状',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',300,270,210,200,160),('2000203000208',8,'唐氏综合征筛查','无症状',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',300,270,200,222,170),('2000203000209',9,'唐氏综合征筛查','无症状',20002,30002,'2021-10-31 8:00:00','/imgs/users/30002.jpg',300,270,120,260,200),('2000203000301',1,'排查常见疾病及各类型流产','无明显症状',21,51,'\"2021/10/31  8:00:00\"','null',70,270,340,344,300),('2000203000302',2,'唐氏综合征筛查','无症状',21,51,'\"2021/10/31  8:00:01\"','null',300,270,340,344,300),('2000203000303',3,'胎儿畸形检查','无症状，胎儿正常',21,50,'\"2021/10/31  8:00:02\"','null',200,270,340,344,300),('2000203000304',4,'唐氏综合征筛查','无症状',21,50,'\"2021/10/31  8:00:03\"','null',100,270,340,344,300),('2000203000305',5,'唐氏综合征筛查','无症状',21,50,'\"2021/10/31  8:00:04\"','null',55,270,340,344,300),('2000203000306',6,'唐氏综合征筛查','无症状',21,50,'\"2021/10/31  8:00:05\"','null',30,270,340,344,300),('2000203000307',7,'唐氏综合征筛查','无症状',21,50,'\"2021/10/31  8:00:06\"','null',120,270,340,344,300),('2000203000308',8,'唐氏综合征筛查','无症状',21,50,'\"2021/10/31  8:00:07\"','null',150,270,340,344,300),('2000203000309',9,'唐氏综合征筛查','无症状',21,50,'\"2021/10/31  8:00:08\"','null',200,270,340,344,300);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `username` varchar(16) DEFAULT NULL,
  `age` varchar(2) DEFAULT NULL,
  `pwd` varchar(16) NOT NULL,
  `describes` varchar(300) DEFAULT NULL,
  `src` varchar(300) DEFAULT NULL,
  `Pregnant_time` varchar(10) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`userid`,`username`,`age`,`pwd`,`describes`,`src`,`Pregnant_time`,`tel`) values (30001,'兰亭','23','123456','第一次怀孕','/imgs/users/30001.jpg','6个月','1380xxxxxx1'),(30002,'石秀艳','28','123456','阴道炎，月经不调，HPV感染','/imgs/users/30002.jpg','7个月','1380xxxxxx2'),(30003,'史雅倩','43','123456','人工流产，月经不调，阴道炎','/imgs/users/30003.jpg','6个月','1380xxxxxx3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
