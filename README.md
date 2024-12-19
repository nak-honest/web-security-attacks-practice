# 프로파일 분리

- 공격용 프로파일 : attack
- 방어용 프로파일 : defend

> attack 프로파일에서 attack 이 붙은 url 로 요청해야 공격에 성공한다. 방어는 defend.

## XSS 실습

### reflected XSS 공격

- 단순 alert 실행
- 로그인한 사용자의 액세스 토큰 탈취
- json 을 이용한 공격

### reflected XSS 방어

- html에서 `th:utext`에서 `th:text` 로 변경
- 필터를 통해 파라미터 및 헤더의 특수문자에 이스케이프 적용
- `CharacterEscapes` 를 상속하여 JSON 직렬화/역직렬화 시 특수문자에 이스케이프 적용
