from pydantic import BaseModel

class SummaryRequest(BaseModel):
    id: int
    content: str

class SummaryResponse(BaseModel):
    id: int
    summary: str

class ChatRequest(BaseModel):
    content: str
    sender: str = "User"    
