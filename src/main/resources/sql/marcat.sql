create table koreaarea
(
    adm_cd8 varchar2(8),
    adm_nm  varchar2(100),
    adm_cd  varchar2(7),
    adm_cd2 varchar2(10),
    sgg     varchar2(5),
    sido    varchar2(2),
    sidonm  varchar2(50),
    temp    varchar2(50),
    sggnm   varchar2(50)
);

ALTER TABLE KOREAAREA
    ADD CONSTRAINT KOREAAREA_pk PRIMARY KEY (adm_cd8);

create table board
(
    board_id    number(19,0),
    member_id   number(19,0) NOT null,
    title       varchar2(200) NOT null,
    contents    varchar2(3000) NOT null,
    view_count  number(10,0) default 0,
    create_time DATE DEFAULT sysdate NOT null,
    status      varchar2(20) not NULL DEFAULT 'ACTIVATION',
    wish_count  number(10,0) DEFAULT 0,
    adm_cd8     varchar2(8) NOT null
);

create sequence board_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE board
    ADD CONSTRAINT board_pk PRIMARY KEY (board_id);

alter table board
    add constraint board_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

alter table board
    add constraint board_koreaArea_fk foreign key (adm_cd8) references koreaarea ON DELETE CASCADE;

alter table board
    add constraint board_status_ck check (status IN ('ACTIVATION', 'INACTIVE'));

create table board_comments
(
    id          number(19,0),
    member_id   number(19,0) NOT NULL,
    board_id    number(19,0) NOT null,
    contents    varchar2(1000) NOT null,
    create_time DATE DEFAULT sysdate NOT null
);

create sequence board_comments_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE board_comments
    ADD CONSTRAINT board_comments_pk PRIMARY KEY (id);

alter table board_comments
    add constraint board_comments_board_fk foreign key (board_id) references board ON DELETE cascade;

alter table board_comments
    add constraint board_comments_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

create table board_images
(
    id               number(19,0),
    board_id         number(19,0) NOT null,
    origin_file_name varchar2(255) NOT null,
    saved_file_name  varchar2(255) NOT null,
    create_time      DATE DEFAULT sysdate NOT null
);

create sequence board_images_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE board_images
    ADD CONSTRAINT board_images_pk PRIMARY KEY (id);

alter table board_images
    add constraint board_images_board_fk foreign key (board_id) references board ON DELETE cascade;

create table board_report
(
    id        number(19,0),
    member_id number(19,0) NOT null,
    board_id  number(19,0) NOT NULL,
    contents  varchar2(255) NOT NULL
);

create sequence board_report_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE board_report
    ADD CONSTRAINT board_report_pk PRIMARY KEY (id);

alter table board_report
    add constraint board_report_goods_fk foreign key (board_id) references board ON DELETE cascade;

alter table board_report
    add constraint board_report_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

alter table board_report add constraint report_contents_ck check(contents IN('스팸홍보/도배글입니다','음란물입니다','욕설/혐오/차별적 표현이 포함되어 있습니다','개인정보 노출 게시물입니다','청소년에게 유해한 내용입니다','저작권침해 게시물입니다'));

create table board_wish_list
(
    bwl_id             number(19,0),
    b_wanted_member_id number(19,0) NOT null,
    board_id           number(19,0) NOT NULL,
    bwl_create_time    DATE DEFAULT sysdate NOT NULL
);

create sequence board_wish_list_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE board_wish_list
    ADD CONSTRAINT board_wish_list_pk PRIMARY KEY (id);

alter table board_wish_list
    add constraint board_wish_list_board_fk foreign key (board_id) references board ON DELETE cascade;

alter table board_wish_list
    add constraint board_wish_list_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

create table categories
(
    categories_id varchar2(10),
    name          varchar2(50) NOT NULL
);

ALTER TABLE categories
    ADD CONSTRAINT categories_pk PRIMARY KEY (categories_id);

