
-- ------------------- 회원 정보 ----------------------------

-- IF NOT EXISTS : 서브쿼리에 데이터가 존재하지 않을 경우 데이터를 조회한다.

-- AUTO INCREMENT : 자동으로 숫자를 입력해준다. 숫자의 시작은 1~N

-- COMMENT : 테이블 또는 컬럼의 뜻을 조회하는 "주석"의 기능이다.

-- NOT NULL : NULL의 값이 들어오면 안된다는 뜻이다.

-- CONSTRAINT : 제한된 조건을 만족해햐지만 삽입/수정하여 무결성을 지키는 것.

-- CONSTRAINT EX) : PK_USER_CODE PK조건에 만족하는가?

-- PRIMARY KEY : 테이블에서 유일하게 식별하기 위해 사용되는 필드

-- ENGINE=INNODB : 데이터 베이스 엔진에 한 종류 대용량 데이터를 처리할 때 쓰임

CREATE TABLE IF NOT EXISTS user_info
(
    user_code     INT AUTO_INCREMENT COMMENT '회원 코드',
    user_name     VARCHAR(255) NOT NULL COMMENT '회원 이름',
    user_email     VARCHAR(255) COMMENT '이메일',
    user_memo     VARCHAR(255)  COMMENT '메모',
    user_group     VARCHAR(255) COMMENT '그룹',
    user_status		VARCHAR(255) DEFAULT 'Y' COMMENT '등록 여부',
    CONSTRAINT pk_user_code PRIMARY KEY (user_code)
) ENGINE=INNODB COMMENT '회원 정보';
-- ---------------------전화번호 코드 ------------------
CREATE TABLE IF NOT EXISTS phone_info
(
    phone_code    INT auto_increment PRIMARY KEY COMMENT '전화번호 코드',
    user_code     INT NOT NULL COMMENT '회원 코드',
    phone        VARCHAR(255) NOT NULL COMMENT '전화번호',
    phone_name	 VARCHAR(255) NOT NULL COMMENT '전화번호 이름',
    CONSTRAINT fk_user_code FOREIGN KEY (user_code) REFERENCES user_info (user_code)
    ) ENGINE=INNODB COMMENT '전화번호 코드';
select * from user_info;



START TRANSACTION;
INSERT INTO user_info (user_name, user_email, user_memo, user_group) VALUES (?, ?, ?, ?);
INSERT INTO phone_info (phone, phone_name) VALUES (?, ?);
COMMIT;
-- DESCRIBE phone_info;

insert all
	into user_info(user_nsme, user_email, user_memo, user_group)
	values(?, ?, ?, ?)
	into
	phone_info(phone, phone_name)
	values(?, ?)
select * from dual
	
