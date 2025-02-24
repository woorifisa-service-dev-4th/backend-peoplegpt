USE peoplegpt;

INSERT INTO User (email, password, name, role) VALUES
('john@woorifisa.com', '1234', 'John Doe', 'ADMIN'),
('alice@woorifisa.com', '1234', 'Alice Williams', 'MENTOR'),
('laura@woorifisa.com', '1234', 'Laura Anderson', 'MENTOR'),
('michael@woorifisa.com', '1234', 'Michael Wilson', 'MENTOR');


INSERT INTO User (email, password, name) VALUES
('jane@woorifisa.com', '1234', 'Jane Smith'),
('bob@woorifisa.com', '1234', 'Bob Jones'),
('charlie@woorifisa.com', '1234', 'Charlie Brown'),
('emily@woorifisa.com', '1234', 'Emily Johnson'),
('sarah@woorifisa.com', '1234', 'Sarah Moore'),
('david@woorifisa.com', '1234', 'David Taylor'),
('tester@test.com', '1234', 'Tester1');

INSERT INTO Post (user_id, title, content, category, filter, tag) VALUES
(1, 'Spring Security를 활용한 OAuth2 인증 구현', 'OAuth2를 적용하여 보안성을 강화하는 방법을 공유합니다.', 'QNA', 'AI', 'PROJECT'),
(2, 'AWS Lambda와 API Gateway를 활용한 서버리스 아키텍처', '서버리스 환경에서 API를 구축하는 방법을 설명합니다.', 'QNA', 'CLOUD', 'STUDY'),
(3, 'GPT API를 활용한 챗봇 서비스 개발', 'OpenAI의 GPT API를 활용하여 챗봇을 개발하는 과정과 주요 개념을 다룹니다.', 'QNA', 'SERVICE', 'LANGUAGE'),
(4, '대규모 트래픽을 고려한 MSA 설계 방법', 'MSA 구조에서 트래픽 분산 및 성능 최적화 방안을 다룹니다.', 'QNA', 'CLOUD', 'ETC'),
(5, 'Spring Batch를 활용한 대량 데이터 처리', 'Spring Batch를 이용하여 효율적으로 대용량 데이터를 처리하는 방법을 소개합니다.', 'QNA', 'AI', 'STUDY'),
(6, '코드 리팩토링 가이드 - 클린 코드 실천법', '클린 코드를 위한 리팩토링 기법과 예제를 공유합니다.', 'CODESHARE', 'SERVICE', NULL),
(7, '효율적인 SQL 쿼리 작성법', '성능을 고려한 SQL 최적화 및 인덱스 활용법을 설명합니다.', 'CODESHARE', 'SERVICE', NULL),
(8, 'Java Stream API 활용 사례', 'Java Stream을 활용하여 코드의 가독성과 성능을 향상시키는 방법을 소개합니다.', 'CODESHARE', 'SERVICE', NULL),
(9, '오늘의 개발 일지 - AI 모델 학습 과정', 'AI 모델을 학습시키면서 겪은 문제점과 해결 방법을 기록합니다.', 'DAILY', 'AI', NULL),
(10, '오늘 배운 디자인 패턴 정리', '팩토리 패턴/싱글턴 패턴 등 오늘 공부한 내용을 간단히 정리합니다.', 'DAILY', 'CLOUD', NULL);

INSERT INTO Comment (user_id, post_id, content) VALUES
(4, 1, 'OAuth2를 적용하는 방법에 대해 더 자세히 설명해주세요.'),
(5, 1, 'Spring Security 설정에 대한 예제 코드를 공유해주실 수 있나요?'),
(6, 2, 'AWS Lambda 함수를 어떻게 테스트하나요?'),
(7, 2, 'API Gateway 설정 시 주의할 점이 있을까요?'),
(8, 3, 'GPT API를 사용하면서 발생한 문제점을 공유해주세요.'),
(9, 3, 'GPT API를 활용한 챗봇 서비스의 성능 최적화 방법을 알려주세요.'),
(10, 4, 'MSA 구조에서의 서비스 간 통신 방법에 대해 설명해주세요.'),
(1, 1, 'This is a comment1'),
(1, 1, 'This is a comment2'),
(4, 1, 'This is a comment3'),
(3, 1, 'This is a comment4');