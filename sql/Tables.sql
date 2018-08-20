/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/29 18:39:29                           */
/*==============================================================*/


drop table if exists DailyChange;

drop table if exists FridgeItemRelationship;

drop table if exists UserFridgeRelationship;

drop table if exists DailyNutrition;

drop table if exists Fridge;

drop table if exists Item;

drop table if exists User;

/*==============================================================*/
/* Table: DailyChange                                           */
/*==============================================================*/
create table DailyChange
(
   changeId             int not null auto_increment,
   fridgeId             int not null,
   itemId               int not null,
   userId               int not null,
   amount               int not null,
   time                 timestamp not null,
   primary key (changeId)
);

/*==============================================================*/
/* Table: DailyNutrition                                        */
/*==============================================================*/
create table DailyNutrition
(
   id                   int not null auto_increment,
   userId               int not null,
   date                 date not null,
   protein              float,
   calories             int,
   vitaminA             float,
   vitaminB             float,
   vitaminC             float,
   carbohydrates        float,
   primary key (id)
);

/*==============================================================*/
/* Table: Fridge                                                */
/*==============================================================*/
create table Fridge
(
   fridgeId             int not null auto_increment,
   primary key (fridgeId)
);

/*==============================================================*/
/* Table: FridgeItemRelationship                                */
/*==============================================================*/
create table FridgeItemRelationship
(
   itemId               int not null,
   amount               int not null,
   fridgeId             int not null,
   id                   int not null auto_increment,
   remainTime           int,
   primary key (id)
);

/*==============================================================*/
/* Table: Item                                                  */
/*==============================================================*/
create table Item
(
   itemId               int not null auto_increment,
   name                 varchar(20),
   shelflife            int,
   calories             int,
   barcode              varchar(20),
   primary key (itemId)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   userId               int not null auto_increment,
   username             varchar(20) not null,
   password             varchar(32) not null,
   tel                  varchar(14) not null,
   role                 int not null,
   primary key (userId)
);

/*==============================================================*/
/* Table: UserFridgeRelationship                                */
/*==============================================================*/
create table UserFridgeRelationship
(
   fridgeId             int not null,
   userId               int not null,
   id                   int not null auto_increment,
   primary key (id)
);

alter table DailyChange add constraint FK_FridgeDailyChange foreign key (fridgeId)
      references Fridge (fridgeId) on delete restrict on update restrict;

alter table DailyChange add constraint FK_ItemChange foreign key (itemId)
      references Item (itemId) on delete restrict on update restrict;

alter table DailyChange add constraint FK_User foreign key (userId)
      references User (userId) on delete restrict on update restrict;

alter table DailyNutrition add constraint FK_UserDailyNutrition foreign key (userId)
      references User (userId) on delete restrict on update restrict;

alter table FridgeItemRelationship add constraint FK_FridgeItemRelationship foreign key (itemId)
      references Item (itemId) on delete restrict on update restrict;

alter table FridgeItemRelationship add constraint FK_FridgeItemRelationship2 foreign key (fridgeId)
      references Fridge (fridgeId) on delete restrict on update restrict;

alter table UserFridgeRelationship add constraint FK_UserFridgeRelationship foreign key (fridgeId)
      references Fridge (fridgeId) on delete restrict on update restrict;

alter table UserFridgeRelationship add constraint FK_UserFridgeRelationship2 foreign key (userId)
      references User (userId) on delete restrict on update restrict;