INSERT INTO categories
VALUES ('100', '중고차');
INSERT INTO categories
VALUES ('200', '디지털기기');
INSERT INTO categories
VALUES ('300', '생활가전');
INSERT INTO categories
VALUES ('400', '가구/인테리어');
INSERT INTO categories
VALUES ('500', '유아동');
INSERT INTO categories
VALUES ('600', '생활/가공식품');
INSERT INTO categories
VALUES ('700', '유아도서');
INSERT INTO categories
VALUES ('800', '스포츠/레저');
INSERT INTO categories
VALUES ('900', '여성잡화');
INSERT INTO categories
VALUES ('1000', '여성의류');
INSERT INTO categories
VALUES ('1100', '남성패션/잡화');
INSERT INTO categories
VALUES ('1200', '게임/취미');
INSERT INTO categories
VALUES ('1300', '뷰티/미용');
INSERT INTO categories
VALUES ('1400', '반려동물용품');
INSERT INTO categories
VALUES ('1500', '도서/티켓/음반');
INSERT INTO categories
VALUES ('1600', '식물');
INSERT INTO categories
VALUES ('1700', '기타 중고물품');

create table commercial
(
    id                  number(19,0),
    categories_id       varchar2(10) NOT NULL,
    title               varchar2(200) NOT null,
    create_time         DATE DEFAULT sysdate NOT null,
    expiry_time         DATE                 NOT null,
    view_status         varchar2(255) NOT null,
    cm_origin_file_name varchar2(255) NOT null,
    cm_saved_file_name  varchar2(255) NOT null,
    cm_url              varchar2(255)
);

create sequence commercial_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE commercial
    ADD CONSTRAINT commercial_pk PRIMARY KEY (id);

ALTER TABLE commercial
    ADD CONSTRAINT commercial_view_status_ck CHECK (view_status IN ('ACTIVATION', 'INACTIVE'));

alter table commercial
    add constraint commercial_categories_fk foreign key (categories_id) references categories ON DELETE cascade;

create table goods
(
    goods_id      number(19,0),
    member_id     number(19,0) NOT null,
    adm_cd8       varchar2(8) NOT null,
    categories_id varchar2(10) NOT null,
    g_title       varchar2(200) NOT null,
    g_contents    varchar2(3000) NOT null,
    g_sell_status varchar2(255) NOT null,
    g_view_count  number(10,0) default 0,
    g_create_time DATE DEFAULT sysdate NOT null,
    g_price       number(10,0) not null,
    g_nego_status varchar2(255) NOT null,
    g_wish_count  number(10,0) DEFAULT 0,
    g_status      varchar2(255) not NULL,
    g_nick_name   varchar2(255) NOT null
);

create sequence goods_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE goods
    ADD CONSTRAINT goods_pk PRIMARY KEY (goods_id);

ALTER TABLE goods
    ADD CONSTRAINT goods_sell_status_ck CHECK (sell_status IN ('SELL', 'SOLD'));

ALTER TABLE goods
    ADD CONSTRAINT goods_nego_status_ck CHECK (nego_status IN ('YES', 'NO'));

alter table goods
    add constraint goods_status_ck check (status IN ('ACTIVATION', 'INACTIVE'));

alter table goods
    add constraint goods_categories_fk foreign key (categories_id) references categories ON DELETE cascade;

alter table goods
    add constraint goods_koreaarea_fk foreign key (adm_cd8) references koreaarea ON DELETE cascade;

alter table goods
    add constraint goods_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

create table goods_comments
(
    id          number(19,0),
    member_id   number(19,0) NOT null,
    goods_id    number(19,0) NOT null,
    contents    varchar2(1000) NOT null,
    create_time DATE DEFAULT sysdate NOT null
);

create sequence goods_comments_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE goods_comments
    ADD CONSTRAINT goods_comments_pk PRIMARY KEY (id);

alter table goods_comments
    add constraint goods_comments_goods_fk foreign key (goods_id) references goods ON DELETE cascade;

alter table goods_comments
    add constraint goods_comments_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

create table goods_images
(
    mi_id               number(19,0),
    member_id           number(19,0) NOT null,
    mi_origin_file_name varchar2(255) NOT null,
    mi_saved_file_name  varchar2(255) NOT null,
    mi_create_time      DATE DEFAULT sysdate NOT null
);

create sequence goods_images_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE goods_images
    ADD CONSTRAINT goods_images_pk PRIMARY KEY (id);

alter table goods_images
    add constraint goods_images_goods_fk foreign key (goods_id) references goods ON DELETE cascade;

create table member
(
    member_id     number(19,0),
    adm_cd8       varchar2(8) NOT null,
    u_id          varchar2(20) NOT null,
    passwd        varchar2(100) NOT null,
    name          varchar2(20) NOT null,
    nick_name     varchar2(20) NOT null,
    phone_num     varchar2(11) NOT null,
    create_time   DATE DEFAULT sysdate NOT null,
    rolestatus    varchar(255)         NOT NULL,
    activated     varchar(255)         NOT null,
    access_token  varchar2(255),
    refresh_token varchar2(255),
    kakao_email   varchar2(255)
);

