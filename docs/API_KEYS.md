# API Keys

## 원칙

API 키는 프론트엔드에 넣지 않는다. 브라우저에 들어가는 값은 노출된다고 봐야 한다.

서버 전용 키는 모두 `tour-diary` 백엔드 실행 환경 변수로 넣는다. 프론트 `tour-diary-web`에는 공개 가능한 설정만 둔다.

## 반드시 가져올 API

### 1. AI 모델 API 키

용도:

```text
사진 Vision 분석
강아지 시점 일기 생성
AI 그림일기 이미지 생성
관광 추천 이유 생성
```

선택지:

```text
OpenAI 같은 통합 AI API
또는
텍스트/비전 API + 로컬 이미지 생성 서버
```

백엔드 환경 변수:

```text
AI_TEXT_API_KEY=
AI_IMAGE_API_KEY=
AI_IMAGE_GENERATE_URL=
```

넣는 위치:

```text
tour-diary/.env.example 참고
IntelliJ Run Configuration > Environment variables
또는 PowerShell $env 변수
```

현재 Spring 설정 위치:

```text
tour-diary/src/main/resources/application.properties
```

주의:

```text
OpenAI 하나로 Vision/Text/Image를 모두 처리하면 AI_TEXT_API_KEY와 AI_IMAGE_API_KEY에 같은 키를 넣어도 된다.
로컬 이미지 서버를 쓰면 AI_IMAGE_API_KEY는 비워도 되고 AI_IMAGE_GENERATE_URL을 채운다.
```

### 2. 한국관광공사 국문 관광정보 서비스_GW

용도:

```text
주변 관광지 조회
위치 기반 관광정보
키워드 검색
반려동물 동반여행정보
관광지 이미지/상세정보 조회
```

백엔드 환경 변수:

```text
KTO_TOUR_API_KEY=
```

넣는 위치:

```text
tour-diary 백엔드 실행 환경 변수
```

### 3. 한국관광공사 무장애 여행 정보_GW

용도:

```text
노견
개모차
보호자 동행 접근성
무장애 열린관광지 추천
```

백엔드 환경 변수:

```text
KTO_ACCESSIBLE_TOUR_API_KEY=
```

넣는 위치:

```text
tour-diary 백엔드 실행 환경 변수
```

### 4. 한국관광공사 두루누비 정보 서비스_GW

용도:

```text
걷기 코스 추천
코리아둘레길/트래킹 코스 조회
다음 산책 코스 추천
```

백엔드 환경 변수:

```text
KTO_DURUNUBI_API_KEY=
```

넣는 위치:

```text
tour-diary 백엔드 실행 환경 변수
```

### 5. 기상청 단기예보 조회서비스

용도:

```text
산책 당시 날씨
다음 추천 산책지 날씨
일기 프롬프트의 날씨/기온 정보
```

백엔드 환경 변수:

```text
KMA_SERVICE_KEY=
```

넣는 위치:

```text
tour-diary 백엔드 실행 환경 변수
```

### 6. 카카오 REST API 키

용도:

```text
주소 -> 좌표 변환
좌표 -> 주소 변환
현재 위치 기반 추천 검색 보조
```

백엔드 환경 변수:

```text
KAKAO_REST_API_KEY=
```

넣는 위치:

```text
tour-diary 백엔드 실행 환경 변수
```

## 선택 API

### 1. 카카오 JavaScript 키

용도:

```text
프론트에서 카카오 지도 표시
추천 장소 마커 표시
```

프론트 환경 변수:

```text
VITE_KAKAO_JAVASCRIPT_KEY=
```

넣는 위치:

```text
tour-diary-web/.env.local
```

주의:

```text
이 키는 브라우저에 노출된다.
Kakao Developers에서 허용 도메인을 localhost와 배포 도메인으로 제한해야 한다.
```

### 2. 한국관광공사 관광사진 정보_GW

용도:

```text
추천 관광지의 공식 관광 이미지 보강
```

지금 MVP에서는 필수는 아니다. 국문 관광정보 서비스의 이미지 정보로 먼저 처리하고, 부족할 때 추가한다.

## 실제 로컬 설정 예시

PowerShell에서 백엔드 실행 전:

```powershell
$env:AI_TEXT_API_KEY="..."
$env:AI_IMAGE_API_KEY="..."
$env:KTO_TOUR_API_KEY="..."
$env:KTO_ACCESSIBLE_TOUR_API_KEY="..."
$env:KTO_DURUNUBI_API_KEY="..."
$env:KMA_SERVICE_KEY="..."
$env:KAKAO_REST_API_KEY="..."
.\gradlew.bat bootRun
```

프론트는 보통 키 없이 실행 가능하다.

```powershell
cd tour-diary-web
npm.cmd run dev
```

카카오 지도를 직접 붙일 때만 `tour-diary-web/.env.local`을 만든다.

```text
VITE_KAKAO_JAVASCRIPT_KEY=...
```

## 넣으면 안 되는 곳

```text
Git에 커밋되는 application.properties의 실제 값
tour-diary-web/src 내부 코드
프론트 .env 파일의 서버 전용 키
README 예시 코드의 실제 키
```

실제 키는 `.env.local`, IDE 실행 환경 변수, 배포 서버 Secret Manager 중 하나에 둔다.
