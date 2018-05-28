/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/28 21:43:15                           */
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
   changeId             int not null,
   fridge               int,
   item                 int,
   amount               int not null,
   primary key (changeId)
);

/*==============================================================*/
/* Table: DailyNutrition                                        */
/*==============================================================*/
create table DailyNutrition
(
   id                   int not null,
   user                 int,
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
   fridgeId             int not null,
   primary key (fridgeId)
);

/*==============================================================*/
/* Table: FridgeItemRelationship                                */
/*==============================================================*/
create table FridgeItemRelationship
(
   item                 int not null,
   fridge               int not null,
   id                   int not null,
   remainTime           int,
   primary key (id)
);

/*==============================================================*/
/* Table: Item                                                  */
/*==============================================================*/
create table Item
(
   itemId               int not null,
   name                 varchar(20) not null,
   shelflife            int not null,
   calories             int not null,
   primary key (itemId)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   userId               int not null,
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
   fridge               int not null,
   user                 int not null,
   id                   int not null,
   primary key (id)
);

alter table DailyChange add constraint FK_FridgeDailyChange foreign key (fridge)
      references Fridge (fridgeId) on delete restrict on update restrict;

alter table DailyChange add constraint FK_ItemChange foreign key (item)
      references Item (itemId) on delete restrict on update restrict;

alter table DailyNutrition add constraint FK_UserDailyNutrition foreign key (user)
      references User (userId) on delete restrict on update restrict;

alter table FridgeItemRelationship add constraint FK_FridgeItemRelationship foreign key (item)
      references Item (itemId) on delete restrict on update restrict;

alter table FridgeItemRelationship add constraint FK_FridgeItemRelationship2 foreign key (fridge)
      references Fridge (fridgeId) on delete restrict on update restrict;

alter table UserFridgeRelationship add constraint FK_UserFridgeRelationship foreign key (fridge)
      references Fridge (fridgeId) on delete restrict on update restrict;

alter table UserFridgeRelationship add constraint FK_UserFridgeRelationship2 foreign key (user)
      references User (userId) on delete restrict on update restrict;