create sequence member_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE member
    ADD CONSTRAINT member_pk PRIMARY KEY (member_id);

ALTER TABLE member
    ADD CONSTRAINT member_rolestatus_ck CHECK (rolestatus IN ('ROLE_USER', 'ROLE_ADMIN'));

ALTER TABLE member
    ADD CONSTRAINT member_activated_ck CHECK (activated IN ('ACTIVATION', 'INACTIVE'));

alter table member
    add constraint member_koreaarea_fk foreign key (adm_cd8) references koreaarea;

alter table member
    add constraint member_nick_name_uq unique (nick_name);

alter table member
    add constraint member_phone_num_uq unique (phone_num);

alter table member
    add constraint member_u_id_uq unique (u_id);

alter table member
    add constraint member_access_token_uq unique (access_token);

alter table member
    add constraint member_refresh_token_uq unique (refresh_token);

alter table member
    add constraint member_kakao_email_uq unique (kakao_email);

create table member_images (
                               mi_id number(19,0),
                               member_id number(19,0) NOT null,
                               mi_origin_file_name varchar2(255) NOT null,
                               mi_saved_file_name varchar2(255) NOT null,
                               mi_create_time DATE DEFAULT sysdate NOT null
);

create sequence member_images_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE member_images ADD CONSTRAINT member_images_pk PRIMARY KEY (id);

alter table member_images add constraint member_images_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

create table report
(
    id        number(19,0),
    member_id number(19,0) NOT null,
    goods_id  number(19,0) NOT NULL,
    contents  varchar2(255) NOT NULL
);

create sequence report_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE report
    ADD CONSTRAINT report_pk PRIMARY KEY (id);

alter table report
    add constraint report_goods_fk foreign key (goods_id) references goods ON DELETE cascade;

alter table report
    add constraint report_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

alter table report
    add constraint report_contents_ck check (contents IN
                                             ('스팸홍보/도배글입니다', '음란물입니다', '욕설/혐오/차별적 표현이 포함되어 있습니다', '개인정보 노출 게시물입니다',
                                              '청소년에게 유해한 내용입니다', '저작권침해 게시물입니다'));

create table wish_list
(
    wl_id            number(19,0),
    wanted_member_id number(19,0) NOT null,
    goods_id         number(19,0) NOT NULL,
    wl_create_time   DATE DEFAULT sysdate NOT null
);

create sequence wish_list_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE wish_list
    ADD CONSTRAINT wish_list_pk PRIMARY KEY (id);

alter table wish_list
    add constraint wish_list_goods_fk foreign key (goods_id) references goods ON DELETE cascade;

alter table wish_list
    add constraint wish_list_member_fk foreign key (member_id) references MEMBER ON DELETE cascade;

create table request_buy
(
    rb_id             number(19,0),
    goods_id          number(19,0) NOT NULL,
    request_member_id number(19,0) NOT null,
    request_status    varchar(255)         NOT NULL,
    rb_create_time    DATE DEFAULT sysdate NOT null
);

create sequence request_buy_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE request_buy
    ADD CONSTRAINT request_buy_pk PRIMARY KEY (rb_id);

alter table request_buy
    add constraint request_buy_goods_fk foreign key (goods_id) references goods ON DELETE cascade;

alter table request_buy
    add constraint request_buy_request_member_fk foreign key (request_member_id) references MEMBER (member_id) ON DELETE cascade;

ALTER TABLE REQUEST_BUY
    ADD CONSTRAINT request_status_ck CHECK (request_status IN ('요청중', '거절됨', '수락됨'));

create table message
(
    ms_id          number(19,0),
    target_id      number(19,0) NOT NULL,
    sender_id      number(19,0) NOT NULL,
    ms_message     varchar(4000)        NOT NULL,
    ms_create_time DATE DEFAULT sysdate NOT null
);

create sequence message_seq increment by 1 start with 1 nocycle nocache;

ALTER TABLE message
    ADD CONSTRAINT message_pk PRIMARY KEY (ms_id);

alter table message
    add constraint message_target_member_fk foreign key (target_id) references MEMBER (member_id) ON DELETE cascade;

alter table message
    add constraint message_sender_member_fk foreign key (sender_id) references MEMBER (member_id) ON DELETE cascade;