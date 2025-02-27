# 백엔드 기본 요소를 기반으로 꼼꼼한 테스트까지 고려하는 백엔드 개발자를 향하여!

<br><br><br>

## 📱Spring 심화 주차 개인 과제

테스트 코드가 왜 중요할까요? 코드 품질 향상, 리팩토링과 유지보수 용이, 빠른 문제 발견, 비즈니스 로직 검증 등...이 있습니다.
지금 당장은 필요하지 않을 것 같은데... 라는 생각은 금물! 도전 과제에 부딪히며 충분히 고민할 시간이 필요합니다!

<br><br><br>

## 🖥️개발환경

- JAVA 8
- JDK 21.0.5
- Spring Boot
- InteliJ IDEA
- JPA
- My SQL

<br><br><br>

## 🕰️ 개발기간

2025-02-21 ~ 25-02-27

<br><br><br>

## 📌 필수기능

### Lv1. 코드 개선

#### 코드 개선 퀴즈-Early Return

  1. 조건에 맞지 않는 경우, 즉시 리턴하여, 불필요한 로직의 실행을 방지하고 성능을 향상시킵니다.
  
  2. 불필요한 동작을 최소화 하도록 코드를 개선할 수 있습니다.
  - 패키지 package org.example.expert.domain.auth.service;의 AuthService 클래스에 있는 signup()중 아래의 코드 부분의 위치를 리팩토링해서

    
      ```
      if(userRepository.existsByEmail(signupRequest.getEmail())){
          throw new InvalidRequestException("이미 존재하는 이메일 입니다.");
      }
      ```
      
    - 해당 에러가 발생하는 상황일 때, passwordEncoder의 encode() 동작이 불필요하게 일어나지 않게 코드를 개선해주세요.
<br><br><br>
#### 리팩토링 퀴즈- 불필요한 if-else 피하기

1. 복잡한 if-else 구조는 코드의 가독성을 떨어뜨리고 유지보수를 어렵게 만듭니다. 불필요한 else블록을 없애, 코드를 간결하게 합니다.

2. 리팩토링을 통해, 동작이 불필요하게 일어나지 않도록 코드를 개선할 수 있습니다.

  - 패키지 package org.example.expert.client;의 WeathrClient 클래스에 있는 getTodayWeather()중 아래의 코드 부분을 리팩토링해주세요.
  
    ```
    WeatherDto[] weatherArray = responseEntity.getBody();
    if(!HttpStatus.OK.equals(responseEntity.getStatusCode())){
        throw new ServerException("날씨 데이터를 가져오는데 실패했습니다. 상태 코드: " + responseEntity.getStatusCode());
    } else {
        if(weatherArray == null || weatherArray.length == 0){
          throw new ServerException("날씨 데이터가 없습니다.");
        }
    }
    ```
<br><br><br>
#### 코드 개선 퀴즈- Validation

1. 동작의 역할에 따라, 코드의 실행 위치를 정하여, 역할을 명확하게 알 수 있도록 코드를 개선할 수 있습니다.

  - 패키지 package org.example.expert.domain.user.service;의 UserService 클래스에 있는 changePassword() 중 아래 코드 부분을 해당 API의 요청 DTO에서 처리할 수 있게 개선해주세요.
    
    ```
    if (userChangePasswordRequest.getNewPassword().length() < 8 ||
        !userChangePasswordRequest.getNewPassword().matches(".*\\d.*") ||
        !userChangePasswordRequest.getNewPassword().matches(".*[A-Z].*")) {
      throw new InvalidRequestException("새 비밀번호는 8자 이상이어야 하고, 숫자와 대문자를 포함해야 합니다.");
    }
    ```
<br><br><br><br><br><br>
### Lv2. N+1 문제

- TodoController와 TodoService를 통해 Todo관련 데이터를 처리합니다.
- 여기서 N+1 문제가 발생할 수 있는 시나리오는 getTodos 메서드에서 모든 Todo를 조회할 때, 각 Todo와 연관된 데이터를 개별적으로 가져오는 경우입니다.
- 요구사항:
  - JPQL fetch join을 사용하여 N+1 문제를 해결하고 있는 TodoRepository가 있습니다. 이를 동일한 동작을 하는 @EntityGraph 기반의 구현으로 수정해주세요.
<br><br><br><br><br><br>
### Lv3. 테스트코드 연습

#### 1. 테스트 코드 연습
  1. 예상대로 성공하는지에 대한 케이스입니다.
  
  2. 테스트가 의도대로 성공할 수 있도록 개선할 수 있습니다.
  - 테스트 패키지 package org.example.expert.config;의 PassEncoderRest 클래스에 있는 matches_메서드가_정상적으로_동작한다() 테스트가 의도대로 성공할 수 있게 수정해주세요.
<br><br><br><br>
#### 2. 테스트 코드 연습 - 1번 케이스
  1. 예상대로 예외처리 하는지에 대한 케이스입니다.
  
  2. 잘못된 예외처리가 의도대로 성공할 수 있도록 개선할 수 있습니다.
  - 테스트 패키지 package org.example.expert.domain.manager.service;의 ManagerServiceTest의 클래스에 있는 manager_목록_조회_시_Todo가_없다면_NPE_에러를_던진다() 테스트가 성공하고, 컨텍스트와 일치하도록 "테스트 코드"와 "테스트 코드 메서드명"을 수정해 주세요.
<br><br><br><br>
#### 2. 테스트 코드 연습 - 2번 케이스
  1. 예상대로 예외처리 하는지에 대한 케이스입니다.
  
  2. 잘못된 예외처리가 의도대로 성공할 수 있도록 개선할 수 있습니다.
  - 테스트 패키지 package org.example.expert.domain.comment.service;의 CommentServiceTest의 클래스에 있는 comment_등록_중_할일을_찾지_못해_에러가_발생한다() 테스트가 성공할 수 있도록 "테스트 코드"를 수정해 주세요.
<br><br><br><br>
#### 2. 테스트 코드 연습 - 3번 케이스
  1. 예상대로 예외처리 하는지에 대한 케이스입니다.
  
  2. 팀원이 잘못 수정한 로직에 대하여, 기존에 성공하던 예외처리가 의도대로 성공할 수 있도록 개선할 수 있습니다.
  - 테스트 패키지 package org.example.expert.domain.manager.service;의 ManagerServiceTest의 클래스에 있는 todo의_user가_null인_경우_예외가_발생한다() 테스트가 성공할 수 있도록 "서비스 로직"을 수정해 주세요.
<br><br><br><br>
