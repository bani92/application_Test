### SOLID
1. 단일 책임 원칙(SRP) \
   테스트는 명료하고 간단하게 작성해야하기 때문에, 단일 책임 원칙을 지키게 됨 \
   테스트가 너무 많아져서 이게 무슨 목적의 클래스인지 눈에 안들어오는 지점이 생김 \
   이 때가 클래스를 분할해야 하는 시점, 그러면서 책임이 자연스럽게 분배 됨. 


2.  개방 폐쇄 원칙(OCP) \
    확장에는 열려있어야하고 수정에는 폐쇄되어 있어야한다.


3. 리스코프 치환 원칙(LSP) \
   자식 타입은 언제나 부모 타입으로 교체할 수 있어야 한다.


4. 인터페이스 분리 원칙(ISP) \
   인터페이스를 각각 사용에 맞게끔 잘게 분리해야한다. \
   SRP - 클래스의 단일 책임, ISP - 인터페이스의 단일 책임


5. 의존성 역전 원칙(DIP) \
   구현 클래스에 의존하지 말고 인터페이스에 의존 \
   화살표의 방향을 바꾸는 테크닉
