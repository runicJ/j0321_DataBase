show tables;

create table insarok (
	idx     int  not null  auto_increment  primary key,  /* 고유번호 auto로 해놓은 것은 primary key로 */
	buser   varchar(10)  not null,   /* 부서명 */
	name    varchar(20)  not null,   /* 이름 */
	jikwi   varchar(10)  not null  default '사원',  /* 직위 */
	gender  char(2)  not null  default '남자',   /* 성별 */
	age     int  default 25,    /* 나이 */
	ipsail  datetime  not null  default now(),   /* 입사일(기본값:오늘날짜) now 오늘날짜 알려주는 함수 */
	address varchar(50)   /* 주소 */
	/* primary key(idx) 따로 줄 수 있음 */
);
desc insarok;
-- drop table insarok;  // 주석처리 후 범위주고 alt + x 가능 // 워크밴치에서 일괄 실수할 수 있으니 미리 주석처리

insert into insarok value (default,'인사과','홍길동','과장',default,38,'1995-1-5','서울');
insert into insarok value (default,'총무과','김말숙','부장','여자',45,'1990-11-5','청주');
insert into insarok value (default,'영업과','이기자','사원',default,28,'2018-1-5','서울');
insert into insarok value (default,'자재과','강감찬','대리',default,30,'2015-1-5','성남');
insert into insarok value (default,'인사과','소나무','과장',default,38,'1990-2-5','파주');
insert into insarok value (default,'인사과','오하늘','대리','여자',32,'2015-1-5','서울');
insert into insarok value (default,'총무과','김준우','대리',default,33,'2016-1-5','인천');
insert into insarok value (default,'영업과','이재희','과장','여자',35,'2000-1-5','서울');
insert into insarok value (default,'인사과','김우진','부장',default,50,'1992-1-5','서울');
insert into insarok value (default,'영업과','이순신','사원',default,29,'2018-1-5','부산');
insert into insarok value (default,'영업과','고미나','과장','여자',43,'1991-6-5','서천');
insert into insarok value (default,'총무과','오나라','사원','여자',26,'2019-1-5','서울');
insert into insarok value (default,'인사과','나도열','과장',default,48,'1993-1-5','거제');
insert into insarok value (default,'영업과','한지원','사원','여자',27,'2020-1-5','서울');
insert into insarok value (default,'인사과','홍길동','대리',default,34,'2016-1-5','창원');
insert into insarok value (default,'영업과','이산돌','과장',default,42,'1993-5-5','홍천');
insert into insarok value (default,'인사과','황미나','사원','여자',28,'2021-1-5','서울');
insert into insarok value (default,'총무과','홍길동','과장',default,44,'1992-1-5','수원');
insert into insarok value (default,'인사과','대나리','대리','여자',32,'2017-1-5','여수');
insert into insarok value (default,'자재과','하지석','사원',default,29,'2017-2-5','강릉');
insert into insarok value (default,'자재과','이진','사원','여자',30,'2022-6-5','서울');
insert into insarok value (default,'영업과','이아진','사원','여자',30,'2010-12-31','서울');

select * from insarok;

-- insarok테이블의 성명/직위/주소 필드만 모든 자료 표시?
select name,jikwi,address from insarok;

-- 홍길동 레코드만 출력?
select * from insarok where name='홍길동';

-- '서울'에 사는 '홍길동' 레코드만 출력?
select * from insarok where address='서울' and name='홍길동';

-- 홍길동 대리만 출력?
select * from insarok where name='홍길동' and jikwi='대리';

-- '서울'에 사는 모든 사람?
select * from insarok where address='서울';

-- '서울'에 살지않는 직원?
select * from insarok where address != '서울';
select * from insarok where address <> '서울';

-- 입사년도가 2000년 이전에 입사한 직원을 보여주시오?
select * from insarok where ipsail < '2000-01-01';

-- 입사년도가 2000~2010년에 입사한 직원을 보여주시오?
select * from insarok where ipsail >= '2000-01-01' and ipsail <= '2010-12-31';
-- 앞의 범위 연산자 대신에 between~and 변경가능
select * from insarok where ipsail between '2000-01-01' and '2010-12-31';

-- 30대 회사원 출력?
select * from insarok where age >= 30 and age < 40;
select * from insarok where age between 30 and 39;

-- 서울/부산에 사는 직원?
select * from insarok where address='서울' or address='부산';
-- 앞의 or연산자는 in()으로 변경가능
select * from insarok where address in('서울','부산');

-- 서울/부산에 사는 사원만 출력?
select * from insarok where jikwi='사원' and address in('서울','부산');

-- '김'씨만 출력?
select * from insarok where name like '김%';

-- '나무'로 끝나는 이름을 가진 직원 출력?
select * from insarok where name like '%나무';

-- '이진'을 '이재희'으로 이름 변경?
update insarok set name='이재희' where name='이진';
update insarok set name='이재혁' where name='이재희' and age=35;

-- 이름 중에서 '재'란 글자를 포함한 직원의 직급을 '과장'으로 변경하시오?
select * from insarok where name like '%재%';  /* 재를 어디 써도 확인 */
update insarok set jikwi='과장' where name like '%재%';

-- 이름 중 2번쨰 글자가 '나'인 직원은?
select * from insarok where name like '_나%'  /* _ : 한개글자 무조건 */

-- 이름 중에서 '재'란 글자를 포함한 직원 중에서 '서울'에 사는 직원의 이름과 입사일?
select name,ipsail from insarok where name like '%재%' and address='서울';

-- 이름 중에서 '재'란 글자를 포함한 직원 중에서 '서울'에 사는 직원중 나이가 35이상인 직원을 퇴사시키시오?
select * from insarok where name like '%재%' and address='서울';
delete from insarok where name like '%재%' and address='서울' and age >= 35;

-- 이름 오름차순으로 출력?(순서: order by ~~ , 오름차순: asc(생략가능), 내림차순: desc)
select * from insarok order by name;
select * from insarok order by name desc;

-- 남자인 자료 중에서 나이 오름차순으로 출력?
select * from insarok where gender='남자' order by age;

-- 남자인 자료 중에서 나이 오름차순으로, 같은 나이면 입사일 내림차순 출력?
select * from insarok where gender='남자' order by age, ipsail desc;

-- 전체자료 중에서 5명만 출력하시오.(입력순서 중 앞에서 5개)
select * from insarok limit 5;

-- 뒤에서 5명(나중에 입력한 회원)만 출력?
select * from insarok order by idx desc limit 5;

-- 남자 회원 5명만 나이 내림차순으로 보여주시오.(limit 출력개수)
select * from insarok where gender='남자' order by idx desc limit 5;

-- 남자 회원 중에서 뒤에서 2명을 빼고, 5명만 출력하시오? (limit 인덱스 번호, 출력개수)
select * from insarok where gender='남자' order by idx limit 2,5;
