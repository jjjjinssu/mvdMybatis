Mybatis 구조
1.Class 생성 "엔티티기준으로"
필요한 필드 생성
생성자생성
@Builder 편하게
 =>직접5가지 패턴을 쓸 필요없이
@Builder 가 된다.!

2.인터페이스로 생성된
Class  Mapper 하기
 
boolean 저장 메서드
              삭제
              수정
              조회


3.xml 만드는법
파일
이름.xml
공통경로 입력후
(xml)에서
sql 작성 !! sql!!!!!!!!!!!!!


mapper 에서 작성한
< 간소화하기 위해 config alias를
여기에 id 후 resultType에 넣어줌>
저장,
삭제,
정보 
.수정 
, 조회등 
해당사항만


1번class에 있는
id 에는 추상메서드 이름 적는다.


insert
 클래스
( 필드이름  , 필드이름)
VALUES ( ? , ?)
=> VALEUS(#{필드이름} , #{필드이름})
change

delete
selcet에서
<!--select 는 resultType이들어감-->




4.Test 창에서
@Test
@Display
--단위 테스트 과정 --
//given
//when
//then
빌더값들 불러옴



5.controller


6.save
