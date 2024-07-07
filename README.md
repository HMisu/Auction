# 💸Second Story
![SecondStroy](https://github.com/HMisu/Second-Story/assets/37448404/5c3d9067-035f-40b6-aacc-a85c67e739ce)


## 프로젝트 소개
## 1. 개발 기간
2024.01~2024.02
## 2. 팀원 구성
- 한미수 : 팀장, 의류 경매 등록/조회, 카테고리 별 상품 조회
- 신형은 : 관리자 페이지(회원관리, 고객 문의사항 답변, 경매 현황)
- 김의현 : 경매 입찰/낙찰
- 김종범 : 회원가입/로그인, 회원정보, 고객 문의사항 작성
- 손우성 : 메인 페이지, 최근 본 경매 목록 저장, 관심 경매 등록
## 3. 프로젝트 구조
## 4. ERD
![auction](https://github.com/HMisu/Second-Story/assets/37448404/ac54db79-d60c-46f5-a476-720bd6d48dbc)


## 주요 기능
### [경매 목록 조회]
- 카테고리, 타켓층, 마감 경매 포함 여부, 정렬과 같은 요소들을 한번에 적용해 조회할 수 있습니다.
- 경매매의 남은 시간을 계산해 화면에서 실시간으로 시간이 줄어드는 것을 확인할 수 있습니다.
![경매 목록](https://github.com/HMisu/Second-Story/assets/37448404/986175ad-ae46-44d3-9f4f-375bc26f56ad)

### [경매 등록 및 조회]
- 사용자가 거래할 상품을 경매로 등록할 수 있습니다.
![상품 등록및 조회](https://github.com/HMisu/Second-Story/assets/37448404/3dca0c87-f946-4a50-8cea-c5b399f5a7e5)
- 등록한 경매의 목록은 마이페이지-등록 경매 에서 확인 가능합니다.
![마이페이지등록경매](https://github.com/HMisu/Second-Story/assets/37448404/a9d8ccaa-7234-4270-9b75-bd395dcd8b89)
- 파라미터로 존재하지 않은 경매 id를 사용해 경매 조회 시도 시 다음과 같은 안내 화면이 표출됩니다.
![찾을수없는경매](https://github.com/HMisu/Second-Story/assets/37448404/73b04345-a968-4ba9-8505-ade65ef74eae)

### [최근 본 경매 및 관심 경매 등록]
- 최근 본 경매 목록과 관심 경매로 등록한 경매의 목록을 헤더에서 확인할 수 있습니다.
![최근 본 상품](https://github.com/HMisu/Second-Story/assets/37448404/71b0c536-8041-4993-b9e6-4109f8df291c)

### [경매 입찰 및 낙찰]
- 마이페이지-포인트에서 포인트를 충전해 입찰을 진행할 수 있습니다.
- 경매의 즉시 구매가와 같은 입찰가를 입력시 즉시 낙찰됩니다.
![입찰](https://github.com/HMisu/Second-Story/assets/37448404/b2ca9111-b29d-4209-b85e-15f34af2df33)
- 마이페이지-입찰 내역 과 마이페이지-낙찰 내역에서 입찰 및 낙찰한 경매의 목록을 조회할 수 있습니다.
![마이페이지입찰](https://github.com/HMisu/Second-Story/assets/37448404/975b6080-b1ea-47ee-8642-70e4028355fd)

### [관리자 페이지:통계데이터]
- 관리자 페이지에서는 회원 정보, 고객 문의사항 관리 뿐만 아니라 통계 데이터를 확인할 수 있습니다.
- 확인 가능한 데이터는 (1)월별 경매 현황, (2)경매 등록과 입찰 신청 그래프, (3)월별 거래 총액입니다.
![통계](https://github.com/HMisu/Second-Story/assets/37448404/577f2e32-e90c-4b60-8d5a-9c112b140f6c)
