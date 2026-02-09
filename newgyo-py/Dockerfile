FROM python:3.9-slim

# uv 설치 (선택 사항이지만 권장)
RUN pip install uv

WORKDIR /app

# 설정 파일 먼저 복사 (캐시 활용)
COPY pyproject.toml uv.lock ./

# 의존성 설치 (가상환경 없이 시스템에 직접 설치하여 컨테이너 최적화)
RUN uv pip install --system --no-cache -r pyproject.toml

COPY . .

# FastAPI가 사용할 8000번 포트 열기 (문서화 용도)
EXPOSE 8000

# uvicorn을 사용하여 서버 실행
# --host 0.0.0.0: 모든 네트워크 인터페이스에서 접속 허용 (컨테이너 외부 접속 필수 설정)
# main:app: main.py 파일의 app 객체를 실행
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]