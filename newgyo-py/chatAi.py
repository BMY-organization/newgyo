from openai import OpenAI
from config import OPENAI_API_KEY
from schemas import ChatRequest

client = OpenAI(api_key=OPENAI_API_KEY)

async def chat_Ai(request: ChatRequest):
    try:
        # GPT-5-nano 모델 호출
        response = client.chat.completions.create(
            model="gpt-4o-mini",  #  실제 존재하는 모델명으로
            messages=[
                {"role": "system", "content": """
                    너는 뉴스 전문 챗봇이다. 뉴스, 시사, 정치, 경제, 사회, 국제, 스포츠, 연예 관련 질문에만 답변해.

                    특히 경제 뉴스 맥락에서 용어 설명(예: GDP, 인플레이션, 금리, 주가지수 등)을 요청하면 간단하고 정확하게 설명해줘. 이는 뉴스 이해를 돕기 위한 것이야.

                    다만 다음 질문은 절대 답변하지 말고 정중히 거절해:
                    - 개인 재테크 조언, 투자 추천
                    - 개인 감정 상담, 연애/인간관계 조언
                    - 코드 작성, 프로그래밍, 수학 문제 풀이
                    - 의료/법률/금융 전문 상담
                    - 미래 예측, 점술, 로또

                    주제와 무관하거나 위에 해당하는 질문이 오면: "죄송해요, 저는 뉴스와 시사 관련 질문만 도와드려요. 최근 뉴스나 경제 용어 설명이 필요하시면 말씀해주세요!"라고 답변해.

                    답변은 항상 간결하고 정확하게, 뉴스 보도 스타일로 해.
                    """
	            },
	            {
                    "role": "user",
                    "content": request.content
                }
            ],
            temperature=0.7,           # gpt-4o-mini는 temperature 지원!
        	max_tokens=500             # 이 모델은 max_tokens 사용 (max_completion_tokens 아님)
        )

        ai_content = response.choices[0].message.content
        
        return {
            "sender": "GPT Bot",
            "content": ai_content.strip()
        }

    except Exception as e:
        return {"error": str(e)}
    