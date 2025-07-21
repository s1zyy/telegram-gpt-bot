package selfprojects.my_telegram_gpt_bot.Quotes;

public class QuotesReply {
    private String quote;
    private String author;

    public QuotesReply(String content, String author) {
        this.quote = content;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
