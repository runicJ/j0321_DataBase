show tables;

-- 직급별 본봉 테이블
create table salary (
	jikkub   char(8)  not null  primary key,  /* 직급 */
	bonbong  int  not null
);
desc salary;
insert into salary values ('부장', 5000000);
insert into salary values ('과장', 4000000);
insert into salary values ('대리', 3000000);
insert into salary values ('사원', 2000000);
select * from salary;

-- 인사관리 테이블
create table insa (
	idx    int not null auto_increment primary key,  /* 인사관리 고유번호 */
	sabun  char(8) not null,  /* 직급코드(년(2)월(2)일(2)일련번호(2) */
	buseo  varchar(10) not null,  /* 인사과/총무과/생산과/영업과 */
	name   varchar(20) not null,  /* 성명 */
	jikkub char(8) not null,  /* 부장/과장/대리/사원 */
	age    int  default 25,  /* 나이 */
	ipsail datetime  default now(),  /* 입사일, String으로 */
	gender char(2)  default '여자',  /* 성별 */
	address varchar(30),  /* 주소 */
	unique key (sabun),  /* 중복불가키 : 사번 */
	foreign key (jikkub) references salary (jikkub)  /* 외래키 */
);
desc insa;
drop table insa;
insert into insa values (default, '24032101', '인사과', '홍길동', '과장', 35, '2000-1-5', '남자', '서울');
insert into insa values (default, '24032102', '영업과', '김말숙', '대리', 31, '2007-11-25', default, '청주');
insert into insa values (default, '24032201', '총무과', '이기자', '사원', 25, '2022-8-22', '남자', '서울');

select * from insa;

-- 이기자의 급여?
-- 사원의 본봉?
select * from salary where jikkub = '사원';
-- 이기자 본봉?
select * from salary s, insa i where s.jikkub = i.jikkub  /* join의 기본 조건 */
select i.name as irum,i.jikkub,s.bonbong from salary s, insa i where s.jikkub = i.jikkub  /* irum = 변수(vo에 넣어놔야) */

/*
	24032101
	24032102
	24032201
	
	01234567890
	2024-03-23
	240323xx => count 2
	사원번호 입력받지 말고...
	
	인사 vo에 한꺼번에 밑에 2개필드 추가 직급,본봉 getset(원래는 인사,salary 따로)
 */