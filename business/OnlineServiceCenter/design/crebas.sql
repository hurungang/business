/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     6/25/2013 2:22:33 PM                         */
/*==============================================================*/


drop table if exists REFERRAL_LOG;

drop index IDX_NAME_PASSWORD on admin;

drop index IDX_NAME on admin;

drop table if exists admin;

drop index IDX_CODE on admin_module;

drop table if exists admin_module;

drop index IDX_NAME on admin_role;

drop table if exists admin_role;

drop index IDX_ROLE_ID on admin_role_module;

drop table if exists admin_role_module;

drop index IDX_CODE on area;

drop table if exists area;

drop index IDX_NAME on commodity;

drop table if exists commodity;

drop index IDX_CB_NAME on commodity_base;

drop table if exists commodity_base;

drop table if exists commodity_base_picture;

drop table if exists commodity_category;

drop index IDX_USER_ID on commodity_comment;

drop index IDX_COMMODITY_ID on commodity_comment;

drop table if exists commodity_comment;

drop table if exists commodity_detail;

drop index IDX_CO_USER_ID on commodity_order;

drop table if exists commodity_order;

drop index IDX_COI_ORDER_ID on commodity_order_item;

drop table if exists commodity_order_item;

drop table if exists commodity_payment;

drop table if exists commodity_picture;

drop index IDX_CPMT_COMMODITY_ID_TYPE_CODE on commodity_promotion;

drop table if exists commodity_promotion;

drop table if exists commodity_provider;

drop index IDX_CT_SITE_MODULE_ID on content;

drop index IDX_CT_POSITION_CODE on content;

drop table if exists content;

drop index IDX_C_NUMBER on coupon;

drop table if exists coupon;

drop table if exists delivery_contact;

drop table if exists label_commodity;

drop index IDX_CONTENT_ID on label_content;

drop index IDX_LABEL_ID on label_content;

drop table if exists label_content;

drop index IDX_NAME on label_word;

drop table if exists label_word;

drop index IDX_CODE on page_component;

drop table if exists page_component;

drop index IDX_NAME on page_template;

drop table if exists page_template;

drop table if exists page_template_component;

drop table if exists point_consume_log;

drop table if exists point_log;

drop table if exists quick_comment;

drop table if exists quick_order;

drop index IDX_CODE on regulation;

drop table if exists regulation;

drop table if exists report_configuration;

drop index IDX_CODE on site_module;

drop table if exists site_module;

drop index IDX_S_C_PHONE_NUMBER on sms_contact;

drop table if exists sms_contact;

drop table if exists sms_content;

drop table if exists sms_group;

drop table if exists sms_sent_log;

drop table if exists survey;

drop table if exists survey_item;

drop table if exists system_code;

drop index IDX_U_MOBILE on user;

drop index IDX_NAME_PASSWORD on user;

drop index IDX_NAME on user;

drop index IDX_EMAIL on user;

drop table if exists user;

drop table if exists user_contract;

drop table if exists user_detail;

drop table if exists user_maintain_log;

/*==============================================================*/
/* Table: REFERRAL_LOG                                          */
/*==============================================================*/
create table REFERRAL_LOG
(
   ID                   integer(10) not null,
   USER_MOBILE          varchar(50) not null,
   INTRODUCER_MOBILE    varchar(50) not null,
   INTRODUCER_NAME      varchar(100) not null,
   INTRODUCE_TIME       timestamp not null,
   STATUS               varchar(50),
   REMARK               tinytext,
   primary key (ID)
);

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   ID                   integer(10) not null auto_increment,
   NAME                 varchar(100) not null,
   PASSWORD             varchar(256) not null,
   FULL_NAME            varchar(100),
   EMAIL                varchar(100) not null,
   PHONE                varchar(50),
   MOBILE               varchar(50),
   ADDRESS              varchar(1024),
   ROLE_ID              integer(10) not null default 0,
   STATUS               varchar(50) not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

alter table admin comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_NAME                                              */
/*==============================================================*/
create unique index IDX_NAME on admin
(
   NAME
);

/*==============================================================*/
/* Index: IDX_NAME_PASSWORD                                     */
/*==============================================================*/
create index IDX_NAME_PASSWORD on admin
(
   NAME,
   PASSWORD
);

