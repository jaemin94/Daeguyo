# Daeguyo

09PROJECTPLAN
=

## ▶️ 개발 동기

#####  현재 비대면 소비로의 급격한 전환이 이뤄지고 있다. 그리고 그 흐름은 배달 서비스가 주도한다. 해당 상황을 인식하고 우리 팀은 배달 서비스 특성을 이해할 필요성을 느꼈다. 팀 프로젝트가 가장 효과적인 학습법이 될 것이라 판단했다. 특히 기존 플랫폼의 수수료 문제로 인해 대안 서비스에 대한 수요가 확대되고 있다는 점에 기인해 지역 밀착형 배달 서비스 구현을 목표로 하는 '대구요' 프로젝트를 진행하게 되었다. 
##### 
<br/>

## ▶️ 개발 목표

##### 배달의 민족, 요기요처럼 대구시 안에서만 배달 하는 웹 서비스 구현 
<br/>

## ▶️ 개발 일정
#### 2023-09-11 ~ 2023-09-15(05Day) : 요구사항분석 / 유스케이스 / 유스케이스 명세서 / ERD
#### 2023-09-16 ~ 2023-09-17(02Day) : 개발환경 구축(GITHUB, GIT, INTELIJ, MYSQL)
#### 2023-09-18 ~ 2023-09-24(07Day) : 사용할 api 정리 및 기능 테스트
#### 2023-09-25 ~ 2023-10-04(10Day) : 보여질 view 페이지 작성
#### 2023-10-05 ~ 2023-10-09(05Day) : view 페이지 수정
#### 2023-10-10 ~ 2023-10-19(10Day) : Back-End 작업
#### 2023-10-20 ~ 2023-10-20(01Day) : 모든 작업 수정 및 완


<br/>

## ▶️ 구성인원 

##### 이재민(조장)  : FrontEnd,BackEnd (FrontEnd(매장선택, 메뉴상세), BackEnd(기본 레이아웃 및 틀 정리, RESTful api, 매장결제승인,회원가입)
##### 김승훈(조원1) : FrontEnd,BackEnd (FrontEnd(회원정보 수정/가입 , 리뷰페이지) , BackEnd(카테고리,매장선택 BackEnd)
##### 백승하(조원2) : FrontEnd,BackEnd (FrontEnd(매장결제 승인, 입점회원가입,로그인) , BackEnd(입점회원가입,회원정보수정 BackEnd)
##### 정세인(조원3) : FrontEnd,BackEnd (FrontEnd(웹 기획, 메인페이지 구현) , BackEnd(로그인,주문내역 BackEnd)
##### 박민영(조원4) : FrontEnd,BackEnd (FrontEnd(마이페이지, 주문내역) , BackEnd(메뉴상세,마이페이지 BackEnd)
##### 양우성(조원5) : FrontEnd,BackEnd (FrontEnd(카테고리, 장바구니) , BackEnd(매인페이지,장바구니 BackEnd)
<br/>

## ▶️ 개발 환경(플랫폼)

##### OS : WINDOW Server 2022 base
##### CPU SPEC : I7 Intel 
##### RAM SPEC : 16GB SAMSUNG DDR4
##### DISK SPEC : 100GB SSD 

<br/>

## ▶️ IDE 종류

##### IntelliJ IDEA 2023-06
<br/>

## ▶️ Software 목록

##### IDE : IntelliJ IDEA 2023.06
##### SpringBoot 2.7.14
##### Gradle
##### GitHub
##### Mysql Server 8.1.0
##### Mysql Workbench 8.0.34
##### MyBatis 2.3.1
##### NaverCloud SMS 4.5.13
##### JSON 1.1.1
##### JACKSON 2.14.2
##### GSON 2.10.1
<br/>

## ▶️ DevOps 

##### Amazon Web Service EC2(Deploy Server)
##### Amazon Web Service RDS(Remote Datebase Server)
##### Git & Github
##### Adobe XD

<br/>



## ▶️ 사용(or 예정) API

##### 원포트 결제 API
##### 카카오맵 API
##### 이메일 인증 API
##### 네이버 SMS API
##### 다음 주소 API
<br/>

## ▶️ 기술스택

##### FrontEnd
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white) </br>
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white) </br>
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E) </br>
![jQueary](https://img.shields.io/badge/jquery-0769AD.svg?style=for-the-badge&logo=jquery&logoColor=%23F7DF1E) </br>
![fontawesome](https://img.shields.io/badge/fontawesome-528DD7.svg?style=for-the-badge&logo=fontawesome&logoColor=%23F7DF1E) </br>

##### BackEnd
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) </br>

##### DEV TOOL
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white) </br>
![InteliJ Idea](https://img.shields.io/badge/intellijidea-000000.svg?style=for-the-badge&logo=intellijidea&logoColor=white) </br>
![AWS](https://img.shields.io/badge/AWS-232F3E.svg?style=for-the-badge&logo=amazonaws&logoColor=white) </br>
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white) </br>
![GitHub](https://img.shields.io/badge/GitHub-181717.svg?style=for-the-badge&logo=github&logoColor=white) 


<br/>

## ▶️ END POINT 

|END POINT|METHOD|DESCRIPTION|
|------|---|---|
|/verifyIamport2/searchAll|GET|결제 내역 전부 표시|
|/verifyIamport2/searchOne|GET|결제 내역 1건 보기|
<br/>







