from transformers import AutoModelForSequenceClassification
from transformers import AutoTokenizer, AutoConfig
from scipy.special import softmax
import sys
import cx_Oracle

def db_conn():
    try:
        connection = cx_Oracle.connect(
            'ProjectAdmin',
            'root',
            'localhost/xe',
            encoding='UTF-8')
        cursor = connection.cursor()
        return connection, cursor
    except cx_Oracle.Error:
        return False, False

def model():
    MODEL = f"cardiffnlp/twitter-roberta-base-sentiment-latest"
    tokenizer = AutoTokenizer.from_pretrained(MODEL)
    model = AutoModelForSequenceClassification.from_pretrained(MODEL)
    return model, tokenizer


def preprocess(text):
    new_text = []
    for t in text.split(" "):
        t = '@user' if t.startswith('@') and len(t) > 1 else t
        t = 'http' if t.startswith('http') else t
        new_text.append(t)
    return " ".join(new_text)

def get_sentiment(text, model, tokenizer):
    text = preprocess(text)
    encoded_input = tokenizer(text, return_tensors='pt') 
    output = model(**encoded_input)
    scores = output[0][0].detach().numpy()
    positive_score = softmax(scores)[2] 
    return positive_score

if __name__ == "__main__":
    if len(sys.argv) == 1: # python Sentiment_Analysis.py 
        print("Please provide a command line argument [posts|comments|normal text] to analyze")
        sys.exit()
    if len(sys.argv) == 2: # python Sentiment_Analysis.py "WHat a wonderful movie" -posts -comments
        model, tokenizer = model()
        if (sys.argv[1].lower() == "-posts" or sys.argv[1] == "p"):
            connection, cursor = db_conn()
            query = "SELECT * FROM posts"
            cursor.execute(query)
            for row in cursor.fetchall():
                score = get_sentiment(row[2], model, tokenizer)
                update_query = f"UPDATE posts SET Post_score = {score*100:.2f} WHERE Post_id = '{row[0]}'"
                cursor.execute(update_query)
                connection.commit()
        elif (sys.argv[1].lower() == "-comments" or sys.argv[1] == "c"):
            connection, cursor = db_conn()
            query = "SELECT * FROM comments"
            cursor.execute(query)
            for row in cursor.fetchall():
                score = get_sentiment(row[2], model, tokenizer)
                update_query = f"UPDATE comments SET COMMENT_SCORE = {score*100:.2f} WHERE Comment_id = '{row[0]}'"
                cursor.execute(update_query)
                connection.commit()
        else:
            post_text = " ".join(sys.argv[1:])
            sentiment_score = get_sentiment(post_text, model, tokenizer)
            with open("sentiment_score.txt", "w") as f:
                f.write(f"{sentiment_score * 100:.2f}")