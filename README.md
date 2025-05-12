# ScheduleManagementApp


## API 명세서
| 기능       | 메서드    | URI                   | 요청 방식    | 요청 데이터                        | 응답 데이터    | 응답 코드                     |
| -------- | ------ | --------------------- | -------- | ----------------------------- | --------- | ------------------------- |
| 일정 등록    | POST   | `/api/schedules`      | 요청 body  | `title`, `author`, `password` | 등록된 일정 정보 | 201: 생성완료                 |
| 일정 조회    | GET    | `/api/schedules/{id}` | 요청 param | 일정 ID                         | 단건 일정 정보  | 200: 정상조회                 |
| 일정 목록 조회 | GET    | `/api/schedules`      | 요청 param | `author`, `modifiedAt` (선택)   | 일정 리스트 정보 | 200: 정상조회                 |
| 일정 수정    | PUT    | `/api/schedules/{id}` | 요청 body  | `title`, `author`, `password` | 수정된 일정 정보 | 200: 정상수정 / 403: 비밀번호 불일치 |
| 일정 삭제    | DELETE | `/api/schedules/{id}` | 요청 body  | `password`                    | -         | 204: 정상삭제 / 403: 비밀번호 불일치 |