/*==============================================================*/
/* Table: admin_module                                          */
/*==============================================================*/
create table admin_module
(
   ID                   integer(10) not null auto_increment,
   PARENT_MODULE_ID     integer(10),
   NAME                 varchar(100) not null,
   CODE                 varchar(100) not null,
   PAGE                 varchar(256),
   TITLE                varchar(256),
   SUBTITLE             varchar(1000),
   MODEL_TYPE           varchar(256),
   IS_SYSTEM_MODULE     varchar(50),
   IS_HIDDEN            boolean default false,
   PRIORITY             int(10),
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

alter table admin_module comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_CODE                                              */
/*==============================================================*/
create unique index IDX_CODE on admin_module
(
   CODE
);

/*==============================================================*/
/* Table: admin_role                                            */
/*==============================================================*/
create table admin_role
(
   ID                   integer(10) not null auto_increment,
   NAME                 varchar(100) not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

alter table admin_role comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_NAME                                              */
/*==============================================================*/
create unique index IDX_NAME on admin_role
(
   NAME
);

/*==============================================================*/
/* Table: admin_role_module                                     */
/*==============================================================*/
create table admin_role_module
(
   ROLE_ID              integer(10) not null default 0,
   MODULE_ID            integer(10) not null default 0,
   ACCESS_CODE          integer(10),
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ROLE_ID, MODULE_ID)
);

alter table admin_role_module comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_ROLE_ID                                           */
/*==============================================================*/
create index IDX_ROLE_ID on admin_role_module
(
   ROLE_ID
);

/*==============================================================*/
/* Table: area                                                  */
/*==============================================================*/
create table area
(
   ID                   integer(10) not null auto_increment,
   CODE                 varchar(50) not null,
   NAME                 varchar(100) not null,
   CATEGORY             varchar(50) not null,
   PARENT_ID            integer(10),
   PRIORITY             integer(10) not null default 0,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

alter table area comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_CODE                                              */
/*==============================================================*/
create unique index IDX_CODE on area
(
   CODE
);

/*==============================================================*/
/* Table: commodity                                             */
/*==============================================================*/
create table commodity
(
   ID                   integer(10) not null default 0,
   COMMODITY_BASE_ID    integer(10) not null default 0,
   CATEGORY_ID          integer(10) not null default 0,
   AREA_ID              integer(10) not null default 0,
   PROVIDER_ID          integer(10),
   PARENT_COMMODITY_ID  integer(10),
   NAME                 varchar(100) not null,
   SHORT_NAME           varchar(50) not null,
   SUMMARY              varchar(256),
   STANDARD             varchar(256),
   PRICE_UNIT           varchar(256),
   PRICE_QUANTITY       DECIMAL(6,2),
   UNIT                 varchar(50) not null,
   QUANTITY             decimal(10,2) not null,
   MINIMUM_BUY          integer(10) not null default 1,
   PICTURE_PATH         varchar(256) not null,
   PRIORITY             integer(10) default 0,
   COMMEND_CODE         varchar(50) not null,
   STATUS               varchar(50) not null,
   ORIGINAL_PRICE       decimal(10,2),
   PRICE                decimal(10,2) not null default 0.00,
   PRICE_1              decimal(10,2),
   PRICE_2              decimal(10,2),
   PRICE_3              decimal(10,2),
   PRICE_4              decimal(10,2),
   PRICE_5              decimal(10,2),
   POINT                integer(10) not null default 0,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

alter table commodity comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_NAME                                              */
/*==============================================================*/
create index IDX_NAME on commodity
(
   NAME
);

/*==============================================================*/
/* Table: commodity_base                                        */
/*==============================================================*/
create table commodity_base
(
   ID                   integer(10) not null auto_increment,
   NAME                 varchar(100) not null,
   SHORT_NAME           varchar(50) not null,
   SUMMARY              varchar(256),
   PICTURE_PATH         varchar(256) not null,
   STATUS               varchar(50) not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

alter table commodity_base comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_CB_NAME                                           */
/*==============================================================*/
create index IDX_CB_NAME on commodity_base
(
   NAME
);

/*==============================================================*/
/* Table: commodity_base_picture                                */
/*==============================================================*/
create table commodity_base_picture
(
   ID                   integer(10) not null,
   COMMODITY_BASE_ID    integer(10),
   PATH                 varchar(256),
   CATEGORY             varchar(50),
   DESCRIPTION          varchar(256),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: commodity_category                                    */
/*==============================================================*/
create table commodity_category
(
   ID                   integer(10) not null auto_increment,
   NAME                 varchar(100) not null,
   PARENT_ID            integer(10),
   DESCRIPTION          varchar(1024),
   STATUS               varchar(50) not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp,
   PRIORITY             integer(5) default 0,
   REMARK               varchar(256),
   primary key (ID)
);

alter table commodity_category comment 'MySQL table';

/*==============================================================*/
/* Table: commodity_comment                                     */
/*==============================================================*/
create table commodity_comment
(
   ID                   integer(10) not null,
   COMMODITY_ID         integer(10) not null,
   USER_ID              integer(10),
   CONTENT              longtext,
   COMMENT_TIME         timestamp,
   COMMENT_LEVEL        integer(10),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_COMMODITY_ID                                      */
/*==============================================================*/
create index IDX_COMMODITY_ID on commodity_comment
(
   COMMODITY_ID
);

/*==============================================================*/
/* Index: IDX_USER_ID                                           */
/*==============================================================*/
create index IDX_USER_ID on commodity_comment
(
   USER_ID
);

/*==============================================================*/
/* Table: commodity_detail                                      */
/*==============================================================*/
create table commodity_detail
(
   ID                   integer(10) not null default 0,
   COMMODITY_BASE_ID    integer(10),
   DETAIL_CODE          varchar(100),
   INTRODUCTION         longtext,
   REMARK               varchar(256),
   primary key (ID)
);

alter table commodity_detail comment 'MySQL table';

/*==============================================================*/
/* Table: commodity_order                                       */
/*==============================================================*/
create table commodity_order
(
   ID                   integer(10) not null,
   USER_ID              integer(10),
   DEAL_TIME            timestamp not null default CURRENT_TIMESTAMP,
   PAY_TYPE             varchar(50),
   ORDER_TYPE           varchar(50),
   QUICK_REQUEST        text,
   QUICK_PROPOSE        text,
   FREIGHT              decimal(6,2),
   DELIVERY_AREA_ID     integer(10),
   DELIVERY_TO_USER_ID  integer(10),
   DELIVERY_ADDRESS     varchar(500),
   PLAN_DELIVERY_TIME   timestamp,
   MEMO                 varchar(1000),
   DELIVERY_TIME        timestamp default '0000-00-00 00:00:00',
   DELIVERYMAN          varchar(100),
   DELIVERY_STATUS      varchar(50),
   POSTCODE             varchar(50),
   CONTACT              varchar(100),
   CONTACT_PHONE        varchar(50),
   UPDATE_TIME          timestamp,
   STATUS               varchar(50),
   COMMENT              varchar(1000),
   REMARK               varchar(256),
   primary key (ID)
)
auto_increment = 1000000000;

/*==============================================================*/
/* Index: IDX_CO_USER_ID                                        */
/*==============================================================*/
create index IDX_CO_USER_ID on commodity_order
(
   USER_ID
);

/*==============================================================*/
/* Table: commodity_order_item                                  */
/*==============================================================*/
create table commodity_order_item
(
   ID                   integer(10) not null,
   ORDER_ID             integer(10) not null,
   COMMODITY_ID         integer(10) not null,
   COMMODITY_NUMBER     integer(5) not null,
   PROMOTION_ID         integer(10),
   PRICE                decimal(10,2) not null,
   DISCOUNT             decimal(3,2) not null,
   REAL_PRICE           decimal(10,2) not null,
   POINT                integer(10),
   RATE                 integer(2),
   COMMENT              varchar(1000),
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_COI_ORDER_ID                                      */
/*==============================================================*/
create index IDX_COI_ORDER_ID on commodity_order_item
(
   ORDER_ID
);

/*==============================================================*/
/* Table: commodity_payment                                     */
/*==============================================================*/
create table commodity_payment
(
   ID                   integer(10) not null auto_increment,
   ORDER_ID             integer(10) not null,
   SUM                  decimal(10,2) not null,
   PAY_TYPE             varchar(50),
   PAY_TIME             timestamp,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: commodity_picture                                     */
/*==============================================================*/
create table commodity_picture
(
   ID                   integer(10) not null auto_increment,
   COMMODITY_ID         integer(10),
   PATH                 varchar(256),
   CATEGORY             varchar(50),
   DESCRIPTION          varchar(256),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: commodity_promotion                                   */
/*==============================================================*/
create table commodity_promotion
(
   ID                   integer(10) not null auto_increment,
   COMMODITY_ID         integer(10) not null,
   TYPE_CODE            varchar(50) not null,
   TYPE_TITLE           varchar(400),
   SPECIAL_FLAG         varchar(50),
   TITLE                varchar(400),
   DESCRIPTION          text,
   PRICE                decimal(10,2) not null,
   MIN_ORDER_COUNT      integer(6),
   CURRENT_ORDER_COUNT  integer(6),
   MAX_ORDER_COUNT      integer(7),
   MAX_INDIVIDUAL_ORDER_COUNT integer(6),
   USER_TYPE            varchar(50),
   USER_LEVEL           int,
   ADDITIONAL_CONDITION varchar(1000),
   START_TIME           timestamp,
   END_TIME             timestamp,
   REGULATION_CODE      varchar(50),
   STATUS               varchar(50),
   PRIORITY             int(5),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_CPMT_COMMODITY_ID_TYPE_CODE                       */
/*==============================================================*/
create unique index IDX_CPMT_COMMODITY_ID_TYPE_CODE on commodity_promotion
(
   COMMODITY_ID,
   TYPE_CODE
);

/*==============================================================*/
/* Table: commodity_provider                                    */
/*==============================================================*/
create table commodity_provider
(
   ID                   integer(10) not null auto_increment,
   NAME                 varchar(100) not null,
   PHONE                varchar(50),
   LINKMAN              varchar(100),
   LINKMAN_PHONE        varchar(50),
   LINKMAN_MOBILE       varchar(50),
   ADDRESS              varchar(1024),
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

alter table commodity_provider comment 'MySQL table';

/*==============================================================*/
/* Table: content                                               */
/*==============================================================*/
create table content
(
   ID                   integer(10) not null default 0,
   SITE_MODULE_ID       integer(10),
   CATEGORY_ID          integer(10) default 0,
   POSITION_CODE        varchar(50) not null,
   TYPE_CODE            varchar(50) not null,
   TITLE                varchar(256),
   PICTURE_PATH         varchar(256),
   KEYWORD              varchar(256),
   SOURCE               varchar(256),
   AUTHOR               varchar(100),
   CONTENT              longtext,
   PRIORITY             integer(10) not null default 0,
   STATUS               varchar(50) not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp,
   REMARK               varchar(256),
   primary key (ID)
);

alter table content comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_CT_POSITION_CODE                                  */
/*==============================================================*/
create index IDX_CT_POSITION_CODE on content
(
   POSITION_CODE
);

/*==============================================================*/
/* Index: IDX_CT_SITE_MODULE_ID                                 */
/*==============================================================*/
create index IDX_CT_SITE_MODULE_ID on content
(
   SITE_MODULE_ID
);

/*==============================================================*/
/* Table: coupon                                                */
/*==============================================================*/
create table coupon
(
   ID                   integer(10) not null auto_increment,
   NAME                 varchar(50) not null,
   NUMBER               varchar(50) not null,
   VALUE                decimal(6,2) not null,
   REQUIRE_ORDER_SUM    decimal(10,2) not null,
   START_DATE           timestamp not null,
   EXPIRE_DATE          timestamp not null,
   PAYMENT_ID           integer(10),
   CREATE_TIME          timestamp,
   COMSUME_TIME         timestamp,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_C_NUMBER                                          */
/*==============================================================*/
create unique index IDX_C_NUMBER on coupon
(
   NUMBER
);

/*==============================================================*/
/* Table: delivery_contact                                      */
/*==============================================================*/
create table delivery_contact
(
   ID                   integer(10) not null auto_increment,
   USER_ID              integer(10) not null,
   AREA_ID              integer(10),
   ADDRESS              varchar(500),
   CONTACT              varchar(100),
   CONTACT_PHONE        varchar(50),
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: label_commodity                                       */
/*==============================================================*/
create table label_commodity
(
   LABEL_NAME           varchar(50) not null default '0',
   COMMODITY_ID         integer(10) not null default 0,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (LABEL_NAME, COMMODITY_ID)
);

alter table label_commodity comment 'MySQL table';

/*==============================================================*/
/* Table: label_content                                         */
/*==============================================================*/
create table label_content
(
   LABEL_NAME           varchar(50) not null default '0',
   CONTENT_ID           integer(10) not null default 0,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (LABEL_NAME, CONTENT_ID)
);

alter table label_content comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_LABEL_ID                                          */
/*==============================================================*/
create index IDX_LABEL_ID on label_content
(
   LABEL_NAME
);

/*==============================================================*/
/* Index: IDX_CONTENT_ID                                        */
/*==============================================================*/
create index IDX_CONTENT_ID on label_content
(
   CONTENT_ID
);

/*==============================================================*/
/* Table: label_word                                            */
/*==============================================================*/
create table label_word
(
   LABEL_NAME           varchar(50) not null,
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   UPDATER              integer(10),
   REMARK               varchar(256),
   primary key (LABEL_NAME)
);

alter table label_word comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_NAME                                              */
/*==============================================================*/
create unique index IDX_NAME on label_word
(
   LABEL_NAME
);

/*==============================================================*/
/* Table: page_component                                        */
/*==============================================================*/
create table page_component
(
   ID                   integer(10) not null auto_increment,
   CODE                 varchar(50) not null,
   NAME                 varchar(100),
   TYPE                 varchar(50) not null,
   START                integer(10),
   FETCH_SIZE           integer(10),
   QUERY                varchar(1000),
   BODY                 longtext,
   STATUS               varchar(50),
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_CODE                                              */
/*==============================================================*/
create unique index IDX_CODE on page_component
(
   CODE
);

/*==============================================================*/
/* Table: page_template                                         */
/*==============================================================*/
create table page_template
(
   ID                   integer(10) not null auto_increment,
   NAME                 varchar(100),
   LANGUAGE             varchar(50),
   TEMPLATE             longtext,
   STATUS               varchar(50),
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_NAME                                              */
/*==============================================================*/
create index IDX_NAME on page_template
(
   NAME
);

/*==============================================================*/
/* Table: page_template_component                               */
/*==============================================================*/
create table page_template_component
(
   TEMPLATE_ID          integer(10) not null,
   COMPONENT_ID         integer(10) not null,
   REFERENCE_NAME       varchar(100),
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (TEMPLATE_ID, COMPONENT_ID)
);

/*==============================================================*/
/* Table: point_consume_log                                     */
/*==============================================================*/
create table point_consume_log
(
   ID                   integer(10) not null auto_increment,
   PAYMENT_ID           integer(10) not null,
   POINT                decimal(10,2) not null,
   CONSUME_TIME         timestamp not null,
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: point_log                                             */
/*==============================================================*/
create table point_log
(
   ID                   integer(10) not null auto_increment,
   USER_ID              integer(10),
   TYPE_CODE            varchar(50),
   ORDER_ID             integer(10),
   POINT                decimal(10,2),
   POINT_TIME           timestamp,
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: quick_comment                                         */
/*==============================================================*/
create table quick_comment
(
   ID                   integer(10) not null auto_increment,
   REFERENCE_ID         integer(10),
   CLIENT_NAME          varchar(200),
   COMMENT_CONTENT      longtext,
   COMMENT_TIME         timestamp,
   REPLY_CONTENT        longtext,
   REPLY_ADMIN_ID       integer(10),
   REPLY_TIME           timestamp,
   COMMENT_LEVEL        integer(10),
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: quick_order                                           */
/*==============================================================*/
create table quick_order
(
   ID                   integer(10) not null,
   USER_ID              integer(10),
   CLIENT_NAME          varchar(200),
   CONTACT              varchar(100),
   CONTACT_PHONE        varchar(50),
   COMMODITY_MEMO       varchar(1000),
   ORDER_MEMO           varchar(1000),
   DEAL_TIME            timestamp not null default CURRENT_TIMESTAMP,
   ORDER_TYPE           varchar(50),
   DELIVERY_AREA_ID     integer(10),
   DELIVERY_ADDRESS     varchar(500),
   PLAN_DELIVERY_TIME   timestamp,
   CONFIRM_ADMIN_ID     integer(10),
   COMMODITY_CONFIRM_COMMENT varchar(1000),
   CONFIRM_DATE         timestamp,
   CONFIRM_PRICE        decimal(10,2),
   ORDER_COMMENT        varchar(1000),
   DELIVERY_TIME        timestamp default '0000-00-00 00:00:00',
   DELIVERYMAN          varchar(100),
   DELIVERY_STATUS      varchar(50),
   UPDATE_TIME          timestamp,
   STATUS               varchar(50),
   COMMENT              varchar(1000),
   REMARK               varchar(256),
   primary key (ID)
)
auto_increment = 1000000000;

/*==============================================================*/
/* Table: regulation                                            */
/*==============================================================*/
create table regulation
(
   ID                   integer(10) not null,
   REGULATION_CODE      varchar(50) not null,
   STATEMENTS           varchar(5000) not null,
   STATUS               varchar(50),
   UPDATER              integer(10),
   UPDATE_TIME          timestamp not null default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_CODE                                              */
/*==============================================================*/
create index IDX_CODE on regulation
(
   REGULATION_CODE
);

/*==============================================================*/
/* Table: report_configuration                                  */
/*==============================================================*/
create table report_configuration
(
   ID                   integer(10) not null,
   NAME                 varchar(100),
   QUERY                varchar(1000) not null,
   TEMPLATE             text not null,
   FORM                 text,
   STATUS               varchar(50) not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: site_module                                           */
/*==============================================================*/
create table site_module
(
   ID                   integer(10) not null auto_increment,
   PARENT_MODULE_ID     integer(10),
   NAME                 varchar(100),
   CODE                 varchar(20),
   TYPE                 varchar(50),
   PAGE_TEMPLATE_ID     integer(10),
   URL                  varchar(256),
   QUERY_STRING         varchar(256),
   TITLE                varchar(256),
   SUBTITLE             varchar(1000),
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   PRIORITY             integer(5),
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_CODE                                              */
/*==============================================================*/
create index IDX_CODE on site_module
(
   CODE
);

/*==============================================================*/
/* Table: sms_contact                                           */
/*==============================================================*/
create table sms_contact
(
   ID                   integer(10) not null auto_increment,
   PHONE_NUMBER         varchar(50) not null,
   CONTACT              varchar(100),
   GROUP_ID             integer(10) not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Index: IDX_S_C_PHONE_NUMBER                                  */
/*==============================================================*/
create unique index IDX_S_C_PHONE_NUMBER on sms_contact
(
   PHONE_NUMBER
);

/*==============================================================*/
/* Table: sms_content                                           */
/*==============================================================*/
create table sms_content
(
   ID                   integer(10) not null auto_increment,
   CONTENT              text not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp,
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: sms_group                                             */
/*==============================================================*/
create table sms_group
(
   ID                   integer(10) not null auto_increment,
   GROUP_NAME           varchar(100) not null,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: sms_sent_log                                          */
/*==============================================================*/
create table sms_sent_log
(
   ID                   integer(10) not null auto_increment,
   CONTENT              text not null,
   RECIPIENTS           text,
   SENDER               integer(10),
   SEND_TIME            timestamp not null,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: survey                                                */
/*==============================================================*/
create table survey
(
   ID                   integer(10) not null,
   SITE_MODULE_ID       integer(10),
   GROUP_CODE           varchar(50),
   SURVEY_CODE          varchar(50) not null,
   TYPE_CODE            varchar(50) not null,
   TITLE                varchar(200) not null,
   DESCRIPTION          text,
   START_TIME           timestamp,
   END_TIME             timestamp,
   COUNT                integer(10),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: survey_item                                           */
/*==============================================================*/
create table survey_item
(
   ID                   integer(10) not null,
   SURVEY_ID            integer(10) not null,
   DESCRIPTION          text not null,
   LINK                 varchar(256),
   REFER_TYPE           varchar(50),
   REFER_ID             integer(10),
   COUNT                integer(10) not null default 0,
   PRIORITY             integer(3),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: system_code                                           */
/*==============================================================*/
create table system_code
(
   ID                   integer(10) not null auto_increment,
   CODE                 varchar(50) not null,
   NAME                 varchar(100) not null,
   CATEGORY             varchar(50) not null,
   PARENT_ID            integer(10),
   PRIORITY             integer(5) not null default 0,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

alter table system_code comment 'MySQL table';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   ID                   integer(10) not null default 0,
   AREA_ID              integer(10),
   PARENT_USER_ID       integer(10),
   NAME                 varchar(100) not null,
   PASSWORD             varchar(256) not null,
   FULL_NAME            varchar(256),
   USER_TYPE            varchar(50),
   AGE                  integer(3),
   SEX                  varchar(50),
   EMAIL                varchar(100) not null,
   PHONE                varchar(50),
   MOBILE               varchar(50),
   CONTACT              varchar(100),
   SERVICE_ADMIN        integer(10),
   WEBSITE              varchar(200),
   FAX                  varchar(50),
   LAST_MAINTAIN_TIME   timestamp,
   SINA_AUTHORIZATION   tinytext,
   QQ_AUTHORIZATION     tinytext,
   RECOMMEND_USER_ID    integer(10),
   CREDENTIAL_TYPE      varchar(50),
   CREDENTIAL_NUMBER    varchar(100),
   POINT                decimal(10,2) default 0,
   EMPIRIC_VALUE        decimal(10,2) default 0,
   LEVEL                integer(5) default 0,
   ADDRESS              varchar(500),
   STATUS               varchar(50) not null,
   PICTURE_PATH         varchar(256),
   REGISTER_TIME        timestamp not null default CURRENT_TIMESTAMP,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp default '0000-00-00 00:00:00',
   REMARK               tinytext,
   primary key (ID)
);

alter table user comment 'MySQL table';

/*==============================================================*/
/* Index: IDX_EMAIL                                             */
/*==============================================================*/
create index IDX_EMAIL on user
(
   EMAIL
);

/*==============================================================*/
/* Index: IDX_NAME                                              */
/*==============================================================*/
create unique index IDX_NAME on user
(
   NAME
);

/*==============================================================*/
/* Index: IDX_NAME_PASSWORD                                     */
/*==============================================================*/
create index IDX_NAME_PASSWORD on user
(
   NAME,
   PASSWORD
);

/*==============================================================*/
/* Index: IDX_U_MOBILE                                          */
/*==============================================================*/
create unique index IDX_U_MOBILE on user
(
   MOBILE
);

/*==============================================================*/
/* Table: user_contract                                         */
/*==============================================================*/
create table user_contract
(
   ID                   integer(10) not null auto_increment,
   USER_ID              integer(10) not null,
   CONTRACT_TYPE        varchar(50) not null,
   START_TIME           timestamp,
   END_TIME             timestamp,
   CONTRACT_CONTENT     longtext,
   CONTRACT_FILE        varchar(256),
   SERVICE_ADMIN        integer(10),
   SIGN_TIME            timestamp,
   STATUS               varchar(50),
   REMARK               varchar(256),
   primary key (ID)
);

/*==============================================================*/
/* Table: user_detail                                           */
/*==============================================================*/
create table user_detail
(
   ID                   integer(10) not null default 0,
   INTRODUCTION         longtext,
   QUESTION             varchar(500),
   ANSWER               varchar(500),
   REMARK               varchar(256),
   primary key (ID)
);

alter table user_detail comment 'MySQL table';

/*==============================================================*/
/* Table: user_maintain_log                                     */
/*==============================================================*/
create table user_maintain_log
(
   ID                   integer(10) not null auto_increment,
   USER_ID              integer(10),
   ADMIN_ID             integer(10),
   MAINTAIN_TIME        timestamp,
   SUMMARY              varchar(1000),
   DETAIL               longtext,
   UPDATER              integer(10),
   UPDATE_TIME          timestamp,
   REMARK               varchar(256),
   primary key (ID)
);

alter table admin add constraint FK_A_AR_ROLE_ID foreign key (ROLE_ID)
      references admin_role (ID) on delete restrict on update restrict;

alter table admin_module add constraint FK_AM_AM_PARENT_MODULE_ID foreign key (PARENT_MODULE_ID)
      references admin_module (ID) on delete restrict on update restrict;

alter table admin_role_module add constraint FK_ARM_AM_MODULE_ID foreign key (MODULE_ID)
      references admin_module (ID) on delete restrict on update restrict;

alter table admin_role_module add constraint FK_ARM_AR_ROLE_ID foreign key (ROLE_ID)
      references admin_role (ID) on delete restrict on update restrict;

alter table area add constraint FK_A_A_AREA_ID foreign key (PARENT_ID)
      references area (ID) on delete restrict on update restrict;

alter table commodity add constraint FK_CM_CC_CATEGORY_ID foreign key (CATEGORY_ID)
      references commodity_category (ID) on delete restrict on update restrict;

alter table commodity add constraint FK_C_A_AREA_ID foreign key (AREA_ID)
      references area (ID) on delete restrict on update restrict;

alter table commodity add constraint FK_C_CB_COMMODITY_BASE_ID foreign key (COMMODITY_BASE_ID)
      references commodity_base (ID) on delete restrict on update restrict;

alter table commodity add constraint FK_C_CP_PROVIDER_ID foreign key (PROVIDER_ID)
      references commodity_provider (ID) on delete restrict on update restrict;

alter table commodity add constraint FK_C_C_PARENT_COMMODITY_ID foreign key (PARENT_COMMODITY_ID)
      references commodity (ID) on delete restrict on update restrict;

alter table commodity_base_picture add constraint FK_CBP_CB_COMMODITY_BASE_ID foreign key (COMMODITY_BASE_ID)
      references commodity_base (ID) on delete restrict on update restrict;

alter table commodity_category add constraint FK_CC_CC_PARENT_ID foreign key (PARENT_ID)
      references commodity_category (ID) on delete restrict on update restrict;

alter table commodity_comment add constraint FK_CC_C_COMMODITY_ID foreign key (COMMODITY_ID)
      references commodity (ID) on delete restrict on update restrict;

alter table commodity_comment add constraint FK_CC_SU_USER_ID foreign key (USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table commodity_detail add constraint FK_CD_CB_COMMODITY_BASE_ID foreign key (COMMODITY_BASE_ID)
      references commodity_base (ID) on delete restrict on update restrict;

alter table commodity_order add constraint FK_CO_A_AREA_ID foreign key (DELIVERY_AREA_ID)
      references area (ID) on delete restrict on update restrict;

alter table commodity_order add constraint FK_CO_U_DELIVERY_TO_USER_ID foreign key (DELIVERY_TO_USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table commodity_order add constraint FK_CO_U_USER_ID foreign key (USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table commodity_order_item add constraint FK_COI_CO_ORDER_ID foreign key (ORDER_ID)
      references commodity_order (ID) on delete restrict on update restrict;

alter table commodity_order_item add constraint FK_COI_CP_PROMOTION_ID foreign key (PROMOTION_ID)
      references commodity_promotion (ID) on delete restrict on update restrict;

alter table commodity_order_item add constraint FK_COI_C_COMMODITY_ID foreign key (COMMODITY_ID)
      references commodity (ID) on delete restrict on update restrict;

alter table commodity_payment add constraint FK_CP_CO_ORDER_ID foreign key (ORDER_ID)
      references commodity_order (ID) on delete restrict on update restrict;

alter table commodity_picture add constraint FK_CP_C_COMMODITY_ID foreign key (COMMODITY_ID)
      references commodity (ID) on delete restrict on update restrict;

alter table commodity_promotion add constraint FK_CPMT_C_COMMODITY_ID foreign key (COMMODITY_ID)
      references commodity (ID) on delete restrict on update restrict;

alter table content add constraint FK_CT_CC_CATEGORY_ID foreign key (CATEGORY_ID)
      references commodity_category (ID) on delete restrict on update restrict;

alter table content add constraint FK_CT_SM_SITE_MODULE_ID foreign key (SITE_MODULE_ID)
      references site_module (ID) on delete restrict on update restrict;

alter table coupon add constraint FK_C_CP_PAYMENT_ID foreign key (PAYMENT_ID)
      references commodity_payment (ID) on delete restrict on update restrict;

alter table delivery_contact add constraint FK_DC_A_AREA_ID foreign key (AREA_ID)
      references area (ID) on delete restrict on update restrict;

alter table delivery_contact add constraint FK_DC_U_USER_ID foreign key (USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table label_commodity add constraint FK_LC_C_COMMODITY_ID foreign key (COMMODITY_ID)
      references commodity (ID) on delete restrict on update restrict;

alter table label_commodity add constraint FK_LC_L_LABEL foreign key (LABEL_NAME)
      references label_word (LABEL_NAME) on delete restrict on update restrict;

alter table label_content add constraint FK_LC_C_CONTENT_ID foreign key (CONTENT_ID)
      references content (ID) on delete restrict on update restrict;

alter table label_content add constraint FK_LC_LW_LABEL_NAME foreign key (LABEL_NAME)
      references label_word (LABEL_NAME) on delete restrict on update restrict;

alter table page_template_component add constraint FK_PTC_PC_COMPONENT_ID foreign key (COMPONENT_ID)
      references page_component (ID) on delete restrict on update restrict;

alter table page_template_component add constraint FK_PTC_PT_TEMPLATE_ID foreign key (TEMPLATE_ID)
      references page_template (ID) on delete restrict on update restrict;

alter table point_consume_log add constraint FK_PCL_CP_PAYMENT_ID foreign key (PAYMENT_ID)
      references commodity_payment (ID) on delete restrict on update restrict;

alter table point_log add constraint FK_PL_CO_ORDER_ID foreign key (ORDER_ID)
      references commodity_order (ID) on delete restrict on update restrict;

alter table point_log add constraint FK_PL_U_USER_ID foreign key (USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table quick_comment add constraint FK_QC_A_ADMIN_ID foreign key (REPLY_ADMIN_ID)
      references admin (ID) on delete restrict on update restrict;

alter table quick_comment add constraint FK_QC_QC_ID foreign key (REFERENCE_ID)
      references quick_comment (ID) on delete restrict on update restrict;

alter table quick_order add constraint FK_QO_A_ADMIN_ID foreign key (CONFIRM_ADMIN_ID)
      references admin (ID) on delete restrict on update restrict;

alter table quick_order add constraint FK_QO_A_AREA_ID foreign key (DELIVERY_AREA_ID)
      references area (ID) on delete restrict on update restrict;

alter table quick_order add constraint FK_QO_U_USER_ID foreign key (USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table site_module add constraint FK_SM_PT_PAGE_TEMPLATE_ID foreign key (PAGE_TEMPLATE_ID)
      references page_template (ID) on delete restrict on update restrict;

alter table site_module add constraint FK_SM_SM_PARENT_MODULE_ID foreign key (PARENT_MODULE_ID)
      references site_module (ID) on delete restrict on update restrict;

alter table sms_contact add constraint FK_SC_SG_GROUP_ID foreign key (GROUP_ID)
      references sms_group (ID) on delete restrict on update restrict;

alter table survey add constraint FK_SV_SM_SITE_MODULE_ID foreign key (SITE_MODULE_ID)
      references site_module (ID) on delete restrict on update restrict;

alter table survey_item add constraint FK_SI_S_SURVEY_ID foreign key (SURVEY_ID)
      references survey (ID) on delete restrict on update restrict;

alter table system_code add constraint FK_SC_SC_PARENT_ID foreign key (PARENT_ID)
      references system_code (ID) on delete restrict on update restrict;

alter table user add constraint FK_U_A_AREA_ID foreign key (AREA_ID)
      references area (ID) on delete restrict on update restrict;

alter table user add constraint FK_U_A_SERVICE_ADMIN foreign key (SERVICE_ADMIN)
      references admin (ID) on delete restrict on update restrict;

alter table user add constraint FK_U_U_PARENT_USER_ID foreign key (PARENT_USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table user add constraint FK_U_U_RECOMMENT_USER_ID foreign key (RECOMMEND_USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table user_contract add constraint FK_UC_A_SERVICE_ADMIN foreign key (SERVICE_ADMIN)
      references admin (ID) on delete restrict on update restrict;

alter table user_contract add constraint FK_UC_U_USER_ID foreign key (USER_ID)
      references user (ID) on delete restrict on update restrict;

alter table user_detail add constraint FK_UD_U_ID foreign key (ID)
      references user (ID) on delete restrict on update restrict;

alter table user_maintain_log add constraint FK_UML_A_ADMIN_ID foreign key (ADMIN_ID)
      references admin (ID) on delete restrict on update restrict;

alter table user_maintain_log add constraint FK_UML_A_UPDATER_ID foreign key (UPDATER)
      references admin (ID) on delete restrict on update restrict;

alter table user_maintain_log add constraint FK_UML_U_USER_ID foreign key (USER_ID)
      references user (ID) on delete restrict on update restrict;

