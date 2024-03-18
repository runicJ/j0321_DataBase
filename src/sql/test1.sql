show tables;  -- 실행 부분 블록 후 alt+x : 범위 주고 실행
-- ctrl + Home , ctrl + End
create table test (  -- CR(select)UD  -- RUD(DML) -- C drop(DDL) -- grant(DCL)
	idx	    int not null auto_increment primary key, -- 고유번호 -추가 수정 후 위에 반드시 적어놓기
	name    varchar(20) not null,      -- 성명(용도) 요구사항분석
	age     int default 20,            -- 나이
	gender  varchar(2) default '남자',  -- 성별
	job     varchar(20) default '기타', -- 직업
	address varchar(50)                -- 주소
);
drop table test;  /* 구조까지 지움 */
delete from test;  --레코드 내용만 지움
desc test;

insert into test values (default,'홍길동',default,default,'서울');
insert into test values (default,'김말숙',34,'여자',default,'청주');
insert into test values (default,'이기자',29,default,default,'부산');
insert into test values (default,'김연아',default,'여자',default,'제주');
insert into test values (default,'손흥민',33,default,default,'서울');
insert into test values (default,'소나무',55,default,default,'제주');
insert into test values (default,'대나무',11,'여자','학생','제주');
insert into test (name,age,gender,address,job,idx) values ('감나무',22,'남자','서울','회사원',default);  /* auto_increment 값이 없으면 밀어넣을 수 있지만 값이 있으면 에러남 */
-- 초기화 시키거나 - 구조까지 지움 - idx
select * from test;

delete from test where name='손흥민';  -- alt+s : 커서 있는 줄만 실행

/* 레코드 수정하기 : update 테이블명 set 필드명='수정내용' where '조건(필드명=값)'; */
update test set age=25 where name='홍길동';

-- 남자들의 나이를 1살씩 모두 더해주시오.
update test set age = age + 1;
update test set age = age - 1;
update test set age = age + 1 where gender='남자';

-- 서울에 사는 사람들만 보여주시오.
select * from test where address='서울';

-- '서울'과 '부산'에 사는 사람들만 보여주시오.
select * from test where address='서울' or address='부산';

-- 나이가 30살 미만인 회원을 보여주시오.
select * from test where age < 30;

-- 나이가 30살 미만인 여자 회원을 보여주시오.
select * from test where age < 30 and gender='여자';

-- 청주에 사는 회원 확인?
select * from test where address='청주';

-- 청주에 사는 회원 삭제?
delete from test where address='청주';

-- '청주/남자/19/강감찬' 인 회원을 등록하세요?
INSERT INTO test VALUES ('강감찬',19,default,'청주');

-- '서울'에 사는 '여자'회원의 나이를 2살씩 빼주시오?
UPDATE test SET age = age - 2 WHERE address='서울' AND gender='여자';

-- test 테이블의 구조보기
DESC test;

-- 필드 구조 변경...(alter table ~~~~ )

-- test 테이블에 job필드(직업명은 5글자 이내, 기본값:기타)-컬럼(을)를 추가(add column)하시오.
ALTER TABLE test ADD COLUMN job VARCHAR(5) DEFAULT '기타';

-- test 테이블에 job필드 삭제하기(drop column)
alter table test drop column job;

-- test 테이블의 job필드의 길이를 20자로 수정하시오(modify column)
alter table test modify column job varchar(20);

-- test 테이블의 name필드명을 irum필도로 변경하시오.(change column)
alter table test change column name irum varchar(20);
alter table test change column irum name varchar(20);

-- test 테이블에 고유번호(idx)필드를 추가하시오. - 기본키(구분이 될 수 있는 중복을 배제한 필드) 추가
ALTER TABLE test ADD COLUMN idx int NOT null auto_increment PRIMARY KEY;  -- auto_increment mysql 명령어, 오라클에 없음 -- 1번이라도 사용했던 번호는 재사용하지 않음

-- primary key 타입도 달라야함 // unique key 중복 배제하는 키

-- idx=7번 삭제하시오?
delete from test where idx = 7;  -- idx = 55 해도 에러 안남. 없는 데이터 지우려고 해도 됨

-- 고유번호(idx) 값을 5번부터 시작하도록 설정하시오?
alter table test auto_increment = 5;