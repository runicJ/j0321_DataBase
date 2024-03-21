show tables;  /* cs[ */

-- 기본키(primary key) : 테이블을 대표하는 키, 중복을 허용하지 않는다. 기본키는 여러개가 올 수 있다.
create table test1 (
	idx  int not null auto_increment primary key,  /* index file 따로 생성하지 않아도 auto_increment 사용(mysql) 중복 불허 */
  name varchar(20)  not null,
  age  int  default 20,
  address varchar(50)
);
desc test1;
drop table test1;
insert into test1 values (default,'홍길동',default,'서울');  /* value 써도 오류x 표준 sql은 values */
insert into test1 values (default,'김말숙',25,'청주');
insert into test1 values (1,'소나무',55,'제주');
select * from test1;

create table test2 (
	idx  int not null auto_increment primary key,
  name varchar(20)  not null,
  age  int  default 20,
  test2Code varchar(10) not null
  /*primary key (idx, test2Code)*/  /* primary key 2개 이상 가능(둘다 중복 되어야 자료가 안 넣어짐) / 그룹 primary 외래키(primary key가 아니어도 됨, 유니크 키 가능)도 그룹으로(타입도 같아야 하는가? */
);
desc test2;
drop table test2;
insert into test2 values (default,'이기자',23,'aaa');
insert into test2 values (default,'김길자',33,'bbb');
insert into test2 values (1,'소나무',55,'ccc');  /* 한번은 test2Code가 중복 없어서 들어가고 그 후론 둘다 중복이라 안들어감 */
insert into test2 values (default,'소나무',55,'bbb');  /* idx가 default라 중복될 일이 없어서 계속 들어감 */
select * from test2;

-- UNIQUE KEY : 중복 불허를 위해 설정하는 키(PRIMARY KEY를 대신해서 중복을 불허시키고자 할때 사용한다.)
create table test3 (
	idx  int not null auto_increment,
  name varchar(20)  not null,
  age  int  default 20,
  job 	varchar(10) not null,
  address  	varchar(20) not null,
  test3Code varchar(10) not null,
  primary key (idx),
  UNIQUE KEY(test3Code)
);
desc test3;
drop table test3;
insert into test3 values (default, '소나무', 13, '학생','서울','ccc');
insert into test3 values (default, '대나무', 43, '회사원','청주', 'eee');
insert into test3 values (default, '느티나무', 33, '회사원','청주', 'ggg');
insert into test3 values (1, '감나무', 19, '군인', '대전', 'fff');    /* X */
insert into test3 values (default, '감나무', 19, 'eee');    /* X */
select * from test3;

/* 외래키(Foreign key)
	하나의 테이블에서 다른 테이블의 정보를 찾기 위해 연결해주는 역할을 할때 지정하는 키이다.
	조건, 현재 테이블의 필드에 외래키로 설정하려고 한다면, 
	반드시 상대쪽 테이블의 해당 필드는 Primary key이거나 Unique key로 등록되어 있어아 한다.
	또한, 외래키로 지정하려는 필드는 상대쪽 테이블의 해당 필드 속성과 같아야 한다. */
create table test4 (
	idx  	int not null auto_increment primary key,
  gender    char(2) default '남자',
  test2Idx int not null,
  test3Code varchar(10) not null,
  foreign key (test2Idx) references test2 (idx),
  /*foreign key (test2Idx) references test2 (test2Code),*/  /* X 앞은 현재 테이블 뒤는 test2의 test2Code */
  /* foreign key (idx, test2Code) references test2 (idx, test2Code), */
  foreign key (test3Code) references test3 (test3Code)  /* test3의 유니키, 속성이 같아야 하나 이름은 같을 필요x */
);
desc test4;
drop table test4;
insert into test4 values (default, default, 1, 'ggg');  /* '111','zzz' 각각 test2, test3에 있어야 하는 데이터 */
insert into test4 values (default, default, 1, 'ccc');
select * from test4;

-- select 필드명 from 테이블명 where 조건식 옵션;
select * from test3, test4;  /*cross join 3x2*/
select test3.*, gender from test3, test4;  /* 별이 앞에 있으면 앞에 기준으로 먼저 나옴 */
select test4.idx, gender from test3, test4;  /* 필드명이 중복되면 생성x, 테이블명. 붙여줌 */
select t3.idx as 고유번호, t4.gender as 성별 from test3 t3, test4 t4;  /* as t3, as t4와 같음 생략, 보통 필드명엔 as붙임 */

select t3.*, t4.gender from test3 t3, test4 t4 where t3.test3Code = t4.test3Code;  /* 내부조인(inner join) 가장 많이 쓰임 */